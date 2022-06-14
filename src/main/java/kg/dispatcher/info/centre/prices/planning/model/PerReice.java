package kg.dispatcher.info.centre.prices.planning.model;

import lombok.*;

import java.sql.Time;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PerReice {

    private int num;

    private int reise;

    private String model_truck;

    private String name_driver;

    private String ex;

    private String ex_driver;

    private double speedWithWeith;

    private double inHours;

    private Time time;

    private double gasoline;

    private double distance;

}
