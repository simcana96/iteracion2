package uniandes.isis2304.parranderos.negocio;

/**
 * Clase para modelar el concepto BEBIDA del negocio de los Parranderos
 *
 */
public class Carrito implements VOCarrito
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador ÚNICO del carrito
	 */
	private long id;
	
	/**
	 * El identificador ÚNICO de la sucursal que tiene el carrito. 
	 */
	private long idSucursal;
	
	/**
	 * El identificador ÚNICO del cliente que usa el carrito. 
	 */
	private long idCliente;
	
	
	/* ****************************************************************
	 * 			Constructor
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public Carrito() 
	{
		this.id = 0;
		this.idSucursal = 0;
		this.idCliente = 0;
		
	}

	/**
	 * Constructor con valores
	 * @param id - Id del carrito 
	 * @param pIdCliente - Id del cliente que usa el carrito
	 * @param pIdSucursal - Id de la sucursal que tiene el carrito. 
	 */
	public Carrito(long id, long pIdCliente, long pIdSucursal) 
	{
		this.id = id;
		this.idSucursal = pIdSucursal;
		this.idCliente = pIdCliente;
	}

	public long getId() {
		return id;
	}

	public long getIdSucursal() {
		return idSucursal;
	}

	public long getIdCliente() {
		return idCliente;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setIdSucursal(long idSucursal) {
		this.idSucursal = idSucursal;
	}

	public void setIdCliente(long idCliente) {
		this.idCliente = idCliente;
	}
	
	/**
	 * @return Una cadena con la información básica del carrito. 
	 */
	@Override
	public String toString() 
	{
		return "Carrito [id=" + id + ", idCliente=" + idCliente + ", idSucursal=" + idSucursal+ " ]";
	}
}
