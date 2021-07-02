package main.java.maps;


import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class Maps  extends JPanel{

    private JButton MyPosition;
    private JButton Cell;
    private JButton zoomPlus;
    private JButton zoomMinus;
    private Canvas canvas;

    public Maps() {
        MyPosition = new JButton();
        Cell = new JButton();
        zoomPlus = new JButton();
        zoomMinus = new JButton();
        canvas = new PaintInMaps();



        setPreferredSize(new Dimension(500, 500));

        //---- zoomPlus ----
        zoomPlus.setText("+");
        zoomPlus.addActionListener(this::zoomPlusActionPerformed);

        //---- zoomMinus ----
        zoomMinus.setText("-");
        zoomMinus.addActionListener(this::zoomMinusActionPerformed);

        GroupLayout mapsLayout = new GroupLayout(this);
        setLayout(mapsLayout);
        mapsLayout.setHorizontalGroup(
                mapsLayout.createParallelGroup()
                        .addGroup(mapsLayout.createSequentialGroup()
                                .addGroup(mapsLayout.createParallelGroup()
                                       .addGroup(mapsLayout.createSequentialGroup()
                                                .addGap(1059, 1059, 1059)
                                                .addGroup(mapsLayout.createParallelGroup()
                                                        .addComponent(MyPosition)
                                                        .addComponent(Cell)))
                                        .addGroup(mapsLayout.createSequentialGroup()
                                                .addGap(1076, 1076, 1076)
                                                .addGroup(mapsLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(zoomPlus, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(zoomMinus, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE))))
                                .addGap(33, 33, 33))
        );
        mapsLayout.setVerticalGroup(
                mapsLayout.createParallelGroup()
                        .addGroup(mapsLayout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(MyPosition)
                                .addGap(18, 18, 18)
                                .addComponent(Cell)
                                .addGap(18, 18, 18)
                                .addComponent(zoomPlus)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(zoomMinus)
                                .addContainerGap(377, Short.MAX_VALUE))
        );


        add(canvas);

        updateUI();
    }

    private void zoomPlusActionPerformed(ActionEvent e) {

    }

    private void zoomMinusActionPerformed(ActionEvent e) {

    }
}

class PaintInMaps extends Canvas{

    public PaintInMaps() {
        super();
        setBackground(Color.BLACK);
        setSize(1000,800);
        setLocation(20 ,20);
        setVisible(true);
        setName("Canvas");
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        ParseXml parse = new ParseXml("F:\\Krsu\\untitled\\src\\main\\java\\example.xml");
        try {
            parse.loadLinks();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        java.util.List<ReliefItems> list =  parse.getReliefItems();
        for (ReliefItems item :
                list) {
            g2.setColor(Color.blue);
            g2.scale(2,2);
            g2.drawOval((int)(item.getX()*1000), (int)(item.getY()*1000), 10, 10);
        }

    }
}
