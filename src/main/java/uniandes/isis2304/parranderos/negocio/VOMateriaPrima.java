package uniandes.isis2304.parranderos.negocio;

public class VOMateriaPrima {

	//Atributos
	
	private long id_materia_prima;
	
	private String nombre;
	
	private long id_producto;
	
	//Metodos
	/**
	 * constructor por defecto
	 */
	public VOMateriaPrima()
	{
		this.id_materia_prima = 0;
		this.nombre = "";
		this.id_producto = 0;
	}
	
	/**
	 * constructor con valores
	 * @param id_materia_prima
	 * @param nombre
	 * @param id_producto
	 */
	public VOMateriaPrima(long id_materia_prima,String nombre,long id_producto)
	{
		this.id_materia_prima = id_materia_prima;
		this.nombre = nombre;
		this.id_producto = id_producto;
	}

	public long getId_materia_prima() {
		return id_materia_prima;
	}

	public void setId_materia_prima(long id_materia_prima) {
		this.id_materia_prima = id_materia_prima;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public long getId_producto() {
		return id_producto;
	}

	public void setId_producto(long id_producto) {
		this.id_producto = id_producto;
	}
	
}
