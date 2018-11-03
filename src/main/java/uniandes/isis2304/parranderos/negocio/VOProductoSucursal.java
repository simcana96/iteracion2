package uniandes.isis2304.parranderos.negocio;

public class VOProductoSucursal {
	
	/**
	 * Atributos
	 */
	
	private long id_producto;
	private long id_sucursal;
	private int precio_asignado;
	private int nivel_reorden;
	
	
	/**
	 * Constructor default
	 */
	
	public VOProductoSucursal()
	{
		id_producto=0;
		id_sucursal=0;
		precio_asignado=0;
		nivel_reorden=0;
	}
	
	/**
	 * Constructor valores
	 */
	
	public VOProductoSucursal(long pId_producto, long pId_sucursal, int pPrecio_asignado, int pNivel_reorden)
	{
		id_producto=pId_producto;
		id_sucursal=pId_sucursal;
		precio_asignado=pPrecio_asignado;
		nivel_reorden=pNivel_reorden;
	}

	public long getId_producto() {
		return id_producto;
	}

	public void setId_producto(long id_producto) {
		this.id_producto = id_producto;
	}

	public long getId_sucursal() {
		return id_sucursal;
	}

	public void setId_sucursal(long id_sucursal) {
		this.id_sucursal = id_sucursal;
	}

	public int getPrecio_asignado() {
		return precio_asignado;
	}

	public void setPrecio_asignado(int precio_asignado) {
		this.precio_asignado = precio_asignado;
	}

	public int getNivel_reorden() {
		return nivel_reorden;
	}

	public void setNivel_reorden(int nivel_reorden) {
		this.nivel_reorden = nivel_reorden;
	}
	
	

}
