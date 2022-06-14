package kg.dispatcher.info.centre.prices.panels.externalMaps;

import javax.swing.*;
import java.awt.*;

class Road extends JPanel {

    public Road(){
        setLocation(200,0);
        setLayout(null);
        setSize(500,150);
        setBorder(BorderFactory.createLineBorder(Color.BLUE, 5, true));

        Truck truck = new Truck();
        Truck truck1 = new Truck();
        Truck truck2 = new Truck();

        add(truck);

        truck1.setLocation(60, truck.getY());
        add(truck1);

        truck2.setLocation(120, truck.getY());
        add(truck2);


        updateUI();
    }

    private int widthOFLine = 400;

    private int YOfLine = 80;

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawLine(60,80,460,80);

    }

    class Truck extends JPanel{
        private JLabel lblIconImageTruck;
        private JLabel lblIdTruck;
        private JLabel lblDeliveredWeight; // вес груа который перевозтится самосвалом

        private ImageIcon imageIcon = new ImageIcon(new ImageIcon("src/main/resources/Venichles/tripper.png").getImage().getScaledInstance(40, 25, Image.SCALE_DEFAULT));
        private ImageIcon imageIconRotated = new ImageIcon(new ImageIcon("src/main/resources/Venichles/tripperRotated.png").getImage().getScaledInstance(40, 25, Image.SCALE_REPLICATE));

        private Triangle triangle = new Triangle();

        private double distanceRoad;
        private double scale;
        private String model;
        private double weight;

        public Truck(){
            setLocation(0,10);
            setLayout(null);
            setSize(40,70);

            lblDeliveredWeight = new JLabel();
            lblDeliveredWeight.setText("delivery weight");
            lblDeliveredWeight.setLocation(0,0);
            lblDeliveredWeight.setSize(80,15);
            add(lblDeliveredWeight);

            lblIconImageTruck = new JLabel(imageIcon);
            lblIconImageTruck.setLocation(0,31);
            lblIconImageTruck.setSize(40,25);
            add(lblIconImageTruck);

            lblIdTruck= new JLabel();
            lblIdTruck.setText("id truck");
            lblIdTruck.setLocation(0,15);
            lblIdTruck.setSize(80,15);
            add(lblIdTruck);

            triangle.setLocation(getWidth()/2, getHeight()-triangle.getHeight());
            triangle.rotatetoDown();
            add(triangle);

            createScale();

            updateUI();
        }


        public void createScale(){
            scale = widthOFLine / distanceRoad;
        }

        public void setTextLblDeliveryWeight(int weight){
            lblDeliveredWeight.setText(String.valueOf(weight));
        }

        private boolean round = false;

        public void setRound(boolean round){
            this.round = round;
        }

        public boolean isRound(){
            return round;
        }

        private int widthOFLine = 400;

        private int YOfLine = 80;

        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(getX() == 450 && !round){
                round = true;
                lblIconImageTruck.setIcon(imageIconRotated);
                triangle.setLocation(getWidth()/2-triangle.getWidth(), 0);
                lblIconImageTruck.setLocation(-5, triangle.getHeight());
                lblIdTruck.setLocation(0, triangle.getHeight()+lblIconImageTruck.getHeight());
                lblDeliveredWeight.setLocation(0, triangle.getHeight()+lblIconImageTruck.getHeight()+lblIdTruck.getHeight());
                triangle.rotateToUp();
                setLocation(getX(), getY()+getHeight()+2);
                updateUI();
            }

            if(!round){
                setLocation(getX()+10, getY());
            }else {
                setLocation(getX()-10, getY());
                if(getX() == 10){
                    lblDeliveredWeight.setLocation(0,0);
                    lblIconImageTruck.setLocation(0,31);
                    lblIdTruck.setLocation(0,15);
                    triangle.setLocation(getWidth()/2-triangle.getWidth(), getHeight()-triangle.getHeight());
                    round= false;
                    lblIconImageTruck.setIcon(imageIcon);
                    lblIconImageTruck.updateUI();
                    triangle.rotatetoDown();
                    setLocation(getX()+20, getY()-getHeight()-2);
                    updateUI();
                }
            }
        }
    }

    class Triangle extends JComponent{

        private int x[], y[];

        private Graphics2D g2d;

        public Triangle(){
            this.setSize(10,10);
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            g.setColor(Color.BLACK);
            int npoints = x.length;//or y.length

            Polygon p = new Polygon(x, y, npoints);
            g.drawPolygon(p);//draws polygon outline
            g.fillPolygon(p);//paints a polygon
        }

        public void rotateToUp(){
            x = new int[]{0, 5, 10, 0};
            y = new int[]{10, 0, 10, 10};
        }

        public void rotatetoDown(){
            x = new int[]{0, 5, 10, 0};
            y = new int[]{0, 10, 0, 0};
        }
    }
}
