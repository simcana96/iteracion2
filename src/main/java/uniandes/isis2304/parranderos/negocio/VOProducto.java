package uniandes.isis2304.parranderos.negocio;

import java.sql.Timestamp;

public class VOProducto {

	private long id_producto;
	
	private String nombre ; 
	
	private String marca; 
	
	private String unidad_medida;
	
	private String especificacion_empaque;
	
	private String codigo_barras;
	
	private Timestamp fecha_vencimiento;
	
	private String categoria_producto;
	
	private int cantidad_presentacion;
	
	/**
	 * constructor por defecto
	 */
	public VOProducto()
	{
		this.id_producto = 0;
		this.nombre = "";
		this.marca = "";
		this.unidad_medida = "";
		this.especificacion_empaque = "";
		this.codigo_barras = "";
		this.fecha_vencimiento = new Timestamp(0);
		this.categoria_producto = "";
		this.cantidad_presentacion = 0 ;
	}
	/**
	 * constructor con valores
	 * @param id_producto
	 * @param nombre
	 * @param unidad_medida
	 * @param especificacion_empaque
	 * @param codigo_barras
	 * @param fecha_vencimiento
	 * @param categoria_producto
	 * @param cantidad_presentacion
	 */
	public VOProducto(long id_producto , String nombre , String unidad_medida , String especificacion_empaque,
			String codigo_barras , Timestamp fecha_vencimiento , String categoria_producto, int cantidad_presentacion)
	{
		this.id_producto = id_producto;
		this.nombre = nombre;
		this.marca = marca;
		this.unidad_medida = unidad_medida;
		this.especificacion_empaque = especificacion_empaque;
		this.codigo_barras = codigo_barras;
		this.fecha_vencimiento = fecha_vencimiento;
		this.categoria_producto = categoria_producto;
		this.cantidad_presentacion = cantidad_presentacion;
	}

	public long getId_producto() {
		return id_producto;
	}

	public void setId_producto(long id_producto) {
		this.id_producto = id_producto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getUnidad_medida() {
		return unidad_medida;
	}

	public void setUnidad_medida(String unidad_medida) {
		this.unidad_medida = unidad_medida;
	}

	public String getEspecificacion_empaque() {
		return especificacion_empaque;
	}

	public void setEspecificacion_empaque(String especificacion_empaque) {
		this.especificacion_empaque = especificacion_empaque;
	}

	public String getCodigo_barras() {
		return codigo_barras;
	}

	public void setCodigo_barras(String codigo_barras) {
		this.codigo_barras = codigo_barras;
	}

	public Timestamp getFecha_vencimiento() {
		return fecha_vencimiento;
	}

	public void setFecha_vencimiento(Timestamp fecha_vencimiento) {
		this.fecha_vencimiento = fecha_vencimiento;
	}

	public String getCategoria_producto() {
		return categoria_producto;
	}

	public void setCategoria_producto(String categoria_producto) {
		this.categoria_producto = categoria_producto;
	}

	public int getCantidad_presentacion() {
		return cantidad_presentacion;
	}

	public void setCantidad_presentacion(int cantidad_presentacion) {
		this.cantidad_presentacion = cantidad_presentacion;
	}
	public long getId() {
		// TODO Auto-generated method stub
		return id_producto;
	}
	
	
}
