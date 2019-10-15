package UT2.Productor_Consumidor_3;

public class Produc_Consum {
  public static void main(String[] args) {
      Cola cola = new Cola();

      Productor p1 = new Productor(cola, 1);
      Productor p2 = new Productor(cola, 2);
      Consumidor c1 = new Consumidor(cola, 1);
      Consumidor c2 = new Consumidor(cola, 2);

      p1.start();
      p2.start();
      c1.start();
      c2.start();

  }
}