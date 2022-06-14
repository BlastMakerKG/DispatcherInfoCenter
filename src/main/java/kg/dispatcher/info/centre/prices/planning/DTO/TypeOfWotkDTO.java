package kg.dispatcher.info.centre.prices.planning.DTO;

import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "typeofwork")
public class TypeOfWotkDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String work_name;

    public boolean equals(String o) {
        return this.work_name.equals(o);
    }

}
