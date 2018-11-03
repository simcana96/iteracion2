package uniandes.isis2304.parranderos.negocio;

public class VOProductoFactura {

	private long id_factura; 
	
	private long id_producto;
	
	private int unidades_compradas;
	
	private int puntos_obtenidos;
	
	public VOProductoFactura()
	{
		this.id_factura = 0;
		this.id_producto = 0;
		this.unidades_compradas = 0;
		this.puntos_obtenidos = 0; 
	}
	
	public VOProductoFactura(long id_factura , long id_producto , int unidades_compradas , int puntos_obtenidos)
	{
		this.id_factura = id_factura;
		this.id_producto = id_producto;
		this.unidades_compradas = unidades_compradas;
		this.puntos_obtenidos = puntos_obtenidos; 
	}

	public long getId_factura() {
		return id_factura;
	}

	public void setId_factura(long id_factura) {
		this.id_factura = id_factura;
	}

	public long getId_producto() {
		return id_producto;
	}

	public void setId_producto(long id_producto) {
		this.id_producto = id_producto;
	}

	public int getUnidades_compradas() {
		return unidades_compradas;
	}

	public void setUnidades_compradas(int unidades_compradas) {
		this.unidades_compradas = unidades_compradas;
	}

	public int getPuntos_obtenidos() {
		return puntos_obtenidos;
	}

	public void setPuntos_obtenidos(int puntos_obtenidos) {
		this.puntos_obtenidos = puntos_obtenidos;
	}
	
}
