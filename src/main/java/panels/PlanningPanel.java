package panels;

import planning.reportDocument.OriginalData;
import planning.reportDocument.Output;
import planning.model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

public class PlanningPanel extends JPanel {

    private OriginalData currentData;
    private Output dataWithPrice;

    private JTable tableOriginalData;
    private JTable tableDataWithPrice;

    private JPanel panelOriginalData;
    private JPanel panelDataWithPrice;
    private JTabbedPane tabbedPane = new JTabbedPane();

    public PlanningPanel(){
        setSize(800,600);
        setLayout(null);

        panelDataWithPrice = new JPanel();
        panelDataWithPrice.setLayout(null);
        panelDataWithPrice.setSize(800,550);
        panelDataWithPrice.setLocation(0,50);

        tableDataWithPrice = new JTable();
        tableDataWithPrice.setSize(800,500);
        tableDataWithPrice.setModel(new DefaultTableModel(
                new Object[][] {},
                new String[] {"Дни", "Вес факт", "Вес норм", "Затртаты", "Себестоимость", "TC"}) {
            Class<?>[] columnTypes = new Class<?>[] {Integer.class, Double.class, Double.class, Double.class, Double.class, JButton.class};
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }
        });


        JScrollPane pane = new JScrollPane(tableDataWithPrice);


        panelOriginalData = new JPanel();
        panelOriginalData.setLayout(null);
        panelOriginalData.setSize(800,550);
        panelOriginalData.setLocation(0,50);

        tableOriginalData = new JTable();
        tableOriginalData.setModel(new DefaultTableModel(
                new Object[][] {},
                new String[] {"Дни", "Растояние", "Топливо", "Вес факт", "Вес норм", "Процент", "ТС"}) {
            Class<?>[] columnTypes = new Class<?>[] {Integer.class, Double.class, Integer.class,  Double.class, Double.class, Double.class, JButton.class};
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }
        });
//        originalData();
        JScrollPane pane2 = new JScrollPane(tableOriginalData);

        panelDataWithPrice.add(pane);
        panelOriginalData.add(pane2);

        tabbedPane.addTab("DataWithPrice",panelDataWithPrice);
        tabbedPane.addTab("OriginalData", panelOriginalData);

        GroupLayout contentPaneLayout = new GroupLayout(this);
        setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                .addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 810, GroupLayout.PREFERRED_SIZE)
                                .addGap(10))
        );
        contentPaneLayout.setVerticalGroup(
                contentPaneLayout.createParallelGroup()
                        .addComponent(tabbedPane)
        );
    }


    private void originalData(){

        currentData = new OriginalData();

        HashMap<Integer, planning.model.Month> month = currentData.month("");

        DefaultTableModel tableModel = (DefaultTableModel) tableOriginalData.getModel();

        for(Integer i : month.keySet()){
            Object[] row = {i, month.get(i).getDistance(), month.get(i).getGas(),month.get(i).getQuantityReise(), month.get(i).getWeightFact(), month.get(i).getWeightNorm(), (month.get(i).getWeightFact() - month.get(i).getWeightNorm())/month.get(i).getWeightNorm() * 100, new JButton()};

            JButton button =(JButton) row[row.length-1];
            button.setText(i.toString());
            button.addActionListener(e -> newFrame(i, month.get(i).getExs()));

            tableModel.addRow(row);
        }

    }


    private void newFrame(int i, List<String> exs){
        JFrame frame = new JFrame();

        frame.setSize(600,600);
        frame.setLocation(1980/3, 1080/3);
        frame.setName("excovator in " + i + "days");

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setSize(600,600);

        JTabbedPane tabbedPane = new JTabbedPane();

        JTable tableTrash = new JTable();
        tableTrash.setSize(600,500);
        tableTrash.setModel(new DefaultTableModel(
                new Object[][] {},
                new String[] {"нахвание", }) {
            Class<?>[] columnTypes = new Class<?>[] {Integer.class, Double.class, Double.class, Double.class, Double.class, JButton.class};
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }
        });


        DefaultTableModel tableModel = (DefaultTableModel) tableTrash.getModel();

        for (String s: exs) {
            tableModel.addRow(s.split("\n"));
        }

        JScrollPane pane = new JScrollPane(tableTrash);


        tabbedPane.addTab("Trash",pane);

        frame.add(panel);
    }
}
