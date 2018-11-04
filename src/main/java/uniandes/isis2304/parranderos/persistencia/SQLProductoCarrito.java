package uniandes.isis2304.parranderos.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

class SQLProductoCarrito 
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
	private PersistenciaSupermercado ps;
	
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/

	/**
	 * Constructor
	 * @param ps - El Manejador de persistencia de la aplicación
	 */
	public SQLProductoCarrito(PersistenciaSupermercado ps)
	{
		this.ps=ps;
	}
	
	
	public long adicionarProductoCarrito( PersistenceManager pm,long pId_Producto,  long pId_Carrito, long id_estante, int pCantidadProducto)
	{
		Query q = pm.newQuery(SQL,"INSERT INTO " + ps.darTablaProductoCarrito() +"(id_producto, id_carrito, id_estante,  cantidad_productos) values (?, ?, ?,?)");
        q.setParameters(pId_Producto, pId_Carrito, id_estante, pCantidadProducto);
       return (long) q.executeUnique();
	}
}
