package ImportPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CurrentlyData extends JPanel {

    private JScrollPane jScrollPane4;
    private JTable location_table2;

    public CurrentlyData() {
        this.setSize(800,600);
        jScrollPane4 = new JScrollPane();
        location_table2 = new JTable();
        modeltab = (DefaultTableModel) location_table2.getModel();

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

    private DefaultTableModel modeltab;
    private boolean flag1 = true;

    public void setLocation_table2(int rowCount, Object[] object, String str){
        this.modeltab = (DefaultTableModel) location_table2.getModel();

        if(flag1) {
            modeltab.insertRow(rowCount, object);
            flag1 = false;
        }else{
            if(object[0].equals( modeltab.getValueAt(rowCount, 0))){
                for (int i = 0; i < object.length; i++) {
                modeltab.setValueAt(object[i], rowCount, i);
                }
            }else{
                modeltab.insertRow(rowCount, object);
            }
        }
    }
}
