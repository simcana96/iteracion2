package uniandes.isis2304.parranderos.negocio;

/**
 * Clase para modelar el concepto CARRITO del negocio del supermercado
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
	private long id_Carrito;
	
	/**
	 * El identificador ÚNICO de la sucursal que tiene el carrito. 
	 */
	private long id_Sucursal;
	
	/**
	 * El identificador ÚNICO del cliente que usa el carrito. 
	 */
	private long id_Cliente;
	
	
	/* ****************************************************************
	 * 			Constructor
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public Carrito() 
	{
		this.id_Carrito= 0;
		this.id_Sucursal = 0;
		this.id_Cliente = 0;
		
	}

	/**
	 * Constructor con valores
	 * @param id_Carrito- id_Carritodel carrito 
	 * @param pid_Cliente - id_Carritodel cliente que usa el carrito
	 * @param pid_Sucursal - Id de la sucursal que tiene el carrito. 
	 */
	public Carrito(long id, long pid_Cliente, long pid_Sucursal) 
	{
		this.id_Carrito= id;
		this.id_Sucursal = pid_Sucursal;
		this.id_Cliente = pid_Cliente;
	}

	public long getId_Carrito() {
		return id_Carrito;
	}

	public long getId_Sucursal() {
		return id_Sucursal;
	}

	public long getId_Cliente() {
		return id_Cliente;
	}

	public void setId_Carrito(long id) {
		this.id_Carrito= id;
	}

	public void setId_Sucursal(long id_Sucursal) {
		this.id_Sucursal = id_Sucursal;
	}

	public void setId_Cliente(long id_Cliente) {
		this.id_Cliente = id_Cliente;
	}
	
	/**
	 * @return Una cadena con la información básica del carrito. 
	 */
	@Override
	public String toString() 
	{
		return "Carrito [id=" + id_Carrito+ ", id_Cliente=" + id_Cliente + ", id_Sucursal=" + id_Sucursal+ " ]";
	}
}
