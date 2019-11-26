package UT3.EJEMPLOS_DB4O.Ejercicio2Ampliacion;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

//ACCESO A BD db4o
public class AccesoDatos {
	static ObjectContainer db;

	// Constructor
	public AccesoDatos() {
		db = Conexion.getDBConexion();
	}

	// Se procesa la cadena que manda el hilo con el dep a localizar
	synchronized Departamentos procesarCadena(String str) {		
		int i;
		Departamentos d = null;
		try {
			i = Integer.parseInt(str);
		} catch (NumberFormatException n) {
			System.out.println("<<DEPARTAMENTO: " + str + " INCORRECTO>> ");
			return d;
		}
		Departamentos dep = new Departamentos(i, null, null, null);
		ObjectSet<Departamentos> result2 = db.queryByExample(dep);

		if (result2.size() == 0)
			System.out.println("<<DEPARTAMENTO: " + i + " NO EXISTE>> ");
		else {
			d = result2.next();
		}
		return d;// devuelvo un objeto Departamentos
	}//
    //DEVUELVE UN OBJETO EMPLEADO
	public Empleados procesarCadena2(String str) {
		int i;
		Empleados d = null;
		try {
			i = Integer.parseInt(str);
		} catch (NumberFormatException n) {
			System.out.println("<<EMPLEADO: " + str + " INCORRECTO>> ");
			return d;
		}
		Empleados dep = new Empleados(i, null);
		ObjectSet<Empleados> result2 = db.queryByExample(dep);

		if (result2.size() == 0)
			System.out.println("<<EMPLEADO: " + i + " NO EXISTE>> ");
		else {
			d = result2.next();
		}
		return d;// devuelvo un objeto Departamentos
	}
}//..fin AccesoDatos
