package UT3.EJEMPLOS_DB4O.EJEMPLO_CONSULTA_DB4O;

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
	JButton boton = new JButton("Enviar");
	JButton desconectar = new JButton("Salir");
	boolean repetir = true;
	static Socket socket;	
	
	ObjectInputStream inObjeto;
	DataOutputStream salida;

	// constructor
	public ClienteBD(Socket s) {
		super("OPERACIONES CON BD");
		socket = s;
		try {
			// flujo de salida -envio cadena
			salida = new DataOutputStream(socket.getOutputStream());
			// flujo de entrada -recibo objeto
			inObjeto = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
		setLayout(null);
		etiqueta.setBounds(10, 10, 200, 30);
		add(etiqueta);
		// llenarLista();
		depconsultar.setBounds(210, 10, 50, 30);
		add(depconsultar);
		textarea1 = new JTextArea();
		scrollpane1 = new JScrollPane(textarea1);
		scrollpane1.setBounds(10, 50, 400, 300);
		add(scrollpane1);
		boton.setBounds(420, 10, 100, 30);
		add(boton);
		desconectar.setBounds(420, 50, 100, 30);
		add(desconectar);

		textarea1.setEditable(false);
		boton.addActionListener(this); 
		desconectar.addActionListener(this); 
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);//NADA
	}// constructor

	// acci�n cuando pulsamos botones
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == boton) { // ENVIAR DEP
			try {
				salida.writeUTF(depconsultar.getText());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if (e.getSource() == desconectar) { // SALIR
			try {
				socket.close();
			} catch (IOException e1) {				
				e1.printStackTrace();
			}
			System.exit(0);
			
		}
	}
	// PROCESO REPETITIVO-------------------------------------------------------------
	public void run() {
		String texto = "";
		while (repetir) {
			try {
				Departamentos d = null;				
				d = (Departamentos) inObjeto.readObject();//recibo un objeto
				textarea1.setText("");
				textarea1.setForeground(Color.blue);
				if (d == null) {					
					textarea1.setForeground(Color.red);
					PintaMensaje("    <<EL DEPARTAMENTO NO EXISTE>>");
				} else {					
					texto = "N�mero Dep: " + d.getDeptNo() + "\n   "
							+ " Nombre: " + d.getDnombre() + "\tLocalidad: "
							+ d.getLoc();					
					textarea1.append(texto);
					PintarEmpleados(d);// VISUALIZAR EMPLEADOS
				}
			}catch (SocketException s){				
				repetir=false;//se produce al cerrar socket en bot�n salir
			} catch (IOException e) {
				e.printStackTrace();
				repetir = false;
			} catch (ClassNotFoundException e) {			
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
	// PINTA LOS EMPLEADOS EN EL AREA
	private void PintarEmpleados(Departamentos d) {
		Set<Empleados> listaemple = d.getEmpleadoses();// obtenemos
		// empleados
		textarea1.setForeground(Color.blue);
		if (listaemple == null) {			
			PintaMensaje("EL DEPARTAMENTO NO TIENE EMPLEADOS");
		} else {			
			PintaMensaje("EMPLEADOS DEL DEPARTAMENTO: " + listaemple.size());
			Iterator<Empleados> it = listaemple.iterator();
			while (it.hasNext()) {
				Empleados emple = new Empleados();
				emple = it.next();
				textarea1.append("\n" + emple.getEmpNo() + " * "
						+ emple.getApellido() + " * " + emple.getOficio()
						+ " * " + emple.getSalario());
			}
			textarea1
					.append("\n=========================================================");
		}
	}//..
	// PINTA CABECERAS
    void PintaMensaje(String mensaje) {
		textarea1
				.append("\n=========================================================");
		textarea1.append("\n" + mensaje);
		textarea1
				.append("\n=========================================================");

	}//..
	// MAIN----------------------------------------------------------
	public static void main(String args[]) throws UnknownHostException,
			IOException {
		int puerto = 44441;
		//
		//Socket s = new Socket("192.168.0.194", puerto);
		Socket s = new Socket("localhost", puerto);
		ClienteBD hiloC = new ClienteBD(s);
		hiloC.setBounds(0, 0, 540, 400);
		hiloC.setVisible(true);
		new Thread(hiloC).start();
	}// fin main -----------------------------------------------------
}// Fin del CLIENTE