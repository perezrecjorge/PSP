package UT2.CuentaBancaria2;

public class Persona2CB2 extends Thread {
    CuentaCB2 cuenta;
    String nombre;
    int cantidad;
    int n;

    public Persona2CB2(String nombre, CuentaCB2 cuenta) {
        this.nombre = nombre.toUpperCase();
        this.cuenta = cuenta;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 3; i++) {
                cuenta.reintegro((int) (Math.random() * 500 + 1), this.nombre);
                sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
