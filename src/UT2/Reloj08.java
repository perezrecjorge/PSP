
import java.applet.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.*;

/* Applet que muestra la hora actual actualizándose
 * cada segundo.
 *
 * Una applet no tiene método main(), podemos ejecutarla:
 * - desde Eclipse
 * - incluyendo <applet code="Reloj08.class" width="200" height="100"> </applet>
 * en un fichero HTML. El fichero HTML se puede abrir de dos formas:
 * * Desde un navegador (mostrará una web con la applet si están habilitadas)
 * * Desde la consola con el visor: appletviewer fichero.html
 *
 */

@SuppressWarnings("serial")
public class Reloj08 extends Applet implements Runnable {
    private Thread hilo = null;
    private Font fuente; // tipo de letra para la hora
    private String horaActual = "";

    /*
     * Método init() Se ejecuta al lanzar la applet. Inicializamos la fuente y
     * lanzamos el hilo.
     */
    public void init() {
        fuente = new Font("Verdana", Font.BOLD, 26);
        hilo = new Thread(this);
        hilo.start();
    }

    /*
     * Método run() Se ejecuta al lanzar el hilo. Cada segundo actualiza la hora
     * y redibuja la ventana.
     */
    public void run() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        while (true) {
            /* obtenemos el "momento actual" */
            Calendar cal = Calendar.getInstance();
            /* obtenemos la hora correspondiente a ese "momento actual" */
            horaActual = sdf.format(cal.getTime());
            repaint();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }

    /*
     * Método paint() Dibuja el contenido de la ventana con la hora.
     */
    public void paint(Graphics g) {
        g.clearRect(1, 1, getSize().width, getSize().height);
        setBackground(Color.yellow);// color de fondo
        g.setFont(fuente);// fuente
        g.drawString(horaActual, 20, 50);// muestra la hora
    }
}
