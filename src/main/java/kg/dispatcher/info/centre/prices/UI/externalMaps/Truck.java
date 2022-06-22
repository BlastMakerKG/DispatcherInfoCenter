package kg.dispatcher.info.centre.prices.UI.externalMaps;

import javax.swing.*;
import java.awt.*;

class Truck extends JPanel {
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
    private int id;

    public Truck(int id,double weight, double distanceRoad){
        this.weight = weight;
        this.distanceRoad = distanceRoad;
        setLocation(0,10);
        setLayout(null);
        setSize(40,70);

        lblDeliveredWeight = new JLabel();
        lblDeliveredWeight.setText(String.valueOf(weight));
        lblDeliveredWeight.setLocation(0,0);
        lblDeliveredWeight.setSize(80,15);
        add(lblDeliveredWeight);

        lblIconImageTruck = new JLabel(imageIcon);
        lblIconImageTruck.setLocation(0,31);
        lblIconImageTruck.setSize(40,25);
        add(lblIconImageTruck);

        lblIdTruck= new JLabel();
        lblIdTruck.setText(String.valueOf(id));
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
            lblDeliveredWeight.setText("0");
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
                lblDeliveredWeight.setText(String.valueOf(weight));
                lblIconImageTruck.updateUI();
                triangle.rotatetoDown();
                setLocation(getX()+20, getY()-getHeight()-2);
                updateUI();
            }
        }
    }
}