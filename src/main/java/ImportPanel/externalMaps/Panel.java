package ImportPanel.externalMaps;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel{

    private JPanel pEx;
    private JPanel pUnloadingPlace;

    private JLabel lblIdExcavator;
    private JLabel lblNameDriveExavator;
    private JLabel lblTypeExcavator;
    private JLabel lblTypeOfdeliveredItem; // тип или качетсво грузимого сырья
    private JLabel lblCountTruck; //todo
    private JLabel lblCountMassWasDelivered;//todo


    private JLabel lblCountReice;//todo
    private JLabel lblNameOfUnloadingPlace; // место отгрузки (отвал)


    public Panel(){
        setLayout(null);
        setVisible(true);
        init_components();
        setSize(800,800);
        setLocation(10,10);
    }

    private void init_components(){

        pEx = new JPanel();
        pEx.setLocation(0,0);
        pEx.setSize(200,150);
        pEx.setLayout(null);
        pEx.setBorder(BorderFactory.createLineBorder(Color.black, 3, true));

        pUnloadingPlace = new JPanel();
        pUnloadingPlace.setSize(100,150);
        pUnloadingPlace.setLocation(700,0);
        pUnloadingPlace.setLayout(null);
        pUnloadingPlace.setBorder(BorderFactory.createLineBorder(Color.GREEN, 7, true));

        lblIdExcavator= new JLabel();
        lblIdExcavator.setText("id ex");
        lblIdExcavator.setLocation(20,20);
        lblIdExcavator.setSize(60, 40);
        pEx.add(lblIdExcavator);


        lblNameDriveExavator= new JLabel();
        lblNameDriveExavator.setText("name of driver ex");
        lblNameDriveExavator.setLocation(20, 40);
        lblNameDriveExavator.setSize(60,40);
        pEx.add(lblNameDriveExavator);

        lblNameOfUnloadingPlace= new JLabel();
        lblNameOfUnloadingPlace.setText("name of unloading place");
        lblNameOfUnloadingPlace.setLocation(20, 40);
        lblNameOfUnloadingPlace.setSize(60,80);
        pUnloadingPlace.add(lblNameOfUnloadingPlace);

        lblTypeExcavator= new JLabel();
        lblTypeExcavator.setText("type ex");
        lblTypeExcavator.setLocation(20, 60);
        lblTypeExcavator.setSize(60,40);
        pEx.add(lblTypeExcavator);

        lblTypeOfdeliveredItem= new JLabel();
        lblTypeOfdeliveredItem.setText("type of delivered item");
        lblTypeOfdeliveredItem.setLocation(20,80);
        lblTypeOfdeliveredItem.setSize(60,40);
        pEx.add(lblTypeOfdeliveredItem);

        pUnloadingPlace.updateUI();

        add(pEx);
        add(new Truck());
        add(pUnloadingPlace);

        updateUI();
    }
}




class Truck extends JPanel{
    private JLabel lblIconImageTruck;
    private JLabel lblIdTruck;
    private JLabel lblDeliveredWeight; // вес груа который перевозтится самосвалом

    public Truck(){
        setLocation(200,0);
        setLayout(null);
        setSize(500,150);
        setBorder(BorderFactory.createLineBorder(Color.BLUE, 5, true));

        lblDeliveredWeight = new JLabel();
        lblDeliveredWeight.setText("delivery weight");
        lblDeliveredWeight.setLocation(20,10);
        lblDeliveredWeight.setSize(80,20);
        add(lblDeliveredWeight);

        lblIconImageTruck = new JLabel(new ImageIcon(new ImageIcon("src/main/resources/Venichles/tripper.png").getImage().getScaledInstance(40, 25, Image.SCALE_DEFAULT)));
        lblIconImageTruck.setLocation(20,40);
        lblIconImageTruck.setSize(40,25);
        add(lblIconImageTruck);

        lblIdTruck= new JLabel();
        lblIdTruck.setText("id truck");
        lblIdTruck.setLocation(20,70);
        lblIdTruck.setSize(60,40);
        add(lblIdTruck);

        updateUI();
    }

    boolean round = false;

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawLine(10,80,490,80);

        int xImage = lblIconImageTruck.getX(), yImage = lblIconImageTruck.getY();
        int xIdTruck = lblIdTruck.getX(), yIdTruck = lblIdTruck.getY();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(xImage > 450 && !round){
            round = true;
            yImage= 90;
            yIdTruck = 120;
        }

        if(!round){
            xImage+=10;
            xIdTruck+=10;
        }else {
            xImage-=10;
            xIdTruck+=10;
            if(xImage < 20){
                round= false;
                yImage=40;
                yIdTruck=70;
            }
        }

        lblIconImageTruck.setLocation(xImage,yImage);
        lblIdTruck.setLocation(xIdTruck, yIdTruck);

        lblIconImageTruck.updateUI();
        lblIdTruck.updateUI();
    }
}
