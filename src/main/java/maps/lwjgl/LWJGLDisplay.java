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

public class LWJGLDisplay {


    public LWJGLDisplay(){
        parse.loadLinks();
        createItems();

        venichles.add(new Tripper(580,120));
        venichles.add(new Excavator(200,200));
        venichles.add(new Tripper(650,130));

        int countTripper = 0;
        for(Objects ob : venichles){
            if(ob instanceof Tripper){
                List<Objects> road = new ArrayList<>();
                roads.put(countTripper, road);
                countTripper++;
            }
        }

        createpoints();
        createText();

        layers.add(new Layer(900,600,350, 100));

        layers.add(new Layer(20,20, 2, Display.getHeight()));
        layers.add(new Layer(20, 20, Display.getWidth(), 2));

        for (int i = 20; i < org.lwjgl.opengl.Display.getHeight(); i = i + 50) {
            layers.add(new Layer(15,i,10,2));
            HorisontalTexts.add(new WriteText(new StringBuilder(String.valueOf(Math.round(i))), 26,i/2));
        }
        for (int i = 20; i < org.lwjgl.opengl.Display.getWidth(); i = i+50) {
            layers.add(new Layer(i, 15, 2,10));
            VerticalTexts.add(new WriteText(new StringBuilder(String.valueOf(Math.round(i))), i-5,16));
        }

        buttonReturnPoints();
    }



    private void createItems(){
        points = new ArrayList<>();
        layers = new ArrayList<>();
        VerticalTexts = new ArrayList<>();
        HorisontalTexts = new ArrayList<>();
        geolines = new HashMap<>();
        venichles = new ArrayList<>();
        road = new ArrayList<>();
        places = new ArrayList<>();
        nameTrippers = new ArrayList<>();
        roads = new HashMap<>();
    }


