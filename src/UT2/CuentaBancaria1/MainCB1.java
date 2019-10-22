package UT2.CuentaBancaria1;

public class MainCB1 {
    public static void main(String[] args) {
        CuentaCB1 cuenta = new CuentaCB1(250,800);
        PersonaCB1 p1 = new PersonaCB1("p1",cuenta);
        PersonaCB1 p2 = new PersonaCB1("p2",cuenta);
        PersonaCB1 p3 = new PersonaCB1("p3",cuenta);

        p1.start();
        p2.start();
        p3.start();

    }


}
