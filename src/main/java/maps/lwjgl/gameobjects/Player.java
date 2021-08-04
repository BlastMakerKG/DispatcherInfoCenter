package maps.lwjgl.gameobjects;

import maps.lwjgl.GameObject;
import lombok.Setter;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

@Setter
public class Player extends GameObject {

    public static float SIZE = 16;
    private float r = 0.1f,g=1.0f,b=0.1f;
    private float speed = 4f;

    public Player(float x,float y){
        init(r,g,b, SIZE, SIZE, x,y);
    }

    public void getInput(){
        if(Keyboard.isKeyDown(Keyboard.KEY_W)){
            move(0,1);
        }if(Keyboard.isKeyDown(Keyboard.KEY_S)){
            move(0,-1);
        }if(Keyboard.isKeyDown(Keyboard.KEY_A)){
            move(-1, 0);
        }if(Keyboard.isKeyDown(Keyboard.KEY_D)){
            move(1,0);
        }if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
            Display.destroy();
//        }if(Mouse.isButtonDown(0)) {
//            float mouseX = Mouse.getDX() * 1f;
//            float mouseY = Mouse.getDY() * 1f;
//
//            move(mouseX, mouseY);
//        }if(Keyboard.isKeyDown(Keyboard.KEY_UP)){
//            resize(1f);
//        }if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)){
//            resize(-1f);
        }

        float zoom = Mouse.getDWheel() * 0.008f;
        resize(zoom);
    }

    public void move(float magX, float magY){
        x += speed * magX;
        y += speed * magY;
    }


}
