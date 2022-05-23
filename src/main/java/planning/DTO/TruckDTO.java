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
@Table(name = "truck")
public class TruckDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int number;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="trucktype_id",referencedColumnName = "id")
    private TruckTypeDTO truckTypeDTO;

    public boolean equal(int num){
        return number == num;
    }
}
