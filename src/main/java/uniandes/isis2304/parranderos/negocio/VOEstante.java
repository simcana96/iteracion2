package uniandes.isis2304.parranderos.negocio;

public class VOEstante {

	/**
	 * id de la bodega en la sucursal
	 */
	private long id_Estante;
	
	private int capacidad_volumen;
	
	private int capacidad_peso;
	
	private int volumen_existencias;
	
	private String categoria_producto;
	
	private long id_sucursal;
	
	//METODOS
	
	/**
	 * Constructor por defecto
	 */
	public VOEstante()
	{
		this.id_Estante = 0;
		this.capacidad_peso = 0;
		this.capacidad_volumen = 0;
		this.volumen_existencias = 0 ;
		this.categoria_producto = "";
		this.id_sucursal = 0;
	}
	
	/**
	 * constructor con valores 
	 * @param id_Estante
	 * @param capacidad_volumen
	 * @param capacidad_peso
	 * @param volumen_existencias
	 * @param categoria_producto
	 * @param id_sucursal
	 */
	public VOEstante(long id_Estante,int capacidad_volumen , int capacidad_peso, int volumen_existencias , String categoria_producto, long id_sucursal)
	{
		this.id_Estante = id_Estante;
		this.capacidad_volumen = capacidad_volumen;
		this.capacidad_peso = capacidad_peso;
		this.volumen_existencias = volumen_existencias;
		this.categoria_producto = categoria_producto;
		this.id_sucursal = id_sucursal;
	}

	public long getId_Estante() {
		return id_Estante;
	}

	public void setId_Estante(long id_Estante) {
		this.id_Estante = id_Estante;
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

	
}
