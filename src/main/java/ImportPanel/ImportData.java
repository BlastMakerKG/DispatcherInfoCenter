package ImportPanel;

import Server.CreateServer;
import XmlFile.ImportDataInXML;
import maps.lwjgl.CreateLWJGL;
import Frame.MonitoringFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.net.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ImportData extends JPanel {

//    private Button save_bnt;
//    private JButton exit_btn;
//    private JButton create_btn;
//    private JButton server_btn;
//    private JLabel jLabel1;
    private JLabel status_msg;
    private JLabel excep_msg;
    private JButton maps;
    private java.awt.List class_list;
    private JScrollPane jScrollPane2;

    private CreateServer[] createsev;
    private ExecutorService executeIt;
    private String predString;
    private DefaultTableModel modeltab;
    private JTable location_table;


    public ImportData(CreateServer[] createsev, ExecutorService executeIt ) {

        this.setLayout(null);
        this.setSize(800,900);
        menu();
        pane();

        this.createsev = createsev;
        this.executeIt = executeIt;

    }

    private JPanel menu;
    private JButton start = null;
    private JButton stop = null;
    private JButton help = null;
    private JButton save = null;
    private JComboBox<String> box;

    private void menu(){
        menu = new JPanel();
        menu.setSize(this.getWidth(), 30);
        menu.setLayout(null);
        menu.setLocation(5,5);
        menu.setVisible(true);
        menu.setBorder(BorderFactory.createLineBorder(Color.gray, 2));


        try {
            start = new JButton(new ImageIcon(ImageIO.read(new File("F:\\Krsu\\DispatcherInfoCenter\\src\\main\\resources\\start.png"))));
            stop = new JButton(new ImageIcon(ImageIO.read(new File("F:\\Krsu\\DispatcherInfoCenter\\src\\main\\resources\\stop.png"))));
            help = new JButton(new ImageIcon(ImageIO.read(new File("F:\\Krsu\\DispatcherInfoCenter\\src\\main\\resources\\help.png"))));
            save = new JButton(new ImageIcon(ImageIO.read(new File("F:\\Krsu\\DispatcherInfoCenter\\src\\main\\resources\\save.png"))));
            maps = new JButton(new ImageIcon(ImageIO.read(new File("F:\\Krsu\\DispatcherInfoCenter\\src\\main\\resources\\maps.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }

        start.setLocation(10,5);
        start.setSize(20,20);
        start.setBorder(BorderFactory.createLineBorder(Color.gray, 2));
        start.addActionListener(e -> server_btnActionPerformed(e));
        menu.add(start);

        stop.setSize(20,20);
        stop.setLocation(40,5);
        stop.setBorder(BorderFactory.createLineBorder(Color.gray, 2));
        stop.addActionListener(e -> exit_btnActionPerformed(e));
        menu.add(stop);

        help.setSize(20,20);
        help.setLocation(70,5);
        help.setBorder(BorderFactory.createLineBorder(Color.gray, 2));
        help.addActionListener(e -> create_btnActionPerformed(e));
        menu.add(help);
        add(menu);

        save.setSize(20,20);
        save.setLocation(100,5);
        save.setBorder(BorderFactory.createLineBorder(Color.gray, 2));
        save.addActionListener(e -> save_bntActionPerformed(e));
        menu.add(save);

        status_msg = new JLabel("Status");
        status_msg.setSize(50,20);
        status_msg.setLocation(130,5);
        status_msg.setBorder(BorderFactory.createLineBorder(Color.gray, 2));
        menu.add(status_msg);

        box = new JComboBox<String>();
        box.setSize(100,20);
        if(createsev != null)
            for(CreateServer server : createsev){
                String[] str = server.getData().split(",");
                box.addItem(str[1]);
            }
        box.setLocation(190,5);
        menu.add(box);



        maps.addActionListener(e -> maps());
        maps.setSize(20,20);
        maps.setLocation(300,5);
        maps.setBorder(BorderFactory.createLineBorder(Color.gray, 2));
        menu.add(maps);


        add(menu);
    }

    private JPanel main;

    private void pane(){
        main = new JPanel();
        main.setVisible(true);
        main.setLayout(null);
        main.setLocation(5, 40);
        main.setSize(this.getWidth(), this.getHeight()-40-5);
        main.setBorder(BorderFactory.createLineBorder(Color.gray, 2));

        class_list = new java.awt.List();
        jScrollPane2 = new JScrollPane();
        location_table = new JTable();
        modeltab = (DefaultTableModel) location_table.getModel();

//        class_list.addActionListener(e -> class_listActionPerformed(e));

        jScrollPane2.setSize(main.getWidth(), main.getHeight());
//        location_table.setSize(main.getWidth(), main.getHeight());


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

        main.add(jScrollPane2);
        add(main);

    }

    private void maps(){
        Runnable run = () -> {
            CreateLWJGL maps = new CreateLWJGL();
        };

        Thread thread = new Thread(run);
        thread.start();
    }


    private List<String[]> data = new ArrayList<>();

    public List<String[]> getData(){
        return data;
    }

    private void create_btnActionPerformed(ActionEvent evt) {
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

