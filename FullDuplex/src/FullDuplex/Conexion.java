package FullDuplex;

import java.net.*;
import java.io.*;
import java.util.*;

public class  Conexion  extends Thread {
  protected Socket s;				// ACCESO PUBLICO PARA LAS CLASES DERIVADAS
  protected DataInputStream i;			// LEYENDO LOS DATOS DE ENTRADA, (ip del vector de socket servidor donde se llamo)
  protected DataOutputStream o;			
  Vector y = new Vector();

  public Conexion (Socket s, Vector x) throws IOException {
    this.s = s;
    i = new DataInputStream (new BufferedInputStream (s.getInputStream ())); //SI HAY DATOS DIGITADOS  SE LEEN DEL SOCKET 
    o = new DataOutputStream (new BufferedOutputStream (s.getOutputStream ())); //ESCRIBE DATOS LEIDOS DEL SOCKET
    y = x;
  }
  protected static Vector handlers = new Vector ();			//SE CREA UN VECTOR NUEVO
  public void run () {
    String name = s.getInetAddress ().toString ();			//LA DIRECC. REMOTA SE LEE EN LA VAR. "name" 
    String Listado="";
    try {    	
	  for (int i=0; i < y.size() ; i++)
	  Listado=Listado+y.get(i)+"\n";     
      
      handlers.addElement (this);
      broadcast ("->" + Listado);
      while (true) {
        String msg = i.readUTF ();
        broadcast (name + " - " + msg);
      }
    } catch (IOException ex) {
      ex.printStackTrace ();
    } finally {
      handlers.removeElement (this);
      y.remove(name);
      Listado="";
	  for (int i=0; i < y.size() ; i++)
	  	Listado=Listado+y.get(i)+"\n";
      broadcast ("<-" + Listado);
      try {
        s.close ();
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }
  }

  protected static void broadcast (String message) {
    synchronized (handlers) {
      Enumeration e = handlers.elements ();
      while (e.hasMoreElements ()) {
        Conexion c = (Conexion) e.nextElement ();
        try {
          synchronized (c.o) {
            c.o.writeUTF (message);
          }
          c.o.flush ();
        } catch (IOException ex) {
          c.stop ();
        }
      }
    }
  }
} 

