package uniandes.isis2304.parranderos.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.VOBodega;

import java.util.List;


public class SQLBodega {

	private final static String SQL = PersistenciaSupermercado.SQL;
	
	private PersistenciaSupermercado ps;
	
	public SQLBodega(PersistenciaSupermercado ps)
	{
		this.ps=ps;
	}
	
	public long adicionarBodega(PersistenceManager pm,long pid_bodega, int pcapacidad_volumen, int pcapacidad_peso, int pvolumen_existencias, String pcategoria_producto, long pid_sucursal )
	{
		 Query q = pm.newQuery(SQL, "INSERT INTO bodega (ID_BODEGA, CAPACIDAD_VOLUMEN, CAPACIDAD_PESO, VOLUMEN_EXISTENCIAS, CATEGORIA_PRODUCTO, ID_SUCURSAL) values (?, ?, ?, ?, ?, ?)");
	         q.setParameters(pid_bodega, pcapacidad_volumen, pcapacidad_peso, pvolumen_existencias, pcategoria_producto, pid_sucursal);
	        return (long) q.executeUnique();
	}
	
	public List<VOBodega> darBodegas(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM bodega");
		q.setResultClass(VOBodega.class);
		return q.executeList();
	}
	
	public VOBodega darBodegaPorId(PersistenceManager pm , long idBodega)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM bodega WHERE ID_BODEGA = ?");
		q.setResultClass(VOBodega.class);
		q.setParameters(idBodega);
		return (VOBodega) q.executeUnique();
	}
	
	public List<VOBodega> darBodegasSucursal(PersistenceManager pm , long idSucursal)
	{
		Query q = pm.newQuery(SQL,"SELECT * FROM bodega WHERE ID_SUCURSAL = ?");
		q.setResultClass(VOBodega.class);
		q.setParameters(idSucursal);
		return q.executeList();
	}

	public long limpiarBodegas(PersistenceManager pm)
	{
		long queryResp = 0;
		
			Query queryBorrar = pm.newQuery(SQL,"DELETE FROM bodega");
			queryResp = (long) queryBorrar.executeUnique();
		
		return queryResp;
		
	}
}
