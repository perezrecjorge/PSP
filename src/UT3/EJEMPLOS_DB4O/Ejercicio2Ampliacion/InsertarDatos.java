package UT3.EJEMPLOS_DB4O.Ejercicio2Ampliacion;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class InsertarDatos {
	final static String BDPer = "EMPLEDEP.YAP";

	public static void main(String[] args) {
		ObjectContainer db = Db4oEmbedded.openFile(
				Db4oEmbedded.newConfiguration(), BDPer);
		
		Date hoy = new Date();
		Date fec = new Date(hoy.getTime());
		
		// -----
		Departamentos d1 = new Departamentos();	
		Empleados e1 = new Empleados(7369, d1, "S�NCHEZ", "EMPLEADO", 7566, fec,
				1040f, 0f);		
		Empleados e4 = new Empleados(7566, d1, "JIM�NEZ", "DIRECTOR", 7839, fec,
				2900f, 0f);
		Empleados e11 = new Empleados(7388, d1, "GIL", "EMPLEADO", 7566, fec,
				1440f, 33f);	
		Empleados e14 = new Empleados(7389, d1, "RAMOS", "EMPLEADO", 7566, fec,
				1140f, 0f);	
		
		Set<Empleados> empleados1 = new HashSet<Empleados>(0);
		
		empleados1.add(e1);empleados1.add(e11);
		empleados1.add(e4);empleados1.add(e14);
		d1.setDeptNo(10); d1.setDnombre("CONTABILIDAD"); d1.setLoc("SEVILLA");
		d1.setEmpleadoses(empleados1);		
		db.store(d1);
		db.store(e1);
		db.store(e4);
		
		//----------
		Departamentos d2 = new Departamentos();			
		Empleados e2 = new Empleados(7499, d2, "ARROYO", "VENDEDOR", 7782, fec,
				1500f, 390f );
		Empleados e3 = new Empleados(7521, d2, "SALA", "VENDEDOR", 7782, fec,
				1625f, 650f);
		Empleados e31 = new Empleados(7522, d2, "MARTIN", "VENDEDOR", 7782, fec,
				1425f, 50f);		
        
        Set<Empleados> empleados2 = new HashSet<Empleados>(0);
		empleados2.add(e2);
		empleados2.add(e3);empleados2.add(e31);
		d2.setDeptNo(30); d2.setDnombre("VENTAS"); d2.setLoc("BARCELONA");		
		
		d2.setEmpleadoses(empleados2);		
		db.store(d2);
		db.store(e2);
		db.store(e3);

		//----------------
		Departamentos d3 = new Departamentos();		
		Empleados e5 = new Empleados(7782, d3, "CEREZO", "DIRECTOR", 7839, fec,
				2885f, 0f );
		Empleados e6 = new Empleados(7839, d3, "REY", "PRESIDENTE", 0, fec, 4100f,0f);
		Set<Empleados> empleados3 = new HashSet<Empleados>(0);
		empleados3.add(e5);
		empleados3.add(e6);
		d3.setDeptNo(20); d3.setDnombre("INVESTIGACI�N"); d3.setLoc("MADRID");		
		d3.setEmpleadoses(empleados3);		
		db.store(d3);
		db.store(e5);
		db.store(e6);
		
		Departamentos d4 = new Departamentos(40, "PRODUCCI�N", "BILBAO", null);
		db.store(d4);

		ListadoEmpleados(db);
		ListaEmpleDep(10, db);//lista os empleados de un departamento

		db.close();
	}// MAIN
   
	//LISTA LOS EMPLEADOS DE UN DEPARTAMENTO CONCRETO
	private static void ListaEmpleDep(int i, ObjectContainer db) {		
		String nombredep;
		
		//OBTENER EL NOMBRE DEL DEPARTAMENTO
		Departamentos dep = new Departamentos(i, null, null, null);
		ObjectSet<Departamentos> result2 = db.queryByExample(dep);
		Departamentos d =null;
		if (result2.size() == 0)
			nombredep = "No existe el departamento: " + i;
		else {
			d = result2.next();
			nombredep = d.getDnombre();
			
		}		
		System.out.println("=========================================================");
		System.out.println("EMPLEADOS DEL DEPARTAMENTO "+ i + ": "+nombredep);
		System.out.println("=========================================================");
		
		Set<Empleados> listaemple = d.getEmpleadoses();//obtenemos empleados
		Iterator <Empleados> it = listaemple.iterator();
		System.out.println("N�mero de empleados: "+listaemple.size() );	
		while(it.hasNext()) {
			Empleados emple = new Empleados();
			emple=it.next();
  	    	System.out.println(emple.getEmpNo()+"*"+emple.getApellido() + " * " +emple.getSalario());
		 }
		System.out.println("==============================" );

				
	}//ListaEmpleDep

	// LISTADO DE TODOS LOS EMPLEADOS
	private static void ListadoEmpleados(ObjectContainer db) {

		Empleados per = new Empleados();//null, null, null, null, null, null, null, null);
		String nombredep;
		ObjectSet<Empleados> result = db.queryByExample(per);
		if (result.size() == 0)
			System.out.println("No existen EMPLEADOS..");
		else {
			System.out.println("==============================================================");
			System.out.println("LISTA DE EMPLEADOS, N�mero de registros: " + result.size());

			while (result.hasNext()) {
				Empleados p = result.next();
				Departamentos dep = p.getDepartamentos();				
				nombredep = dep.getDnombre();
				
				System.out.println("N�mEmp: " + p.getEmpNo());
				System.out.println("\tApellido:" + p.getApellido()
						+ ", Oficio:" + p.getOficio() + ", Salario: "
						+ p.getSalario() + ", Departamento: " + nombredep
						+ ", Fecha: " + p.getFechaAlt());
			}
			System.out.println("==================================================================");
		}
	}// ListadoEmpleados
}// clase
