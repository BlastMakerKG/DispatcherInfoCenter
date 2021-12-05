package Frame;

import ImportPanel.CurrentlyData;
import ImportPanel.ImportData;
import Server.CreateServer;
import Server.DB.service.DataService;
import Server.DB.service.DataServiceImpl;
import maps.lwjgl.CreateLWJGL;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.GroupLayout;

public class MonitoringFrame extends JFrame {

    private final JTabbedPane main = new JTabbedPane();
    private static ExecutorService executeIt = Executors.newFixedThreadPool(2);
    private static List<CreateServer> createsev = new ArrayList<>();
    private static CreateLWJGL lwjgl = new CreateLWJGL();
    private static DataService dataService = new DataServiceImpl();


    public MonitoringFrame() {

        dataService.createDataTable();

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
        CurrentlyData currentlyData = new CurrentlyData();
        ImportData importData = new ImportData(createsev,executeIt, lwjgl, currentlyData, dataService);
        //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container contentPane = getContentPane();

        //======== Main ========
        {
            main.setBackground(Color.white);
            main.setName("Главное");

            main.addTab("Модуль оцифровки трассы", importData);
            main.addTab("Положение транспортов", currentlyData);

        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                .addComponent(main, GroupLayout.PREFERRED_SIZE, 810, GroupLayout.PREFERRED_SIZE)
                                .addGap(10))
        );
        contentPaneLayout.setVerticalGroup(
                contentPaneLayout.createParallelGroup()
                        .addComponent(main)
        );
        pack();
        setLocationRelativeTo(getOwner());
    }


}

