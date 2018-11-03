package uniandes.isis2304.parranderos.persistencia;

import java.util.Date;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto Promocion de Supermercado
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 */
class SQLPromocion 
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra acá para facilitar la escritura de las sentencias
	 */
	private final static String SQL = PersistenciaSupermercado.SQL;

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia general de la aplicación
	 */
	private PersistenciaSupermercado pp;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/

	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicación
	 */
	public SQLPromocion (PersistenciaSupermercado pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar una PROMOCION a la base de datos de Supermercado
	 * @return EL número de tuplas insertadas
	 */
//	public long adicionarPromocion(PersistenceManager pm, long pId, Date pFechaTerminacion, String pDescripcion, Integer pUnidadesDisponibles) 
//	{
//        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaPromocion()  + "(id, fechainicio, fechaterminacion, descripcion, unidadesdisponibles) values (?, ?,?,?)");
//        q.setParameters(pId,  pFechaTerminacion,  pDescripcion, pUnidadesDisponibles );
//        return (long) q.executeUnique();            
//	
//	}
}
