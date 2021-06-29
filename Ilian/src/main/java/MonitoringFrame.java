import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;
import javax.swing.table.*;
import org.jxmapviewer.*;
/*
 * Created by JFormDesigner on Thu Jun 24 16:17:29 ALMT 2021
 */



/**
 * @author unknown
 */
public class MonitoringFrame extends JFrame {
    public MonitoringFrame() {
        initComponents();
    }

    private void save_bntActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void exit_btnActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void create_btnActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void server_btnActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void class_listActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
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
        kit = new JXMapKit();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container contentPane = getContentPane();

        //======== Main ========
        {
            Main.setBackground(Color.white);
            Main.setName("\u0413\u043b\u0430\u0432\u043d\u043e\u0435");

            //======== jPanel1 ========
            {
                jPanel1.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border. EmptyBorder(
                0, 0, 0, 0) , "JF\u006frmDes\u0069gner \u0045valua\u0074ion", javax. swing. border. TitledBorder. CENTER, javax. swing. border. TitledBorder
                . BOTTOM, new java .awt .Font ("D\u0069alog" ,java .awt .Font .BOLD ,12 ), java. awt. Color.
                red) ,jPanel1. getBorder( )) ); jPanel1. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .
                beans .PropertyChangeEvent e) {if ("\u0062order" .equals (e .getPropertyName () )) throw new RuntimeException( ); }} );

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

                GroupLayout mapsLayout = new GroupLayout(maps);
                maps.setLayout(mapsLayout);
                mapsLayout.setHorizontalGroup(
                    mapsLayout.createParallelGroup()
                        .addGroup(mapsLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(kit, GroupLayout.DEFAULT_SIZE, 1141, Short.MAX_VALUE)
                            .addContainerGap())
                );
                mapsLayout.setVerticalGroup(
                    mapsLayout.createParallelGroup()
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
                .addComponent(Main, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 1153, GroupLayout.PREFERRED_SIZE)
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addComponent(Main)
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
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
    private JXMapKit kit;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
