package maps.lwjgl.objects.venichles;

public class Excavator extends Venichle {

    private String model;

    public Excavator(float x, float y) {
        super(x, y, 1,0,1, "");
    }

    public Excavator(float x, float y, String model, String filename) {
        super(x, y, 1,0,1, filename);
        this.model = model;
    }
}
