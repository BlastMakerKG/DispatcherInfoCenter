package server;

import panels.CurrentlyData;
import DB.service.DataService;
import lombok.Getter;
import lombok.Setter;

import java.awt.List;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

@Getter
@Setter
public class CreateServer implements Runnable{

    public Socket getClientSocket() {
        return clientSocket;
    }

    public void setClientSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    private String data;
    public String status = "";
    private String name_client;
    DefaultTableModel modeltab;

    public boolean isFlag() {
        return flag;
    }

    private boolean flag = true, flag_msg = true;
    
    private Socket clientSocket;
    
//    public CreateServer(Socket socket,List client_list,JTable loc_table,JLabel label, int queue){
//        this.clientSocket = socket;
//        name_client = clientSocket.getInetAddress().getCanonicalHostName();
//        client_list.add(clientSocket.getInetAddress().getCanonicalHostName());
//        label.setText("Передача данных началась");
//
//        modeltab = (DefaultTableModel) loc_table.getModel();
//
//        Runnable run = () -> {
//            try{
//                while(!clientSocket.isClosed()){
//                    if(data != null){
//
//                             String str = data;
//                            String[] subStr1, subStr2;
//                            String delimeter = ",";
//                            subStr1 = str.split(delimeter);
//                            System.out.println(Integer.parseInt(subStr1[0]) + subStr1[1] + Double.parseDouble(subStr1[2]) +
//                                                                                 Double.parseDouble(subStr1[3]) + Double.parseDouble(subStr1[4]) + Double.parseDouble(subStr1[5]) + Double.parseDouble(subStr1[6]));
//                            modeltab.insertRow(queue, new Object[]{Integer.parseInt(subStr1[0]),subStr1[1],Double.parseDouble(subStr1[2]),
//                                                                                 Double.parseDouble(subStr1[3]),Double.parseDouble(subStr1[4]),Double.parseDouble(subStr1[5]),Double.parseDouble(subStr1[6])});
//                             Thread.sleep(2000);
//                             modeltab.removeRow(queue);
//                        }
//                }
//                }
//            catch(Exception ex)
//            {
//                ex.printStackTrace();
//            }
//        };
//         Thread myThread = new Thread(run);
//         myThread.start();
//    }

    private java.util.List<String[]> datas;

    private DataService dataService;

    public CreateServer(Socket socket, List client_list, JTable loc_table, JLabel label, java.util.List<String[]> datas, DataService dataService, CurrentlyData currentlyData){
        this.datas = datas;
        this.clientSocket = socket;
        this.dataService = dataService;
        name_client = clientSocket.getInetAddress().getCanonicalHostName();
        client_list.add(clientSocket.getInetAddress().getCanonicalHostName());
        label.setText("Передача данных началась");
         
        modeltab = (DefaultTableModel) loc_table.getModel();
        
        Runnable run = () -> {
            try{
                while(!clientSocket.isClosed()){
                    if(data != null){
                        String str = data;
                        String delimeter = ",";
                        String[] splitStr = str.split(delimeter);
                        datas.add(splitStr);
//                        System.out.println(Integer.parseInt(splitStr[0]) + splitStr[1] + Double.parseDouble(splitStr[2]) +
//                                                                                 Double.parseDouble(splitStr[3]) + Double.parseDouble(splitStr[4]) + Double.parseDouble(splitStr[5]) + Double.parseDouble(splitStr[6]));
                        synchronized(modeltab)
                        {
                            modeltab.insertRow(modeltab.getRowCount(), new Object[]{Integer.parseInt(splitStr[0]),splitStr[1],Double.parseDouble(splitStr[2]),
                                                                                 Double.parseDouble(splitStr[3]),Double.parseDouble(splitStr[4]),Double.parseDouble(splitStr[5]),Double.parseDouble(splitStr[6])});
                            }
                            currentlyData.setLocation_table2(modeltab.getRowCount(), new Object[]{Integer.parseInt(splitStr[0]),splitStr[1],Double.parseDouble(splitStr[2]),
                                    Double.parseDouble(splitStr[3]),Double.parseDouble(splitStr[4]),Double.parseDouble(splitStr[5]),Double.parseDouble(splitStr[6])}, str);
                             Thread.sleep(2000);
                        }
                    }
                }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        };
         Thread myThread = new Thread(run);
         myThread.start();
    }
 
    @Override
    public void run() {
        while (flag) {          
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(getClientSocket().getInputStream()));
                data = reader.readLine();
                System.out.println(data.getBytes("UTF-8").length);

                dataService.saveData(data);

                if(data != null)
                {
                    
                   if(flag_msg)
                    {
                        JOptionPane.showMessageDialog(null,
                                "Данные получены",
                                "Данные",
                                JOptionPane.WARNING_MESSAGE);
                        flag_msg = false;
                        
                    }
                    if(!clientSocket.isClosed())
                    {
                        System.out.println(data);
                        status ="Передача данных"; 
                    }
                }
                else
                {
                    flag = false;
                    status ="Клиент отключился";
                    JOptionPane.showMessageDialog(null,
                            "Клиент отключился",
                            "Клиент",
                            JOptionPane.WARNING_MESSAGE);
                }
                
            }
            
            
            catch(Exception ex)
            {
                ex.printStackTrace();
                flag = false;
            }
            
        }

    }
    
}
