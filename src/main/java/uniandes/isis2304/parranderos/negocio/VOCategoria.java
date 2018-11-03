package uniandes.isis2304.parranderos.negocio;

public class VOCategoria {
	
	/**
	 * Atributos
	 */
	
	private String nombre;
	
	/**
	 * Constructor default 
	 */
	
	public VOCategoria()
	{
		nombre="";
	}

	/**
	 * Constructor valores
	 */
	public VOCategoria(String pNombre)
	{
		nombre=pNombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
