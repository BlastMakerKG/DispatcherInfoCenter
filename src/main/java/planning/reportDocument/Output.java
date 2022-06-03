package planning.reportDocument;

import planning.model.*;
import planning.functions.*;
import planning.respositories.*;
import planning.DTO.*;
import org.apache.commons.math3.util.Precision;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Duration;
import java.util.*;


public class Output {

//    @Autowired
    OriginalData calculation;

//    @Autowired
    Equation equation;

//    @Autowired
    Optimal optimal;

//    @Autowired
    Solution solution;

//    @Autowired
    ExcavatorRepository excavatorService = new ExcavatorRepository();

//    @Autowired
    TruckTripsRespository truckTripsRespository;

//    @Autowired
    TruckTypeRepository truckService = new TruckTypeRepository();


    /**
     * Функция для рассчета суточных затрат по эксковатору
     * входные данные (день)
     * выходные данные список эксковаторов @perDayByEx  с ему принадлашим сасмосвалами (тоже список @TruckForEx)
     *
     * */

    public List<Excavator> waste(int day){

        List<Excavator> objects = calculation.perDayByExes(day);

        List<Excavator> temp = new ArrayList<>();
        boolean checked = false;
        for (Excavator perDayExcavator : objects) {
            ExcavatorDTO ex = excavatorService.getByName(perDayExcavator.getType()).get(0);

            for (Truck str : perDayExcavator.getTrucks()) {
                TruckTypeDTO truckTypeDTO = truckService.getByName(str.getType_truck()).get(0);
                Truck comeTruck = get_come(new Timestamp(truckTripsRespository.findAllDateTime().get(day - 1).getTime()), str.getType_truck());

                double answer =
                        equation.model_day_waste(perDayExcavator.getWeight_fact_avarage(), str.getSpeed(), perDayExcavator.getDistance_avarage(),
                                ex.getCarrying_capacity_max(), truckTypeDTO.getWeight(), Precision.round(perDayExcavator.getGas_avarage(), 3), Precision.round(perDayExcavator.getTimeInHours(), 3), comeTruck.getWaste_gas_truck(), comeTruck.getSpeed(), comeTruck.getSpent_time_in_hour()); // todo

                str.setWaste_truck(Precision.round(answer, 3));

                str.setCost_price(Precision.round(str.getWaste_truck()/(perDayExcavator.getDistance()* perDayExcavator.getWeight_fact()),3));

                checked =true;

            }
            if(checked){
                temp.add(perDayExcavator);
                checked = false;
            }
        }

        return temp;
    }


