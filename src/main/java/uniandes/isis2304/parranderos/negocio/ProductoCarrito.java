package uniandes.isis2304.parranderos.negocio;

/**
 * Clase para modelar el concepto PRODUCTOCARRITO del negocio del supermercado
 *
 */
public class ProductoCarrito implements VOProductoCarrito
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador ÚNICO del carrito
	 */
	private long id_Carrito;
	
	/**
	 * El identificador ÚNICO del producto.
	 */
	private long id_Producto;
	
	/**
	 * El identificador del estante en el que estaba el producto
	 */
	private long id_Estante;
	
	/**
	 * Cantidad del producto que se lleva en el carrito
	 */
	private int cantidad_Productos;
	
	
	
	/* ****************************************************************
	 * 			Constructor
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public ProductoCarrito() 
	{
		this.id_Carrito = 0;
		this.id_Producto= 0;
		this.cantidad_Productos = 0;
		
	}

	/**
	 * Constructor con valores
	 * @param pid_Carrito - Id del carrito que tiene un cliente.
	 * @param pIdProducto - Id del producto que está en el carrito.
	 * @param pCantidad - Cantidad del producto que está en el carrito.
	 */
	public ProductoCarrito(long pid_Carrito, long pIdProducto, int pCantidad, long pIdEstante) 
	{
		this.id_Carrito = pid_Carrito;
		this.id_Producto= pIdProducto;
		this.cantidad_Productos = pCantidad;
		this.id_Estante = pIdEstante;
	}

	public long getId_Carrito() {
		return id_Carrito;
	}

	public long getId_Producto() {
		return id_Producto;
	}

	public long getId_Estante() {
		return id_Estante;
	}

	public int getCantidad_Productos() {
		return cantidad_Productos;
	}

	public void setId_Carrito(long id_Carrito) {
		this.id_Carrito = id_Carrito;
	}

	public void setId_Producto(long id_Producto) {
		this.id_Producto = id_Producto;
	}

	public void setId_Estante(long id_Estante) {
		this.id_Estante = id_Estante;
	}

	public void setCantidad_Productos(int cantidad_Productos) {
		this.cantidad_Productos = cantidad_Productos;
	}

	
	
	
	
}
