package maps.lwjgl.objects.venichles;

import maps.lwjgl.objects.Venichle;

public class Excavator extends Venichle {

    private String model;

    public Excavator(float x, float y) {
        super(x, y, 1,0,1);
    }

    public Excavator(float x, float y, String model) {
        super(x, y, 1,0,1);
        this.model = model;
    }
}
