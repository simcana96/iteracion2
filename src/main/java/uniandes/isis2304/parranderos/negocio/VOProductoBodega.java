package uniandes.isis2304.parranderos.negocio;

public class VOProductoBodega {
	
	/**
	 * Atributos
	 */
	
	private long id_producto;
	private long id_bodega;
	private int unidadesDisponibles;
	
	/**
	 * Constructor default
	 */

	public VOProductoBodega()
	{
		id_producto=0;
		id_bodega=0;
		unidadesDisponibles=0;
	}
	
	/**
	 * Constructor valores
	 */
	
	public VOProductoBodega(long pId_producto, long pId_bodega, int pUnidadesDisponibles)
	{
		id_producto=pId_producto;
		id_bodega=pId_bodega;
		unidadesDisponibles=pUnidadesDisponibles;
	}

	public long getId_producto() {
		return id_producto;
	}

	public void setId_producto(long id_producto) {
		this.id_producto = id_producto;
	}

	public long getId_bodega() {
		return id_bodega;
	}

	public void setId_bodega(long id_bodega) {
		this.id_bodega = id_bodega;
	}

	public int getUnidadesDisponibles() {
		return unidadesDisponibles;
	}

	public void setUnidadesDisponibles(int unidadesDisponibles) {
		this.unidadesDisponibles = unidadesDisponibles;
	}
	
	
}
