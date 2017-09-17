package algoritmodecalidad;

public class Criterio {

	private String nombre;
	private String descripcion;
	private String caracteristicas;
	private String aceptable;
	private String medianamenteAceptable;
	private String noAceptable;
	private int nivelActual;
	private boolean esPonderada;
	
	public Criterio(
			String nombre,
			String descripcion, 
			String caracteristicas, 
			String aceptable, 
			String medianamenteAceptable,
			String noAceptable,
			boolean esPonderada) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.caracteristicas = caracteristicas;
		this.aceptable = aceptable;
		this.medianamenteAceptable = medianamenteAceptable;
		this.noAceptable = noAceptable;
		this.esPonderada = esPonderada;
		nivelActual = 0;
	}
	
	public int getNivelActual() {
		return nivelActual;
	}

	public void setNivelActual(int nivelActual) {
		this.nivelActual = nivelActual;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getCaracteristicas() {
		return caracteristicas;
	}

	public String getAceptable() {
		return aceptable;
	}

	public String getMedianamenteAceptable() {
		return medianamenteAceptable;
	}

	public String getNoAceptable() {
		return noAceptable;
	}
	
	public int GetNivelPonderado() {
		return esPonderada ? getNivelActual() * 2 : getNivelActual();
	}
}
