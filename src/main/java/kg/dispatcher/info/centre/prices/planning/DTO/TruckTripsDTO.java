package kg.dispatcher.info.centre.prices.planning.DTO;

import lombok.*;
import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "trucktrip")
public class TruckTripsDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "truck_id", referencedColumnName = "id", nullable=false)
    private TruckDTO truck;

    @ManyToOne
    @JoinColumn(name = "truckdriver_id", referencedColumnName = "id", nullable=false)
    private TruckDriverDTO truckdriver;

    private int trip_number;

    private Timestamp arrival_time;

    private Timestamp begin_loading;

    private Time loading_time;

    private Timestamp begin_unloading;

    private Time unloading_time;

    private int actual_weight;

    private double trip_distance;

    private int fuel_at_loading;

    private int fuel_at_unloading;

    @ManyToOne
    @JoinColumn(name = "excavator_id", referencedColumnName = "id", nullable=false)
    private ExcavatorDTO excavator;

    @ManyToOne
    @JoinColumn(name = "excavatordriver_id", referencedColumnName = "id",nullable=false)
    private ExcavatorsDriverDTO excavatorDriver;

    @ManyToOne
    @JoinColumn(name = "typeofwork_id", referencedColumnName = "id",nullable=false)
    private TypeOfWotkDTO typeOfWork;

    @ManyToOne
    @JoinColumn(name = "unloadingpoint_id", referencedColumnName = "id",nullable=false)
    private UnloadPointDTO unloadingPoint;


}
