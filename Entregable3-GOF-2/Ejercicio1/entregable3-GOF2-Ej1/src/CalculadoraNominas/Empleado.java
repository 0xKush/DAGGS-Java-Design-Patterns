package CalculadoraNominas;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Empleado {

	private String nombre;
	private Nomina nominaBase;
	private int a�osTrabajados;
	private boolean cargoGestion;
	
	public Empleado(String nombre, Nomina base, String a�osT, String cargoGestion) {
		this.nombre = nombre;
		this.nominaBase =  base;
		this.a�osTrabajados = Integer.parseInt(a�osT);
		this.cargoGestion = ( cargoGestion.equals("SI") ? true : false );
	}
	
	public float importeNomina() {
		Nomina n = this.nominaBase;
		int a�os = this.a�osTrabajados;
		int multiplicador = 0;
		// sexenios
		multiplicador = a�os/6;
		n = new Sexenio(n, multiplicador);
		// quinquenios
		multiplicador = a�os/5;
		n = new Quinquenio(n, multiplicador);
		// trienio
		multiplicador = a�os/3;
		n = new Trienio(n, multiplicador);
		//cargoGestion
		n = new CargoGestion(n,this.cargoGestion);
		
		return n.calcularNomina();
	}
	
	public static List<Empleado> leerEmpleados(String fileName) {
		Scanner scan = null;
		List<Empleado> empleados = new ArrayList<Empleado>();
		File inputFile = new File(fileName);
		try {
			scan = new Scanner(inputFile);
		} catch (FileNotFoundException e) {
			System.err.println("the file " + inputFile.getAbsolutePath()
			+ " does not exists: " + e.getMessage());
			System.exit(1);
		}
		while(scan.hasNextLine()) {
			String[] tokens = scan.nextLine().split("\t");
			if (tokens.length != 4) {
				throw new IllegalArgumentException(
						"the line does not contain 4 tokens");
			}else {
				Nomina nominaBase = ( tokens[1].equals("A") ? new EscalaA() : 
					( tokens[1].equals("B") ? new EscalaB() : new EscalaC() ) );
				empleados.add(new Empleado(tokens[0], nominaBase, tokens[2], tokens[3]));
			}
		}
		scan.close();
		
		return empleados;
	}


	public String getNombre() {
		return nombre;
	}
	
	
}
