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
        this.setSize(800,600);
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
                        new Object[][] {},
                        new String[] {"ID", "Дата", "Широта", "Долгота", "Абсолютная высота", "Расстояние", "Скорость"}
                ) {
                    Class<?>[] columnTypes = new Class<?>[] {Integer.class, String.class, Double.class, Double.class, Double.class, Double.class, Double.class};
                    @Override
                    public Class<?> getColumnClass(int columnIndex) {
                        return columnTypes[columnIndex];
                    }
                });
                jScrollPane4.setViewportView(location_table2);
            }

            GroupLayout jPanel2Layout = new GroupLayout(this);
            setLayout(jPanel2Layout);
            jPanel2Layout.setHorizontalGroup(
                    jPanel2Layout.createParallelGroup()
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jScrollPane4, GroupLayout.PREFERRED_SIZE, 800, GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(30, Short.MAX_VALUE))
            );
            jPanel2Layout.setVerticalGroup(
                    jPanel2Layout.createParallelGroup()
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jScrollPane4, GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
                                    .addGap(30,30,30))
            );
        }
    }
}