    /**
     * Функция для Эрлана
     * Высчета удельного расхода горючего по каждому рейсу дня по определенному самосвалу.
     * Для проверки
     *  TODO Эрлан
     *
     *  Функция была закончена, но в конце все равно ответ не правильный
     *  затраты выходят не правильные
     *  особенно себестоимость TODO
     */
    public List<Waste> month(){
        List<Waste> month = new ArrayList<>();

        int days = truckTripsRespository.findAllDateTime().size();

        for(int i = 0; i < days; i++) {
            List<Excavator> excavators = new ArrayList<>();

            double weight_fact_all = 0;
            double weight_norm_all = 0;
            double waste_all = 0;
            double waste_for_per_tonn_km_all = 0;

//            List<String> exsByName = truckTripsRespository.AllNameExByDay(new Timestamp(truckTripsRespository.findAllDateTime().get(i).getTime()));

            List<Integer> nums = truckTripsRespository.findNums(new Timestamp(truckTripsRespository.findAllDateTime().get(i).getTime()));

            List<Truck> trucks = new ArrayList<>();

            for(Integer num : nums){

                // список записей из базы по определенному самосвалу в определнный день (выбран по номеру самосвала)
                List<TruckTripsDTO> truckTripsDTOS = truckTripsRespository.findByTimeOfComeLoadingaAndNumSamosval(new Timestamp(truckTripsRespository.findAllDateTime().get(i).getTime()), num);

                // для того что бы сделать расчет по порожнему расходу самосвала
                //то есть чтобы он сделал хотябы 2 рейса
                if(truckTripsDTOS.size()<2){
                    continue;
                }

                Truck truck = new Truck();

                TruckTripsDTO temp = null;

                TruckTypeDTO truckTypeDTO = truckTripsDTOS.get(0).getTruck().getTruckTypeDTO();
                ExcavatorDTO excavatorDTO = truckTripsDTOS.get(0).getExcavator();

                truck.setTruckTypeDTO(truckTypeDTO);
                truck.setExcavatorDTO(excavatorDTO);
                truck.setType_truck(truckTypeDTO.getName());

                //первый цикл для прохода по рейсам одного самосвала в определнные день
                for(TruckTripsDTO ob : truckTripsDTOS){

                    // проверка что самосвал не заправлялся по дороге
                    if(ob.getFuel_at_loading() - ob.getFuel_at_unloading() < 0)
                        continue;

                    double hours;

                    // время проведенное в дороге
                    Duration time = Duration.between(ob.getArrival_time().toInstant(), ob.getBegin_unloading().toInstant());

                    hours = (double) time.getSeconds() / 3600;

                    double speed = ob.getTrip_distance()/hours;

                    if(speed  <= 0){
                        System.out.println(ob.getId());
                        continue;
                    }


                    double waste_gas = ob.getFuel_at_loading() - ob.getFuel_at_unloading();


                    truck.setWeight_norm(ob.getTruck().getTruckTypeDTO().getRated_load()+truck.getWeight_norm());
                    truck.setSpeed(speed+truck.getSpeed());
                    truck.setType_of_work(ob.getTypeOfWork().getWork_name());
                    truck.setDistance(ob.getTrip_distance()+truck.getDistance());
                    truck.setWeight(ob.getTruck().getTruckTypeDTO().getWeight());
                    truck.setSpent_time_in_hour(hours+truck.getSpent_time_in_hour());
                    truck.setWaste_gas_truck(waste_gas + truck.getWaste_gas_truck());
                    truck.setSpecific_waste_with_mass(equation.specific_gas( ob.getFuel_at_loading() -ob.getFuel_at_unloading(), ob.getTruck().getTruckTypeDTO().getWeight()+ob.getActual_weight(), hours, ob.getTrip_distance()/hours) + truck.getSpecific_waste_with_mass());
                    truck.setCount_reice(truck.getCount_reice()+1);
                    truck.setWeight_fact(ob.getActual_weight()+truck.getWeight_fact());

                    if(temp == null){
                        temp = ob;
                        continue;
                    }

                    // время потраченно на возвращение
                    Duration time_come = Duration.between(temp.getBegin_unloading().toInstant(), ob.getArrival_time().toInstant());
                    double hours_come = (double) time_come.getSeconds() / 3600;


                    double speed_come = temp.getTrip_distance()/hours_come;
                    if(temp.getFuel_at_unloading() - ob.getFuel_at_loading() > 0 && hours_come > 0 && speed_come > 0) {
                        truck.setSpecific_waste_without_mass(equation.specific_gas(temp.getFuel_at_unloading() - ob.getFuel_at_loading(), ob.getTruck().getTruckTypeDTO().getWeight(), hours_come, speed_come) + truck.getSpecific_waste_without_mass());
                        truck.setReverse_reice(truck.getReverse_reice()+1);
                    }
                    temp = ob;
                }

                truck.setSpeed(Precision.round(truck.getSpeed()/truck.getCount_reice(), 9));
                truck.setSpecific_waste_without_mass(Precision.round(truck.getSpecific_waste_without_mass()/truck.getReverse_reice(),9));
                truck.setSpecific_waste_with_mass(Precision.round(truck.getSpecific_waste_with_mass()/truck.getCount_reice(),9));
                truck.setWaste_truck(Precision.round(equation.model_day_waste(truck.getSpeed(), truck.getWeight_fact()/truck.getCount_reice(), excavatorDTO.getVolume()*1.8d, truck.getDistance()/truck.getCount_reice(), truck.getSpecific_waste_with_mass(), truck.getSpecific_waste_without_mass()),9));

                truck.setCost_price(Precision.round(truck.getWaste_truck()/(truck.getDistance()*truck.getWeight_fact()), 5));
                truck.setCount_truck(1);
                trucks.add(truck);
            }

            List<Truck> trucks_temp = new ArrayList<>();

            for(Truck truck : trucks){

                if(trucks_temp.isEmpty()){
                    trucks_temp.add(truck);
                    continue;
                }

                if(!trucks_temp.contains(truck)){
                    trucks_temp.add(truck);
                }else {

                    trucks_temp.forEach( e -> {
                        if(e.getType_truck().equals(truck.getType_truck())){
                            e.setWaste_truck(truck.getWaste_truck()+e.getWaste_truck());
                            e.setCost_price(truck.getCost_price()+e.getCost_price());
                            e.setTruckTypeDTO(truck.getTruckTypeDTO());
                            e.setSpeed(truck.getSpeed() + e.getSpeed());
                            e.setWaste_gas_truck(truck.getWaste_gas_truck()+e.getWaste_gas_truck());
                            e.setDistance(truck.getDistance()+e.getDistance());
                            e.setCount_reice(truck.getCount_reice()+e.getCount_reice());
                            e.setWeight_fact(truck.getWeight_fact()+e.getWeight_fact());
                            e.setWeight_norm(truck.getWeight_norm()+e.getWeight_norm());
                            e.setCount_truck(e.getCount_truck()+1);
                        }
                    });
                }

            }

            double distance = 0;

            for(Truck truck : trucks_temp){
                waste_all += truck.getWaste_truck()/truck.getCount_truck();
                waste_for_per_tonn_km_all += truck.getCost_price()/truck.getCount_truck();
                weight_fact_all += truck.getWeight_fact();
                weight_norm_all += truck.getWeight_norm();
                distance += truck.getDistance();

                truck.setWaste_truck(Precision.round(truck.getWaste_truck()/truck.getCount_truck(),5));
                truck.setCost_price(Precision.round(truck.getCost_price()/truck.getCount_truck(), 5));
                truck.setSpeed(Precision.round(truck.getSpeed()/truck.getCount_truck(),5));

            }

            excavators.add(Excavator.builder()
                        .type("all")
                        .typeofWork("type_of_work")
                        .trucks(new ArrayList<>(trucks_temp))
                        .weight_fact(weight_fact_all)
                        .weight_norm(Precision.round(weight_norm_all, 6))
                        .distance(distance)
                        .waste(Precision.round(waste_all/trucks_temp.size(),4))
                        .wastePerKM(Precision.round(waste_for_per_tonn_km_all/trucks_temp.size(),4))
                        .build());



            month.add(
                    Waste.builder()
                            .excavators(excavators)
                            .day(i)
                            .waste(Precision.round(waste_all,3))
                            .weight_fact(weight_fact_all)
                            .weight_norm(Precision.round(weight_norm_all,3))
                            .wastePerMKM(Precision.round(waste_for_per_tonn_km_all/trucks_temp.size(),3))
                            .build());
        }

        return month;
    }

