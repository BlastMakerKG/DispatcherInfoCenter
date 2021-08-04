package maps.lwjgl.gameobjects;

import maps.lwjgl.GameObject;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

public class Point extends GameObject {

    public static final float SIZE = 16;

    public Point(float x, float y){
        init(1.0f,0.1f,0.1f, SIZE, SIZE,x,y);
    }

    public void getInput(){
        float zoom = Mouse.getDWheel() * 0.008f;
        resize(zoom);
        if(Mouse.isButtonDown(0)) {
            float mouseX = Mouse.getDX() * 1f;
            float mouseY = Mouse.getDY() * 1f;

            move(mouseX, mouseY);
        }if(Keyboard.isKeyDown(Keyboard.KEY_UP)){
                resize(1f);
        }if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)){
                resize(-1f);
        }
    }


    private void move(float x, float y){
        this.x += x;
        this.y += y;
    }

}
