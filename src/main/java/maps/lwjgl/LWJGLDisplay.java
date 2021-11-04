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

        venichles.add(new Tripper(400.0f, 150.0f, "tripper.png"));
        venichles.add(new Tripper( (float)((Math.random()- 300)+ 100), (float) ((Math.random()- 300)+ 100), "tripper.png"));
        venichles.add(new Excavator(200,200, "excavator.png"));

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
    int x=0,y=0, id = 1, height;
    public void converterToXY(String id, double[] coordinates, int height){

        Runnable run = () -> {
            this.height = height;
            this.id = Integer.parseInt(id);
            if(firstCoordinate){
                firstCoordinates = coordinates;
                venichles.add(new Tripper((float)coordinates[0],(float) coordinates[1], "tripper.jpg"));
                firstCoordinate=false;
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

            for (Objects wr : VerticalTexts) {
                wr.text.setRenderString(new StringBuilder().append(Math.round(Float.parseFloat(wr.text.getRenderString().toString()) + 12000)));
            }
            for (Objects wr : HorisontalTexts) {
                wr.text.setRenderString(new StringBuilder().append(Math.round(Float.parseFloat(wr.text.getRenderString().toString()) + 77000)));
            }
        }
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
        foreachListToRender(points, mouseX,mouseY,zoom);


        for (int i = 0; i < geolines.size(); i++) {
            foreachListToRender( geolines.get(i), mouseX,mouseY,zoom);
        }




        for (Objects car : venichles) {
            car.x += mouseX;
            car.y += mouseY;
            car.resize(zoom);
            if(x != 0 || y!= 0) {
                if(car instanceof Tripper) {

                    venichles.get(id-1).x += x;
                    venichles.get(id-1).y += y;
                }
                if(height > 750){
                    r+=0.01;g-=0.01;
                }
                roads.get(venichles.indexOf(car)).add(new Road(car.x,car.y, r,g,b));
            }

            x =0; y=0;
        }

        for(int index : roads.keySet()){
            foreachListToRender(roads.get(index), mouseX, mouseY, zoom);
        }

        foreachListToRender(road, mouseX,mouseY,zoom);
    }

    float r=0.3f,g=0.7f,b=0.8f;

    private void foreachListToRender(List<Objects> listObject, float mouseX, float mouseY, float zoom){
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

        for(Objects text : nameTrippers){
            text.x += Math.round(text.getX() + mouseX);
            text.y += Math.round(text.getY() + mouseY);
        }
    }

    private void updateText(float zoom, float mouse, List<Objects> horisontalTexts) {
        for(Objects wr : horisontalTexts) {
            wr.text.setRenderString(new StringBuilder().append(Math.round(Float.parseFloat(wr.text.getRenderString().toString()) - mouse)));
            if (zoom < 0) {
                wr.text.setRenderString(new StringBuilder().append(Math.round(Float.parseFloat(wr.text.getRenderString().toString()) / 2)));
            } else if (zoom > 0) {
                wr.text.setRenderString(new StringBuilder().append(Math.round(Float.parseFloat(wr.text.getRenderString().toString()) * 2)));
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
        renderForeach(points);
        renderForeach( layers);
        renderForeach(VerticalTexts);
        renderForeach(HorisontalTexts);
        for (int i = 0; i < geolines.size(); i++) {
            renderForeach(geolines.get(i));
        }
        renderForeach(venichles);

        for (int i = 0; i < roads.size(); i++) {
            renderForeach(roads.get(i));
        }

        renderForeach(places);
        renderForeach(nameTrippers);

        text_center.render();
    }


    private void renderForeach(List<Objects> objects){
        for(Objects ob : objects){
            ob.render();
        }
    }

    public void setVenichles(List<Objects> cars){
        this.venichles = cars;
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
    private List<Objects> layers;
    private final ParseXmlPoints parse = new ParseXmlPoints("src\\main\\resources\\example.xml");
    private final HashMap<Integer,LinkedList<ReliefItems>> geoLines = parse.getGeoLines();
    private final List<ReliefItems> list =  parse.getReliefItems();
    private List<Objects> VerticalTexts;
    private List<Objects> HorisontalTexts;
    private HashMap<Integer, List<Objects>> geolines;
    private List<Objects> venichles;
    private HashMap<Integer, List<Objects>> roads;
    private List<Objects> road;
    private List<Objects> places;
    private List<Objects> nameTrippers;

    private double[] firstCoordinates = new double[2];

    private WriteText text_center;
}
