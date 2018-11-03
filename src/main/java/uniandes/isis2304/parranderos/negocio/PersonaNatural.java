package uniandes.isis2304.parranderos.negocio;

/**
 * Clase para modelar el concepto Persona del negocio de SuperAndes
 */
public class PersonaNatural  implements VOPersonaNatural
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador ÚNICO de los bares
	 */
	private long id;
	/**
	 * Nombre de la persona que se encuentra en la tabla cliente
	 */
	private String nombre;
	/**
	 * Correo de la persona (Unico para cada persona)(Se encuentra en la tabla cliente)
	 */
	private String correo;
	 /**
	  * Cédula de la persona
	  */
	private Integer cedula;
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	/**
     * Constructor por defecto
     */
	public PersonaNatural()
	{
		this.nombre = "";
		this.correo = "";
		this.cedula = 0;
	}
	/**
	 * Constructor con valores
	 * @param pNombre - El nombre de la persona. Debe exixtir un cliente con dicho nombre.
	 * @param pCorreo - El correo de la persona. Debe exixtir un cliente con dicho correo.
	 * @param pCedula - La cedula de la persona. 
	 */
	public PersonaNatural(String pNombre, String pCorreo, Integer pCedula)
	{
		this.nombre = "";
		this.correo = "";
		this.cedula = 0;
	}
	/**
	 * @return El nombre de la persona
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @return El correo de la persona
	 */
	public String getCorreo() {
		return correo;
	}
	/**
	 * @return La cédula de la persona
	 */
	public Integer getCedula() {
		return cedula;
	}
	
	/**
	 * @param nombre - Nuevo nombre de la persona
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @param correo - Nuevo correo de la persona
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	/**
	 * @param cedula - nueva cédula de la persona
	 */
	public void setCedula(Integer cedula) {
		this.cedula = cedula;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de la persona
	 */
	public String toString() {
		return "Persona [nombre=" + nombre + ", correo=" + correo + ", cedula=" + cedula + "]";
	}
	
	
}
