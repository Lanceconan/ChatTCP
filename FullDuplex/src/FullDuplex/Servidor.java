package FullDuplex;

import java.net.*;	//OCULTA SOCKET
import java.io.*;	//ENTRADAS y SALIDAS
import java.util.*;	//UTILITARIOS
public class  Servidor  {
  
    public Servidor (int port) throws IOException {
    Vector x = new Vector();			// DECLACION DE VECTORES
    ServerSocket server = new ServerSocket (port);	// INICIALIZANDO SOCKET
    while (true) {					// SI SE RECIBE CONEXION CLIENTE
    Socket client = server.accept ();			// CONEXION ACEPTADA
    System.out.println ("Conexion Exitosa con: " + client.getInetAddress ()); // DEVUELVE DIRECCION IP DEL CLIENTE
    x.add(client.getInetAddress ());				// AGREGA LA DIRECCION IP VECOTOR
    Conexion c = new Conexion (client,x);		
    c.start ();    }
  }
  
  public static void main (String args[]) throws IOException {
    new Servidor (4321);
  }
} 

