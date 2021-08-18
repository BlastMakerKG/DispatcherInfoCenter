package ImportPanel;

import Server.CreateServer;
import XmlFile.ImportDataInXML;
import maps.lwjgl.CreateLWJGL;
import Frame.MonitoringFrame;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.*;
import java.util.*;
import java.util.List;
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
    private Button maps;
    private java.awt.List class_list;
    private JScrollPane jScrollPane2;

    private CreateServer[] createsev;
    private ExecutorService executeIt;
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
        maps = new Button("Maps");


        //======== jPanel1 ========
        {
            setBorder (new CompoundBorder( new TitledBorder (new EmptyBorder(0, 0, 0, 0) , "JFormDesigner Evaluation", TitledBorder.CENTER, TitledBorder.BOTTOM, new Font ("Dialog" ,Font.BOLD ,12), Color.red), getBorder()));
            addPropertyChangeListener (e -> {
                        if ("border".equals(e.getPropertyName()))
                            throw new RuntimeException( );
            });

            //---- save_bnt ----
            save_bnt.setActionCommand("Сохранить");
            save_bnt.setLabel("Сохранить");
            save_bnt.addActionListener(e -> save_bntActionPerformed(e));


            // ---- Maps ---
            maps.setActionCommand("Create maps");
            maps.addActionListener(e -> maps());
            maps.setLabel("Maps");
            maps.setBackground(Color.gray);
            maps.setForeground(Color.cyan);
            maps.setFont(new Font("TimeNewRoman",Font.BOLD, 14));

            //---- exit_btn ----
            exit_btn.setBackground(new Color(255, 102, 102));
            exit_btn.setFont(new Font("Verdana", Font.BOLD, 14));
            exit_btn.setForeground(Color.white);
            exit_btn.setText("Выйти");
            exit_btn.addActionListener(e -> exit_btnActionPerformed(e));

            //---- create_btn ----
            create_btn.setBackground(new Color(0, 204, 0));
            create_btn.setFont(new Font("Verdana", Font.BOLD, 14));
            create_btn.setForeground(Color.white);
            create_btn.setText("Помощь");
            create_btn.addActionListener(e -> create_btnActionPerformed(e));

            //---- server_btn ----
            server_btn.setBackground(new Color(255, 102, 0));
            server_btn.setFont(new Font("Verdana", Font.BOLD, 14));
            server_btn.setForeground(Color.white);
            server_btn.setText("Начать передачу данных");
            server_btn.addActionListener(e -> server_btnActionPerformed(e));

            //---- jLabel1 ----
            jLabel1.setFont(new Font("Tahoma", Font.BOLD, 14));
            jLabel1.setText("Подключенные устройства");

            //---- status_msg ----
            status_msg.setText("Статус:");

            //---- class_list ----
            class_list.addActionListener(e -> class_listActionPerformed(e));

            //======== jScrollPane2 ========
            {

                //---- location_table ----
                location_table.setModel(new DefaultTableModel(
                        new Object[][] {
                        },
                        new String[] {
                                "ID", "Дата", "Широта", "Долгота", "Абсолютная высота", "Расстояние", "Скорость"
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
                                            .addComponent(maps, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                                                    .addComponent(maps, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                    .addGap(10,10,10)
                                                    .addComponent(save_bnt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))

//                                                    .addGap(22,22,22)
            );
        }
    }

    private void maps(){
        Runnable run = () -> {
            CreateLWJGL maps = new CreateLWJGL();
        };

        Thread thread = new Thread(run);
        thread.start();
    }


    private List<String[]> data = new ArrayList<>();

//    public List<String[]> getData(){
//        return data;
//    }

    private void create_btnActionPerformed(java.awt.event.ActionEvent evt) {
        Runnable run = () -> {
            int count =1;
            while(true) {
                try{
                    for(int j=0; j < createsev.length; j++) {
                        if(predString == null) {
                            String str = createsev[j].getData();
                            String[] subStr1;
                            String delimeter = ",";
                            subStr1 = str.split(delimeter);
                            modeltab.insertRow(modeltab.getRowCount(),
                                    new Object[]{Integer.parseInt(
                                            subStr1[0])+" "+count++,
                                            subStr1[1],
                                            Double.parseDouble(subStr1[2]),
                                            Double.parseDouble(subStr1[3]),
                                            Double.parseDouble(subStr1[4]),
                                            Double.parseDouble(subStr1[5]),
                                            Double.parseDouble(subStr1[6])
                                    }
                            );

                            predString = str;
                        }
                        else{
                            String str = createsev[j].getData();
                            if(str != predString){
                                String[] splitStr;
                                String delimeter = ",";
                                splitStr = str.split(delimeter);
                                modeltab.insertRow(modeltab.getRowCount(),
                                        new Object[]{
                                                Integer.parseInt(splitStr[0])+" "+
                                                        count++,
                                                splitStr[1],
                                                Double.parseDouble(splitStr[2]),
                                                Double.parseDouble(splitStr[3]),
                                                Double.parseDouble(splitStr[4]),
                                                Double.parseDouble(splitStr[5]),
                                                Double.parseDouble(splitStr[6])
                                        }
                                );
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

        };
        Thread thread = new Thread(run);
        thread.start();

    }

    private void server_btnActionPerformed(ActionEvent evt) {

        try{
            final ServerSocket serverSocket = new ServerSocket(24500);
            System.out.println("Сервер создан ждем подключения");
            status_msg.setText("Сервер успешно создан");

            Runnable runnable = () -> {
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

//                        createsev[i] = new CreateServer(clientSocket,class_list, location_table2,excep_msg,i);
//                        executeIt.execute(createsev[i]);

                        i++;
                    } catch (IOException ex) {
                        Logger.getLogger(MonitoringFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }

//                   JOptionPane.showMessageDialog(null,
//                              "Клиент подключился",
//                              "Клиент",
//                              JOptionPane.WARNING_MESSAGE);

                }
            };

            Thread myThread = new Thread(runnable);
            myThread.start();


        }

        catch(Exception ex)
        {
            excep_msg.setText(ex.getMessage());
        }

    }

    private void class_listActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void exit_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exit_btnActionPerformed
        System.exit(0);
    }

    private void save_bntActionPerformed(java.awt.event.ActionEvent evt) {

        ImportDataInXML xmlFile = new ImportDataInXML();

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

