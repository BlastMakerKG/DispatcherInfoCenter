package planning.DTO;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "trucktype")
public class TruckTypeDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private double volume;

    private double avarage_speed;

    private double optimal_speed;

    private double cost_price;

    private double weight;

    private double max_load_capacity;

    private double spec_fuel_consumption;

    private double rated_load;
}
