package Dependes;

import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.util.*;
import java.io.IOException;
import java.util.List;

public class TrafficPlan extends JFrame {

    public TrafficPlan() throws IOException, SAXException, ParserConfigurationException {
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(300,300);
        JPanel panel = panelka();
        setContentPane(panel);
        setSize(panel.getWidth()+25,panel.getHeight()+50);

    }


    private JPanel panelka() throws ParserConfigurationException, SAXException, IOException {
        JPanel panel = new JPanel();
        //panel.setSize(300,300);
        panel.setLayout(null);

        Graph graph = new Graph();
        panel.setSize(graph.getWidth()+50, graph.getHeight()+50);
        panel.add(graph);

        JLabel xLabel = new JLabel("time");
        xLabel.setLocation(graph.getWidth()/2,graph.getHeight()+25);
        xLabel.setSize(100,20);
        xLabel.setFont(new Font("TimesNewRoman", 1, 14));
        xLabel.setBackground(Color.MAGENTA);
        panel.add(xLabel);

        JLabel yLabel = new JLabel("Way");
        yLabel.setSize(100,20);
        yLabel.setLocation(5, graph.getHeight()/2);
        yLabel.setBackground(Color.MAGENTA);
        yLabel.setFont(new Font("TimesNewRoman", 1 ,14));
//        Graphics2D g2 = (Graphics2D) yLabel.getGraphics();
//        g2.rotate(-Math.PI/2);
        panel.add(yLabel);

        return panel;
    }


    private class Graph extends JPanel{

        private ParseForDepends parse = new ParseForDepends("src\\main\\java\\Dependes\\plans.xml");

        public Graph() throws IOException, SAXException, ParserConfigurationException {
            parse.loadLinks();
            setBackground(Color.gray);
            setVisible(true);
            setSize(parse.getTemp().size()*10, parse.getTemp().size()*10);
            setLocation(50,25);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D) g;
//            g2.drawLine(5,5,5,getHeight());
//            g2.drawLine(5,getHeight()-5,getWidth()-5,getHeight()-5);
            g2.setColor(Color.RED);

//            for (int i = 0; i < getWidth(); i = i+10) {
//                g2.drawLine(0, i, 10, i);
//                g2.drawLine(i,240, i, 250);
//            }


            List<Integer[]> temp = parse.getTemp();

            boolean firts = true;
            Integer[] xy = new Integer[2];

            for (Integer[] item :
                    temp) {
                if(firts){
                    firts = false;
                }else {
                    g2.drawLine(xy[1], getHeight() -xy[0], item[1], getHeight() -item[0]);
                }

                xy = item;
            }

            updateUI();

        }
    }
}