    /**
     *
     * Функция для высчета месячных затрат по суткам
     * Работает на данный момент не корректно
     *
     * @return - возвращает список обектов Waste
     */

    public List<Waste> wasteMonth() {

        List<Waste> month = new ArrayList<>();

        List<Timestamp> dates = new ArrayList<>();
        for (Date date : truckTripsRespository.findAllDateTime()) {
            dates.add(new Timestamp(date.getTime()));
        }

        for (int i = 1; i <= dates.size(); i++) {

            double waste = 0;
            double waste_per = 0;
            double weight_fact = 0;
            double weight_norm = 0;

            List<Excavator> excavators = new ArrayList<>();
            List<Excavator> perByExcavators = waste(i);

            for (Excavator excavator : perByExcavators){

                double waste_inEx = 0;
                double waste_per_inEx = 0;
                double weightFact_inEx = 0;
                double weightNorm_InEx = 0;
                double distance = 0;


                List<Truck> truks = new ArrayList<>();

                for(Truck truck : excavator.getTrucks()){
                    waste_inEx += truck.getWaste_truck();
                    waste_per_inEx += truck.getCost_price();
                    weightFact_inEx += truck.getWeight_fact();
                    weightNorm_InEx += truck.getWeight_norm();
                    distance += truck.getDistance();
                    truks.add(truck);

                }
                weight_fact += weightFact_inEx;
                weight_norm += weightNorm_InEx;
                waste += waste_inEx/truks.size();
                waste_per += waste_per_inEx/ truks.size();
                excavators.add(
                        Excavator.builder()
                                .type(excavator.getType())
                                .distance(Precision.round(distance, 3))
                                .weight_fact(weightFact_inEx)
                                .weight_norm(Precision.round(weightNorm_InEx,3))
                                .waste(Precision.round(waste_inEx/truks.size(), 3))
                                .wastePerKM(Precision.round(waste_per_inEx/truks.size(),3))
                                .trucks(truks)
                                .typeofWork(excavator.getTypeofWork())
                                .build());
            }
            month.add(
                    Waste.builder()
                            .excavators(excavators)
                            .day(i+1)
                            .waste(Precision.round(waste/ perByExcavators.size(),3))
                            .weight_fact(weight_fact)
                            .weight_norm(Precision.round(weight_norm,3))
                            .wastePerMKM(Precision.round(waste_per/ perByExcavators.size(),3))
                            .build());
        }

        return month;
    }

