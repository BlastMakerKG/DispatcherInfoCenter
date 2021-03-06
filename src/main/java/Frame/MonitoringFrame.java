package Frame;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import panels.CurrentlyData;
import panels.ImportData;
import panels.PlanningPanel;
import panels.externalMaps.*;
import DB.service.DataService;
import DB.service.DataServiceImpl;
import DB.model.Data;
import DB.util.*;
import server.*;
import maps.lwjgl.*;

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
    private static List<UDPServer> createsev = new ArrayList<>();
    private static CreateLWJGL lwjgl = new CreateLWJGL();
    private static DataService dataService = new DataServiceImpl();


    public MonitoringFrame() {

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
        PlanningPanel planning = new PlanningPanel();


        Maps panel = new Maps(); // todo
        //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container contentPane = getContentPane();

        //======== Main ========
        {
            main.setBackground(Color.white);
            main.setName("??????????????");

            main.addTab("???????????? ?????????????????? ????????????", importData);
            main.addTab("?????????????????? ??????????????????????", currentlyData);

            main.addTab("External maps", panel);

            main.addTab("Planning", planning);
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

