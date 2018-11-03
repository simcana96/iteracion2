package uniandes.isis2304.parranderos.negocio;

import java.util.Date;

/**
 * Interfaz para los métodos get de PROMOCION.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 */
public interface VOPromocion
{
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	/**
	 * @return Id de la promoción
	 */
	public long getId();
	/**
	 * @return Fecha de inicion de la promoción.
	 */
	public Date getFechaInicio();
	/**
	 * @return Fecha de terminación de la promoción. 
	 */
	public Date getFechaTerminacion();
	/**
	 * @return Descripción de la promoción. 
	 */
	public String getDescripcion();
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de la promoción. 
	 */
	public String toString() ;
}
