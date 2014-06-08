package Simplex;

import java.net.*; 
import java.io.*; 
import javax.swing.JOptionPane;

public class Servidor
{

public static void main (String[] argumentos)throws IOException
{
   
   ServerSocket socketServidor = null; 
   Socket socketCliente = null; 
   try
     {
       socketServidor = new ServerSocket (12345); 
     }
     catch (Exception e)
     {
       JOptionPane.showMessageDialog(null,("fallo : " + e.toString())); 
       System.exit (0); 
     } 
   System.out.println ("Servidor iniciado... (Para Socket TCP)"); 
   System.out.println ("Esperando Mensaje....");
   while(true)
     {
     try
       {
         socketCliente = socketServidor.accept(); 
         AtencionClienteThread miThread = new AtencionClienteThread ("Cliente: " + socketCliente.toString() ,socketCliente); 
       }
     catch (IOException e)
       {
         JOptionPane.showMessageDialog(null,("fallo en la conexion : " + e.toString())); 
         socketServidor.close(); 
         System.exit (0); 
       } 
     } 
 } 
} 