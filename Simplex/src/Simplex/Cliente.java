package Simplex;

import java.net.*; 
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.*;

public class Cliente
{
 public static void main (String[] argumentos)throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException
 {
    UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
    BufferedReader stdin = new BufferedReader(new InputStreamReader (System.in));
    Socket cliente = null; 
    PrintWriter escritor = null; 
    BufferedReader lector = null; 
    String DatosEnviados = null; 
    String maquina; 
    int puerto; 
    BufferedReader DatosTeclado = new BufferedReader ( new InputStreamReader (System.in)); 
    if (argumentos.length !=2)
    {       
        maquina = JOptionPane.showInputDialog(null, "Ingrese IP del Servidor");;
        puerto = 12345; 
        System.out.println ("Conectado a:\nEQUIPO = " + maquina + " Mediante Puerto = " + puerto); 
    } 
    else
    {
        maquina = argumentos [0]; 
        Integer pasarela = new Integer (argumentos[1]); 
        puerto = pasarela.parseInt(pasarela.toString()); 
        System.out.println ("Conectado a " + maquina + " Mediante puerto: " + puerto); 
    } 
    try
    {
    cliente = new Socket (maquina,puerto); 
    }
    catch (Exception e)
    {
	 JOptionPane.showMessageDialog(null,"FALLO : "+ e.toString()); 
        System.exit (0); 
    } 
    try
    {
        escritor = new PrintWriter(cliente.getOutputStream(), true); 
        lector = new BufferedReader(new InputStreamReader (cliente.getInputStream())); 
    }
    catch (Exception e)
    {
        JOptionPane.showMessageDialog(null,"FALLO : "+ e.toString()); 
        cliente.close(); 
        System.exit (0); 
    } 
 
    do
    {
        System.out.print("\n\nEscriba su mensaje: ");
        DatosEnviados = DatosTeclado.readLine(); 
        escritor.println (DatosEnviados); 
        if (!DatosEnviados.equals("Fin"))
        {
            System.out.println (lector.readLine()); 
            System.out.println (lector.readLine()); 
            System.out.println (lector.readLine()); 
            System.out.println (lector.readLine()); 
            System.out.println (lector.readLine()); 
        } 
    }while (!DatosEnviados.equals("Fin"));
    JOptionPane.showMessageDialog(null,"Ha finaliado conexion con el servidor"); 
    try
    {
        escritor.close(); 
    }
    catch (Exception e){}
    }
    }