package maps.lwjgl.objects.venichles;

public class Tripper extends Venichle {
    private String model;
    private float x, y;

    public Tripper(float x, float y,String filename) {
        super(x, y, 0,1,0, filename);
    }

    public Tripper(String model, float x, float y, String filename){
        super(x,y, 1,1,0, filename);
        this.model = model;
    }
}
