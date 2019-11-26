package UT3.EJEMPLOS_DB4O.Ejercicio1Ampliacion;

import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;

class HiloServidor extends Thread {
	Socket socket;
	int identificador;
	static JLabel texto = new JLabel();	
	
	ObjectInputStream inObjeto;
	DataOutputStream salida;
	
	public HiloServidor(Socket s, int idCliente) throws IOException {//
		socket = s;
		identificador=idCliente;
		// flujo de salida -envio cadena
		salida = new DataOutputStream(socket.getOutputStream());
		// flujo de entrada -recibo objeto
		inObjeto = new ObjectInputStream(socket.getInputStream());		
	}
    //
	public void run() {
		try {
			AccesoDatos adat = new AccesoDatos();			 			
			while (true) {	
				Departamentos d= (Departamentos) inObjeto.readObject();							
				String cadena = adat.InsertarDep(d);			
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
				
				salida.close();
				socket.close();
				System.out.println("cerrando cliente");
			} catch (IOException ee) {
				ee.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} // de run()
}// ..HiloServidor
