package maps;


import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCursor;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.LinkedList;

public class Maps  extends JPanel{

    private JButton myPosition;
    private JButton cell;
    private JButton zoomPlus;
    private JButton zoomMinus;
    private JPanel canvas;

    public Maps() {
        myPosition = new JButton();
        cell = new JButton();
        zoomPlus = new JButton();
        zoomMinus = new JButton();
        canvas = new Canvas();

        setPreferredSize(new Dimension(500, 500));

        //--- Canvas ---
        canvas.setSize(1000, 1000);

        //-----MyPosition---
        myPosition.setText("MyPosition");

        //---- Cell ---
        cell.setText("Cell");

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
                                                        .addComponent(myPosition)
                                                        .addComponent(cell)))
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
                                .addComponent(myPosition)
                                .addGap(18, 18, 18)
                                .addComponent(cell)
                                .addGap(18, 18, 18)
                                .addComponent(zoomPlus)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(zoomMinus)
                                .addContainerGap(377, Short.MAX_VALUE))
        );


        add(canvas);

        updateUI();
    }

    private static double zoom = 1;

    private void zoomPlusActionPerformed(ActionEvent e) {
        zoom += 0.1;
        canvas.repaint();
    }

    private void zoomMinusActionPerformed(ActionEvent e) {
        zoom -= 0.1;
        canvas.repaint();
    }

    private static class Canvas extends JPanel {
        private static final int maxX = 1000;
        private static final int maxY = 1000;
//        private static double zoom = 1;

        public Canvas() {
            this.setPreferredSize(new Dimension(maxX, maxY));
            this.setFocusable(true);
            this.setBackground(Color.BLACK);

            addMouseListener(new MouseAdapter() {
                private Color background;

                @Override
                public void mousePressed(MouseEvent e) {
                    background = getBackground();
                    setBackground(Color.RED);
                    repaint();
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    setBackground(background);
                }
            });

//            Button zoomIn = new Button("Zoom In");
//            zoomIn.addActionListener(new ActionListener() {
//                public void actionPerformed(ActionEvent e) {
//                    zoom += 0.1;
//                    repaint();
//                }
//            });
//            add(zoomIn);
//
//            Button zoomOut = new Button("Zoom Out");
//            zoomOut.addActionListener(new ActionListener() {
//                public void actionPerformed(ActionEvent e) {
//                    zoom -= 0.1;
//                    repaint();
//                }
//            });
//            add(zoomOut);


            // add(circle); // Comment back in if using JLayeredPane
        }

        @Override
        public void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.scale(zoom, zoom);
            super.paintComponent(g);

            g2.setColor(Color.RED);

            //circle.paint(g); // Comment out if using JLayeredPane

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
//                g2.setColor(Color.blue);
//                g2.scale(2,2);
//                g2.drawOval((int)(item.getX()/2000), (int)(item.getY()/2000), 10, 10);
                Circle circle = new Circle((int)item.getX()/2000, (int)item.getY()/2000);
            }

            LinkedList<ReliefItems> geoLines = parse.getGeoLines();
            ReliefItems temp = null;
            boolean first =true;
            for (ReliefItems item : geoLines){
                g2.setColor(Color.RED);
                g2.scale(2, 2);
                if(!first) {
                    g2.drawLine((int)(temp.getX()/1000), (int)(temp.getY()/1000), (int)(item.getX()/1000), (int)(item.getY()/1000));
                }else{
                    first = false;
                }
                temp = item;
            }
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(20000, 20000);
        }

    }

    static class Circle extends JPanel {
        private Color color = Color.RED;
        private final int x;
        private final int y;
        private static final int DIMENSION = 100;

        public Circle(int x, int y) {
            // setBounds(x, y, DIMENSION, DIMENSION);
            this.x = x;
            this.y = y;
            addMouseListener(new MouseAdapter() {

                @Override
                public void mousePressed(MouseEvent e) {
                    color = Color.BLUE;
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }
            });
        }

        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setPaint(color);
            g2.fillOval(x, y, DIMENSION, DIMENSION);
        }

        // I had some trouble getting this to work with JLayeredPane even when setting the bounds
        // In the constructor
        // @Override
        // public void paintComponent(Graphics g) {
        //     Graphics2D g2 = (Graphics2D) g;
        //     g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //     g2.setPaint(color);
        //     g2.fillOval(x, y, DIMENSION, DIMENSION);
        // }

        @Override
        public  Dimension getPreferredSize(){
            return new Dimension(DIMENSION, DIMENSION);
        }
    }
}

