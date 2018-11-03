package uniandes.isis2304.parranderos.negocio;

public class VOProveedor{

	/**
	 * Atributos
	 */
	
	private long nit;
	private String nombre;
	private int calificacion;
	
	/**
	 * Constructor default
	 */
	
	public VOProveedor()
	{
		nit =0;
		nombre="";
		calificacion=0;
	}
	
	/**
	 * Constructor valores
	 */
	
	public VOProveedor(long pNit, String pNombre, int pCalificacion)
	{
		nit=pNit;
		nombre=pNombre;
		calificacion=pCalificacion;
	}
	
	public long getNit() {
		return nit;
	}

	public void setNit(long nit) {
		this.nit = nit;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}


	
}
