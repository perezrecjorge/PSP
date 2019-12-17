package UT3.COAC;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Pantalla {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        InetAddress grupo = InetAddress.getByName("225.0.0.1");
        int puerto = 1209;
        MulticastSocket socket = new MulticastSocket(puerto);
        socket.joinGroup(grupo);

        byte[] recibidos = new byte[1024];
        DatagramPacket recibo = new DatagramPacket(recibidos,recibidos.length);

        while (true){
            socket.receive(recibo);
                ByteArrayInputStream bais = new ByteArrayInputStream(recibidos);
                ObjectInputStream in = new ObjectInputStream(bais);
                Mensaje mensaje = (Mensaje)in.readObject();
                System.out.println(mensaje);
                in.close();
        }

    }

}
