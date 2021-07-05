package main.java;

import main.java.maps.Maps;
import java.awt.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.*;
import javax.swing.GroupLayout;

public class MonitoringFrame extends JFrame {

    CreateServer[] createsev = new CreateServer[10];
    static ExecutorService executeIt = Executors.newFixedThreadPool(5);

    public MonitoringFrame() {

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

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                setVisible(true);
                initComponents();
            }
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
            Main.setName("\u0413\u043b\u0430\u0432\u043d\u043e\u0435");

            ImportData importData = new ImportData(createsev,executeIt);
            Main.addTab("\u041c\u043e\u0434\u0443\u043b\u044c \u043e\u0446\u0438\u0444\u0440\u043e\u0432\u043a\u0438 \u0442\u0440\u0430\u0441\u0441\u044b", importData);

            CurrentlyData currentlyData = new CurrentlyData();
            Main.addTab("\u041f\u043e\u043b\u043e\u0436\u0435\u043d\u0438\u0435 \u0442\u0440\u0430\u043d\u0441\u043f\u043e\u0440\u0442\u043e\u0432", currentlyData);

            Maps maps = new Maps();

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
    }

    private JTabbedPane Main;
}