    private void createpoints(){
        list.sort(ReliefItems::compareTo);
        float r = 0.1f, g = 1.0f, b =0.1f;
        for (int i =0; i<list.size(); i++) {
            points.add(new Point((float) (list.get(i).getX()), (float) (list.get(i).getY()), r,g,b));
            if(i !=0)
                if(list.get(i).getZ() != list.get(i-1).getZ()){
                    r+=0.02f;
                    g-=0.01f;
                }

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

//        points.add(new Point((float) Display.getHeight() /2 - 20, (float)Display.getWidth() / 2 - 20, r,g,b));
//        points.add(new Point((float)Display.getHeight() /2 +20, (float)Display.getWidth() / 2 + 20, r,g,b));
//        points.add(new Point((float)Display.getHeight() - 40, (float)Display.getWidth() - 40, r,g,b));
//        points.add(new Point((float)Display.getHeight() /2 + 40, (float)Display.getWidth() / 2 - 60, r,g,b));
//        points.add(new Point((float)Display.getHeight() /2 - 60, (float)Display.getWidth() / 2 - 60, r,g,b));
//        points.add(new Point((float)Display.getHeight() /2 - 80, (float)Display.getWidth() / 2 + 80, r,g,b));
//        points.add(new Point((float)Display.getHeight() /2 + 40, (float)Display.getWidth() / 2 - 60, r,g,b));
    }



    private void createText(){

        int trippers = 0, excovators = 0;

        text_center = new WriteText(new StringBuilder(points.get(points.size()-1).x + "  "+ points.get(points.size()-1).y), 100, 100);


        for (Objects transport : venichles){
            if(transport instanceof Tripper){
                trippers++;
                nameTrippers.add(new WriteText(new StringBuilder(venichles.indexOf(transport)), Math.round(transport.x + 5f), Math.round(transport.y+5f)));
            }if(transport instanceof Excavator){
                excovators++;
            }
        }



        places.add(new WriteText(new StringBuilder("The volume of transported cargo = " + 1000000), 910, 680/2));
        places.add(new WriteText(new StringBuilder("Trippers -" + trippers), 910, 660/2));
        places.add(new WriteText(new StringBuilder("Excavator -" + excovators), 910, 640/2));
    }

    boolean firstCoordinate = true;
    int x=0,y=0;
    public void converterToXY(double[] coordinates){

        Runnable run = () -> {
            if(firstCoordinate){
                firstCoordinates = coordinates;
                first=false;
            }


            if(firstCoordinates[0] - coordinates[0]  >0.001){
                x--;
            }else if (firstCoordinates[0] - coordinates[0]  < -0.001){
                x++;
            }

            if(firstCoordinates[1] - coordinates[1] > 0.001){
                y--;
            }else if(firstCoordinates[1] - coordinates[1] < 0.001){
                y++;
            }
        };

        Thread thread = new Thread(run);
        thread.start();



    }


    private List<Objects> line(float x1, float y1, float x2, float y2){
        List<Objects> temp = new ArrayList<>();

        Line line = new Line(x1,y1,x2,y2);
        temp.add(line);

        return temp;
    }

    boolean first = true;


    private void buttonReturnPoints(){
        Runnable run = () -> {
            if (Mouse.getX() > 900 && Mouse.getX() < 1250 && Mouse.getY() > 600 && Mouse.getY() < 700 && Mouse.isButtonDown(Mouse.getEventButton()) && first) {
                first = false;
                for (Objects ob : points) {
                    ob.x -= 12000;
                    ob.y -= 77000;
                }

                for (int i = 0; i < geolines.size(); i++) {
                    for (Objects ob : geolines.get(i)) {
                        ob.x -= 12000;
                        ob.y -= 77000;
                    }
                }

                for (WriteText wr : VerticalTexts) {
                    wr.setRenderString(new StringBuilder().append(Math.round(Float.parseFloat(wr.getRenderString().toString()) + 12000)));
                }
                for (WriteText wr : HorisontalTexts) {
                    wr.setRenderString(new StringBuilder().append(Math.round(Float.parseFloat(wr.getRenderString().toString()) + 77000)));
                }
            }
        };

        Thread thread = new Thread(run);
        thread.start();

    }

    public void getInput(){
        keyboardActivity();

        buttonReturnPoints();


        float zoom = Mouse.getDWheel() * 0.008f;
        float mouseX = 0, mouseY = 0;
        if(Mouse.isButtonDown(0)) {
            mouseX = Mouse.getDX() * 1f;
            mouseY = Mouse.getDY() * 1f;
        }


        writing(zoom,mouseX,mouseY);
        foreach((ArrayList<Objects>) points, mouseX,mouseY,zoom);


        for (int i = 0; i < geolines.size(); i++) {
            foreach( (ArrayList<Objects>)geolines.get(i), mouseX,mouseY,zoom);
        }
        for (Objects car :
                venichles) {
            if(car instanceof Tripper) {
                car.x += x;
                car.y += y;
            }

            car.x += mouseX;
            car.y += mouseY;
            car.resize(zoom);
            if(x != 0 || y!= 0) {
                roads.get(venichles.indexOf(car)).add(new Road(car.x,car.y));
                road.add(new Road(car.x, car.y));
            }



            x =0; y=0;
        }

        foreach((ArrayList<Objects>) road, mouseX,mouseY,zoom);


    }

    private void foreach(ArrayList<Objects> listObject, float mouseX, float mouseY, float zoom){
        for (Objects ob : listObject){
            ob.resize(zoom);
            ob.x += mouseX;
            ob.y += mouseY;
        }
    }

    private void writing(float zoom, float mouseX, float mouseY){
        text_center.setRenderString(new StringBuilder().append(Math.round(points.get(0).x)).append(" ").append(Math.round(points.get(0).y)));

        updateText(zoom, mouseX, VerticalTexts);
        updateText(zoom, mouseY, HorisontalTexts);

        for(WriteText text : nameTrippers){
            text.setX(Math.round(text.getX() + mouseX));
            text.setY(Math.round(text.getY() + mouseY));
        }
    }

    private void updateText(float zoom, float mouseY, List<WriteText> horisontalTexts) {
        for(WriteText wr : horisontalTexts) {
            wr.setRenderString(new StringBuilder().append(Math.round(Float.parseFloat(wr.getRenderString().toString()) - mouseY)));
            if (zoom < 0) {
                wr.setRenderString(new StringBuilder().append(Math.round(Float.parseFloat(wr.getRenderString().toString()) / 2)));
            } else if (zoom > 0) {
                wr.setRenderString(new StringBuilder().append(Math.round(Float.parseFloat(wr.getRenderString().toString()) * 2)));
            }
        }
    }

    private void keyboardActivity(){

        if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
            Display.destroy();
        }

        if(Keyboard.isKeyDown(Keyboard.KEY_A)){
            if(Keyboard.isKeyDown(Keyboard.KEY_W)){
                y=1;
            }else if(Keyboard.isKeyDown(Keyboard.KEY_S)){
                y=-1;
            }
            x=-1;
        }else if(Keyboard.isKeyDown(Keyboard.KEY_D)){
            if(Keyboard.isKeyDown(Keyboard.KEY_W)){
                y=1;
            }else if(Keyboard.isKeyDown(Keyboard.KEY_S)){
                y=-1;
            }
            x=1;
        }else if(Keyboard.isKeyDown(Keyboard.KEY_W)){
            if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
                x = 1;
            }if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
                x = -1;
            }
            y=1;
        }else if(Keyboard.isKeyDown(Keyboard.KEY_S)){
            if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
                x = -1;
            }if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
                x = 1;
            }
            y=-1;
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

        for(WriteText text : nameTrippers){
            text.render();
        }

        text_center.render();
    }

//    public void update(){
//        for (Objects go : points) {
//            go.update();
//        }
//        for(Objects wall : layers){
//            wall.update();
//        }
//        for(WriteText wr : VerticalTexts){
//            wr.update();
//        }
//        for (WriteText wr : HorisontalTexts){
//            wr.update();
//        }
//        for (int i = 0; i < geolines.size(); i++) {
//            for(Objects ob : geolines.get(i)){
//                ob.update();
//            }
//        }
//    }



    private List<Objects> points;
    private List<Layer> layers;
    private ParseXmlPoints parse = new ParseXmlPoints("src\\main\\resources\\example.xml");
    private HashMap<Integer,LinkedList<ReliefItems>> geoLines = parse.getGeoLines();
    private List<ReliefItems> list =  parse.getReliefItems();
    private List<WriteText> VerticalTexts;
    private List<WriteText> HorisontalTexts;
    private HashMap<Integer, List<Objects>> geolines;
    private List<Objects> venichles;
    private HashMap<Integer, List<Objects>> roads;
    private List<Objects> road;
    private List<WriteText> places;
    private List<WriteText> nameTrippers;

    private double[] firstCoordinates = new double[2];

    private WriteText text_center;
}
