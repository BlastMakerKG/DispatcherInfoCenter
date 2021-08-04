package maps.lwjgl;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class Frame {

    private int length;
    private Sprite spr;
    private int numDisplayed;


    public boolean render(){
        spr.render();

        numDisplayed++;
        if(numDisplayed>= length){
            numDisplayed = 0;
            return true;
        }

        return false;
    }
}
