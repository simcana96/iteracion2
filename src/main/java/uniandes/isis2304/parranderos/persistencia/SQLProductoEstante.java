package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.VOProductoEstante;



public class SQLProductoEstante {
	private final static String SQL = PersistenciaSupermercado.SQL;
	
	private PersistenciaSupermercado ps;
	
	public SQLProductoEstante(PersistenciaSupermercado ps)
	{
		this.ps=ps;
	}
	
	public long adicionarProductoEstante(PersistenceManager pm,long pid_producto, long pid_estante, int punidades_disponibles, int ppunto_abastecimiento)
	{
		 Query q = pm.newQuery(SQL, "INSERT INTO producto_estante (ID_PRODUCTO, ID_ESTANTE, UNIDADES_DISPONIBLES, PUNTO_ABASTECIMIENTO) values (?, ?, ?, ?)");
	         q.setParameters(pid_producto, pid_estante, punidades_disponibles, ppunto_abastecimiento);
	        return (long) q.executeUnique();
	}
	
	public List<VOProductoEstante> darProductosDeEstante(PersistenceManager pm , long id_estante)
	{
		Query q = pm.newQuery(SQL,"SELECT * FROM producto_estante WHERE ID_ESTANTE = ?");
		q.setResultClass(VOProductoEstante.class);
		q.setParameters(id_estante);
		return q.executeList();
	}
	
	public List<VOProductoEstante> darEstantesDeProducto(PersistenceManager pm , long idProducto)
	{
		Query q = pm.newQuery(SQL,"SELECT * FROM producto_estante WHERE ID_PRODUCTO = ?");
		q.setResultClass(VOProductoEstante.class);
		q.setParameters(idProducto);
		return q.executeList();
	}
	
	public List<VOProductoEstante> darProductosDeEstantes(PersistenceManager pm )
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM producto_estante");
		q.setResultClass(VOProductoEstante.class);
		return q.executeList();
	}
	
	public VOProductoEstante darProductoEstante(PersistenceManager pm , long id_producto , long id_estante)
	{
		Query q = pm.newQuery(SQL,"SELECT * FROM producto_estante WHERE ID_PRODUCTO = ? AND ID_ESTANTE = ?");
		q.setResultClass(VOProductoEstante.class);
		q.setParameters(id_producto,id_estante);
		return (VOProductoEstante) q.executeUnique();
	}
	
	public long actualizarUnaVenta(PersistenceManager pm , long id_producto , long id_estante, int unidades_compradas)
	{
		Query q = pm.newQuery(SQL, "UPDATE producto_estante SET UNIDADES_DISPONIBLES = UNIDADES_DISPONIBLES - ? WHERE ID_PRODUCTO = ? AND ID_ESTANTE = ?");
		q.setResultClass(VOProductoEstante.class);
		q.setParameters(unidades_compradas,id_producto , id_estante);
		return (long) q.executeUnique();
	}
	
}
