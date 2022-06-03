package server;

import DB.service.*;
import panels.CurrentlyData;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

@Setter
@Getter
public class UDPServer extends  Thread{

    private String data;
    public String status = "";
    private String name_client;
    private DefaultTableModel modeltab;
    private DatagramSocket clientSocket;
    private java.util.List<String[]> datas;
    private boolean flag = true, flag_msg = true;
    private DataService dataService;

    private CurrentlyData currentlyData;


    public UDPServer( DatagramSocket server, List client_list, JTable loc_table, JLabel label, java.util.List<String[]> datas, DataService dataService, CurrentlyData currentlyData){
        this.datas = datas;
//        this.clientSocket = socket;
        clientSocket = server;
        this.dataService = dataService;
//        name_client = clientSocket.getInetAddress().getCanonicalHostName();
//        client_list.add(clientSocket.getInetAddress().getCanonicalHostName());
        label.setText("Передача данных началась");

        modeltab = (DefaultTableModel) loc_table.getModel();

        this.currentlyData = currentlyData;

//        Runnable run = () -> {
//            try{
//
//                DatagramPacket packet = new DatagramPacket(buf, buf.length);
//                clientSocket.receive(packet);
//                InetAddress address = packet.getAddress();
//                int port = packet.getPort();
//                packet = new DatagramPacket(buf, buf.length, address, port);
//                data = new String(packet.getData(), 0, packet.getLength());
//
//                while(!clientSocket.isClosed()){
//                    if(data != null){
//
//                        String str = data;
//                        String delimeter = ",";
//                        String[] splitStr = str.split(delimeter);
//                        datas.add(splitStr);
////                        System.out.println(Integer.parseInt(splitStr[0]) + splitStr[1] + Double.parseDouble(splitStr[2]) +
////                                                                                 Double.parseDouble(splitStr[3]) + Double.parseDouble(splitStr[4]) + Double.parseDouble(splitStr[5]) + Double.parseDouble(splitStr[6]));
//                        synchronized(modeltab)
//                        {
//                            modeltab.insertRow(modeltab.getRowCount(), new Object[]{Integer.parseInt(splitStr[0]),splitStr[1],Double.parseDouble(splitStr[2]),
//                                    Double.parseDouble(splitStr[3]),Double.parseDouble(splitStr[4]),Double.parseDouble(splitStr[5]),Double.parseDouble(splitStr[6])});
//                        }
//                        currentlyData.setLocation_table2(modeltab.getRowCount(), new Object[]{Integer.parseInt(splitStr[0]),splitStr[1],Double.parseDouble(splitStr[2]),
//                                Double.parseDouble(splitStr[3]),Double.parseDouble(splitStr[4]),Double.parseDouble(splitStr[5]),Double.parseDouble(splitStr[6])}, str);
//                        Thread.sleep(2000);
//                    }
//                }
//            }
//            catch(Exception ex)
//            {
//                ex.printStackTrace();
//            }
//        };
//        Thread myThread = new Thread(run);
//        myThread.start();
    }


    private byte[] buf = new byte[70];

    @Override
    public void run() {
        while (flag) {
            try {

                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                clientSocket.receive(packet);

                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                packet = new DatagramPacket(buf, buf.length, address, port);
                data = new String(packet.getData(), 0, packet.getLength());
                System.out.println(data);
                while(clientSocket.isConnected()){
//                    if(data != null){

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
                        System.out.println(data.getBytes("UTF-8").length);
                        Thread.sleep(2000);
//                    }
                }



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
