package kg.dispatcher.info.centre.prices.UI.externalMaps;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Ex extends JPanel{

   private JScrollPane scrollPane;

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

//    private java.util.List<Truck> roads = new ArrayList<>();


    private int id;
    private String driver_name;
    private String unload_point;
    private String type;

    public Ex(int id, String driver_name, String unload_point, String type){
        this.id = id;
        this.driver_name = driver_name;
        this.unload_point = unload_point;
        this.type = type;
        setLayout(null);
        setVisible(true);
        setSize(800,150);
        setLocation(10,10);
        init_components();
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
        lblIdExcavator.setText(String.valueOf(id));
        lblIdExcavator.setLocation(20,20);
        lblIdExcavator.setSize(60, 40);
        pEx.add(lblIdExcavator);


        lblNameDriveExavator= new JLabel();
        lblNameDriveExavator.setText(driver_name);
        lblNameDriveExavator.setLocation(20, 40);
        lblNameDriveExavator.setSize(200,40);
        pEx.add(lblNameDriveExavator);

        lblNameOfUnloadingPlace= new JLabel();
        lblNameOfUnloadingPlace.setText(unload_point);
        lblNameOfUnloadingPlace.setLocation(20, 40);
        lblNameOfUnloadingPlace.setSize(60,80);
        pUnloadingPlace.add(lblNameOfUnloadingPlace);

        lblTypeExcavator= new JLabel();
        lblTypeExcavator.setText(type);
        lblTypeExcavator.setLocation(20, 60);
        lblTypeExcavator.setSize(60,40);
        pEx.add(lblTypeExcavator);

//        lblTypeOfdeliveredItem= new JLabel();
//        lblTypeOfdeliveredItem.setText("Gold");
//        lblTypeOfdeliveredItem.setLocation(20,80);
//        lblTypeOfdeliveredItem.setSize(60,40);
//        pEx.add(lblTypeOfdeliveredItem);

        pUnloadingPlace.updateUI();

        add(pEx);


        List<Truck> trucks = new ArrayList<>();

        trucks.add(new Truck(14124,124, 14.2));
        trucks.add(new Truck(214,114, 14.2));
        trucks.add(new Truck(12521,154, 14.2));

        add(new Road(trucks));


        add(pUnloadingPlace);

        updateUI();
    }

}





