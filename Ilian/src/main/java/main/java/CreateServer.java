package main.java;

import java.awt.List;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author student2
 */
public class CreateServer implements Runnable{

    /**
     * @return the clientSocket
     */
    public Socket getClientSocket() {
        return clientSocket;
    }

    /**
     * @param clientSocket the clientSocket to set
     */
    public void setClientSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    private String data;
    private String data1="";
    public String status = "";
    private String name_client;
    private JTable myTable;
    DefaultTableModel modeltab;
   
    private boolean flag = true,flag_msg = true;
    
    private Socket clientSocket;
    
    public CreateServer(Socket socket,List client_list,JTable loc_table,JLabel label, int queue){     
        this.clientSocket = socket;
        name_client = clientSocket.getInetAddress().getCanonicalHostName();
        client_list.add(clientSocket.getInetAddress().getCanonicalHostName());
        label.setText("Передача данных началась");
         
        modeltab = (DefaultTableModel) loc_table.getModel();
        
        Runnable run = new Runnable(){
            @Override
            public void run() {
                try{
                    while(!clientSocket.isClosed()){
                        if(data != null){
                            
                                 String str = data;
                                String[] subStr1, subStr2;
                                String delimeter = ",";  
                                subStr1 = str.split(delimeter);
                                System.out.println(Integer.parseInt(subStr1[0]) + subStr1[1] + Double.parseDouble(subStr1[2]) +
                                                                                     Double.parseDouble(subStr1[3]) + Double.parseDouble(subStr1[4]) + Double.parseDouble(subStr1[5]) + Double.parseDouble(subStr1[6]));
                                modeltab.insertRow(queue, new Object[]{Integer.parseInt(subStr1[0]),subStr1[1],Double.parseDouble(subStr1[2]),
                                                                                     Double.parseDouble(subStr1[3]),Double.parseDouble(subStr1[4]),Double.parseDouble(subStr1[5]),Double.parseDouble(subStr1[6])});
                                 Thread.sleep(2000);
                                 modeltab.removeRow(queue);   
                            }   
                    }         
                    } 
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }  
            }            
        };
         Thread myThread = new Thread(run);
         myThread.start();
    }
    
    public CreateServer(Socket socket,List client_list,JTable loc_table,JLabel label){     
        this.clientSocket = socket;
        name_client = clientSocket.getInetAddress().getCanonicalHostName();
        client_list.add(clientSocket.getInetAddress().getCanonicalHostName());
        label.setText("Передача данных началась");
         
        modeltab = (DefaultTableModel) loc_table.getModel();
        
        Runnable run = new Runnable(){
            @Override
            public void run() {
                try{
                    while(!clientSocket.isClosed()){
                        if(data != null){
                            
                                 String str = data;
                                String[] subStr1, subStr2;
                                String delimeter = ",";  
                                subStr1 = str.split(delimeter);
                                System.out.println(Integer.parseInt(subStr1[0]) + subStr1[1] + Double.parseDouble(subStr1[2]) +
                                                                                     Double.parseDouble(subStr1[3]) + Double.parseDouble(subStr1[4]) + Double.parseDouble(subStr1[5]) + Double.parseDouble(subStr1[6]));
                                modeltab.insertRow(modeltab.getRowCount(), new Object[]{Integer.parseInt(subStr1[0]),subStr1[1],Double.parseDouble(subStr1[2]),
                                                                                     Double.parseDouble(subStr1[3]),Double.parseDouble(subStr1[4]),Double.parseDouble(subStr1[5]),Double.parseDouble(subStr1[6])});
                                 Thread.sleep(2000);
                            }   
                        }         
                    } 
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }  
            }            
        };
         Thread myThread = new Thread(run);
         myThread.start();
    }
 
    @Override
    public void run() {
        while (flag) {          
            try
            {
                BufferedReader reader = new BufferedReader(new InputStreamReader(getClientSocket().getInputStream()));
                data = reader.readLine();
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

    /**
     * @return the data
     */
    public String getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * @return the name_client
     */
    public String getName_client() {
        return name_client;
    }

    /**
     * @param name_client the name_client to set
     */
    public void setName_client(String name_client) {
        this.name_client = name_client;
    }
    
}
