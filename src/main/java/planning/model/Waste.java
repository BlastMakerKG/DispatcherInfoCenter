package planning.model;

import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Waste {

    private double waste;
    private double wastePerMKM;
    private List<Excavator> excavators;
    private double weight_fact;
    private double weight_norm;
    private int day;
}
