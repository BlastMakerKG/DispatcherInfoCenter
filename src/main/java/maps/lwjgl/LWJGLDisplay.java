package maps.lwjgl;

import XmlFile.ParseXmlPoints;
import XmlFile.ReliefItems;
import maps.lwjgl.objects.*;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import java.util.*;


public class LWJGLDisplay {

    private List<Objects> points;
    private List<Layer> layers;
    private ParseXmlPoints parse = new ParseXmlPoints("F:\\Krsu\\DispatcherInfoCenter\\src\\main\\resources\\example.xml");
    private HashMap<Integer,LinkedList<ReliefItems>> geoLines = parse.getGeoLines();
    private List<ReliefItems> list =  parse.getReliefItems();
    private List<WriteText> VerticalTexts;
    private List<WriteText> HorisontalTexts;
    private HashMap<Integer, List<Objects>> geolines;

    private Layer central_point;
    private WriteText text_center;

    public LWJGLDisplay(){
        points = new ArrayList<>();
        layers = new ArrayList<>();
        VerticalTexts = new ArrayList<>();
        HorisontalTexts = new ArrayList<>();
        geolines = new HashMap<>();

        parse.loadLinks();

        central_point = new Layer(0,0,1,1);
        text_center = new WriteText(new StringBuilder(central_point.x + "  "+ central_point.y), 100, 100);

        for (ReliefItems item : list) {
            points.add(new Point((float) (item.getX()), (float) (item.getY())));
        }

        ReliefItems temp = null;
        boolean first = true;

        for (int i = 0; i < geoLines.size(); i++) {
            for (ReliefItems item : geoLines.get(i)){

                if(first) {
                    first = false;
                }else{
                    geolines.put(i ,line((float)temp.getX(), (float)temp.getY(), (float) item.getX(), (float) item.getY()));
                }
                temp = item;
            }
        }

        points.add(new Point((float) Display.getHeight() /2 - 20, (float)Display.getWidth() / 2 - 20));
        points.add(new Point((float)Display.getHeight() /2 +20, (float)Display.getWidth() / 2 + 20));
        points.add(new Point((float)Display.getHeight() - 40, (float)Display.getWidth() - 40));
        points.add(new Point((float)Display.getHeight() /2 + 40, (float)Display.getWidth() / 2 - 60));
        points.add(new Point((float)Display.getHeight() /2 - 60, (float)Display.getWidth() / 2 - 60));
        points.add(new Point((float)Display.getHeight() /2 - 80, (float)Display.getWidth() / 2 + 80));
        points.add(new Point((float)Display.getHeight() /2 + 40, (float)Display.getWidth() / 2 - 60));
        points.add(new Venichle((float)Display.getHeight() /2 - Venichle.SIZE / 2, (float)Display.getWidth() / 2 - Venichle.SIZE/2));

        layers.add(new Layer(20,20, 2, Display.getHeight()));
        layers.add(new Layer(20, 20, Display.getWidth(), 2));

        line(points.get(points.size()-2).x, points.get(points.size()-2).y, points.get(points.size()-3).x, points.get(points.size()-3).y);

        for (int i = 20; i < org.lwjgl.opengl.Display.getHeight(); i = i + 50) {
            layers.add(new Layer(15,i,10,2));
            HorisontalTexts.add(new WriteText(new StringBuilder(String.valueOf((int)(central_point.y + i))), 26,i/2));
        }
        for (int i = 20; i < org.lwjgl.opengl.Display.getWidth(); i = i+50) {
            layers.add(new Layer(i, 15, 2,10));
            VerticalTexts.add(new WriteText(new StringBuilder(String.valueOf(central_point.x + i)), i-5,16));
        }
    }


    private List<Objects> line(float x1, float y1, float x2, float y2){
        List<Objects> temp = new ArrayList<>();

        Line line = new Line(x1,y1,x2,y2);
        temp.add(line);

        return temp;
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

        for(Objects ob : points){
            if(! (ob.x == 0 && ob.y == 0)) {
                ob.resize(zoom);
            }
            ob.x += mouseX;
            ob.y += mouseY;
        }
        for (int i = 0; i < geolines.size(); i++) {
            for(Objects ob : geolines.get(i)){
                ob.resize(zoom);
                ob.x += mouseX;
                ob.y += mouseY;
            }
        }

        central_point.x += mouseX;
        central_point.y += mouseY;
        central_point.resize(zoom);
        text_center.setRenderString(new StringBuilder().append(points.get(0).x).append(" ").append(points.get(0).y));

        for(WriteText wr : VerticalTexts){
            wr.setRenderString(new StringBuilder().append(Float.parseFloat(wr.getRenderString().toString())-mouseX));
            if(zoom < 0){
                wr.setRenderString(new StringBuilder().append(Float.parseFloat(wr.getRenderString().toString())/2));
            }else if(zoom > 0){
                wr.setRenderString(new StringBuilder().append(Float.parseFloat(wr.getRenderString().toString())*2));
            }
        }
        for(WriteText wr : HorisontalTexts) {
            wr.setRenderString(new StringBuilder().append(Float.parseFloat(wr.getRenderString().toString()) - mouseY));
            if (zoom < 0) {
                wr.setRenderString(new StringBuilder().append(Float.parseFloat(wr.getRenderString().toString()) / 2));
            } else if (zoom > 0) {
                wr.setRenderString(new StringBuilder().append(Float.parseFloat(wr.getRenderString().toString()) * 2));
            }
        }
    }

    public void render(){
        for (Objects go : points) {
            go.render();
        }
        for(Objects wall : layers){
            wall.render();
        }
        for (WriteText wr : VerticalTexts){
            wr.render();
        }
        for (WriteText wr : HorisontalTexts){
            wr.render();
        }
        for (int i = 0; i < geolines.size(); i++) {
            for(Objects ob : geolines.get(i)){
                ob.render();
            }
        }

        central_point.render();
        text_center.render();
    }

    public void update(){
        for (Objects go : points) {
            go.update();
        }
        for(Objects wall : layers){
            wall.update();
        }
        for(WriteText wr : VerticalTexts){
            wr.update();
        }
        for (WriteText wr : HorisontalTexts){
            wr.update();
        }
        for (int i = 0; i < geolines.size(); i++) {
            for(Objects ob : geolines.get(i)){
                ob.update();
            }
        }
    }
}
