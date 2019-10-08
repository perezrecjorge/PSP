package UT2.Ejemplos;// ejemplo del libro

import java.applet.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.*;


public class Reloj extends Applet implements Runnable {
    private Thread hilo = null;
    private Font fuente; // tipo de letra para la hora
    private String horaActual = "";

    /*
     * Método init() Se ejecuta al lanzar la applet. Inicializamos la fuente y
     * lanzamos el hilo.
     */
    public void init() {
        fuente = new Font("Verdana", Font.BOLD, 26);

    }

    public void start()
    {
        if (hilo==null)
        {
            hilo = new Thread (this);
            hilo.start();
        }
    }
    /*
     * Método run() Se ejecuta al lanzar el hilo. Cada segundo actualiza la hora
     * y redibuja la ventana.
     */
    public void run() {
        Thread hiloActual = Thread.currentThread();
        while (hilo==hiloActual)  //La condición deja de ser cierta cuando se detiene el applet,
        // por lo que se invoca al método stop que hace que hilo valga null.
        {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            /* obtenemos el "momento actual" */
            Calendar cal = Calendar.getInstance();
            /* obtenemos la hora correspondiente a ese "momento actual" */
            horaActual = sdf.format(cal.getTime());
            repaint();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {}
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

    public void stop()
    {
        hilo = null;
    }
}