package UT2.Productor_Consumidor_3;
public class Cola {
    private int numero;
    private boolean disponible = false;//inicialmente cola vacia
	private int turno = 1;

    public synchronized int get(int valor, int numeroconsumidor) {
    	  while (!disponible || numeroconsumidor != turno) {
    	    try {
    	          wait();
    	    } catch (InterruptedException e) { }
    	  }

    	  System.out.println(valor + "-> Consumidor " + numeroconsumidor + ": Se consume: " + numero);
    	  disponible = false;

    	  if (turno == 1) {
    	  	turno = 2;
		  } else {
    	  	turno = 1;
		  }

    	  notifyAll();
    	  return numero;
    	}


    public synchronized void put(int valor, int numeroproductor) {
    	  while (disponible || numeroproductor != turno){
    	    try {
    	          wait();
    	    } catch (InterruptedException e) { }
    	  }
    	  disponible = true;
    	  System.out.println(valor + "-> Productor " + numeroproductor + ": Se produce: " + numero);
    	  notifyAll();
    	}

}
