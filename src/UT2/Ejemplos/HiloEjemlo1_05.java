package UT2.Ejemplos;/* Extendiendo la clase Thread
 *
 * Ejemplo de uso de métodos de Thread
 */

public class HiloEjemplo2_05 extends Thread {

    public void run() {
        System.out.println("\tDentro del Hilo: " + this.getName() +
                ", Prioridad: "+this.getPriority() +
                ", ID: " +this.getId() );
    }

    //método principal
    public static void main(String[] args) {
        HiloEjemplo2_05 h = null;
        for (int i = 0; i < 3; i++) {
            h = new HiloEjemplo2_05(); //crear hilo
            h.setName("HILO-"+i); //damos nombre al hilo
            h.setPriority(i+1); //damos prioridad
            h.start(); //iniciar hilo
            System.out.println("Informacion del " +h.getName() +": "+ h.toString());
        }
        System.out.println("3 HILOS CREADOS...");
    }
}
