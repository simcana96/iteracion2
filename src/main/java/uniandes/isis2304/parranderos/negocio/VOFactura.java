package uniandes.isis2304.parranderos.negocio;

import java.sql.Timestamp;

public class VOFactura {

	//Atributos
	
	
	private long id_factura;
	
	private Timestamp fecha_factura;
	
	private long id_cliente;
	
	//Metodos
	
	/**
	 * Constructor por defecto
	 */
	public VOFactura()
	{
		this.id_factura = 0;
		this.fecha_factura = new Timestamp(0);
		this.id_cliente = 0 ;
		
	}
	
	/**
	 * constructor con valores
	 * @param id_factura
	 * @param fecha_factura
	 * @param id_cliente
	 */
	public VOFactura(long id_factura , Timestamp fecha_factura , long id_cliente)
	{
		this.id_factura = id_factura;
		this.fecha_factura = fecha_factura;
		this.id_cliente = id_cliente;
	}

	public long getId_factura() {
		return id_factura;
	}

	public void setId_factura(long id_factura) {
		this.id_factura = id_factura;
	}

	public Timestamp getFecha_factura() {
		return fecha_factura;
	}

	public void setFecha_factura(Timestamp fecha_factura) {
		this.fecha_factura = fecha_factura;
	}

	public long getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(long id_cliente) {
		this.id_cliente = id_cliente;
	}
	
	
	
}
