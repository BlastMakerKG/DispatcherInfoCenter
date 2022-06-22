package kg.dispatcher.info.centre.prices.UI.document;

import kg.dispatcher.info.centre.prices.planning.model.Excavator;
import kg.dispatcher.info.centre.prices.planning.model.Truck;
import kg.dispatcher.info.centre.prices.planning.model.Waste;
import kg.dispatcher.info.centre.prices.planning.reportDocument.Output;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.List;

public class CostPrice extends JPanel {
    private JTable tableDataWithPrice;

    private Output output;

    public CostPrice(Output output){
        this.output = output;
        setLayout(null);
        setSize(800,550);
        setLocation(0,50);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        tableDataWithPrice = new JTable();

        tableDataWithPrice.setModel(new DefaultTableModel(
                getData(),
                new String[] {"Дни", "Вес факт", "Вес норм", "Затраты","Себестоимость","ТС"}) {
            Class<?>[] columnTypes = new Class<?>[] {Integer.class, String.class, String.class,  String.class, Double.class, JButton.class};
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }
        });
        ButtonEditor be1 = new ButtonEditor();
        TableColumn col3 = tableDataWithPrice.getColumnModel().getColumn(5);
        col3.setCellEditor(be1);
        col3.setCellRenderer(new ButtonRenderer());
        setSize(800,500);
        JScrollPane pane = new JScrollPane(tableDataWithPrice);
        GroupLayout contentPaneLayoutForPanelDateWithPrice = new GroupLayout(this);
        this.setLayout(contentPaneLayoutForPanelDateWithPrice);
        contentPaneLayoutForPanelDateWithPrice.setHorizontalGroup(
                contentPaneLayoutForPanelDateWithPrice.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayoutForPanelDateWithPrice.createSequentialGroup()
                                .addComponent(pane, GroupLayout.PREFERRED_SIZE, 800, GroupLayout.PREFERRED_SIZE)
                                .addGap(10)));
        contentPaneLayoutForPanelDateWithPrice.setVerticalGroup(
                contentPaneLayoutForPanelDateWithPrice.createParallelGroup()
                        .addComponent(pane)
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

    private Object[][] getData(){
        java.util.List<Waste> month =  output.month();

        Object[][] data = new Object[month.size()][6];

        int index = 0;
        for (Waste s : month){

            JButton button = new JButton();
            button.setText(String.valueOf(s.getDay()));
            button.addActionListener(e -> listExs(s.getExcavators()));

            Object[] row = {s.getDay()+1, s.getWeight_fact(), s.getWeight_norm(), s.getWaste(), s.getWastePerMKM(), button};

            data[index] = row;
            index++;

        }

        return data;
    }

    private void listExs(List<Excavator> excavators) {
        JFrame frame = new JFrame();

        frame.setSize(800,300);
        frame.setLocation(1980/3, 1080/3);
        frame.setVisible(true);

        JPanel panel = new JPanel();
        panel.setSize(800,300);

        JTable tableTrash = new JTable();
        tableTrash.setModel(new DefaultTableModel(
                new Object[][] {},
                new String[] {"название", "тип работы", "Вес факт", "Вес норм", "затраты", "себестоимость", "Самосвалы"}) {
            Class<?>[] columnTypes = new Class<?>[] {String.class, String.class, Double.class, Double.class, Double.class, Double.class, JButton.class};
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }
        });
        ButtonEditor be1 = new ButtonEditor();
        TableColumn col3 = tableTrash.getColumnModel().getColumn(6);
        col3.setCellEditor(be1);
        col3.setCellRenderer(new ButtonRenderer());

        DefaultTableModel tableModel = (DefaultTableModel) tableTrash.getModel();

        for (Excavator s: excavators) {
            JButton button = new JButton();
            button.setName("list");
            button.addActionListener(e -> listtrucks(s.getTrucks()));
            tableModel.addRow(new Object[]{s.getType(),  s.getTypeofWork(), s.getWeight_fact(), s.getWeight_norm(), s.getWaste(), s.getWastePerKM(), button});
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

    private void listtrucks(List<Truck> trucks) {
        JFrame frame = new JFrame();

        frame.setSize(1200,300);
        frame.setLocation(1980/3, 1080/3 + 50);
        frame.setVisible(true);

        JPanel panel = new JPanel();
        panel.setSize(1200,600);

        JTable tableTrash = new JTable();
        tableTrash.setModel(new DefaultTableModel(
                new Object[][] {},
                new String[] {"название", "тип работы", "Сред скорость","Удельный расх","Порожний расх","Вес авто",
                        "Вес факт", "Вес норм", "затраты", "себестоимость"}) {
            Class<?>[] columnTypes = new Class<?>[] {String.class, String.class, Double.class, String.class,
                    String.class, String.class, String.class, String.class, String.class,String.class};
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }
        });

        DefaultTableModel tableModel = (DefaultTableModel) tableTrash.getModel();

        for (Truck s: trucks) {
            tableModel.addRow(new Object[]{s.getType_truck(), s.getType_of_work(),
                    s.getSpeed(),s.getSpecific_waste_with_mass(), s.getSpecific_waste_without_mass(), s.getWeight(),
                    s.getWeight_fact(), s.getWeight_norm(), s.getWaste_truck(), s.getCost_price()});
        }

        JScrollPane pane = new JScrollPane(tableTrash);
        panel.add(pane);

        GroupLayout contentPaneLayoutForPanelOriginalData = new GroupLayout(panel);
        panel.setLayout(contentPaneLayoutForPanelOriginalData);
        contentPaneLayoutForPanelOriginalData.setHorizontalGroup(
                contentPaneLayoutForPanelOriginalData.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayoutForPanelOriginalData.createSequentialGroup()
                                .addComponent(pane, GroupLayout.PREFERRED_SIZE, 1200, GroupLayout.PREFERRED_SIZE)
                                .addGap(10)));
        contentPaneLayoutForPanelOriginalData.setVerticalGroup(
                contentPaneLayoutForPanelOriginalData.createParallelGroup()
                        .addComponent(pane)
        );

        frame.getContentPane().add(panel);
    }

}
