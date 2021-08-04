package ImportPanel;

import Dependes.TrafficPlan;
import Server.CreateServer;
import maps.lwjgl.GameMain;
import org.xml.sax.SAXException;
import Frame.MonitoringFrame;
//import sun.awt.resources.awt;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ImportData extends JPanel {

    private Button save_bnt;
    private JButton exit_btn;
    private JButton create_btn;
    private JButton server_btn;
    private JLabel jLabel1;
    private JLabel status_msg;
    private JLabel excep_msg;
    private java.awt.List class_list;
    private JScrollPane jScrollPane2;

    private CreateServer[] createsev;
    private  ExecutorService executeIt;
    private String predString;
    private DefaultTableModel modeltab;
    private JTable location_table;

    public ImportData(CreateServer[] createsev, ExecutorService executeIt ) {
        this.createsev = createsev;
        this.executeIt = executeIt;

        save_bnt = new Button();
        exit_btn = new JButton();
        create_btn = new JButton();
        server_btn = new JButton();
        jLabel1 = new JLabel();
        status_msg = new JLabel();
        excep_msg = new JLabel();
        class_list = new java.awt.List();
        jScrollPane2 = new JScrollPane();
        location_table = new JTable();
        modeltab = (DefaultTableModel) location_table.getModel();


        Button maps = new Button("Maps");
        maps.setSize(100,20);
        maps.setLocation(save_bnt.getLocation().x, save_bnt.getY()+40);
        maps.addActionListener(e -> {
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    GameMain maps = new GameMain();
                }
            };

            Thread thread = new Thread(run);
            thread.start();
        });

        add(maps);


        //======== jPanel1 ========
        {
            setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border. EmptyBorder(
                    0, 0, 0, 0) , "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn", javax. swing. border. TitledBorder. CENTER, javax. swing. border. TitledBorder
                    . BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .awt .Font .BOLD ,12 ), java. awt. Color.
                    red) , getBorder( )) ); addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java.beans.PropertyChangeEvent e) {if ("\u0062ord\u0065r" .equals (e .getPropertyName () )) throw new RuntimeException( ); }} );

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



            GroupLayout jPanel1Layout = new GroupLayout(this);
            setLayout(jPanel1Layout);
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
    }


    private List<String[]> data = new ArrayList<>();

    public List<String[]> getData(){
        return data;
    }

    private void create_btnActionPerformed(java.awt.event.ActionEvent evt) {
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
                                String[] subStr1;
                                String delimeter = ",";
                                subStr1 = str.split(delimeter);
                                modeltab.insertRow(modeltab.getRowCount(), new Object[]{Integer.parseInt(subStr1[0])+" "+count++,subStr1[1],Double.parseDouble(subStr1[2]),
                                        Double.parseDouble(subStr1[3]),Double.parseDouble(subStr1[4]),Double.parseDouble(subStr1[5]),Double.parseDouble(subStr1[6])});

                                predString = str;
                            }
                            else{
                                String str = createsev[j].getData();
                                if(str != predString){
                                    String[] subStr1;
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

    }

    private void server_btnActionPerformed(java.awt.event.ActionEvent evt) {

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

//                            TrafficPlan trafficPlan = new TrafficPlan(clientSocket);
//
                            createsev[i] = new CreateServer(clientSocket,class_list, location_table,excep_msg,data);
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

    private void save_bntActionPerformed(java.awt.event.ActionEvent evt) {

        XMLImport xmlFile = new XMLImport();

        for (int i = 0; i < createsev.length; i++) {
            data = createsev[i].getDatas();
        }

        xmlFile.save(data);

//        String str = createsev[0].getData();
//        if (str != predString) {
//            String[] subStr1, subStr2;
//            String delimeter = ",";
//            subStr1 = str.split(delimeter);
//            modeltab.insertRow(5, new Object[]{Integer.parseInt(subStr1[0]), "Hello", Double.parseDouble(subStr1[2]),
//                    Double.parseDouble(subStr1[3]), Double.parseDouble(subStr1[4]), Double.parseDouble(subStr1[5]), Double.parseDouble(subStr1[6])});
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


        }
    }

