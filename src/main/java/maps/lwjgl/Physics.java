package maps.lwjgl;

import java.awt.*;

public class Physics {

    public static boolean checkCollision(GameObject go1, GameObject go2){
        Rectangle r1 = new Rectangle((int)go1.getX(),(int) go1.getSpr().getSx(), (int)go1.getY(),(int) go1.getSpr().getSy());
        Rectangle r2 = new Rectangle((int)go2.getX(),(int) go2.getSpr().getSx(), (int)go2.getY(),(int) go2.getSpr().getSy());


        return r1.intersects(r2);
    }
}
