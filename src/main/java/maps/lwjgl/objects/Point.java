package maps.lwjgl.objects;

import maps.lwjgl.Objects;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class Point extends Objects {

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


    public void move(float x, float y){
        this.x += x;
        this.y += y;
    }

}