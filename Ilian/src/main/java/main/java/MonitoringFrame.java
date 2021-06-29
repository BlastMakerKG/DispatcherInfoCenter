package main.java;

import org.jxmapviewer.JXMapKit;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.VirtualEarthTileFactoryInfo;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCursor;
import org.jxmapviewer.painter.CompoundPainter;
import org.jxmapviewer.painter.Painter;
import org.jxmapviewer.viewer.*;

import main.java.*;

import java.awt.*;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;
import javax.swing.event.MouseInputListener;
import javax.swing.plaf.synth.Region;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author student2
 */
public class MonitoringFrame extends javax.swing.JFrame {

    ServerSocket server_scoket;
    Socket socket;
    DefaultTableModel modeltab, modeltab2;
    CreateServer[] createsev = new CreateServer[10];
    static ExecutorService executeIt = Executors.newFixedThreadPool(5);
    String predString;

    java.util.List<Painter<JXMapViewer>> painters = new ArrayList<Painter<JXMapViewer>>();
    
    /**
     * Creates new form MonitoringFrame
     */
    private void MyPositionActionPerformed(ActionEvent e) {
        TileFactoryInfo info = new VirtualEarthTileFactoryInfo(VirtualEarthTileFactoryInfo.MAP);
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        kit.setTileFactory(tileFactory);

        MouseInputListener mia = new PanMouseInputListener(kit);
        kit.addMouseListener(mia);
        kit.addMouseMotionListener(mia);
        //kit.addMouseWheelListener(new ZoomMouseWheelListenerCursor(kit));

        RoutePainter routePainter = new RoutePainter(createsev[0].getGeopisitions());
        kit.zoomToBestFit(new HashSet<GeoPosition>(createsev[0].getGeopisitions()), 0.7);

        Set<Waypoint> waypoints = new HashSet<Waypoint>(Arrays.asList(
                new DefaultWaypoint(createsev[0].getGeopisitions().get(0)),
                new DefaultWaypoint(createsev[0].getGeopisitions().get(createsev[0].getGeopisitions().size() - 1))));

        // Create a waypoint painter that takes all the waypoints
        WaypointPainter<Waypoint> waypointPainter = new WaypointPainter<Waypoint>();
        waypointPainter.setWaypoints(waypoints);

        // Create a compound painter that uses both the route-painter and the waypoint-painter
        painters.add(routePainter);
        painters.add(waypointPainter);

        CompoundPainter<JXMapViewer> painter = new CompoundPainter<JXMapViewer>(painters);
        kit.setOverlayPainter(painter);
        kit.setAddressLocation(new GeoPosition(31.52455, 25.5163565));
    }

