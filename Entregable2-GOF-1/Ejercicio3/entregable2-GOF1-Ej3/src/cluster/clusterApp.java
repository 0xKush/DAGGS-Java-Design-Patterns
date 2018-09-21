package cluster;

public class clusterApp {

	public static void main(String[] args) {
		/* Ejemplo simple de trabajo principal en serie:
		** Ejecuci�n de	Programa A seguida de ejecuci�n de subtrabajo en paralelo (ProgramaB y ProgramaC)
		*/
		Trabajo trabajoPrincipal = TrabajoBuilder.newSerial().thatRuns(new ProgramaA())
									.andAlsoRuns(TrabajoBuilder.newParalell().thatRuns(new ProgramaB())
																.andAlsoRuns(new ProgramaC())
																.build())
									.build();
		trabajoPrincipal.run();
	}
	
}
