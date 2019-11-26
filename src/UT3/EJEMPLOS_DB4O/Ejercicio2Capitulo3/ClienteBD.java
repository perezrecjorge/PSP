package UT3.EJEMPLOS_DB4O.Ejercicio2Capitulo3;

import javax.swing.*;
import java.awt.event.*;
import java.net.*;
import java.util.*;
import java.io.*;
import java.awt.*;

public class ClienteBD extends JFrame implements ActionListener, Runnable {	
	private static final long serialVersionUID = 1L;
	static JTextField depconsultar = new JTextField(2);
	static JLabel etiqueta = new JLabel("Departamento a consultar:");
	private JScrollPane scrollpane1;
	static JTextArea textarea1;
	
	JButton desconectar = new JButton("Salir");
	boolean repetir = true;
	static Socket socket;		

    JRadioButton listaEmple =new JRadioButton("Listado de Empleados");
    JRadioButton listaDepar =new JRadioButton("Listado de Departamentos");   
    ButtonGroup grupo = new  ButtonGroup();     	
	
	//ObjectInputStream inObjeto;
	DataOutputStream salida;
	DataInputStream entrada;

	// constructor
	public ClienteBD(Socket s) {
		super("OPERACIONES CON BD - EJERCICIO 2");
		socket = s;
		try {
			// flujo de salida -envio cadena
			salida = new DataOutputStream(socket.getOutputStream());
			// flujo de entrada -recibo objeto
			//inObjeto = new ObjectInputStream(socket.getInputStream());
			entrada = new DataInputStream(socket.getInputStream());		
			
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
		setLayout(null);	

		textarea1 = new JTextArea();
		scrollpane1 = new JScrollPane(textarea1);
		scrollpane1.setBounds(10, 50, 400, 300);
		add(scrollpane1);
	
		 Font f = new Font( "Courier",Font.PLAIN,12 );
		 textarea1.setFont(f);	 
		 
		desconectar.setBounds(420, 10, 100, 30);
		add(desconectar);

	    listaEmple.setBounds(new Rectangle(10,10,190,25));
	    add(listaEmple);
	  
	    listaDepar.setBounds(new Rectangle(210,10,190,25));
	    add(listaDepar);
	    grupo.add(listaEmple);
	    grupo.add(listaDepar);
		
		textarea1.setEditable(false);
		listaEmple.addActionListener(this);
		listaDepar.addActionListener(this); 
	
		desconectar.addActionListener(this); 
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);//NADA
	}// constructor

	// acci�n cuando pulsamos botones
	public void actionPerformed(ActionEvent e) {
	
		if (e.getSource() == desconectar) { // SALIR
			try {
				socket.close();
			} catch (IOException e1) {				
				e1.printStackTrace();
			}
			System.exit(0);			
		}		
		if (e.getSource()==listaEmple) {	
			//consultar empleados 
			try {			
				salida.writeUTF("EMP");				 
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if (e.getSource()==listaDepar) {
			//consultar departamentos
			try {			
				salida.writeUTF("DEP");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	// PROCESO REPETITIVO-------------------------------------------------------------
	public void run() {
		String texto = "";		
		while (repetir) {
						
			try {							
				String cadena= entrada.readUTF();
				textarea1.setText("");
				textarea1.setForeground(Color.blue);
				textarea1.append(cadena);				
				
			}catch (SocketException s){					
				repetir=false;//se produce al cerrar socket en bot�n salir
			} catch (IOException e) {
				e.printStackTrace();
				repetir = false;
			} 
		}
		try {			
			socket.close(); // CERRAR SOCKET
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}// fin run -----------------------------------------------------------------
	
	
	// MAIN----------------------------------------------------------
	public static void main(String args[]) throws UnknownHostException,
			IOException {
		int puerto = 44441;
		//
		Socket s = new Socket("localhost", puerto);
		ClienteBD hiloC = new ClienteBD(s);
		hiloC.setBounds(0, 0, 540, 400);
		hiloC.setVisible(true);
		new Thread(hiloC).start();
	}// fin main -----------------------------------------------------
}// Fin del CLIENTE