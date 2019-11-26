package UT3.EJEMPLOS_DB4O.Ejercicio2Capitulo3;

import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;

class HiloServidor extends Thread {
	Socket socket;
	int identificador;
	static JLabel texto = new JLabel();	
	DataInputStream entrada;
	DataOutputStream salida;
	public HiloServidor(Socket s, int idCliente) throws IOException {//
		socket = s;
		identificador=idCliente;
		entrada = new DataInputStream(socket.getInputStream());
		salida =new DataOutputStream(socket.getOutputStream());
	}
    //
	public void run() {
		try {
			AccesoDatos adat = new AccesoDatos();			 			
			while (true) {				
				String depar = entrada.readUTF();							
				String cadena = adat.listado(depar);			
				salida.writeUTF(cadena); //envio resultado
			} 
		} catch (IOException e) {
			// cuando un cliente Cierra la conexion
			Servidor.conexiones--;
			Servidor.numconex.setText(Servidor.conexiones.toString());
			texto.setText("<<LIBERADA LA CONEXION: "+ identificador+" >>\n");			
			texto.setForeground(Color.red);
			Servidor.area.append(texto.getText());
			try {				
				entrada.close();
				salida.close();
				socket.close();
				System.out.println("cerrando cliente");
			} catch (IOException ee) {
				ee.printStackTrace();
			}
		}
	} // de run()
}// ..HiloServidor
