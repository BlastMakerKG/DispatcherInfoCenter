package ImportPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CurrentlyData extends JPanel {

    private JScrollPane jScrollPane4;
    private JTable location_table2;
    private JLabel jLabel2;
    private JScrollPane jScrollPane1;
    private JList<String> connect_avto_list;
    private JLabel status_msg1;
    private JLabel excep_msg1;

    public CurrentlyData() {
        jScrollPane4 = new JScrollPane();
        location_table2 = new JTable();
        jLabel2 = new JLabel();
        jScrollPane1 = new JScrollPane();
        connect_avto_list = new JList<>();
        status_msg1 = new JLabel();
        excep_msg1 = new JLabel();

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

            GroupLayout jPanel2Layout = new GroupLayout(this);
            setLayout(jPanel2Layout);
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
    }
}
