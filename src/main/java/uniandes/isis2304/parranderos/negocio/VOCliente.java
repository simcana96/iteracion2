package uniandes.isis2304.parranderos.negocio;

public class VOCliente {
	
	/**
	 *  Atributos Cliente
	 */

	private long id_cliente;
	private String nombre;
	private String correo;
	private int puntos;
	 
	
	
	/**
	 * Constructor por defecto
	 */
	
	public VOCliente()
	{
		this.id_cliente=0;
		this.nombre="";
		this.correo="";
		this.puntos=0;
		
	}
	


	/**
	 * Constructor con valores
	 */
	
	public VOCliente(long pId_cliente, String pNombre, String pCorreo, int pPuntos)
	{
		this.id_cliente=pId_cliente;
		this.nombre=pNombre;
		this.correo=pCorreo;
		this.puntos=pPuntos;
	}

	public long getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(long id_cliente) {
		this.id_cliente = id_cliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
}
