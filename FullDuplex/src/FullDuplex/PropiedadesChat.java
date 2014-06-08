package FullDuplex;

import java.net.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * A Swing-based top level window class.
 * <P>
 * Bajado del Web del Programador
 */
public class PropiedadesChat extends JFrame implements Runnable{
  BorderLayout borderLayout1 = new BorderLayout();
  JMenuBar menuBar1 = new JMenuBar();
  JMenu menuFile = new JMenu();
  JMenuItem menuFileExit = new JMenuItem();
  JMenu menuHelp = new JMenu();
  JMenuItem menuHelpAbout = new JMenuItem();
  JSplitPane jSplitPane1 = new JSplitPane();
  JSplitPane jSplitPane2 = new JSplitPane();
  JScrollPane jScrollPane1 = new JScrollPane();
  JScrollPane jScrollPane2 = new JScrollPane();
  JTextArea jTextArea1 = new JTextArea();
  JTextArea jTextArea2 = new JTextArea();
  JPanel jPanel1 = new JPanel();
  JLabel jLabel1 = new JLabel();
  JSplitPane jSplitPane3 = new JSplitPane();
  JScrollPane jScrollPane3 = new JScrollPane();
  JPanel jPanel2 = new JPanel();
  TextField input = new TextField();
  JTextArea output = new JTextArea();  
  JButton jButton1 = new JButton();
  DataInputStream i;
  DataOutputStream o;
  protected Thread listener;
  /**
   * Construye una nueva instancia
   */
  public PropiedadesChat(String title, InputStream i, OutputStream o) {
    super("Chat Grupo 8 - Full Duplex");
    this.i = new DataInputStream (new BufferedInputStream (i));
    this.o = new DataOutputStream (new BufferedOutputStream (o));
    try  
    {
        jbInit();
    }
    catch (Exception e) {
    }
    input.requestFocus ();
    listener = new Thread (this);
    listener.start ();     
    }

  /**
   * iniciar el estado de la instancia.
   */
    private void jbInit() throws Exception {
    this.getContentPane().setLayout(borderLayout1);
    this.setSize(new Dimension(595, 592));
    menuFile.setText("Opciones");
    menuFileExit.setText("Salir");
    jSplitPane1.setOrientation(JSplitPane.VERTICAL_SPLIT);
    jTextArea1.setText("");    
    jTextArea2.setEditable(false);
    output.setEditable(false);
    jLabel1.setText("Chat Grupo 8 - Full Duplex");
    jLabel1.setFont(new Font("Dialogo", 1, 40));
    jButton1.setText("Enviar");
    menuFile.add(menuFileExit);
    menuBar1.add(menuFile);
    menuHelp.add(menuHelpAbout);
    menuBar1.add(menuHelp);
    this.setJMenuBar(menuBar1);
    this.getContentPane().add(jSplitPane1, BorderLayout.CENTER);
    jSplitPane1.add(jSplitPane2, JSplitPane.BOTTOM);
    jSplitPane2.add(jScrollPane1, JSplitPane.RIGHT);
    jScrollPane1.getViewport().add(jTextArea1, null);
    jScrollPane1.getViewport().add(output, null);
    jSplitPane2.add(jScrollPane2, JSplitPane.LEFT);
    jScrollPane2.getViewport().add(jTextArea2, null);
    jSplitPane1.add(jPanel1, JSplitPane.TOP);
    jPanel1.add(jLabel1, null);
    this.getContentPane().add(jSplitPane3, BorderLayout.SOUTH);
    jSplitPane3.add(jScrollPane3, JSplitPane.BOTTOM);
    jScrollPane3.getViewport().add(input, null);
    jSplitPane3.add(jPanel2, JSplitPane.TOP);
    jPanel2.add(jButton1, null);
//----------------------------------------------------------------------    
    menuFileExit.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        fileExit_ActionPerformed(e);
      }
    });  
//----------------------------------------------------------------------        
    jButton1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
      	try {
            o.writeUTF (input.getText());
            o.flush (); 
            System.out.println("Enviando..." + input.getText());
      	} 
      	catch (IOException ex) {
        	ex.printStackTrace();
        	listener.stop ();
      	}
      	input.setText ("");            
      }
    });    
//----------------------------------------------------------------------    
  }

  public void run () {
    Vector control = new Vector();
    String s;
    try {
      while (true) {
        String line = i.readUTF();
		
        if (line.substring(0,2).equals("->")){
          	jTextArea2.setText("                  \n");
       		jTextArea2.append(line.substring(2));       		
        }
        
        else
        {
	    if (line.substring(0,2).equals("<-"))
            {
                jTextArea2.setText("                  \n");
                menuFile.setText("Opciones");
                System.out.println(" " +line);
                jTextArea2.append(line.substring(2));
            }
  	    else output.append(line + "\n");        	
        }
      }
    } catch (IOException ex) {
      ex.printStackTrace ();
    } finally {
      listener = null;
      input.hide ();
      validate ();
      try {
        o.close ();
      } catch (IOException ex) {
        ex.printStackTrace ();
      }
    }
  }

  public boolean handleEvent (Event e) {
    if ((e.target == input) && (e.id == Event.ACTION_EVENT)) {
      try {
        o.writeUTF ((String) e.arg);
            o.flush (); 
      } catch (IOException ex) {
        ex.printStackTrace();
        listener.stop ();
      }
      input.setText ("");
      return true;
    } else if ((e.target == this) && (e.id == Event.WINDOW_DESTROY)) {
      if (listener != null)
        listener.stop ();
      hide ();
      return true;
    }
    return super.handleEvent (e);
  }
  //----------------------------------------------------------------------      
  void fileExit_ActionPerformed(ActionEvent e) {
    System.exit(0);
  }
}
