package uniandes.isis2304.parranderos.negocio;

public class VOBodega {

	/**
	 * id de la bodega en la sucursal
	 */
	private long id_Bodega;
	
	private int capacidad_volumen;
	
	private int capacidad_peso;
	
	private int volumen_existencias;
	
	private String categoria_producto;
	
	private long id_sucursal;
	
	//METODOS
	
	/**
	 * Constructor por defecto
	 */
	public VOBodega()
	{
		this.id_Bodega = 0;
		this.capacidad_peso = 0;
		this.capacidad_volumen = 0;
		this.volumen_existencias = 0 ;
		this.categoria_producto = "";
		this.id_sucursal = 0;
	}
	/**
	 * constructor de la bodega
	 * @param id_Bodega
	 * @param capacidad_volumen
	 * @param capacidad_peso
	 * @param volumen_existencias
	 * @param categoria_producto
	 * @param id_sucursal
	 */
	public VOBodega(long id_Bodega,int capacidad_volumen , int capacidad_peso, int volumen_existencias , String categoria_producto, long id_sucursal)
	{
		this.id_Bodega = id_Bodega;
		this.capacidad_volumen = capacidad_volumen;
		this.capacidad_peso = capacidad_peso;
		this.volumen_existencias = volumen_existencias;
		this.categoria_producto = categoria_producto;
		this.id_sucursal = id_sucursal;
	}

	public long getId_Bodega() {
		return id_Bodega;
	}

	public void setId_Bodega(long id_Bodega) {
		this.id_Bodega = id_Bodega;
	}

	public int getCapacidad_volumen() {
		return capacidad_volumen;
	}

	public void setCapacidad_volumen(int capacidad_volumen) {
		this.capacidad_volumen = capacidad_volumen;
	}

	public int getCapacidad_peso() {
		return capacidad_peso;
	}

	public void setCapacidad_peso(int capacidad_peso) {
		this.capacidad_peso = capacidad_peso;
	}

	public int getVolumen_existencias() {
		return volumen_existencias;
	}

	public void setVolumen_existencias(int volumen_existencias) {
		this.volumen_existencias = volumen_existencias;
	}

	public String getCategoria_producto() {
		return categoria_producto;
	}

	public void setCategoria_producto(String categoria_producto) {
		this.categoria_producto = categoria_producto;
	}

	public long getId_sucursal() {
		return id_sucursal;
	}

	public void setId_sucursal(long id_sucursal) {
		this.id_sucursal = id_sucursal;
	}
	
	@Override
	public String toString()
	{
		return "Bodega id : " + id_Bodega;
	}
	
//	public long limpiarBodegas ()
//	{
//        long borrrados = ps.limpiarBodegas();	
//        return borrrados;
//	}
	
	
}
