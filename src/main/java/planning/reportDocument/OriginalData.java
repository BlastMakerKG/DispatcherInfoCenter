package planning.reportDocument;

import planning.model.Excavator;
import planning.DTO.*;
import planning.model.Month;
import planning.model.PerReice;
import planning.model.Truck;
import planning.respositories.*;
import org.apache.commons.math3.util.Precision;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class OriginalData {

    TruckTripsRespository truckTripsRespository = new TruckTripsRespository();


    ExcavatorDriverRepository excavatorDriverRepository;


    ExcavatorRepository excavatorRepository;


    TruckDriverRepository truckDriverRepository;


    TruckTypeRepository truckTypeRepository;


    TruckRepository truckRepository;


    TypeOfWorkRepository typeOfWorkRepository;


    UnloadPointRepository unloadPointRepository;

    /**
     * Функция для расчета выходного месячного документа, также выбока по типам работ
     *
     * @param type можно передать "" без выборки, либо "Тип работы"
     * @return
     */
    public HashMap<Integer, Month> month(String type){
        HashMap<Integer, Month> month = new HashMap<>();

        int days = 1;
        double distance = 0;
        int gas = 0;
        List<String> trucks = new ArrayList<>();
        List<String> exs = new ArrayList<>();
        long weightFact = 0;
        double weightNorm = 0;

        List<Timestamp> dates = new ArrayList<>();
        for(Date date : truckTripsRespository.findAllDateTime()){
            dates.add(new Timestamp(date.getTime()));
        }

        for(Timestamp date : dates){
            List<TruckTripsDTO> truckTripsDTOS;
            if(type.equals("")) {
                truckTripsDTOS = truckTripsRespository.findByTimeOfComeLoading(date);
            }else {
                truckTripsDTOS = truckTripsRespository.findObjectByTypeOfWorkAndTime(type, date);
            }
            for(TruckTripsDTO truckTripsDTO : truckTripsDTOS){
                distance += truckTripsDTO.getTrip_distance();
                gas += Math.abs(truckTripsDTO.getFuel_at_loading() - truckTripsDTO.getFuel_at_unloading());
                if(!trucks.contains(truckTripsDTO.getTruck().getTruckTypeDTO().getName()))
                    trucks.add(truckTripsDTO.getTruck().getTruckTypeDTO().getName());
                if(!exs.contains(truckTripsDTO.getExcavator().getName())){
                    exs.add(truckTripsDTO.getExcavator().getName());
                }
                weightFact += truckTripsDTO.getActual_weight();
                weightNorm += truckTripsDTO.getTruck().getTruckTypeDTO().getRated_load();
            }

            List<String> temp_trucks = new ArrayList<>(trucks);
            trucks.clear();
            for(String str : temp_trucks){
                int count = truckTripsRespository.findCountSamosvalbyDateAndType(date, str).size();
                trucks.add(str+" - "+count);
            }

            List<String> temp_exs = new ArrayList<>(exs);
            exs.clear();
            for(String ex : temp_exs){
                int count = truckTripsRespository.findCountEx(date, ex).size();
                exs.add(ex+" - "+count);
            }


            month.put(days, Month.builder()
                    .trucks(new ArrayList<>(trucks))
                    .exs(new ArrayList<>(exs))
                    .distance(Precision.round(distance, 3))
                    .gas(gas)
                    .quantityReise(truckTripsRespository.findbyReise(date).size())
                    .weightFact(weightFact)
                    .weightNorm(Precision.round(weightNorm, 3))
                    .build());
            days++;
            distance = 0;
            gas = 0;
            trucks.clear();
            exs.clear();
            weightFact = 0;
            weightNorm = 0;
        }

        return month;
    }

    public List<Truck> perDayByTruck(int days){
        List<Truck> views = new ArrayList<>();

        List<Integer> nums = truckTripsRespository.findNums(new Timestamp(truckTripsRespository.findAllDateTime().get(days-1).getTime()));

        for(Integer num : nums) {
            List<TruckTripsDTO> orderByNum = truckTripsRespository.findByTimeOfComeLoadingaAndNumSamosval(new Timestamp(truckTripsRespository.findAllDateTime().get(days - 1).getTime()), num);

            double speed = 0;
            double coust_gas = 0;
            for(TruckTripsDTO ob : orderByNum){

                if(ob.getFuel_at_loading() - ob.getFuel_at_unloading() <= 0){
                    continue;
                }

                double hours;

                Duration time = Duration.between(ob.getArrival_time().toInstant(), ob.getBegin_unloading().toInstant());

                hours = (double) time.getSeconds() / 3600;

                speed += ob.getTrip_distance()/hours;
                coust_gas += ob.getFuel_at_loading() - ob.getFuel_at_unloading();
            }

            views.add(Truck.builder()
                    .count_reice(orderByNum.size())
                    .type_truck(orderByNum.get(0).getTruck().getTruckTypeDTO().getName())
                    .excavator_type_tie_with_truck(orderByNum.get(0).getExcavator().getName())
                    .num_truck(num)
                    .avg_speed_truck_for_reice(Precision.round(speed / orderByNum.size(), 6))
                    .avg_waste_gas_for_reice(Precision.round(coust_gas / orderByNum.size(), 6))
                    .waste_gas_truck(coust_gas)
                    .build());

        }

        return views;
    }


    /**
     * Функция для рвсчета выходного документа по эксковаторам для суток с его связкой самосвалов
     *
     * @param day - день
     * @return список обьектов типа Ex
     */
    public List<Excavator> perDayByExes(int day){
        List<Excavator> views = new ArrayList<>();

        List<String> nums = truckTripsRespository.AllNameExByDay(new Timestamp(truckTripsRespository.findAllDateTime().get(day-1).getTime()));

        for(String ex : nums) {
            List<TruckTripsDTO> orderByEx = truckTripsRespository.findAllObjectByEx(new Timestamp(truckTripsRespository.findAllDateTime().get(day - 1).getTime()), ex);

            double speed = 0;
            double coust_gas = 0;
            double weight_fact = 0;
            double weight_norm = 0;
            double distance = 0;
            String typeOfWork = "";

            List<Truck> tr_temp = new ArrayList<>();
            List<String> trucks = new ArrayList<>();
            for(TruckTripsDTO ob : orderByEx){


                if(ob.getFuel_at_loading() - ob.getFuel_at_unloading() <=0){continue;}

                double hours;
                Duration time = Duration.between(ob.getArrival_time().toInstant(), ob.getBegin_unloading().toInstant());
                hours = (double) time.getSeconds() / 3600;

                coust_gas += ob.getFuel_at_loading() - ob.getFuel_at_unloading();
                weight_fact += ob.getActual_weight();
                weight_norm += ob.getTruck().getTruckTypeDTO().getRated_load();
                distance += ob.getTrip_distance();

                if(!trucks.contains(ob.getTruck().getTruckTypeDTO().getName())) {
                    trucks.add(ob.getTruck().getTruckTypeDTO().getName());
                    tr_temp.add(Truck.builder()
                            .num_truck(ob.getTruck().getNumber())
                            .type_truck(ob.getTruck().getTruckTypeDTO().getName())
                            .distance(Precision.round(ob.getTrip_distance(),3))
                            .waste_gas_truck(ob.getFuel_at_loading() - ob.getFuel_at_unloading())
                            .speed(Precision.round(ob.getTrip_distance()/hours,3))
                            .count_truck(truckTripsRespository.findQuantitySamByEx(new Timestamp(truckTripsRespository.findAllDateTime().get(day - 1).getTime()), ex, ob.getTruck().getTruckTypeDTO().getName()).size())
                            .driver_name_truck(ob.getTruckdriver().getName())
                            .weight_fact(ob.getActual_weight())
                            .weight_norm(Precision.round(ob.getTruck().getTruckTypeDTO().getRated_load(), 3))
                            .count_reice(1)
                            .type_of_work(ob.getTypeOfWork().getWork_name())
                            .build());

                }else{
                    tr_temp.forEach((e) -> {
                        if ( e.getType_truck().equals(ob.getTruck().getTruckTypeDTO().getName())) {
                                e.setDistance(Precision.round(e.getDistance()+ob.getTrip_distance(),3));
                                e.setSpeed(Precision.round(e.getSpeed()+ (ob.getTrip_distance()/hours),3));
                                e.setCount_reice(e.getCount_reice()+1);
                                e.setWeight_fact(ob.getActual_weight()+e.getWeight_fact());
                                e.setWeight_norm(Precision.round(ob.getTruck().getTruckTypeDTO().getRated_load()+e.getWeight_norm(),3));
                                if(!e.getType_of_work().equals(ob.getTypeOfWork().getWork_name())) {
                                    e.setType_of_work(ob.getTypeOfWork().getWork_name());
                                }
                        }
                    });
                }

            }


            for (Truck truck : tr_temp){
                truck.setSpeed(truck.getSpeed()/truck.getCount_reice());
                typeOfWork += truck.getType_of_work()+"\n";
            }

            views.add(Excavator.builder()
                    .type(orderByEx.get(0).getExcavator().getName())
                    .driver_name(orderByEx.get(0).getExcavatorDriver().getName())
                    .weight_fact_avarage(Precision.round(weight_fact/orderByEx.size(),3))
                    .weight_norm_avarage(Precision.round(weight_norm/orderByEx.size(),3))
                    .typeofWork(typeOfWork)
                    .speed(Precision.round(speed/orderByEx.size(),3))
                    .timeInHours(distance/speed)
                    .trucks(new ArrayList<>(tr_temp))
                    .weight_fact(weight_fact)
                    .weight_norm(Precision.round(weight_norm, 6))
                    .gas((int)coust_gas)
                    .gas_avarage(coust_gas/orderByEx.size())
                    .distance(Precision.round(distance, 6))
                    .distance_avarage(Precision.round(distance, 6)/orderByEx.size())
                    .build());

        }

        return views;
    }


    /**
     * для выходного документа по самосвалу по рейсово
     *
     * @param days - день
     * @param num - номер самосвала
     * @return - список обьекта PerReice
     */
    public List<PerReice> perReice(int days, int num){
        List<PerReice> perReises = new ArrayList<>();

        List<TruckTripsDTO> truckTripsDTOS = truckTripsRespository.findByTimeOfComeLoadingaAndNumSamosval(new Timestamp(truckTripsRespository.findAllDateTime().get(days - 1).getTime()), num);

        for(TruckTripsDTO ob : truckTripsDTOS){
            double hours;

            Duration time = Duration.between(ob.getArrival_time().toInstant(), ob.getBegin_unloading().toInstant());

            hours = (double) time.getSeconds() / 3600;

            double speed = ob.getTrip_distance()/hours;
            double gasoline = Math.abs(ob.getFuel_at_loading() - ob.getFuel_at_unloading());

            perReises.add(PerReice.builder()
                    .distance(ob.getTrip_distance())
                    .gasoline(gasoline)
                    .inHours(Precision.round(hours, 6))
                    .time(new Time( (int)time.getSeconds() / 3600,(int)(time.getSeconds() - (time.getSeconds() / 3600) *3600) / 60, (int) (time.getSeconds() - (((time.getSeconds() - (time.getSeconds() / 3600) *3600) / 60)*60) )))
                    .model_truck(ob.getTruck().getTruckTypeDTO().getName())
                    .name_driver(ob.getTruckdriver().getName())
                    .ex(ob.getExcavator().getName())
                    .ex_driver(ob.getExcavatorDriver().getName())
                    .reise(ob.getTrip_number())
                    .num(ob.getTruck().getNumber())
                    .speedWithWeith(Precision.round(speed,6))
                    .build());
        }

        return perReises;
    }




}


