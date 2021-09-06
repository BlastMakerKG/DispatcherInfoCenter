package maps.lwjgl.objects.venichles;

import maps.lwjgl.objects.Venichle;

public class Tripper extends Venichle {
    private String model;
    private float x, y;

    public Tripper(float x, float y) {
        super(x, y, 1,1,0);
    }

    public Tripper(String model, float x, float y){
        super(x,y, 1,1,0);
        this.model = model;
    }
}
