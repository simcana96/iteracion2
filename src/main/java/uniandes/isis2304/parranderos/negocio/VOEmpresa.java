package uniandes.isis2304.parranderos.negocio;

public class VOEmpresa {
	
	/**
	 * Atributos
	 */
	private long nit;
	private String direccion;
	

	public long getNit() {
		return nit;
	}

	public void setNit(long nit) {
		this.nit = nit;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * Constructor por default
	 */
	
	public VOEmpresa()
	{
		nit=0;
		direccion="";
	}
	
	/**
	 * Constructor con valores
	 */
	public VOEmpresa(long pId_empresa, int pNit, String pDireccion)
	{
		nit=pNit;
		direccion=pDireccion;
	}
	
	
}
