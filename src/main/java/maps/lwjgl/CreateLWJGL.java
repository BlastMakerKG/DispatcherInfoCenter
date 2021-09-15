package maps.lwjgl;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.*;
import org.lwjgl.opengl.DisplayMode;

import java.awt.*;
import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;

public class CreateLWJGL {

    private static LWJGLDisplay game;
    private static ArrayList<DisplayMode> resolutions = new ArrayList<>();

    public CreateLWJGL() {
        initDisplay();
        initDisplaymodes();

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
//            update();
        }
    }

    private static void getInput(){
        game.getInput();
    }

//    private static void update(){
//        game.update();
//    }

    private static void render(){
        glClear(GL_COLOR_BUFFER_BIT);
        glLoadIdentity();
        //Draw

        game.render();
//        for (int i = 0; i < resolutions.size(); i++) {
//            if(Display.getDisplayMode().getWidth() >resolutions.get(i).getWidth() && Display.getDisplayMode().getHeight() >resolutions.get(i).getHeight()){
//                try {
//                    Display.setDisplayMode(resolutions.get(i));
//                } catch (LWJGLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }

        Display.update();
        Display.sync(60);
    }

    private static  void initGl(){
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, Display.getWidth(),0, Display.getHeight(), -1,10);
        glMatrixMode(GL_MODELVIEW);

        glDisable(GL_DEPTH_TEST);
        glClearColor(0,0,0,0);
    }

    private static void initDisplaymodes(){
        try {
            Display.setResizable(true);
            Display.setVSyncEnabled(true);
//            Display.setFullscreen(true);
            DisplayMode[] modes = Display.getAvailableDisplayModes();
            for (int i = 0; i < modes.length; i++) {
                DisplayMode current = modes[i];
                resolutions.add(current);
            }
//            for (int i = 1; i < resolutions.size(); i++) {
//                if(resolutions.get(i-1).getWidth() > resolutions.get(i).getWidth() && resolutions.get(i-1).getHeight() > resolutions.get(i).getHeight()){
//                    DisplayMode temp = resolutions.get(i-1);
//                    resolutions.set(i-1, resolutions.get(i));
//                    resolutions.set(i, temp);
//                }
//            }
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
    }

    private static void  initDisplay(){
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
