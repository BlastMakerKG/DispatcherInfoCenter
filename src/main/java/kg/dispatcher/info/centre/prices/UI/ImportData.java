package kg.dispatcher.info.centre.prices.UI;

import com.teamdev.jxbrowser.browser.Browser;
import com.teamdev.jxbrowser.engine.Engine;
import com.teamdev.jxbrowser.engine.EngineOptions;
import com.teamdev.jxbrowser.engine.RenderingMode;
import com.teamdev.jxbrowser.frame.Frame;
import com.teamdev.jxbrowser.js.JsArray;
import com.teamdev.jxbrowser.js.JsFunction;
import com.teamdev.jxbrowser.js.JsObject;
import com.teamdev.jxbrowser.js.internal.JsArrayBufferImpl;
import com.teamdev.jxbrowser.js.internal.JsArrayImpl;
import com.teamdev.jxbrowser.js.internal.JsContext;
import com.teamdev.jxbrowser.view.swing.BrowserView;
import kg.dispatcher.info.centre.prices.DB.service.DataService;
import kg.dispatcher.info.centre.prices.server.*;
import kg.dispatcher.info.centre.prices.maps.lwjgl.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.teamdev.jxbrowser.engine.RenderingMode.HARDWARE_ACCELERATED;
import static com.teamdev.jxbrowser.engine.RenderingMode.OFF_SCREEN;

public class ImportData extends JPanel {

    private JLabel status_msg;
    private JLabel excep_msg;
    private JButton maps;
    private java.awt.List class_list;
    private JScrollPane jScrollPane2;

    private List<CreateServer> createsev;
    private ExecutorService executeIt;
    private String predString;
    private DefaultTableModel modeltab;
    private JTable location_table;
    private CurrentlyData currentlyData;
    private DataService dataService;
    private List<Objects> venichles;

    public ImportData(){}


    public ImportData(List<CreateServer> createsev, ExecutorService executeIt, CreateLWJGL lwjgl, CurrentlyData currentlyData, DataService dataService) {

        this.setLayout(null);
        this.setSize(800,600);
        menu();
        pane();

        this.dataService = dataService;

        this.createsev = createsev;
        this.executeIt = executeIt;
        excep_msg = new JLabel();
        this.lwjgl = lwjgl;
        this.currentlyData = currentlyData;

        venichles = new ArrayList<>();

    }

    private JPanel menu;
    private JButton start;
    private JButton stop;
    private JButton help;
    private JButton save;
    private JLabel statusShow;
    private JComboBox<String> box;
    private JTable location_table2;
    private JPanel main;
    private CreateLWJGL lwjgl;

    private boolean flag = false;
    private List<String[]> data = new ArrayList<>();

    private Thread positionThread;
    private Thread connectionThread;

    private void menu(){
        menu = new JPanel();
        location_table2 = new JTable();
        menu.setSize(this.getWidth(), 30);
        menu.setLayout(null);
        menu.setLocation(5,5);
        menu.setVisible(true);
        menu.setBorder(BorderFactory.createLineBorder(Color.gray, 2));


        start = new JButton(uploadIcon("start"));
        stop = new JButton(uploadIcon("stop"));
        help = new JButton(uploadIcon("help"));
        save = new JButton(uploadIcon("save"));
        maps = new JButton(uploadIcon("maps"));



        start.setLocation(10,5);
        start.setSize(20,20);
        start.setBorder(BorderFactory.createLineBorder(Color.gray, 2));
        start.addActionListener(this::server_btnActionPerformed);
        menu.add(start);

        stop.setSize(20,20);
        stop.setLocation(40,5);
        stop.setBorder(BorderFactory.createLineBorder(Color.gray, 2));
        stop.addActionListener(this::stop_btnActionPerformed);
        menu.add(stop);

        help.setSize(20,20);
        help.setLocation(70,5);
        help.setBorder(BorderFactory.createLineBorder(Color.gray, 2));
//        help.addActionListener(this::create_btnActionPerformed);
        menu.add(help);
        add(menu);

        save.setSize(20,20);
        save.setLocation(100,5);
        save.setBorder(BorderFactory.createLineBorder(Color.gray, 2));
        save.addActionListener(this::save_bntActionPerformed);
        menu.add(save);

        status_msg = new JLabel("Status:");
        status_msg.setSize(70,20);
        status_msg.setLocation(130,5);
        status_msg.setBorder(BorderFactory.createLineBorder(Color.gray, 2));
        menu.add(status_msg);

        statusShow = new JLabel(uploadIcon("no"));
        statusShow.setSize(20,20);
        statusShow.setLocation(175,5);
        menu.add(statusShow);

        box = new JComboBox<>();
        box.setSize(100,20);
        if(createsev != null)
            for(CreateServer server : createsev){
                String[] str = server.getData().split(",");
                box.addItem(str[1]);
            }
        box.setLocation(220,5);
        menu.add(box);



        maps.addActionListener(e -> maps());
        maps.setSize(20,20);
        maps.setLocation(330,5);
        maps.setBorder(BorderFactory.createLineBorder(Color.gray, 2));
        menu.add(maps);


        add(menu);
    }

