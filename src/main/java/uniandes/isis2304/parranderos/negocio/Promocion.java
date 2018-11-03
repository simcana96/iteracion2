package uniandes.isis2304.parranderos.negocio;

import java.util.Date;

/**
 * Clase para modelar el concepto PROMOCION del negocio de superAndes
 */
public class Promocion implements VOPromocion
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * Identificador unico de cada promoción
	 */
	private long id;
	/**
	 * Fecha de inicio de la promoción
	 */
	private Date fechaInicio;
	/**
	 * Fecha de terminación de la promoción
	 */
	private Date fechaTerminacion;
	/**
	 * Descripción de la promoción.
	 */
	private String descripcion;
	/**
	 * Cantidades disponibles de la promoción.
	 */
	private Integer unidadesDisponibles;
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	 /**
     * Constructor por defecto
     */
	public Promocion()
	{
		this.id = 0;
		this.fechaInicio = null;
		this.fechaTerminacion = null;
		this.descripcion = "";
		this.unidadesDisponibles = 0;
	}
	/**
	 * Constructor con valores
	 * @param pId - Id de la promoción
	 * @param pFechaInicio - Fecha de inicio de la promoción
	 * @param pFechaTerminacion - Fecha de fin de la promoción
	 * @param pDescripcion - Descripción de la promoción
	 */
	public Promocion(long pId, Date pFechaInicio, Date pFechaTerminacion, String pDescripcion, Integer pCantidadesDisponibles)
	{
		this.id = pId;
		this.fechaInicio = pFechaInicio;
		this.fechaTerminacion = pFechaTerminacion;
		this.descripcion = pDescripcion;
		this.unidadesDisponibles = pCantidadesDisponibles;
	}
	
	/**
	 * @return Id de la promoción
	 */
	public long getId() {
		return id;
	}
	/**
	 * @return Fecha de inicion de la promoción.
	 */
	public Date getFechaInicio() {
		return fechaInicio;
	}
	/**
	 * @return Fecha de terminación de la promoción. 
	 */
	public Date getFechaTerminacion() {
		return fechaTerminacion;
	}
	/**
	 * @return Descripción de la promoción. 
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @return Cantidad disponibles de la promoción. 
	 */
	public Integer getUnidadesDisponibles() {
		return unidadesDisponibles;
	}
	/**
	 * @param id - Nueva cantidad disponible de la promoción. 
	 */
	public void setUnidadesDisponibles(Integer cantidadesDisponibles) {
		this.unidadesDisponibles = cantidadesDisponibles;
	}
	/**
	 * @param id - Nuevo id de la promoción.
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @param fechaInicio - Nueva fecha de inicio de la promoción.
	 */
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	/**
	 * @param fechaTerminacion - Nueva fecha de terminación de la promoción. 
	 */
	public void setFechaTerminacion(Date fechaTerminacion) {
		this.fechaTerminacion = fechaTerminacion;
	}
	/**
	 * @param descripcion - Nueva descripción de la promoción. 
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	@Override
	public String toString() {
		return "Promocion [id=" + id + ", fechaInicio=" + fechaInicio + ", fechaTerminacion=" + fechaTerminacion
				+ ", descripcion=" + descripcion + ", cantidadesDisponibles=" + unidadesDisponibles + "]";
	}
	
	
	
	
}
