package maps.lwjgl;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.*;
import static org.lwjgl.opengl.GL11.*;

public class CreateLWJGL {

    private static LWJGLDisplay game;

    public CreateLWJGL() {
        initDisplay();
        initGl();
        initGame();

        gameLoop();

        cleanup();
    }

    private static void initGame(){
        game = new LWJGLDisplay();
    }

    private static void cleanup(){
        Keyboard.destroy();
        Mouse.destroy();
        org.lwjgl.opengl.Display.destroy();

    }

    private static void gameLoop(){
        while (!org.lwjgl.opengl.Display.isCloseRequested()){
            getInput();


            render();
            update();
        }
    }

    private static void getInput(){
        game.getInput();
    }

    private static void update(){
        game.update();
    }

    private static void render(){
        glClear(GL_COLOR_BUFFER_BIT);
        glLoadIdentity();
        //Draw

        game.render();

        org.lwjgl.opengl.Display.update();
        org.lwjgl.opengl.Display.sync(60);
    }

    private static  void initGl(){
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, org.lwjgl.opengl.Display.getWidth(),0, Display.getHeight(), -1,10);
        glMatrixMode(GL_MODELVIEW);

        glDisable(GL_DEPTH_TEST);
        glClearColor(0,0,0,0);
    }

    private static void  initDisplay(){
        try {
            org.lwjgl.opengl.Display.setDisplayMode(new DisplayMode(1280,720));
            org.lwjgl.opengl.Display.create();
            Keyboard.create();
            Mouse.create();
            org.lwjgl.opengl.Display.setResizable(true);
            org.lwjgl.opengl.Display.setVSyncEnabled(true);
            org.lwjgl.opengl.Display.setFullscreen(true);
        } catch (LWJGLException e) {
            e.printStackTrace();
        }

    }

}
