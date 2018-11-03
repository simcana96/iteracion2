package uniandes.isis2304.parranderos.negocio;

public class VOSucursal {

	private long id_sucursal;
	
	private String nombre;
	
	private String ciudad; 
	
	private String direccion;
	
	private int tamano;
	
	private String tipo_cliente;

	public VOSucursal()
	{
		this.id_sucursal = 0;
		this.nombre = "";
		this.ciudad = "";
		this.direccion = "";
		this.tamano = 0;
		this.tipo_cliente = "";
		
	}
	
	public VOSucursal(long id_sucursal , String nombre , String ciudad , String direccion ,
			int tamano , String tipo_cliente)
	{
		this.id_sucursal = id_sucursal;
		this.nombre = nombre;
		this.ciudad = ciudad;
		this.direccion = direccion;
		this.tamano = tamano;
		this.tipo_cliente = tipo_cliente;
	}

	public long getId_sucursal() {
		return id_sucursal;
	}

	public void setId_sucursal(long id_sucursal) {
		this.id_sucursal = id_sucursal;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getTamano() {
		return tamano;
	}

	public void setTamano(int tamano) {
		this.tamano = tamano;
	}

	public String getTipo_cliente() {
		return tipo_cliente;
	}

	public void setTipo_cliente(String tipo_cliente) {
		this.tipo_cliente = tipo_cliente;
	}
	
}