    private void CellActionPerformed(ActionEvent e) {
        Runnable run = new Runnable() {
            @Override
            public void run() {

                Set<Waypoint> waypoints = null;
                WaypointPainter<Waypoint> waypointPainter;
                while(true) {
                    for (int i = 0; i < createsev.length; i++) {
                        if(createsev[i] != null) {
                            waypoints = new HashSet<Waypoint>(Arrays.asList(new DefaultWaypoint(createsev[i].getPosition())));
                        }
                    }

                    waypointPainter = new WaypointPainter<Waypoint>();
                    waypointPainter.setWaypoints(waypoints);
                    painters.add(waypointPainter);
                    CompoundPainter<JXMapViewer> painter = new CompoundPainter<>(painters);
                    kit.setOverlayPainter(painter);

                    try {
                        Thread.sleep(5000);
                        painters.remove(waypointPainter);
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                }
            }
        };
        Thread thread = new Thread(run);
        thread.start();
    }

    private void zoomPlusActionPerformed(ActionEvent e) {
        kit.setZoom(kit.getZoom()-1);
    }

    private void zoomMinusActionPerformed(ActionEvent e) {
        kit.setZoom(kit.getZoom()+1);
    }

    public MonitoringFrame() {
        initComponents();
        modeltab = (DefaultTableModel) location_table.getModel();
        modeltab2 = (DefaultTableModel) location_table2.getModel();
     
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    // Generated using JFormDesigner Evaluation license - unknown
    private void initComponents() {
        Main = new JTabbedPane();
        jPanel1 = new JPanel();
        save_bnt = new Button();
        exit_btn = new JButton();
        create_btn = new JButton();
        server_btn = new JButton();
        jLabel1 = new JLabel();
        status_msg = new JLabel();
        excep_msg = new JLabel();
        class_list = new List();
        jScrollPane2 = new JScrollPane();
        location_table = new JTable();
        jPanel2 = new JPanel();
        jScrollPane4 = new JScrollPane();
        location_table2 = new JTable();
        jLabel2 = new JLabel();
        jScrollPane1 = new JScrollPane();
        connect_avto_list = new JList<>();
        status_msg1 = new JLabel();
        excep_msg1 = new JLabel();
        maps = new JPanel();
        MyPosition = new JButton();
        Cell = new JButton();
        kit = new JXMapViewer();
        zoomPlus = new JButton();
        zoomMinus = new JButton();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container contentPane = getContentPane();

        //======== Main ========
        {
            Main.setBackground(Color.white);
            Main.setName("\u0413\u043b\u0430\u0432\u043d\u043e\u0435");

            //======== jPanel1 ========
            {
                jPanel1.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax.
                swing. border. EmptyBorder( 0, 0, 0, 0) , "JFor\u006dDesi\u0067ner \u0045valu\u0061tion", javax. swing. border
                . TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new java .awt .Font ("Dia\u006cog"
                ,java .awt .Font .BOLD ,12 ), java. awt. Color. red) ,jPanel1. getBorder
                ( )) ); jPanel1. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java
                .beans .PropertyChangeEvent e) {if ("bord\u0065r" .equals (e .getPropertyName () )) throw new RuntimeException
                ( ); }} );

                //---- save_bnt ----
                save_bnt.setActionCommand("\u0421\u043e\u0445\u0440\u0430\u043d\u0438\u0442\u044c");
                save_bnt.setLabel("C\u043e\u0445\u0440\u0430\u043d\u0438\u0442\u044c");
                save_bnt.addActionListener(e -> save_bntActionPerformed(e));

                //---- exit_btn ----
                exit_btn.setBackground(new Color(255, 102, 102));
                exit_btn.setFont(new Font("Verdana", Font.BOLD, 14));
                exit_btn.setForeground(Color.white);
                exit_btn.setText("\u0412\u044b\u0439\u0442\u0438");
                exit_btn.addActionListener(e -> exit_btnActionPerformed(e));

                //---- create_btn ----
                create_btn.setBackground(new Color(0, 204, 0));
                create_btn.setFont(new Font("Verdana", Font.BOLD, 14));
                create_btn.setForeground(Color.white);
                create_btn.setText("\u041f\u043e\u043c\u043e\u0449\u044c");
                create_btn.addActionListener(e -> create_btnActionPerformed(e));

                //---- server_btn ----
                server_btn.setBackground(new Color(255, 102, 0));
                server_btn.setFont(new Font("Verdana", Font.BOLD, 14));
                server_btn.setForeground(Color.white);
                server_btn.setText("\u041d\u0430\u0447\u0430\u0442\u044c \u043f\u0435\u0440\u0435\u0434\u0430\u0447\u0443 \u0434\u0430\u043d\u043d\u044b\u0445");
                server_btn.addActionListener(e -> server_btnActionPerformed(e));

                //---- jLabel1 ----
                jLabel1.setFont(new Font("Tahoma", Font.BOLD, 14));
                jLabel1.setText("\u041f\u043e\u0434\u043a\u043b\u044e\u0447\u0435\u043d\u043d\u044b\u0435 \u0443\u0441\u0442\u0440\u043e\u0439\u0441\u0442\u0432\u0430");

                //---- status_msg ----
                status_msg.setText("\u0421\u0442\u0430\u0442\u0443\u0441:");

                //---- class_list ----
                class_list.addActionListener(e -> class_listActionPerformed(e));

                //======== jScrollPane2 ========
                {

                    //---- location_table ----
                    location_table.setModel(new DefaultTableModel(
                        new Object[][] {
                        },
                        new String[] {
                            "ID", "\u0414\u0430\u0442\u0430", "\u0428\u0438\u0440\u043e\u0442\u0430", "\u0414\u043e\u043b\u0433\u043e\u0442\u0430", "\u0410\u0431\u0441\u043e\u043b\u044e\u0442\u043d\u0430\u044f \u0432\u044b\u0441\u043e\u0442\u0430", "\u0420\u0430\u0441\u0441\u0442\u043e\u044f\u043d\u0438\u0435", "\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c"
                        }
                    ) {
                        Class<?>[] columnTypes = new Class<?>[] {
                            Integer.class, String.class, Double.class, Double.class, Double.class, Double.class, Double.class
                        };
                        @Override
                        public Class<?> getColumnClass(int columnIndex) {
                            return columnTypes[columnIndex];
                        }
                    });
                    jScrollPane2.setViewportView(location_table);
                }

                GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                    jPanel1Layout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 777, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createParallelGroup()
                                    .addComponent(class_list, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1, GroupLayout.Alignment.TRAILING)
                                    .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(status_msg, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(excep_msg, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)))
                                .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(create_btn, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(exit_btn, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE))
                                .addComponent(server_btn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(save_bnt, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(79, 79, 79))
                );
                jPanel1Layout.setVerticalGroup(
                    jPanel1Layout.createParallelGroup()
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel1Layout.createParallelGroup()
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jScrollPane2)
                                    .addGap(24, 24, 24))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(class_list, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
                                    .addGap(19, 19, 19)
                                    .addComponent(status_msg)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(excep_msg, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
                                    .addGap(44, 44, 44)
                                    .addComponent(server_btn, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                                    .addGap(26, 26, 26)
                                    .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(create_btn)
                                        .addComponent(exit_btn))
                                    .addGap(22, 22, 22)
                                    .addComponent(save_bnt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                );
            }
            Main.addTab("\u041c\u043e\u0434\u0443\u043b\u044c \u043e\u0446\u0438\u0444\u0440\u043e\u0432\u043a\u0438 \u0442\u0440\u0430\u0441\u0441\u044b", jPanel1);

            //======== jPanel2 ========
            {

                //======== jScrollPane4 ========
                {

                    //---- location_table2 ----
                    location_table2.setModel(new DefaultTableModel(
                        new Object[][] {
                        },
                        new String[] {
                            "ID", "\u0414\u0430\u0442\u0430", "\u0428\u0438\u0440\u043e\u0442\u0430", "\u0414\u043e\u043b\u0433\u043e\u0442\u0430", "\u0410\u0431\u0441\u043e\u043b\u044e\u0442\u043d\u0430\u044f \u0432\u044b\u0441\u043e\u0442\u0430", "\u0420\u0430\u0441\u0441\u0442\u043e\u044f\u043d\u0438\u0435", "\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c"
                        }
                    ) {
                        Class<?>[] columnTypes = new Class<?>[] {
                            Integer.class, String.class, Double.class, Double.class, Double.class, Double.class, Double.class
                        };
                        @Override
                        public Class<?> getColumnClass(int columnIndex) {
                            return columnTypes[columnIndex];
                        }
                    });
                    jScrollPane4.setViewportView(location_table2);
                }

                //---- jLabel2 ----
                jLabel2.setFont(new Font("Tahoma", Font.BOLD, 14));
                jLabel2.setText("\u041f\u043e\u0434\u043a\u043b\u044e\u0447\u0435\u043d\u043d\u044b\u0435 \u0443\u0441\u0442\u0440\u043e\u0439\u0441\u0442\u0432\u0430");

                //======== jScrollPane1 ========
                {

                    //---- connect_avto_list ----
                    connect_avto_list.setModel(new AbstractListModel<String>() {
                        String[] values = {
                            "Item 1",
                            "Item 2",
                            "Item 3",
                            "Item 4",
                            "Item 5"
                        };
                        @Override
                        public int getSize() { return values.length; }
                        @Override
                        public String getElementAt(int i) { return values[i]; }
                    });
                    jScrollPane1.setViewportView(connect_avto_list);
                }

                //---- status_msg1 ----
                status_msg1.setText("\u0421\u0442\u0430\u0442\u0443\u0441:");

                GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                    jPanel2Layout.createParallelGroup()
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jScrollPane4, GroupLayout.PREFERRED_SIZE, 777, GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup()
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(80, 80, 80)
                                    .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(status_msg1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(excep_msg1, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(62, 62, 62)
                                    .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel2, GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                                        .addComponent(jScrollPane1))))
                            .addContainerGap(98, Short.MAX_VALUE))
                );
                jPanel2Layout.setVerticalGroup(
                    jPanel2Layout.createParallelGroup()
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jScrollPane4, GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)
                            .addGap(24, 24, 24))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addComponent(jLabel2)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
                            .addGap(47, 47, 47)
                            .addComponent(status_msg1)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(excep_msg1, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
            }
            Main.addTab("\u041f\u043e\u043b\u043e\u0436\u0435\u043d\u0438\u0435 \u0442\u0440\u0430\u043d\u0441\u043f\u043e\u0440\u0442\u043e\u0432", jPanel2);

            //======== maps ========
            {
                maps.setPreferredSize(new Dimension(500, 500));

                //---- MyPosition ----
                MyPosition.setText("Road");
                MyPosition.addActionListener(e -> MyPositionActionPerformed(e));

                //---- Cell ----
                Cell.setText("Points");
                Cell.addActionListener(e -> CellActionPerformed(e));

                //---- zoomPlus ----
                zoomPlus.setText("+");
                zoomPlus.addActionListener(e -> zoomPlusActionPerformed(e));

                //---- zoomMinus ----
                zoomMinus.setText("-");
                zoomMinus.addActionListener(e -> zoomMinusActionPerformed(e));

                GroupLayout mapsLayout = new GroupLayout(maps);
                maps.setLayout(mapsLayout);
                mapsLayout.setHorizontalGroup(
                    mapsLayout.createParallelGroup()
                        .addGroup(mapsLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(kit, GroupLayout.PREFERRED_SIZE, 1035, GroupLayout.PREFERRED_SIZE)
                            .addGroup(mapsLayout.createParallelGroup()
                                .addGroup(mapsLayout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addGroup(mapsLayout.createParallelGroup()
                                        .addComponent(MyPosition)
                                        .addComponent(Cell)))
                                .addGroup(mapsLayout.createSequentialGroup()
                                    .addGap(35, 35, 35)
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
                        .addGroup(mapsLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(kit, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
                            .addContainerGap())
                );
            }
            Main.addTab("\u041e\u0442\u043e\u0431\u0440\u0430\u0436\u0435\u043d\u0438\u0435", maps);
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addComponent(Main, GroupLayout.PREFERRED_SIZE, 1153, GroupLayout.PREFERRED_SIZE)
                    .addGap(170, 170, 170))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addComponent(Main)
        );
        pack();
        setLocationRelativeTo(getOwner());
    }// </editor-fold>//GEN-END:initComponents

    private void create_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_create_btnActionPerformed
        // TODO add your handling code here:
        Runnable run = new Runnable()
        {
            @Override
            public void run() {
            int i= 0;
            int count =1;
                while(true)
                {
                    try{
                        for(int j=0; j < createsev.length; j++)
                        {
                            if(predString == null)
                            {
                             String str = createsev[j].getData();
                             String[] subStr1, subStr2;
                             String delimeter = ",";  
                             subStr1 = str.split(delimeter);
                             modeltab.insertRow(modeltab.getRowCount(), new Object[]{Integer.parseInt(subStr1[0])+" "+count++,subStr1[1],Double.parseDouble(subStr1[2]),
                                                                         Double.parseDouble(subStr1[3]),Double.parseDouble(subStr1[4]),Double.parseDouble(subStr1[5]),Double.parseDouble(subStr1[6])});
  
                             predString = str;
                            }
                            else{
                             String str = createsev[j].getData();
                             if(str != predString){
                             String[] subStr1, subStr2;
                             String delimeter = ",";  
                             subStr1 = str.split(delimeter);
                             modeltab.insertRow(modeltab.getRowCount(), new Object[]{Integer.parseInt(subStr1[0])+" "+count++,subStr1[1],Double.parseDouble(subStr1[2]),
                                                                         Double.parseDouble(subStr1[3]),Double.parseDouble(subStr1[4]),Double.parseDouble(subStr1[5]),Double.parseDouble(subStr1[6])});
  
                             predString = str;
                                 
                             }                
                            }
                        }          
                    }
                    catch(Exception ex)
                    {
                        ex.printStackTrace();
                    }
          
                    try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MonitoringFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
                
            }
            
        };
        Thread thread = new Thread(run);
        thread.start();
        
    }//GEN-LAST:event_create_btnActionPerformed

    private void server_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_server_btnActionPerformed
        // TODO add your handling code here:
       
        try{
            final ServerSocket serverSocket = new ServerSocket(24500);
            System.out.println("Сервер создан ждем подключения");
             status_msg.setText("Сервер успешно создан");


            
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    int i =0;
                   while(!serverSocket.isClosed())
                   {
                 Socket clientSocket;
                       try {
                           clientSocket = serverSocket.accept();
                           createsev[i] = new CreateServer(clientSocket,class_list, location_table,excep_msg,i);
                           executeIt.execute(createsev[i]);

//                           createsev[i] = new CreateServer(clientSocket,class_list, location_table2,excep_msg,i);
//                           executeIt.execute(createsev[i]);
                           i++;
                       } catch (IOException ex) {
                           Logger.getLogger(MonitoringFrame.class.getName()).log(Level.SEVERE, null, ex);
                       }
                 
//                   JOptionPane.showMessageDialog(null,
//                              "Клиент подключился",
//                              "Клиент",
//                              JOptionPane.WARNING_MESSAGE);
                 
            }
                }
            };
            
            Thread myThread = new Thread(runnable);
            myThread.start();
            
           
        }
        
        catch(Exception ex)
        {
            excep_msg.setText(ex.getMessage());
        }
       
    }//GEN-LAST:event_server_btnActionPerformed

    private void class_listActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_class_listActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_class_listActionPerformed

    private void exit_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exit_btnActionPerformed
        // TODO add your handling code here:
      System.exit(0);
    }//GEN-LAST:event_exit_btnActionPerformed

    private void save_bntActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save_bntActionPerformed