    /**
     * Функция для взятия данных о поррожнем вовращении самосвала
     * Все данные усредненные по сутке
     *
     * Входные данные (день, тип самосвала)
     *
     * @return - возвращает обьект ComingTruck который высчитывается для порожнего самосвала
     * */

    private Truck get_come(Timestamp date, String truckName){
        List<TruckTripsDTO> truckTripsDTOS = truckTripsRespository.getObjectsByDateAndByTypeTruck(date, truckName);


        Truck comeTruck = Truck.builder()
                .speed(0)
                .distance(0)
                .waste_gas_truck(0)
                .spent_time_in_hour(0)
                .build();

        boolean first = true;

        int count = 1;

        TruckTripsDTO temp = null;
        for(TruckTripsDTO ob : truckTripsDTOS){

            if(first){
                temp = ob;
                first = false;
                continue;
            }

            if(ob.getTrip_number() == temp.getTrip_number()+1) {

                if(temp.getFuel_at_unloading() - ob.getFuel_at_loading() <= 0){
                    continue;
                }
                Duration time = Duration.between(temp.getBegin_unloading().toInstant(), ob.getArrival_time().toInstant());

                comeTruck.setSpent_time_in_hour(Precision.round((double) Math.abs(time.getSeconds()) / 3600, 3) + comeTruck.getSpent_time_in_hour());
                comeTruck.setSpeed((temp.getTrip_distance() / Precision.round((double) Math.abs(time.getSeconds()) / 3600, 3)) + comeTruck.getSpeed());
                comeTruck.setWaste_gas_truck(temp.getFuel_at_unloading() - ob.getFuel_at_loading() + comeTruck.getWaste_gas_truck());
                comeTruck.setDistance(temp.getTrip_distance() + comeTruck.getDistance());

                count++;
            }

            temp = ob;

        }

        comeTruck.setSpeed(Precision.round(comeTruck.getSpeed()/count, 3));
        comeTruck.setSpent_time_in_hour(Precision.round((comeTruck.getSpent_time_in_hour()/count)/2, 3));// todo changed
        comeTruck.setWaste_gas_truck(Precision.round(comeTruck.getWaste_gas_truck()/count,3));
        comeTruck.setDistance(Precision.round(comeTruck.getDistance()/count,3));

        return comeTruck;
    }
}
