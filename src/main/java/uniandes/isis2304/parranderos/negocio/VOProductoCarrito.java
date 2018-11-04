package uniandes.isis2304.parranderos.negocio;

public interface VOProductoCarrito 
{
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	/**
	* @return El id del carrito 
	*/
	public long getId_Carrito();
	
	/**
	 * @return El id del producto 
	 */
	public long getId_Producto();
	
	/**
	 * @return El id del producto 
	 */
	public long getId_Estante();
	
	/**
	 * @return La cantidad del  producto que queremos
	 */
	public int getCantidad_Productos();
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del carrito
	 */
	public String toString();
}
