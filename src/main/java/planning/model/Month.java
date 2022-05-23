package planning.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Month {
    private double distance = 0;
    private int gas = 0;
    private long weightFact = 0;
    private double weightNorm = 0;
    private int quantityReise = 0;
    private List<String> trucks = new ArrayList<>();
    private List<String> exs = new ArrayList<>();
}
