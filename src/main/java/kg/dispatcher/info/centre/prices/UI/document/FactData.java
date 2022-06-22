package kg.dispatcher.info.centre.prices.UI.document;

import kg.dispatcher.info.centre.prices.UI.Document;
import kg.dispatcher.info.centre.prices.planning.model.Month;
import kg.dispatcher.info.centre.prices.planning.reportDocument.OriginalData;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.HashMap;
import java.util.List;

public class FactData extends JPanel {
    private OriginalData currentData;
    private JTable tableOriginalData;

    public FactData(OriginalData currentData){
        this.currentData = currentData;

        setLayout(null);
        setSize(800,550);
        setLocation(0,50);

        String[] colums = new String[] {"Дни", "Растояние", "Топливо", "Количество рейсов","Вес факт", "Вес норм", "Процент", "Тип эксковатора - количесвто", "Тип самосвала - количество"};
        Object[][] datas = originalData();
        tableOriginalData = new JTable(datas, colums);
        tableOriginalData.setModel(new DefaultTableModel(datas, colums) {
            Class<?>[] columnTypes = new Class<?>[] {Integer.class, Double.class, Integer.class,  Double.class, Double.class,Double.class,Double.class, JButton.class, JButton.class};
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }
        });
        ButtonEditor be = new ButtonEditor();
        TableColumn col = tableOriginalData.getColumnModel().getColumn(7);
        col.setCellEditor(be);
        col.setCellRenderer(new ButtonRenderer());
        TableColumn col1 = tableOriginalData.getColumnModel().getColumn(8);
        col1.setCellEditor(be);
        col1.setCellRenderer(new ButtonRenderer());

        JScrollPane pane2 = new JScrollPane(tableOriginalData);



        GroupLayout contentPaneLayoutForPanelOriginalData = new GroupLayout(this);
        this.setLayout(contentPaneLayoutForPanelOriginalData);
        contentPaneLayoutForPanelOriginalData.setHorizontalGroup(
                contentPaneLayoutForPanelOriginalData.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayoutForPanelOriginalData.createSequentialGroup()
                                .addComponent(pane2, GroupLayout.PREFERRED_SIZE, 800, GroupLayout.PREFERRED_SIZE)
                                .addGap(10)));
        contentPaneLayoutForPanelOriginalData.setVerticalGroup(
                contentPaneLayoutForPanelOriginalData.createParallelGroup()
                        .addComponent(pane2)
        );
    }

    private static class ButtonRenderer implements TableCellRenderer {

        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int rowIndex, int vColIndex) {
            return (Component) value;
        }
    }

    private static class ButtonEditor extends AbstractCellEditor implements TableCellEditor {

        private JButton jButton;

        public ButtonEditor() {
        }

        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int column) {
            jButton = (JButton) value;
            return (JButton) value;
        }

        public Object getCellEditorValue() {
            return jButton;
        }
    }

    HashMap<Integer, Month> month;
    private Object[][] originalData(){

        month = currentData.month("");

        Object[][] data = new Object[month.keySet().size()][9];
        int index = 0;

        for(Integer i : month.keySet()){

            JButton exs = new JButton();
            exs.setText(i.toString());
            exs.addActionListener(e -> newFrame(i, month.get(i).getExs()));

            JButton trucks = new JButton();
            trucks.setText(i.toString());
            trucks.addActionListener(e -> newFrame(i, month.get(i).getTrucks()));

            Object[] row = {i, month.get(i).getDistance(), month.get(i).getGas(),month.get(i).getQuantityReise(), month.get(i).getWeightFact(), month.get(i).getWeightNorm(), (month.get(i).getWeightFact() - month.get(i).getWeightNorm())/month.get(i).getWeightNorm() * 100, exs, trucks};

            data[index] = row;
            index++;
        }

        return data;
    }

    private void newFrame(int i, List<String> exs){
        JFrame frame = new JFrame();

        frame.setSize(600,600);
        frame.setLocation(1980/3, 1080/3);
        frame.setVisible(true);

        JPanel panel = new JPanel();
        panel.setSize(600,600);



        JTable tableTrash = new JTable();
        tableTrash.setSize(600,500);
        tableTrash.setModel(new DefaultTableModel(
                new Object[][] {},
                new String[] {"название", "Количество"}) {
            Class<?>[] columnTypes = new Class<?>[] {String.class, String.class};
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }
        });

        DefaultTableModel tableModel = (DefaultTableModel) tableTrash.getModel();

        for (String s: exs) {
            tableModel.addRow(s.split("-"));
        }

        JScrollPane pane = new JScrollPane(tableTrash);
        panel.add(pane);

        GroupLayout contentPaneLayoutForPanelOriginalData = new GroupLayout(panel);
        panel.setLayout(contentPaneLayoutForPanelOriginalData);
        contentPaneLayoutForPanelOriginalData.setHorizontalGroup(
                contentPaneLayoutForPanelOriginalData.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayoutForPanelOriginalData.createSequentialGroup()
                                .addComponent(pane, GroupLayout.PREFERRED_SIZE, 800, GroupLayout.PREFERRED_SIZE)
                                .addGap(10)));
        contentPaneLayoutForPanelOriginalData.setVerticalGroup(
                contentPaneLayoutForPanelOriginalData.createParallelGroup()
                        .addComponent(pane)
        );

        frame.getContentPane().add(panel);
    }
}
