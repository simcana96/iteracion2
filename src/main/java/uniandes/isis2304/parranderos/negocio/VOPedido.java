package uniandes.isis2304.parranderos.negocio;

import java.sql.Timestamp;

public class VOPedido {
	
	/**
	 * Atributos
	 */
	
	private long id_pedido;
	private Timestamp fecha_acordada;
	private Timestamp fecha_entrega;
	private String estado;
	private int precio;
	private int calificacion;
	private int cantidad_pedir;
	private long id_sucursal;
	private long id_proveedor;
	private long id_materia_prima;
	
	/**
	 * Constructor default
	 */
	public VOPedido()
	{
		id_pedido=0;
		fecha_acordada=new Timestamp(0);
		fecha_entrega = new Timestamp(0);
		estado= "";
		precio= 0;
		calificacion = 0;
		cantidad_pedir= 0;
		id_sucursal=0;
		id_proveedor=0;
		id_materia_prima=0;
	}

	/**
	 * Constructor valores
	 */
	public VOPedido(long pId_pedido,Timestamp pFecha_acordada, Timestamp pFecha_entrega,String pEstado, int pPrecio, int pCalificacion, int pCantidad_pedir, long pId_sucursal, long pId_proveedor, long pId_materia_prima )
	{
		id_pedido=pId_pedido;
		fecha_acordada=pFecha_acordada;
		fecha_entrega = pFecha_entrega;
		estado= pEstado;
		precio= pPrecio;
		calificacion = pCalificacion;
		cantidad_pedir= pCantidad_pedir;
		id_sucursal=pId_sucursal;
		id_proveedor=pId_proveedor;
		id_materia_prima=pId_materia_prima;
	}

	public long getId_pedido() {
		return id_pedido;
	}

	public void setId_pedido(long id_pedido) {
		this.id_pedido = id_pedido;
	}

	public Timestamp getFecha_acordada() {
		return fecha_acordada;
	}

	public void setFecha_acordada(Timestamp fecha_acordada) {
		this.fecha_acordada = fecha_acordada;
	}

	public Timestamp getFecha_entrega() {
		return fecha_entrega;
	}

	public void setFecha_entrega(Timestamp fecha_entrega) {
		this.fecha_entrega = fecha_entrega;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public int getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}

	public int getCantidad_pedir() {
		return cantidad_pedir;
	}

	public void setCantidad_pedir(int cantidad_pedir) {
		this.cantidad_pedir = cantidad_pedir;
	}

	public long getId_sucursal() {
		return id_sucursal;
	}

	public void setId_sucursal(long id_sucursal) {
		this.id_sucursal = id_sucursal;
	}

	public long getId_proveedor() {
		return id_proveedor;
	}

	public void setId_proveedor(long id_proveedor) {
		this.id_proveedor = id_proveedor;
	}

	public long getId_materia_prima() {
		return id_materia_prima;
	}

	public void setId_materia_prima(long id_materia_prima) {
		this.id_materia_prima = id_materia_prima;
	}
	
}
