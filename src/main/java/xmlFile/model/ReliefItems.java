package xmlFile.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReliefItems implements Comparable {

    private long id;

    private double x;

    private double y;

    private double z;


    @Override
    public int compareTo(Object o) {
        ReliefItems item = (ReliefItems) o;
        if(item.z > z){
            return 1;
        }else if(item.z < z){
            return 0;
        }else{
            return -1;
        }

    }
}
