package uniandes.isis2304.parranderos.negocio;

/**
 * Interfaz para los métodos get de Persona.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 */
public interface VOPersonaNatural
{
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	/**
	 * @return El id de la persona natural. 
	 */
	public long getId();
	/**
	 * @return El nombre de la persona
	 */
	public String getNombre();
	/**
	 * @return El correo de la persona
	 */
	public String getCorreo();
	/**
	 * @return La cédula de la persona
	 */
	public Integer getCedula();
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de la persona
	 */
	public String toString();
}
