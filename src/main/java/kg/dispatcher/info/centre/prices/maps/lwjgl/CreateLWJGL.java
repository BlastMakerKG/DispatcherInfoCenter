package kg.dispatcher.info.centre.prices.maps.lwjgl;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.*;
import org.lwjgl.opengl.DisplayMode;

import java.util.ArrayList;
import java.util.Arrays;

import static org.lwjgl.opengl.GL11.*;

public class CreateLWJGL {

    public static LWJGLDisplay game;
    private static ArrayList<DisplayMode> resolutions = new ArrayList<>();

    public synchronized  void start(){
        initDisplay();
        initDisplaymodes();

        initGl();
        initGame();

        gameLoop();

        cleanup();
    }

    private void initGame(){
        game = new LWJGLDisplay();
    }

    private void cleanup(){
        Keyboard.destroy();
        Mouse.destroy();
        org.lwjgl.opengl.Display.destroy();

    }

    private void gameLoop(){
        while (!org.lwjgl.opengl.Display.isCloseRequested()){
            getInput();


            render();


//            update();
        }
    }

    private void getInput(){
        game.getInput();
    }

//    private static void update(){
//        game.update();
//    }

    private void render(){
        glClear(GL_COLOR_BUFFER_BIT);
        glLoadIdentity();
        //Draw

        game.render();

        Display.update();
        Display.sync(60);
    }

    private void initGl(){
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, Display.getWidth(),0, Display.getHeight(), -1,10);
        glMatrixMode(GL_MODELVIEW);
        glDisable(GL_DEPTH_TEST);
        glClearColor(0,0,0,0);
    }

    private void initDisplaymodes(){
        try {
            Display.setResizable(true);
            Display.setVSyncEnabled(true);
            DisplayMode[] modes = Display.getAvailableDisplayModes();
            resolutions.addAll(Arrays.asList(modes));
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
    }

    private void  initDisplay(){
        try {
            Display.setDisplayMode(new DisplayMode(1280,720));
            Display.create();
            Keyboard.create();
            Mouse.create();

        } catch (LWJGLException e) {
            e.printStackTrace();
        }

    }

}
