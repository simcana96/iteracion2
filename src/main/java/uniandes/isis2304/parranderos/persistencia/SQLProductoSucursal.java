package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.VOProductoSucursal;



public class SQLProductoSucursal {

private final static String SQL = PersistenciaSupermercado.SQL;
	
	private PersistenciaSupermercado ps;
	
	public SQLProductoSucursal(PersistenciaSupermercado ps)
	{
		this.ps=ps;
	}
	
	public long adicionarProductoSucursal(PersistenceManager pm, long pid_producto, long pid_sucursal, int pprecio_asignado, long pnivel_reorden)
	{
		 Query q = pm.newQuery(SQL, "INSERT INTO producto_sucursal (ID_PRODUCTO, ID_SUCURSAL, PRECIO_ASIGNADO, NIVEL_REORDEN) values (?, ?, ?, ?)");
	         q.setParameters(pid_producto, pid_sucursal, pprecio_asignado, pnivel_reorden);
	        return (long) q.executeUnique();
	}
	
	public List<VOProductoSucursal> darProductosDeSucursal(PersistenceManager pm , long id_sucursal)
	{
		Query q = pm.newQuery(SQL,"SELECT * FROM producto_sucursal WHERE ID_SUCURSAL = ?");
		q.setResultClass(VOProductoSucursal.class);
		q.setParameters(id_sucursal);
		return q.executeList();
	}
	
	public List<VOProductoSucursal> darSucursalesDeProducto(PersistenceManager pm , long idProducto)
	{
		Query q = pm.newQuery(SQL,"SELECT * FROM producto_sucursal WHERE ID_PRODUCTO = ?");
		q.setResultClass(VOProductoSucursal.class);
		q.setParameters(idProducto);
		return q.executeList();
	}
	
	public List<VOProductoSucursal> darProductosDeSucursales(PersistenceManager pm )
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM producto_sucursal");
		q.setResultClass(VOProductoSucursal.class);
		return q.executeList();
	}
	
	public VOProductoSucursal darProductoSucursal(PersistenceManager pm , long id_Producto , long id_Sucursal)
	{
		Query q = pm.newQuery(SQL,"SELECT * FROM producto_sucursal WHERE ID_PRODUCTO = ? AND ID_SUCURSAL = ?");
		q.setResultClass(VOProductoSucursal.class);
		q.setParameters(id_Producto,id_Sucursal);
		return (VOProductoSucursal) q.executeUnique();
	}
	
	
}
