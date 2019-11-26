package UT3.EJEMPLOS_DB4O.Ejercicio2Capitulo3;

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

	// DEVUELVE CADENA CON EMPLEADOS O CON DEPARTAMENTOS
	synchronized String listado(String depar) {
		String cadena = "";
		int num;
		// listado de empleados
		if (depar.equals("EMP")) {
			Empleados per = new Empleados();
			ObjectSet<Empleados> result = db.queryByExample(per);
			num=result.size();
			if (num == 0)
				cadena = " \n No existen Registros de Empleados...";
			else {				
				cadena = " EMP_NO APELLIDO      OFICIO       SALARIO\n";
				cadena +=" =========================================\n";
				while (result.hasNext()) {
					Empleados p = result.next();
					cadena += " " + p.getEmpNo() + "   ";
					
					int n=p.getApellido().length();
					
					String blancos="";
					if (n<12){
						for(int i=n; i<=12; i++)
							blancos+=" ";
					   cadena= cadena +p.getApellido() + blancos + " ";
					}
					else
						 cadena= cadena +p.getApellido() + " ";
                    n=p.getOficio().length();
					
					blancos="";
					if (n<12){
						for(int i=n; i<=12; i++)
							blancos+=" ";
					   cadena= cadena +p.getOficio() + blancos + " ";
					}
					else
						 cadena= cadena +p.getOficio() + " ";
					cadena += p.getSalario() + " \n";
				}// while
				cadena += "\n       N�mero de empleados: "+num + "\n";
			}// else			

		}// if

		// listado de DEPARTAMENTOS
		else {
			Departamentos per = new Departamentos();
			ObjectSet<Departamentos> result = db.queryByExample(per);
			num=result.size();
			if (num == 0)
				cadena = " \n No existen Registros de Departamentos...";
			else {				
				cadena = " N�Dept  NOMBRE          LOCALIDAD  \n";
				cadena +=" =========================================\n";
				while (result.hasNext()) {
					Departamentos p = result.next();
					cadena += "   " + p.getDeptNo() + "    ";
										
					int n=p.getDnombre().length();
					
					String blancos="";
					if (n<15){
						for(int i=n; i<15; i++)
							blancos+=" ";
					   cadena= cadena +p.getDnombre() + blancos + " ";
					}
					else
						 cadena= cadena +p.getDnombre() + " ";
					
                    n=p.getLoc().length();					
					blancos="";
					if (n<15){
						for(int i=n; i<15; i++)
							blancos+=" ";
					   cadena= cadena +p.getLoc() + blancos + " ";
					}
					else
						 cadena= cadena +p.getLoc() + " ";
					cadena += " \n";
				}// while
				cadena += "\n       N�mero de departamentos: "+num + "\n";
			}// else	

		}//else

		return cadena;
	}
}// ..fin AccesoDatos
