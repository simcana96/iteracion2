package uniandes.isis2304.parranderos.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.ProductoCarrito;
import uniandes.isis2304.parranderos.negocio.VOCliente;
import uniandes.isis2304.parranderos.negocio.VOProductoEstante;

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
	
	public ProductoCarrito darProductoCarrito(PersistenceManager pm, long pId_Producto, long pId_Carrito)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + ps.darTablaProductoCarrito() + " WHERE id_producto = "+pId_Producto+ " and id_carrito = "+pId_Carrito);
		q.setParameters(pId_Producto, pId_Carrito);
		q.setResultClass(ProductoCarrito.class);
		System.out.println(q.execute());
		return (ProductoCarrito) q.executeList().get(0);		
	}


	public void eliminarProducto(PersistenceManager pm,Long pId_Producto) {


		Query q = pm.newQuery(SQL, "DELETE FROM " + ps.darTablaProductoCarrito() + " WHERE id_producto = "+pId_Producto);
		q.execute();
	}


	public void actualizarCarrito(PersistenceManager pm, Long id_Producto, int cantidad, Long id_Carrito) {
		Query q = pm.newQuery(SQL, "UPDATE "+ ps.darTablaProductoCarrito()+" SET CANTIDAD_PRODUCTOS = CANTIDAD_PRODUCTOS - ? WHERE ID_PRODUCTO = ? and ID_CARRITO = ? ");
		q.setResultClass(VOProductoEstante.class);
		q.setParameters(cantidad,id_Producto, id_Carrito);
		q.executeUnique();
	}
}
