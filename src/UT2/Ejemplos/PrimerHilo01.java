package UT2.Ejemplos;/* Extendiendo la clase Thread
 *
 * El hilo escribe tantas líneas como se le pasa
 * por parámetro al crearlo.
 *
 * La función main crea un hilo para imprimir 10 líneas
 */

public class PrimerHilo01 extends Thread {
    private int x;

    PrimerHilo01 (int x)
    {
        this.x=x;
    }

    public void run() {
        for (int i=0; i<x;i++)
            System.out.println("En el Hilo... "+i);
    }

    public static void main(String[] args) {
        PrimerHilo01 p = new PrimerHilo01 (10);
        p.start();
    }
}