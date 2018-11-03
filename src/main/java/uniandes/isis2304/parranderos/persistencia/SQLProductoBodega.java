package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.VOProductoBodega;



public class SQLProductoBodega {
	
	private final static String SQL = PersistenciaSupermercado.SQL;
	
	private PersistenciaSupermercado ps;
	
	public SQLProductoBodega(PersistenciaSupermercado ps)
	{
		this.ps=ps;
	}
	
	public long adicionarProductoBodega(PersistenceManager pm,long pid_producto, long pid_bodega,int punidades_disponibles)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO producto_bodega (ID_PRODUCTO, ID_BODEGA, UNIDADES_DISPONIBLES) values (?, ?, ?)");
	         q.setParameters(pid_producto, pid_bodega, punidades_disponibles);
	        return (long) q.executeUnique();
	}
	
	public List<VOProductoBodega> darProductosDeBodega(PersistenceManager pm , long id_bodega)
	{
		Query q = pm.newQuery(SQL,"SELECT * FROM producto_bodega WHERE ID_BODEGA = ?");
		q.setResultClass(VOProductoBodega.class);
		q.setParameters(id_bodega);
		return q.executeList();
	}
	
	public List<VOProductoBodega> darBodegasDeProducto(PersistenceManager pm , long idProducto)
	{
		Query q = pm.newQuery(SQL,"SELECT * FROM producto_bodega WHERE ID_PRODUCTO = ?");
		q.setResultClass(VOProductoBodega.class);
		q.setParameters(idProducto);
		return q.executeList();
	}
	
	public List<VOProductoBodega> darProductosDeBodegas(PersistenceManager pm )
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM producto_bodega");
		q.setResultClass(VOProductoBodega.class);
		return q.executeList();
	}
	
	public VOProductoBodega darProductoBodega(PersistenceManager pm , long id_producto , long id_bodega)
	{
		Query q = pm.newQuery(SQL,"SELECT * FROM producto_bodega WHERE ID_PRODUCTO = ? AND ID_BODEGA = ?");
		q.setResultClass(VOProductoBodega.class);
		q.setParameters(id_producto,id_bodega);
		return (VOProductoBodega) q.executeUnique();
	}
	
	

}
