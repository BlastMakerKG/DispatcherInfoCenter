package maps.lwjgl;

import maps.*;
import maps.lwjgl.gameobjects.*;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import java.util.*;

public class Game2D {

    private List<GameObject> items;
    private List<Layer> layers;
//    private List<GUIText> texts;
    private ParseXml parse = new ParseXml("F:\\Krsu\\untitled\\src\\main\\java\\example.xml");
    private HashMap<Integer,LinkedList<ReliefItems>> geoLines = parse.getGeoLines();
    private List<ReliefItems> list =  parse.getReliefItems();
    private List<Mine.WriteText> VerticalTexts;
    private List<Mine.WriteText> HorisontalTexts;


    public Game2D(){
        items = new ArrayList<>();
        layers = new ArrayList<>();
        VerticalTexts = new ArrayList<>();
        HorisontalTexts = new ArrayList<>();
        parse.loadLinks();

        for (ReliefItems item : list) {
            items.add(new Point((float) (Display.getHeight() /2 + (item.getX())), (float) (Display.getWidth() / 2 + (item.getY()))));
        }

        ReliefItems temp = null;
        boolean first =true;

        for (int i = 0; i < geoLines.size(); i++) {
            for (ReliefItems item : geoLines.get(i)){

                if(first) {
                    first = false;
                }else{
                    line((float)temp.getX(), (float)temp.getY(), (float) item.getX(), (float) item.getY());
                }
                temp = item;
            }
        }

        items.add(new Point(Display.getHeight() /2 - 20, Display.getWidth() / 2 - 20));
        items.add(new Point(Display.getHeight() /2 +20, Display.getWidth() / 2 + 20));
        items.add(new Point(Display.getHeight() - 40, Display.getWidth() - 40));
        items.add(new Point(Display.getHeight() /2 + 40, Display.getWidth() / 2 - 60));
        items.add(new Point(Display.getHeight() /2 - 60, Display.getWidth() / 2 - 60));
        items.add(new Point(Display.getHeight() /2 - 80, Display.getWidth() / 2 + 80));
        items.add(new Point(Display.getHeight() /2 + 40, Display.getWidth() / 2 - 60));
        items.add(new Player(Display.getHeight() /2 - Player.SIZE / 2, Display.getWidth() / 2 - Player.SIZE/2));

        layers.add(new Layer(20,20, 2,Display.getHeight()));
        layers.add(new Layer(20, 20, Display.getWidth(), 2));

        line(items.get(items.size()-2).x, items.get(items.size()-2).y, items.get(items.size()-3).x, items.get(items.size()-3).y);

        for (int i = 20; i < Display.getHeight(); i = i + 50) {
            layers.add(new Layer(15,i,10,2));
            HorisontalTexts.add(new Mine.WriteText(new StringBuilder(String.valueOf(i)), 26,i-10));
        }
        for (int i = 20; i < Display.getWidth(); i = i+50) {
            layers.add(new Layer(i, 15, 2,10));
            VerticalTexts.add(new Mine.WriteText(new StringBuilder(String.valueOf(i)), i-5,16));
        }
    }


    private void line(float x1, float y1,float x2,float y2){

        x1 += Point.SIZE/2;
        x2 += Point.SIZE/2;
        y1 += Point.SIZE/2;
        y2 += Point.SIZE/2;

        if(x1 < x2 && y1 < y2){
            for (float i = x1; i < x2; i++, y1++) {
                items.add(new Layer(i,y1, 1,1));
            }
        }else if(x1 > x2 && y1 < y2){
            for (float i = x1; i > x2; i--, y1+=1.13) {
                items.add(new Layer(i,y1, 1,1));
            }
        }else if(x1 > x2 && y1 > y2){
            for (float i = x1; i > x2; i--, y1--) {
                items.add(new Layer(i,y1, 1,1));
            }
        }else{
            for (float i = x1; i < x2; i++, y1--) {
                items.add(new Layer(i,y1, 1,1));
            }
        }
    }

    public void getInput(){
        if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
            Display.destroy();
        }

        float zoom = Mouse.getDWheel() * 0.008f;
        float mouseX = 0, mouseY = 0;
        if(Mouse.isButtonDown(0)) {
            mouseX = Mouse.getDX() * 1f;
            mouseY = Mouse.getDY() * 1f;
        }

        for(GameObject ob : items){
            if(! (ob.x == 0 && ob.y == 0)) {
                ob.resize(zoom);
            }
            ob.x += mouseX;
            ob.y += mouseY;
        }

        for(Mine.WriteText wr : VerticalTexts){
            wr.setRenderString(new StringBuilder().append(Float.valueOf(wr.getRenderString().toString())-mouseX));
        }
        for(Mine.WriteText wr : HorisontalTexts){
            wr.setRenderString(new StringBuilder().append(Float.valueOf(wr.getRenderString().toString())-mouseY));
        }
    }

    public void render(){
        for (GameObject go : items) {
            go.render();
        }
        for(GameObject wall : layers){
            wall.render();
        }
        for (Mine.WriteText wr : VerticalTexts){
            wr.render();
        }
        for (Mine.WriteText wr : HorisontalTexts){
            wr.render();
        }
    }

    public void update(){
        if(Display.isFullscreen()){
            try {
                Display.setDisplayMode(new DisplayMode(1920,1080));
            } catch (LWJGLException e) {
                e.printStackTrace();
            }
        }
        for (GameObject go : items) {
            go.update();
        }
        for(GameObject wall : layers){
            wall.update();
        }
        for(Mine.WriteText wr : VerticalTexts){
            wr.update();
        }
        for (Mine.WriteText wr : HorisontalTexts){
            wr.update();
        }
    }
}
