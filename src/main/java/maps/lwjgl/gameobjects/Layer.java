package maps.lwjgl.gameobjects;

import maps.lwjgl.GameObject;

public class Layer extends GameObject {

    public Layer(float x , float y, float sizeX, float sizeY){
        init(1.0f,0.5f, 0, sizeX, sizeY, x, y);
    }
}
