package uniandes.isis2304.parranderos.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.Carrito;
import uniandes.isis2304.parranderos.negocio.VOCarrito;
import uniandes.isis2304.parranderos.negocio.VOCliente;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto Carrito de Supermercado
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 */
class SQLCarrito 
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
	public SQLCarrito (PersistenciaSupermercado pp)
	{
		this.pp = pp;
	}
	
	public long adicionarCarrito(PersistenceManager pm, long idCarrito, long idSucursal,  long idPersona )
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaCarrito()  + "(id_carrito, id_Sucursal, id_Cliente) values (?, ?, ?)");
        q.setParameters( idCarrito, idSucursal, idPersona);
        return (long) q.executeUnique();  
	}

	public Carrito darCarritoPorId(PersistenceManager pm, long id_Carrito) {
		
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCarrito() + " WHERE id_carrito = ?");
		q.setResultClass(Carrito.class);
		q.setParameters(id_Carrito);
		System.out.println(id_Carrito);
		
		return (Carrito) q.executeUnique();
	}
}