//         TODO add your handling code here:
        
    String str = createsev[0].getData();
                             if(str != predString){
                             String[] subStr1, subStr2;
                             String delimeter = ",";  
                             subStr1 = str.split(delimeter);
                             modeltab.insertRow(5, new Object[]{Integer.parseInt(subStr1[0]),"Hello",Double.parseDouble(subStr1[2]),
                                                                         Double.parseDouble(subStr1[3]),Double.parseDouble(subStr1[4]),Double.parseDouble(subStr1[5]),Double.parseDouble(subStr1[6])});
//        try{
//            
//        JFileChooser fileChooser = new JFileChooser();
//        fileChooser.showSaveDialog(this);
//         File file =fileChooser.getSelectedFile();
//         
//         ExportData.exportToCSV(location_table, file);
//         
//        }

//        JFileChooser fileChooser = new JFileChooser();
//        fileChooser.setDialogTitle("Specify a file save");
//        int userSelection = fileChooser.showSaveDialog(this);
//        if(userSelection == JFileChooser.APPROVE_OPTION){
//            File fileToSave = fileChooser.getSelectedFile();
//            //lets write to file
//         
//            try {
//                  FileWriter fw = new FileWriter(fileToSave);
//                BufferedWriter bw = new BufferedWriter(fw);
//                for (int i = 0; i<location_table.getRowCount(); i++) 
//                {
//                    for (int j = 0; j<location_table.getColumnCount(); j++) {
//                        //write
//                        bw.write(location_table.getValueAt(i, j).toString()+",");
//                    }
//                    bw.newLine();//record per line 
//                }
//                JOptionPane.showMessageDialog(this, "SUCCESSFULLY LOADED","INFORMATION",JOptionPane.INFORMATION_MESSAGE);
//                bw.close();
//                fw.close();
//            } catch (IOException ex) {
//               JOptionPane.showMessageDialog(this, "ERROR","ERROR MESSAGE",JOptionPane.ERROR_MESSAGE);
//            }}

//
//            try (PrintWriter writer = new PrintWriter(new File("test.csv"))) {
//
//      StringBuilder sb = new StringBuilder();
//      sb.append("id,");
//      sb.append(';');
//      sb.append("Name");
//      sb.append('\n');
//
//      sb.append("1");
//      sb.append(';');
//      sb.append("Prashant Ghimire");
//      sb.append('\n');
//
//      writer.write(sb.toString());
//
//      System.out.println("done!");
//
//    } catch (FileNotFoundException e) {
//      System.out.println(e.getMessage());
//    }
//            
//        }
//        
// 
        
        
    }//GEN-LAST:event_save_bntActionPerformed
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MonitoringFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MonitoringFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MonitoringFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MonitoringFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MonitoringFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JTabbedPane Main;
    private JPanel jPanel1;
    private Button save_bnt;
    private JButton exit_btn;
    private JButton create_btn;
    private JButton server_btn;
    private JLabel jLabel1;
    private JLabel status_msg;
    private JLabel excep_msg;
    private List class_list;
    private JScrollPane jScrollPane2;
    private JTable location_table;
    private JPanel jPanel2;
    private JScrollPane jScrollPane4;
    private JTable location_table2;
    private JLabel jLabel2;
    private JScrollPane jScrollPane1;
    private JList<String> connect_avto_list;
    private JLabel status_msg1;
    private JLabel excep_msg1;
    private JPanel maps;
    private JButton MyPosition;
    private JButton Cell;
    private JXMapViewer kit;
    private JButton zoomPlus;
    private JButton zoomMinus;
    // End of variables declaration//GEN-END:variables

  
}