    private ImageIcon uploadIcon(String filename){
        try {
            return new ImageIcon(ImageIO.read(new File("src\\main\\resources\\"+filename+".png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void pane(){
        main = new JPanel();
        main.setVisible(true);
        main.setLayout(null);
        main.setLocation(5, 40);
        main.setSize(this.getWidth(), this.getHeight()-20);
//        main.setBorder(BorderFactory.createLineBorder(Color.gray, 2));

        class_list = new java.awt.List();
        jScrollPane2 = new JScrollPane();
        location_table = new JTable();
        modeltab = (DefaultTableModel) location_table.getModel();

        jScrollPane2.setSize(main.getWidth(), main.getHeight()-5);

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
//        lwjgl= new CreateLWJGL();
//        Runnable run = () -> lwjgl.start();
//
//        Thread thread = new Thread(run);
//        thread.start();



        // Initialize Chromium.
        Engine engine = Engine.newInstance(EngineOptions.newBuilder(HARDWARE_ACCELERATED)
                .licenseKey("1BNDHFSC1G31O3C3IGXF949Z05HCSJ4MZELSDQ7IEQ19I348MUU7TUVJTOHP2LVFVVZJMA")
                .build());

// Create a Browser instance.
        Browser browser = engine.newBrowser();

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("JxBrowser AWT/Swing");
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    // Shutdown Chromium and release allocated resources.
                    engine.close();
                }
            });
            // Create and embed Swing BrowserView component to display web content.
            frame.add(BrowserView.newInstance(browser));
            frame.setSize(1280, 800);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            // Load the required web page.
            browser.navigation().loadUrl(new File("F:\\prices\\prices\\src\\main\\resources\\map\\map.html").getAbsolutePath());
//            browser.settings().enableJavaScript();
        });


    }

//    private void create_btnActionPerformed(ActionEvent evt) {
//        Runnable run = () -> {
//            int count =1;
//            while(flag) {
//                try{
//                    for (CreateServer createServer : createsev) {
//                        String str = createServer.getData();
//                        String delimeter = ",";
//                        String[] splitStr = str.split(delimeter);
//                        CreateLWJGL.game.converterToXY(splitStr[0], new double[]{Double.parseDouble(splitStr[2]), Double.parseDouble(splitStr[3])}, Math.round(Float.parseFloat(splitStr[4])));
//                        CreateLWJGL.game.setVenichles(venichles);
//                        currentlyData.setLocation_table2(modeltab.getRowCount(),
//                                new Object[]{Integer.parseInt(
//                                        splitStr[0]) + " " + count++,
//                                        splitStr[1],
//                                        Double.parseDouble(splitStr[2]),
//                                        Double.parseDouble(splitStr[3]),
//                                        Double.parseDouble(splitStr[4]),
//                                        Double.parseDouble(splitStr[5]),
//                                        Double.parseDouble(splitStr[6])
//                                }, str);

