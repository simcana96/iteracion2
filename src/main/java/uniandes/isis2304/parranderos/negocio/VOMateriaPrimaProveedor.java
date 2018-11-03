package uniandes.isis2304.parranderos.negocio;

public class VOMateriaPrimaProveedor {

	//Atributos
	
	private long id_proveedor;
	
	private long id_materiaprima;
	
	private int calificacion;
	
	/**
	 * constructor por defecto
	 */
	public VOMateriaPrimaProveedor()
	{
		this.id_proveedor = 0 ;
		this.id_materiaprima = 0 ;
		this.calificacion = 0; 
	}
	
	/**
	 * constructor con valores
	 * @param id_proveedor
	 * @param id_materiaprima
	 */
	public VOMateriaPrimaProveedor(long id_proveedor , long id_materiaprima)
	{
		this.id_proveedor = id_proveedor;
		this.id_materiaprima = id_materiaprima;
		this.calificacion = 0;
	}

	public long getId_proveedor() {
		return id_proveedor;
	}

	public void setId_proveedor(long id_proveedor) {
		this.id_proveedor = id_proveedor;
	}

	public long getId_materiaprima() {
		return id_materiaprima;
	}

	public void setId_materiaprima(long id_materiaprima) {
		this.id_materiaprima = id_materiaprima;
	}

	public int getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}
	
	
}
