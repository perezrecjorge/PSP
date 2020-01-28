package UT3.COAC;

import java.io.*;

public class Mensaje implements Serializable {
    String nombre;
    String tipo;
    int clasificacion;

    public Mensaje(String nombre, String tipo, int clasificacion) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.clasificacion = clasificacion;
    }

    @Override
    public String toString(){
        return "Nombre: "+this.nombre+"\nTipo: "+this.tipo+"\nClasificaci�n: "+this.clasificacion;
    }


    public static Mensaje fromByteArray (byte [] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bais =new ByteArrayInputStream(bytes);
        ObjectInputStream in = new ObjectInputStream(bais);
        Mensaje mensaje = (Mensaje)in.readObject();
        in.close();
        return mensaje;
    }

    public byte [] toByteArray() throws IOException {
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bs);
        out.writeObject(this);
        out.close();
        byte[] bytes = bs.toByteArray();
        return bytes;
    }

}
