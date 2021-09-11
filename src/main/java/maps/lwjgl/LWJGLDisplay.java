package maps.lwjgl;

import XmlFile.ParseXmlPoints;
import XmlFile.ReliefItems;
import maps.lwjgl.objects.*;
import maps.lwjgl.objects.venichles.Excavator;
import maps.lwjgl.objects.venichles.Tripper;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import java.util.*;

import static org.lwjgl.opengl.GL11.glClearColor;


public class LWJGLDisplay {

    private List<Objects> points;
    private List<Layer> layers;
    private ParseXmlPoints parse = new ParseXmlPoints("F:\\Krsu\\DispatcherInfoCenter\\src\\main\\resources\\example.xml");
    private HashMap<Integer,LinkedList<ReliefItems>> geoLines = parse.getGeoLines();
    private List<ReliefItems> list =  parse.getReliefItems();
    private List<WriteText> VerticalTexts;
    private List<WriteText> HorisontalTexts;
    private HashMap<Integer, List<Objects>> geolines;
    private List<Objects> venichles;
    private List<Objects> road;
    private List<WriteText> places;

    private WriteText text_center;

    public LWJGLDisplay(){
        points = new ArrayList<>();
        layers = new ArrayList<>();
        VerticalTexts = new ArrayList<>();
        HorisontalTexts = new ArrayList<>();
        geolines = new HashMap<>();
        venichles = new ArrayList<>();
        road = new ArrayList<>();
        places = new ArrayList<>();

        parse.loadLinks();



        list.sort(ReliefItems::compareTo);
        float r = 0.1f, g = 1.0f, b =0.1f;
        for (int i =0; i<list.size(); i++) {
            points.add(new Point((float) (list.get(i).getX()), (float) (list.get(i).getY()), r,g,b));
            if(i !=0)
                if(list.get(i).getZ() != list.get(i-1).getZ()){
                    r+=0.03f;
                    g-=0.01f;
                }

        }

        text_center = new WriteText(new StringBuilder(points.get(points.size()-1).x + "  "+ points.get(points.size()-1).y), 100, 100);

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

        points.add(new Point((float) Display.getHeight() /2 - 20, (float)Display.getWidth() / 2 - 20, r,g,b));
        points.add(new Point((float)Display.getHeight() /2 +20, (float)Display.getWidth() / 2 + 20, r,g,b));
        points.add(new Point((float)Display.getHeight() - 40, (float)Display.getWidth() - 40, r,g,b));
        points.add(new Point((float)Display.getHeight() /2 + 40, (float)Display.getWidth() / 2 - 60, r,g,b));
        points.add(new Point((float)Display.getHeight() /2 - 60, (float)Display.getWidth() / 2 - 60, r,g,b));
        points.add(new Point((float)Display.getHeight() /2 - 80, (float)Display.getWidth() / 2 + 80, r,g,b));
        points.add(new Point((float)Display.getHeight() /2 + 40, (float)Display.getWidth() / 2 - 60, r,g,b));

        venichles.add(new Tripper((float)Display.getHeight() /2 - Venichle.SIZE / 2, (float)Display.getWidth() / 2 - Venichle.SIZE/2));
        venichles.add(new Excavator(200,200));
        venichles.add(new Tripper(1200,800));

        layers.add(new Layer(900,600,2, 100));
        layers.add(new Layer(900,600,350, 2));
        layers.add(new Layer(900,700,350, 2));
        layers.add(new Layer(1250,600,2, 100));
        places.add(new WriteText(new StringBuilder("The volume of transported cargo = " + 1000000), 910, 680/2));
        places.add(new WriteText(new StringBuilder("Trippers -" + venichles.size()), 910, 660/2));
        places.add(new WriteText(new StringBuilder("Excavator -" + venichles.size()), 910, 640/2));

        layers.add(new Layer(20,20, 2, Display.getHeight()));
        layers.add(new Layer(20, 20, Display.getWidth(), 2));

        line(points.get(points.size()-2).x, points.get(points.size()-2).y, points.get(points.size()-3).x, points.get(points.size()-3).y);

        for (int i = 20; i < org.lwjgl.opengl.Display.getHeight(); i = i + 50) {
            layers.add(new Layer(15,i,10,2));
            HorisontalTexts.add(new WriteText(new StringBuilder(String.valueOf(Math.round(i))), 26,i/2));
        }
        for (int i = 20; i < org.lwjgl.opengl.Display.getWidth(); i = i+50) {
            layers.add(new Layer(i, 15, 2,10));
            VerticalTexts.add(new WriteText(new StringBuilder(String.valueOf(Math.round(i))), i-5,16));
        }
    }


    private List<Objects> line(float x1, float y1, float x2, float y2){
        List<Objects> temp = new ArrayList<>();

        Line line = new Line(x1,y1,x2,y2);
        temp.add(line);

        return temp;
    }

    public void getInput(){

        float x=0,y=0;
        if(Keyboard.isKeyDown(Keyboard.KEY_A)){
            x=-1;
        }else if(Keyboard.isKeyDown(Keyboard.KEY_D)){
            x=1;
        }else if(Keyboard.isKeyDown(Keyboard.KEY_W)){
            y=1;
        }else if(Keyboard.isKeyDown(Keyboard.KEY_S)){
            y=-1;
        }






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
        for (Objects car :
                venichles) {
            car.x += x;
            car.y += y;

            car.x += mouseX;
            car.y += mouseY;
            car.resize(zoom);
            if(x != 0 || y!= 0) {
                road.add(new Road(car.x, car.y));
            }

            x =0; y=0;
        }
        for (Objects road : road){
            road.x += mouseX;
            road.y += mouseY;
        }

        text_center.setRenderString(new StringBuilder().append(Math.round(points.get(0).x)).append(" ").append(Math.round(points.get(0).y)));

        for(WriteText wr : VerticalTexts){
            wr.setRenderString(new StringBuilder().append(Math.round(Float.parseFloat(wr.getRenderString().toString())-mouseX)));
            if(zoom < 0){
                wr.setRenderString(new StringBuilder().append(Math.round(Float.parseFloat(wr.getRenderString().toString())/2)));
            }else if(zoom > 0){
                wr.setRenderString(new StringBuilder().append(Math.round(Float.parseFloat(wr.getRenderString().toString())*2)));
            }
        }
        for(WriteText wr : HorisontalTexts) {
            wr.setRenderString(new StringBuilder().append(Math.round(Float.parseFloat(wr.getRenderString().toString()) - mouseY)));
            if (zoom < 0) {
                wr.setRenderString(new StringBuilder().append(Math.round(Float.parseFloat(wr.getRenderString().toString()) / 2)));
            } else if (zoom > 0) {
                wr.setRenderString(new StringBuilder().append(Math.round(Float.parseFloat(wr.getRenderString().toString()) * 2)));
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
        for(Objects ob : venichles){
            ob.render();
        }
        if(road.size() > 0) {
            for (Objects road : road) {
                road.render();
            }
        }

        for(WriteText text : places){
            text.render();
        }

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
