package Simplex;

import java.io.*; 
import java.net.*; 
public class AtencionClienteThread extends Thread
{
  Socket SockCliente; 
  String name; 
  public void run()
  {
    String Entrada = "Inicio"; 
    try
    {
      PrintWriter escritor = new PrintWriter (SockCliente.getOutputStream(), true); 
      BufferedReader lector = new BufferedReader (new InputStreamReader (SockCliente.getInputStream())); 
      while (!Entrada.equals("Fin"))
      {
        Entrada = leerDatos(lector); 
        if (!Entrada.equals("Fin"))
        {
          presentaAlCliente (SockCliente); 
          System.out.println("Mensaje: " + Entrada); 
          enviarDatos (Entrada, escritor); 
        } 
      } 
      SockCliente.close(); 
      escritor.close(); 
      lector.close(); 
    }
      catch (IOException e)
        {
          System.out.println("Fallo: " + e.toString()); 
          System.exit(0); 
        } 
    } 
    public AtencionClienteThread(String nameThread, Socket Cliente)
    {
      name = nameThread; 
      SockCliente = Cliente; 
      this.start(); 
    } 
      public void enviarDatos(String Salida, PrintWriter escritor)
      {
        Estado Conf = new Estado (name); 
        escritor.println("Se Recibio: " + Salida + "\nServidor:" + Conf.toString()); 
      } 

   public String leerDatos(BufferedReader lector) throws IOException
   {
     String datos = null; 
     try{datos = lector.readLine(); 
   }
   catch (Exception e)
     {
       System.out.println ("Fallo al leer datos : "+ e.toString()); 
       System.exit (0); 
     } 
   return datos; 
 } 
 public static void presentaAlCliente(Socket s)
  {
    System.out.println("\nSe ha recibido un mensaje desde:\nPuerto remoto: " + s.getPort()); //Puerto REMOTO del Cliente 
    System.out.println("Puerto local: " + s.getLocalPort()); 
    System.out.println("Maquina: " + s.getInetAddress()); 
  } 
} 
