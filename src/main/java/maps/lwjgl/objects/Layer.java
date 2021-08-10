package maps.lwjgl.objects;

import maps.lwjgl.Objects;

public class Layer extends Objects {

    public Layer(float x , float y, float sizeX, float sizeY){
        init(1.0f,0.5f, 0, sizeX, sizeY, x, y);
    }
}
