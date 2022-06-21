package kg.dispatcher.info.centre.prices.planning.services;


import org.springframework.data.jpa.domain.Specification;
import kg.dispatcher.info.centre.prices.planning.DTO.*;
import kg.dispatcher.info.centre.prices.planning.respositories.*;
import kg.dispatcher.info.centre.prices.planning.specification.*;
import lombok.Getter;
import lombok.Setter;
import kg.dispatcher.info.centre.prices.planning.DTO.TruckTripsDTO;

import java.io.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Setter
@Getter
public class Parser {

    TypeOfWorkRepository typeOfWotk;

    TruckTripsRepository truckTrips;

    TruckRepository truckRepository;

    TruckDriverRepository truckDriverRepository;

    TruckTypeRepository truckTypeRepository;

    ExcavatorRepository excavatorRepository;

    ExcavatorDriverRepository excavatorDriverRepository;

    UnloadPointRepository unloadPointRepository;

    private List<TruckTripsDTO> truckTripsDTOS;

    public void parse(String path){
        InputStream ins = null;
        try {
            ins = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Scanner obj = new Scanner(ins);
        truckTripsDTOS = new ArrayList<>();
        while (obj.hasNextLine()){

            String[] strs = obj.nextLine().split("\t");

            Specificator<TypeOfWotkDTO> spec = new Specificator<>(SearchCriteria.builder().key("work_name").operation(":").value(strs[12]).build());
            List<TypeOfWotkDTO> typeWorkSort = typeOfWotk.findAll(Specification.where(spec));
            TypeOfWotkDTO type = null;
            if(typeWorkSort.size() != 0){
                type = typeWorkSort.get(0);
            }else {
                type = TypeOfWotkDTO.builder().work_name(strs[12]).build();
                type = typeOfWotk.save(type);
            }

            Specificator<UnloadPointDTO> spec2 = new Specificator<>(SearchCriteria.builder().key("unloading_point_name").operation(":").value(strs[13]).build());
            List<UnloadPointDTO> unloadPointSort = unloadPointRepository.findAll(Specification.where(spec2));
            UnloadPointDTO unloadPoint  = null;
            if(unloadPointSort.size() != 0){
                unloadPoint = unloadPointSort.get(0);
            }else {
                unloadPoint = UnloadPointDTO.builder().unloading_point_name(strs[13]).build();
                unloadPoint = unloadPointRepository.save(unloadPoint);
            }

            Specificator<TruckTypeDTO> spec3 = new Specificator<>(SearchCriteria.builder().key("name").operation(":").value(strs[2]).build());
            List<TruckTypeDTO> truckTypeSort = truckTypeRepository.findAll(Specification.where(spec3));
            TruckTypeDTO  truckType = null;
            if(truckTypeSort.size() != 0){
                truckType = truckTypeSort.get(0);
            }else{
                truckType = TruckTypeDTO.builder().name(strs[2]).build();
                truckTypeRepository.save(truckType);
            }

            Specificator<TruckDriverDTO> spec7 = new Specificator<>(SearchCriteria.builder().key("name").operation(":").value(strs[1]).build());
            List<TruckDriverDTO> truckDriverSort = truckDriverRepository.findAll(Specification.where(spec7));
            TruckDriverDTO truckDriver = null;
            if(truckDriverSort.size() != 0){
                truckDriver = truckDriverSort.get(0);
            }else {
                truckDriver = TruckDriverDTO.builder().name(strs[1]).build();
                truckDriver = truckDriverRepository.save(truckDriver);
            }


            TruckDTO truck = null;
            Specificator<TruckDTO> spec4 = new Specificator<>(SearchCriteria.builder().key("number").operation(":").value(Integer.parseInt(strs[0])).build());
            List<TruckDTO> truckSort = truckRepository.findAll(Specification.where(spec4));
            if(truckSort.size() != 0){
                truck = truckSort.get(0);
            }else {
                truck = TruckDTO.builder().number(Integer.parseInt(strs[0])).truckTypeDTO(truckType).build();
                truck = truckRepository.save(truck);
            }

            Specificator<ExcavatorsDriverDTO> spec5 = new Specificator<>(SearchCriteria.builder().key("name").operation(":").value(strs[5]).build());
            List<ExcavatorsDriverDTO> excavatorsDriverSort = excavatorDriverRepository.findAll(Specification.where(spec5));
            ExcavatorsDriverDTO excavatorsDriver = null;
            if(excavatorsDriverSort.size() != 0){
                excavatorsDriver = excavatorsDriverSort.get(0);
            }else {
                excavatorsDriver = ExcavatorsDriverDTO.builder().name(strs[5]).build();
                excavatorsDriver = excavatorDriverRepository.save(excavatorsDriver);
            }

            Specificator<ExcavatorDTO> spec6 = new Specificator<>(SearchCriteria.builder().key("name").operation(":").value(strs[4]).build());
            List<ExcavatorDTO> excavatorSort = excavatorRepository.findAll(Specification.where(spec6));
            ExcavatorDTO excavator = null;
            if(excavatorSort.size() != 0){
                excavator = excavatorSort.get(0);
            }else {
                excavator = ExcavatorDTO.builder().name(strs[4]).build();
                excavator = excavatorRepository.save(excavator);
            }



            TruckTripsDTO truckTripsDTO = TruckTripsDTO.builder()
                    .truck(truck)
                    .actual_weight(Integer.parseInt(strs[10]))
                    .excavator(excavator)
                    .arrival_time(Timestamp.valueOf(LocalDateTime.parse(strs[6],DateTimeFormatter.ofPattern("MM/d/yyyy H:mm"))))
                    .begin_loading(Timestamp.valueOf(LocalDateTime.parse((strs[7]),DateTimeFormatter.ofPattern("MM/d/yyyy H:mm"))))
                    .begin_unloading(Timestamp.valueOf(LocalDateTime.parse(strs[14],DateTimeFormatter.ofPattern("MM/d/yyyy H:mm"))))
                    .excavatorDriver(excavatorsDriver)
                    .fuel_at_loading(Integer.parseInt(strs[16]))
                    .fuel_at_unloading(Integer.parseInt(strs[17]))
                    .loading_time(Time.valueOf(strs[8]))
                    .trip_distance(Double.parseDouble(strs[9].replace(",", ".")))
                    .trip_number(Integer.parseInt(strs[3]))
                    .truckdriver(truckDriver)
                    .typeOfWork(type)
                    .unloading_time(Time.valueOf(strs[15]))
                    .unloadingPoint(unloadPoint)
                    .build();

            truckTripsDTOS.add(truckTripsDTO);
        }

    }
}