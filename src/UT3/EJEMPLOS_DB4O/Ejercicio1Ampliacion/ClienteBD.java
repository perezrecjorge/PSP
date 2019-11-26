package UT3.EJEMPLOS_DB4O.Ejercicio1Ampliacion;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ClienteBD extends JFrame implements ActionListener, Runnable {
	private static final long serialVersionUID = 1L;
	JTextField depar = new JTextField(2);
	JTextField nombre = new JTextField();
	JTextField loc = new JTextField();
	Socket socket = null;

	ObjectOutputStream outObjeto;
	DataInputStream entrada;

	JLabel etiqueta = new JLabel("");
	JLabel Lnumero = new JLabel("N� de departamento:");
	JLabel Lnombre = new JLabel("Nombre:");
	JLabel Lloc = new JLabel("Localidad:");

	JButton btnAlta = new JButton("Alta");
	JButton btnLimpiar = new JButton("Limpiar");

	// Inicia la pantalla
	public ClienteBD(Socket s) {
		super("ALTA DE DEPARTAMENTOS");
		socket = s;
		try {
			entrada = new DataInputStream(socket.getInputStream());
			outObjeto = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e1) {
			System.out.println("ERROR DE E/S");
			e1.printStackTrace();
			System.exit(0);
		}

		JLabel cab = new JLabel("GESTI�N DE DEPARTAMENTOS");
		setLayout(null);
		cab.setBounds(new Rectangle(30, 15, 200, 25));
		add(cab);

		Lnumero.setBounds(new Rectangle(75, 50, 140, 20));
		depar.setBounds(new Rectangle(220, 50, 80, 20));

		Lnombre.setBounds(new Rectangle(75, 75, 120, 20));
		nombre.setBounds(new Rectangle(150, 75, 175, 20));

		Lloc.setBounds(new Rectangle(75, 105, 120, 20));
		loc.setBounds(new Rectangle(150, 105, 250, 20));

		etiqueta.setBounds(new Rectangle(100, 150, 400, 20));
		add(etiqueta);

		add(Lnumero);
		add(depar, null);
		add(Lnombre);
		add(nombre);
		add(Lloc);
		add(loc);

		btnAlta.setBounds(new Rectangle(30, 150, 200, 30));
		btnLimpiar.setBounds(new Rectangle(270, 150, 200, 30));

		add(btnAlta);
		add(btnLimpiar);

		btnAlta.addActionListener(this); // pulsamos el bot�n
		btnLimpiar.addActionListener(this); // pulsamos el bot�n

		// CERRAMOS VENTANA ---------------------------------------------------
		addWindowListener(new WindowListener() {
			public void windowClosing(WindowEvent we) {				
				try { // CERRAMOS SOCKET
					socket.close();
					System.out.println("Cliente cerrado  .....");
					System.exit(0);
				} catch (IOException e) {
					System.err.println("NO SE PUEDE CERRAR socket cliente."
							+ e.getMessage());
					System.exit(0);
				}
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
			}
		});

	}// fin constructor
		//

	public void actionPerformed(ActionEvent e) // acci�n cuando pulsamos botones
	{
		int dep;
		try {
			dep = Integer.parseInt(depar.getText());
			if (e.getSource() == btnAlta) { // SE PULSA EL BOTON ALTA
				InsertarDep(dep, nombre.getText(), loc.getText());
			} else if (e.getSource() == btnLimpiar) {
				// SE PULSA LIMPIAR
				LimpiarCampos();
			}
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(null, " DEPARTAMENTO ERR�NEO ",
					"<<MENSAJE DEL CLIENTE>>", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
	}// fin acci�n de botones

	//
	void InsertarDep(int num, String nom, String loc) {// INSERTO UN DEPART.
		Departamentos d = new Departamentos();
		if(nom.trim().length()==0 || loc.trim().length()==0)
			JOptionPane
			.showMessageDialog(null, " HAY DATOS NULOS ", "<<MENSAJE DEL CLIENTE>>",
					JOptionPane.INFORMATION_MESSAGE);
		else {
			
		if (nom.length() > 15)
			nom = nom.substring(0, 15);
		d.setDnombre(nom);
		if (loc.length() > 15)
			loc = loc.substring(0, 15);
		d.setLoc(loc);
		d.setDeptNo((byte) num);
		
		// envio departamento
		try {
			outObjeto.writeObject(d);// envio objeto
			String texto = entrada.readUTF(); // recibo contestaci�n
			JOptionPane
					.showMessageDialog(null, texto, "<<MENSAJE DEL SERVIDOR>>",
							JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e) {
			e.printStackTrace();
		}
		}

	}// fin InsertarDep

	//
	void LimpiarCampos() {
		depar.setText("");
		nombre.setText("");
		loc.setText("");
		etiqueta.setText("");
	}

	// MAIN
	public static void main(String args[]) {
		int puerto = 44441;
		// si se conecta a maquina remota poner IP, por ej "192.168.0.194"
		Socket s = null;
		try {
			s = new Socket("localhost", puerto);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,
					"IMPOSIBLE CONECTAR CON EL SERVIDOR\n" + e.getMessage(),
					"<<MENSAJE DE ERROR:1>>", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}

		ClienteBD cliente = new ClienteBD(s);
		cliente.setBounds(0, 0, 500, 250);
		cliente.setVisible(true);
		new Thread(cliente).start();

	}// main

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}// fin de la clase Pantalla
