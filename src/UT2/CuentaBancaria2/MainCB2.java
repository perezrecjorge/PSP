package UT2.CuentaBancaria2;

public class MainCB2 {
    public static void main(String[] args) {
        CuentaCB2 cuenta = new CuentaCB2(250,800);
        Persona1CB2 p1 = new Persona1CB2("p1",cuenta);
        Persona2CB2 p2 = new Persona2CB2("p2",cuenta);

        p1.start();
        p2.start();

    }
}
