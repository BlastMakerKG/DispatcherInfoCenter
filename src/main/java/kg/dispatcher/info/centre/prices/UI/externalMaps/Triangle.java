package kg.dispatcher.info.centre.prices.UI.externalMaps;


import javax.swing.*;
import java.awt.*;

class Triangle extends JComponent {

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