//                        if(predString == null) {
//                            currentlyData.setLocation_table2(modeltab.getRowCount(),
//                                    new Object[]{Integer.parseInt(
//                                            splitStr[0])+" "+count++,
//                                            splitStr[1],
//                                            Double.parseDouble(splitStr[2]),
//                                            Double.parseDouble(splitStr[3]),
//                                            Double.parseDouble(splitStr[4]),
//                                            Double.parseDouble(splitStr[5]),
//                                            Double.parseDouble(splitStr[6])
//                                    });
//                            modeltab.insertRow(modeltab.getRowCount(),
//                                    new Object[]{Integer.parseInt(
//                                            splitStr[0])+" "+count++,
//                                            splitStr[1],
//                                            Double.parseDouble(splitStr[2]),
//                                            Double.parseDouble(splitStr[3]),
//                                            Double.parseDouble(splitStr[4]),
//                                            Double.parseDouble(splitStr[5]),
//                                            Double.parseDouble(splitStr[6])
//                                    }
//                            );
//
//                            predString = str;
//                        }
//                        else{
//                            if(str != predString){
//                                currentlyData.setLocation_table2(modeltab.getRowCount(),
//                                        new Object[]{
//                                                Integer.parseInt(splitStr[0])+" "+
//                                                        count++,
//                                                splitStr[1],
//                                                Double.parseDouble(splitStr[2]),
//                                                Double.parseDouble(splitStr[3]),
//                                                Double.parseDouble(splitStr[4]),
//                                                Double.parseDouble(splitStr[5]),
//                                                Double.parseDouble(splitStr[6])
//                                        });
//                                modeltab.insertRow(modeltab.getRowCount(),
//                                        new Object[]{
//                                                Integer.parseInt(splitStr[0])+" "+
//                                                        count++,
//                                                splitStr[1],
//                                                Double.parseDouble(splitStr[2]),
//                                                Double.parseDouble(splitStr[3]),
//                                                Double.parseDouble(splitStr[4]),
//                                                Double.parseDouble(splitStr[5]),
//                                                Double.parseDouble(splitStr[6])
//                                        }
//                                );
//                                predString = str;
//                            }
//                        }

                        // currentlyData.setLocation_table2(modeltab);

//                    }
//                }
//                catch(Exception ex) {
//                    ex.printStackTrace();
//                }
//
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException ex) {
//                    Logger.getLogger(MonitoringFrame.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//
//        };
//        positionThread = new Thread(run);
//        positionThread.start();
//
//    }
    private ServerSocket serverSocket;
    private void server_btnActionPerformed(ActionEvent evt) {
        statusShow.setIcon(uploadIcon("ok"));
        flag =true;

        try{
            serverSocket = new ServerSocket(24500);
            System.out.println("Сервер создан ждем подключения");
            JOptionPane.showMessageDialog(null,
                    "Сервер создан ждем подключения",
                    "Server",
                    JOptionPane.WARNING_MESSAGE);

            Runnable runnable = () -> {
//                int i =0;
                while(!serverSocket.isClosed()) {
                    Socket clientSocket;
                    try {
                        clientSocket = serverSocket.accept();
                        if (clientSocket.isConnected()) {
                            createsev.add(new CreateServer(clientSocket, class_list, location_table, excep_msg, data, dataService, currentlyData));
                            createsev.add(new CreateServer(clientSocket, class_list, location_table, excep_msg, data, dataService, currentlyData));
                            executeIt.execute(createsev.get(0));
                            executeIt.execute(createsev.get(1));
//                        venichles.add(new Tripper( (float)((Math.random()- 300)+ 100), (float) ((Math.random()- 300)+ 100), "tripper.png"));

//                        create_btnActionPerformed(evt);

//                        createsev[i] = new CreateServer(clientSocket,class_list, location_table2,excep_msg,i);
//                        executeIt.execute(createsev[i]);

//                            i++;
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(ImportData.class.getName()).log(Level.SEVERE, null, ex);
                    }

                   JOptionPane.showMessageDialog(null,
                              "Клиент подключился",
                              "Клиент",
                              JOptionPane.WARNING_MESSAGE);

                }
            };

            connectionThread = new Thread(runnable);
            connectionThread.start();


        } catch(Exception ex) {
            excep_msg.setText(ex.getMessage());
        }

    }

    private void stop_btnActionPerformed(ActionEvent evt) {
        flag = false;
        statusShow.setIcon(uploadIcon("no"));

        try {
            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(connectionThread != null){
            connectionThread.interrupt();
        }

        JOptionPane.showMessageDialog(null,
                "Сервер остановлен",
                "Server",
                JOptionPane.WARNING_MESSAGE);
    }

    private void save_bntActionPerformed(ActionEvent evt) {

        server.ExportData.expoertEithAnotherWay(location_table, "src\\main\\resources\\exportfile\\file.csv");

//        xmlFile.save(data);

    }
}

