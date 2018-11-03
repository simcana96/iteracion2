package uniandes.isis2304.parranderos.negocio;

public class VOProductoEstante {

	private long id_producto;
	
	private long id_estante;
	
	private int unidades_disponibles;
	
	private int punto_abastecimiento;
	
	public VOProductoEstante()
	{
		this.id_producto = 0;
		this.id_estante = 0;
		this.unidades_disponibles = 0;
		this.punto_abastecimiento = 0;
	}
	public VOProductoEstante(long id_producto , long id_estante, int unidades_disponibles,
			int punto_abastecimiento)
	{
		this.id_producto = id_producto;
		this.id_estante = id_estante;
		this.unidades_disponibles = unidades_disponibles;
		this.punto_abastecimiento = punto_abastecimiento;
	}
	
	public long getId_producto() {
		return id_producto;
	}
	public void setId_producto(long id_producto) {
		this.id_producto = id_producto;
	}
	public long getId_estante() {
		return id_estante;
	}
	public void setId_estante(long id_estante) {
		this.id_estante = id_estante;
	}
	public int getUnidades_disponibles() {
		return unidades_disponibles;
	}
	public void setUnidades_disponibles(int unidades_disponibles) {
		this.unidades_disponibles = unidades_disponibles;
	}
	public int getPunto_abastecimiento() {
		return punto_abastecimiento;
	}
	public void setPunto_abastecimiento(int punto_abastecimiento) {
		this.punto_abastecimiento = punto_abastecimiento;
	}
	
}
