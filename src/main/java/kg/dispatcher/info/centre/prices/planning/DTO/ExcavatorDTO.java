package kg.dispatcher.info.centre.prices.planning.DTO;

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
@Table(name = "excavator")
public class ExcavatorDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private double carrying_capacity_max;

    private double carrying_capacity_norm;

    private double volume;

    private double max_speed;

    private double carring_capasity_opt;

}
