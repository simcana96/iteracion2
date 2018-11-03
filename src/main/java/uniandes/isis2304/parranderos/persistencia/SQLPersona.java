package uniandes.isis2304.parranderos.persistencia;

import java.sql.Date;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.PersonaNatural;
import uniandes.isis2304.parranderos.negocio.VOCategoria;
import uniandes.isis2304.parranderos.negocio.VOPersonaNatural;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto Persona de Supermercado
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 */
class SQLPersona
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
	public SQLPersona (PersistenciaSupermercado pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar una PERSONA a la base de datos de Supermercado
	 * @return EL número de tuplas insertadas
	 */
	public long adicionarPersona(PersistenceManager pm, String pNombre, String pCorreo, Integer pCedula) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaPersonaNatural()  + "(nombre, correo, cedula ) values (?, ?, ?)");
        q.setParameters( pNombre,  pCorreo, pCedula);
        return (long) q.executeUnique();            
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar una PERSONA a la base de datos de Supermercado
	 * @return EL número de tuplas insertadas
	 */
	public PersonaNatural darPersona(PersistenceManager pm, Integer pCedula) 
	{
		Query q = pm.newQuery(SQL,"SELECT * FROM " + pp.darTablaPersonaNatural() + " WHERE CEDULA = ?");
		q.setParameters(pCedula);
		q.setResultClass(PersonaNatural.class);
		q.setParameters(pCedula);
		return (PersonaNatural) q.executeUnique();           
	}
}
