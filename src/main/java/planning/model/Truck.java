package planning.model;


import lombok.*;
import planning.DTO.ExcavatorDTO;
import planning.DTO.TruckTypeDTO;

import java.sql.Time;
import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Truck {

    private int num_truck;
    private int count_reice;
    private int reverse_reice;
    private double speed;
    private int count_truck;
    private String type_truck;
    private double distance;
    private double spent_time_in_hour;
    private double weight_fact;
    private double weight_norm;
    private double specific_waste_with_mass;
    private double specific_waste_without_mass;
    private double waste_truck;
    private double cost_price;
    private String driver_name_truck;
    private String excavator_type_tie_with_truck;
    private ExcavatorDTO excavatorDTO;
    private TruckTypeDTO truckTypeDTO;
    private double avg_speed_truck_for_reice;
    private double avg_waste_gas_for_reice;
    private double waste_gas_truck;
    private String type_of_work;
    private double weight;
    private List<PerReice> reices;

    @Override
    public boolean equals(Object obj) {
        Truck temp = (Truck)obj;
        return temp.type_truck.equals(type_truck);
    }

//    public boolean contains(Truck o){
//        return type_truck.equals(o.getType_truck());
//    }

    private class PerReice {

        private int reise;

        private double speed_with_weight;

        private double speed_without_weight;

        private double spent_time_truck_with_weight_in_hour;

        private double spent_time_truck_without_weight_in_hour;

        private Time spent_time_truck_with_weight;

        private Time spent_time_truck_without_weight;

        private double waste_gasoline_truck_with_mass;

        private double waste_gasoline_truck_without_mass;

        private double distance_go_truck_with_mass;

        private double distance_go_truck_without_mass;
    }

}
