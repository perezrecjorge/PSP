package UT2.PingPong;

public class PingPong extends Thread {

    private String mensaje;
    private static String turno;

    public PingPong (String msg){
        this.mensaje = msg;
    }

    @Override
    public void run() {
        while(true) {
            comanzarJuego();
        }

    }

    public synchronized void comanzarJuego(){
        if (!mensaje.equals(turno)){
            try {
                turno=mensaje;
                Thread.currentThread().sleep(1000);
                System.out.println(mensaje);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

/*
    public void comanzarJuego() {
        synchronized(PingPong.class) {
            if (!mensaje.equals(turno)){
                turno=mensaje;
                try {
                    System.out.println(mensaje);
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

 */
}
