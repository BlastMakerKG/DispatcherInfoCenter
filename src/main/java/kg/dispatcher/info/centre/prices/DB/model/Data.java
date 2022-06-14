package kg.dispatcher.info.centre.prices.DB.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "data")
public class Data {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column
    private String data;

    @Override
    public String toString() {
        return id + ") " + data;
    }
}
