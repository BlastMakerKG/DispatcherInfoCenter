package panels.externalMaps;

import javax.swing.*;
import java.awt.*;

public class Maps extends JPanel {
//     private JScrollPane scrollPane;

     public Maps(){

         setLayout(null);
         setVisible(true);
         setSize(800,800);

//         scrollPane = new JScrollPane();
//         scrollPane.setSize(800,200);
//         scrollPane.updateUI();
//
//         scrollPane.add(new Ex());
//         scrollPane.add(new Ex());
//         scrollPane.add(new Ex());
//         scrollPane.add(new Ex());
//         scrollPane.add(new Ex());
//         scrollPane.add(new Ex());
//         scrollPane.add(new Ex());
//         scrollPane.add(new Ex());
//         scrollPane.add(new Ex());

//         scrollPane.updateUI();
//         add(scrollPane);
         Ex ex1 = new Ex();
         ex1.setLocation(0, 0);

         Ex ex2 = new Ex();
         ex2.setLocation(0, ex1.getHeight());

//         ex2.addTruck(new Truck());

         add(ex1);
         add(ex2);

         updateUI();
//         add(new Ex());
     }

     @Override
    public void paintComponent(Graphics g){
         super.paintComponent(g);
     }
}
