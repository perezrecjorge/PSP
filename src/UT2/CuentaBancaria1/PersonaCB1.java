package UT2.CuentaBancaria1;


public class PersonaCB1 extends Thread {
    CuentaCB1 cuenta;
    String nombre;

    public PersonaCB1(String nombre,CuentaCB1 cuenta) {
        this.nombre=nombre.toUpperCase();
        this.cuenta = cuenta;
    }

    @Override
    public void run() {
        for (int i = 0; i < 2; i++) {
            try {
                cuenta.ingreso((int) (Math.random() * 500 + 1),this.nombre);
                sleep(1000);
                cuenta.reintegro((int) (Math.random() * 500 + 1),this.nombre);
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
