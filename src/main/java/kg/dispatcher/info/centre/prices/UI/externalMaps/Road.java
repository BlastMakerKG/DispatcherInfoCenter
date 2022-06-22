package kg.dispatcher.info.centre.prices.UI.externalMaps;

import javax.swing.*;
import java.awt.*;
import java.util.List;

class Road extends JPanel {

    public Road(List<Truck> trucks){
        setLocation(200,0);
        setLayout(null);
        setSize(500,150);
        setBorder(BorderFactory.createLineBorder(Color.BLUE, 5, true));


        for (int i = 0; i < trucks.size(); i++) {
            trucks.get(i).setLocation(i*80, trucks.get(i).getY());
            add(trucks.get(i));
        }


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

}
