/* Implementando interface Runnable

 *
 * Cada hilo escribe un número de líneas
 * que se determina al construirlo
 */
package UT2.ejemplos;

public class PrimerHiloR_06 implements Runnable {
    private int x;

    PrimerHiloR_06 (int x)
    {
        this.x=x;
    }

    public void run() {
        for (int i=0; i<x;i++)
            System.out.println("En el Hilo... "+i);
    }
}
