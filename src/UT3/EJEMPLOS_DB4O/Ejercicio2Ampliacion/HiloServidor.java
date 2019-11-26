package UT3.EJEMPLOS_DB4O.Ejercicio2Ampliacion;

import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;

class HiloServidor extends Thread {
	Socket socket;
	int identificador;
	static JLabel texto = new JLabel();	
	ObjectOutputStream outObjeto;
	DataInputStream entrada;
	public HiloServidor(Socket s, int idCliente) throws IOException {//
		socket = s;
		identificador=idCliente;
		entrada = new DataInputStream(socket.getInputStream());		
		outObjeto = new ObjectOutputStream(socket.getOutputStream());		
	}
    //
	public void run() {
		try {
			AccesoDatos adat = new AccesoDatos();			 			
			while (true) {				
				String depar = entrada.readUTF();
				System.out.println(" Recibo: "+depar);
				Empleados dep = adat.procesarCadena2(depar.trim());			
				// Se env�a el objeto
				outObjeto.writeObject(dep);
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
				outObjeto.close();
				socket.close();
				System.out.println("cerrando cliente");
			} catch (IOException ee) {
				ee.printStackTrace();
			}
		}
	} // de run()
}// ..HiloServidor
