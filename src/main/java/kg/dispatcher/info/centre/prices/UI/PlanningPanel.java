package kg.dispatcher.info.centre.prices.UI;

import kg.dispatcher.info.centre.prices.planning.reportDocument.OriginalData;
import kg.dispatcher.info.centre.prices.planning.reportDocument.Output;
import kg.dispatcher.info.centre.prices.planning.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    public PlanningPanel( OriginalData currentData){
        this.currentData = currentData;

        setSize(800,600);
        setLayout(null);

        panelDataWithPrice = new JPanel();
        panelDataWithPrice.setLayout(null);
        panelDataWithPrice.setSize(800,550);
        panelDataWithPrice.setLocation(0,50);
        panelDataWithPrice.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        tableDataWithPrice = new JTable();
        tableDataWithPrice.setModel(new DefaultTableModel(
                new Object[][] {},
                new String[] {"Дни", "Растояние", "Топливо", "Вес факт", "Вес норм", "Процент", "ТС"}) {
            Class<?>[] columnTypes = new Class<?>[] {Integer.class, Double.class, Integer.class,  Double.class, Double.class,Double.class, JButton.class};
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }
        });
        tableDataWithPrice.setSize(800,500);
        tableDataWithPrice.getColumn("ТС").setCellRenderer(new ButtonRenderer());
        tableDataWithPrice.getColumn("ТС").setCellEditor(new ButtonEditor(new JCheckBox()));

        JScrollPane pane = new JScrollPane(tableDataWithPrice);
        GroupLayout contentPaneLayoutForPanelDateWithPrice = new GroupLayout(panelDataWithPrice);
        panelDataWithPrice.setLayout(contentPaneLayoutForPanelDateWithPrice);
        contentPaneLayoutForPanelDateWithPrice.setHorizontalGroup(
                contentPaneLayoutForPanelDateWithPrice.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayoutForPanelDateWithPrice.createSequentialGroup()
                                .addComponent(pane, GroupLayout.PREFERRED_SIZE, 800, GroupLayout.PREFERRED_SIZE)
                                .addGap(10)));
        contentPaneLayoutForPanelDateWithPrice.setVerticalGroup(
                contentPaneLayoutForPanelDateWithPrice.createParallelGroup()
                        .addComponent(pane)
        );



        panelOriginalData = new JPanel();
        panelOriginalData.setLayout(null);
        panelOriginalData.setSize(800,550);
        panelOriginalData.setLocation(0,50);

        tableOriginalData = new JTable();
        tableOriginalData.setModel(new DefaultTableModel(
                new Object[][] {},
                new String[] {"Дни", "Растояние", "Топливо", "Вес факт", "Вес норм", "Процент", "ТС"}) {
            Class<?>[] columnTypes = new Class<?>[] {Integer.class, Double.class, Integer.class,  Double.class, Double.class,Double.class, JButton.class};
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }
        });
        tableOriginalData.getColumn("ТС").setCellRenderer(new ButtonRenderer());
        tableOriginalData.getColumn("ТС").setCellEditor(new ButtonEditor(new JCheckBox()));

        originalData();
        JScrollPane pane2 = new JScrollPane(tableOriginalData);



        GroupLayout contentPaneLayoutForPanelOriginalData = new GroupLayout(panelOriginalData);
        panelOriginalData.setLayout(contentPaneLayoutForPanelOriginalData);
        contentPaneLayoutForPanelOriginalData.setHorizontalGroup(
                contentPaneLayoutForPanelOriginalData.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayoutForPanelOriginalData.createSequentialGroup()
                                .addComponent(pane2, GroupLayout.PREFERRED_SIZE, 800, GroupLayout.PREFERRED_SIZE)
                                .addGap(10)));
        contentPaneLayoutForPanelOriginalData.setVerticalGroup(
                contentPaneLayoutForPanelOriginalData.createParallelGroup()
                        .addComponent(pane2)
        );


        tabbedPane.addTab("Себестоимость",panelDataWithPrice);
        tabbedPane.addTab("Затраты", panelOriginalData);

        GroupLayout contentPaneLayout = new GroupLayout(this);
        setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                .addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 800, GroupLayout.PREFERRED_SIZE)
                                .addGap(10))
        );
        contentPaneLayout.setVerticalGroup(
                contentPaneLayout.createParallelGroup()
                        .addComponent(tabbedPane)
        );
    }


    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }
        public java.awt.Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "Modify" : value.toString());
            return this;
        }
    }
    class ButtonEditor extends DefaultCellEditor {
        private String label;

        public ButtonEditor(JCheckBox checkBox)
        {
            super(checkBox);
        }

        private JButton button;
        public java.awt.Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int column)
        {
            label = (value == null) ? "Modify" : value.toString();
            table.getModel().getValueAt(0, 6);
            button = new JButton();
            button.setText(table.getValueAt(table.getSelectedRowCount(), 0).toString());
            button.addActionListener(e -> newFrame((Integer) table.getValueAt(table.getSelectedRowCount(), 0), month.get(table.getValueAt(table.getSelectedRowCount(), 0)).getExs()));
//            button.setText(label);
            return button;
        }
        public Object getCellEditorValue()
        {
            return new String(label);
        }
    }

    HashMap<Integer, Month> month;
    private void originalData(){

        month = currentData.month("");

        DefaultTableModel tableModel = (DefaultTableModel) tableOriginalData.getModel();

        for(Integer i : month.keySet()){

            Object[] row = {i, month.get(i).getDistance(), month.get(i).getGas(),month.get(i).getQuantityReise(), month.get(i).getWeightFact(), month.get(i).getWeightNorm(), (month.get(i).getWeightFact() - month.get(i).getWeightNorm())/month.get(i).getWeightNorm() * 100, "1"};
//            JButton button = (JButton) row[row.length-1];
//            button.setText(i.toString());
//            button.addActionListener(e -> newFrame(i, month.get(i).getExs()));


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
                new String[] {"название", }) {
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
