package kg.dispatcher.info.centre.prices.UI;

import kg.dispatcher.info.centre.prices.DB.service.DataService;
import kg.dispatcher.info.centre.prices.maps.lwjgl.CreateLWJGL;
import kg.dispatcher.info.centre.prices.UI.externalMaps.Maps;
import kg.dispatcher.info.centre.prices.planning.reportDocument.OriginalData;
import kg.dispatcher.info.centre.prices.server.CreateServer;
import kg.dispatcher.info.centre.prices.server.UDPServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class Frame extends JFrame {

    private final JTabbedPane main = new JTabbedPane();
    private static ExecutorService executeIt = Executors.newFixedThreadPool(2);
    private static List<CreateServer> createsev = new ArrayList<>();
    private static CreateLWJGL lwjgl = new CreateLWJGL();
    @Autowired
    private DataService dataService;

    @Autowired
    OriginalData currentlyData;


    public void start() {

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(this.getName()).log(Level.SEVERE, null, ex);
        }

        EventQueue.invokeLater(() -> {
            setVisible(true);
            initComponents();
        });
    }

    private void initComponents() {
        CurrentlyData currentlyData = new CurrentlyData();
        ImportData importData = new ImportData(createsev,executeIt, lwjgl, currentlyData, dataService);
        PlanningPanel planning = new PlanningPanel(this.currentlyData);


        Maps panel = new Maps(); // todo
        //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container contentPane = getContentPane();

        //======== Main ========
        {
            main.setBackground(Color.white);
            main.setName("Главное");

            main.addTab("Модуль оцифровки трассы", importData);
            main.addTab("Положение транспортов", currentlyData);

            main.addTab("Анимация работы", panel);

            main.addTab("Отчеты", planning);
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
