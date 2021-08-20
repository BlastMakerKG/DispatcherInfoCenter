package Frame;

import ImportPanel.CurrentlyData;
import ImportPanel.ImportData;
import Server.CreateServer;
import Server.DB.service.DataService;
import Server.DB.service.DataServiceImpl;

import java.awt.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.GroupLayout;

public class MonitoringFrame extends JFrame {

    CreateServer[] createsev = new CreateServer[10];
    static ExecutorService executeIt = Executors.newFixedThreadPool(5);

    public MonitoringFrame() {

        DataService dataService = new DataServiceImpl();
        dataService.createDataTable();
        dataService.saveData("gfdgdgd");

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(MonitoringFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        EventQueue.invokeLater(() -> {
            setVisible(true);
            initComponents();
        });
    }

    private void initComponents() {
        Main = new JTabbedPane();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container contentPane = getContentPane();

        //======== Main ========
        {
            Main.setBackground(Color.white);
            Main.setName("Главное");

            ImportData importData = new ImportData(createsev,executeIt);
            Main.addTab("Модуль оцифровки трассы", importData);

            CurrentlyData currentlyData = new CurrentlyData();
            Main.addTab("Положение транспортов", currentlyData);

//            Maps maps = new Maps();
//            Main.addTab("\u041e\u0442\u043e\u0431\u0440\u0430\u0436\u0435\u043d\u0438\u0435", maps);
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                .addComponent(Main, GroupLayout.PREFERRED_SIZE, 810, GroupLayout.PREFERRED_SIZE)
                                .addGap(10))
        );
        contentPaneLayout.setVerticalGroup(
                contentPaneLayout.createParallelGroup()
                        .addComponent(Main)
        );
        pack();
        setLocationRelativeTo(getOwner());
    }

    private JTabbedPane Main;
}

