package maps.lwjgl;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.*;
import static org.lwjgl.opengl.GL11.*;

public class GameMain {

    private static Game2D game;

    public GameMain() {
        initDisplay();
        initGl();
        initGame();

        gameLoop();

        cleanup();
    }

    private static void initGame(){
        game = new Game2D();
    }

    private static void cleanup(){
        Keyboard.destroy();
        Mouse.destroy();
        Display.destroy();

    }

    private static void gameLoop(){
        while (!Display.isCloseRequested()){
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

        Display.update();
        Display.sync(60);
    }

    private static  void initGl(){
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, Display.getWidth(),0,Display.getHeight(), -1,10);
        glMatrixMode(GL_MODELVIEW);

        glDisable(GL_DEPTH_TEST);
        glClearColor(0,0,0,0);
    }

    private static void  initDisplay(){
        try {
            Display.setDisplayMode(new DisplayMode(800,600));
            Display.create();
            Keyboard.create();
            Mouse.create();
//            Display.setResizable(true);
            Display.setVSyncEnabled(true);
            Display.setFullscreen(true);
        } catch (LWJGLException e) {
            e.printStackTrace();
        }

    }

}
