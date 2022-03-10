package Server;

import com.opencsv.CSVWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


public class ExportData {
    
    public static void ExportToCSVfile(JTable table,File pathToExportTo) throws IOException{
            Writer writer = null;
            DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
            int Row = defaultTableModel.getRowCount();
            int Col = defaultTableModel.getColumnCount();
            try {
                writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pathToExportTo), "utf-8"));

                StringBuilder bufferHeader = new StringBuilder();
                for (int j = 0; j < Col; j++) {
                    bufferHeader.append(defaultTableModel.getColumnName(j));
//                    if (j!=Col)
                        bufferHeader.append(";");
                }
                writer.write(bufferHeader.toString() + "\r\n");

                for (int i = 0 ; i < Row ; i++){
                     StringBuilder buffer = new StringBuilder();
                    for (int j = 0 ; j < Col ; j++){
                        buffer.append(defaultTableModel.getValueAt(i,j));
//                        if (j!=Col)
                            buffer.append(";");

                    }
                    writer.write(buffer.toString() + "\r\n");
                }
            } finally {
                  writer.close();
            }
        }

    public static void expoertEithAnotherWay(JTable table, String path){
        DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
        int Row = defaultTableModel.getRowCount();
        int Col = defaultTableModel.getColumnCount();
        List<String[]> csvData = new ArrayList<>();
        String[] strs = new String[Row];

        for (int j = 0 ; j < Col ; j++){
            for (int i = 0 ; i < Row ; i++){
                strs[i] = String.valueOf(defaultTableModel.getValueAt(i,j));
            }
            csvData.add(strs);
        }

        try (CSVWriter writer = new CSVWriter(new FileWriter(path))) {
            writer.writeAll(csvData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    public static boolean exportToCSV(JTable tableToExport, File pathToExportTo) {

    try {

        TableModel model = tableToExport.getModel();
        FileWriter csv = new FileWriter(pathToExportTo);

        for (int i = 0; i < model.getColumnCount(); i++) {
            csv.write(model.getColumnName(i) + ";");
        }

        csv.write("\n");

        for (int i = 0; i < model.getRowCount(); i++) {
            for (int j = 0; j < model.getColumnCount(); j++) {
                csv.write(model.getValueAt(i, j).toString() + ";");
            }
            csv.write("\n");
        }
        
        JOptionPane.showMessageDialog(null, 
                              "Файл сохранен " + pathToExportTo.getName(), 
                              "Файл", 
                              JOptionPane.WARNING_MESSAGE);

        csv.close();
        return true;
    } catch (IOException e) {
        e.printStackTrace();
    }
    return false;
}
    
}
