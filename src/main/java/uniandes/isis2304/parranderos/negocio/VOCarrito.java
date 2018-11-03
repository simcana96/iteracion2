package uniandes.isis2304.parranderos.negocio;

/**
 * Interfaz para los métodos get de carrito.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 */
public interface VOCarrito
{
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	/**
	* @return El id del carrito 
	*/
	public long getId();
	
	/**
	 * @return El id del cliente que usa el carrito
	 */
	public long getIdCliente();
	
	/**
	 * @return El id de la sucursal que tiene el carrito 
	 */
	public long getIdSucursal();
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del carrito
	 */
	public String toString();
	
	
}
