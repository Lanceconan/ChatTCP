package FullDuplex;

import javax.swing.JOptionPane;
import java.net.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;     

public class ChatClientes
 {
  public static void main(String [] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {  
  UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
  BufferedReader stdin = new BufferedReader(new InputStreamReader (System.in));
  String maquina;
//  public Application1() throws IOException {  //
    System.out.flush();
    Socket s;
    try{
    	maquina = JOptionPane.showInputDialog(null, "Ingrese IP del Servidor");
    	s = new Socket ( maquina , 4321);
    	Frame frame = new PropiedadesChat ("Chat " + maquina + ":" + "4321",s.getInputStream (), s.getOutputStream ());
    	//"127.0.0.1"
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame.getSize();
        if (frameSize.height > screenSize.height) {
          frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
          frameSize.width = screenSize.width;
        }
        frame.setLocation((screenSize.width - frameSize.width)/2, (screenSize.height - frameSize.height)/2);
        frame.addWindowListener(new WindowAdapter() { public void windowClosing(WindowEvent e) { System.exit(0); } });
        frame.setVisible(true);
    }catch(Exception e)
    {
    	JOptionPane.showMessageDialog(null, "IP mal ingresada\nFin de Programa ");
    }   
  }
   // public static void main(String [] args) throws IOException{//
      {
        try
         {
           UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
         }
           catch (Exception e)
         {
           e.printStackTrace();
         }
         new ChatClientes();
 }
}


