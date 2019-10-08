/* Extendiendo la clase Thread
 *
 * El hilo escribe 5 líneas
 *
 * La función main crea 3 hilos
 */

public class HiloEjemplo1_02 extends Thread {
    private int c; // contador de cada hilo
    private int hilo;

    // constructor
    public HiloEjemplo1_02(int hilo) {
        this.hilo = hilo;
        System.out.println("CREANDO HILO:" + hilo);
    }

    // metodo run
    public void run() {
        c = 0;
        while (c <= 5) {
            System.out.println("Hilo:" + hilo + " C = " + c);
            c++;
        }
    }

    // función principal
    public static void main(String[] args) {
        HiloEjemplo1_02 h = null;
        for (int i = 0; i < 3; i++) {
            h = new HiloEjemplo1_02(i + 1); // crear hilo
            h.start(); // iniciar hilo
        }
        System.out.println("3 HILOS CREADOS...");
    }
}
