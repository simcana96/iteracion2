package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.VOProveedor;



public class SQLProveedor {
private final static String SQL = PersistenciaSupermercado.SQL;
	
	private PersistenciaSupermercado ps;
	
	public SQLProveedor(PersistenciaSupermercado ps)
	{
		this.ps=ps;
	}
	
	public long adicionarProveedor(PersistenceManager pm,long pnit, String pnombre, int pcalificacion)
	{
		if(pcalificacion<=5 && pcalificacion>=0)
		{
		 Query q = pm.newQuery(SQL, "INSERT INTO proveedores (NIT, NOMBRE, CALIFICACION) values (?, ?, ?)");
	         q.setParameters(pnit, pnombre, pcalificacion);
	        return (long) q.executeUnique();
		}
		else
		{
			return 0;
		}
	}
	
	public VOProveedor darProveedorPorId(PersistenceManager pm, long idProveedor)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM proveedores WHERE NIT = ?");
		q.setResultClass(VOProveedor.class);
		q.setParameters(idProveedor);
		return (VOProveedor) q.executeUnique();
	}
	
	public List<VOProveedor> darProveedores(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM proveedores");
		q.setResultClass(VOProveedor.class);
		return q.executeList();
	}

	public long limpiarProveedores(PersistenceManager pm)
	{
		long queryResp = 0;
		
			Query queryBorrar = pm.newQuery(SQL,"DELETE FROM proveedores");
			queryResp = (long) queryBorrar.executeUnique();
		
		return queryResp;
		
	}
}
