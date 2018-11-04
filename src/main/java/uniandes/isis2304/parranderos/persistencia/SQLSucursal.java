package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.VOSucursal;



/**
 * @author Danielmontoya
 *
 */
public class SQLSucursal {
private final static String SQL = PersistenciaSupermercado.SQL;
	
	private PersistenciaSupermercado ps;
	
	public SQLSucursal(PersistenciaSupermercado ps)
	{
		this.ps=ps;
	}
	
	public long adicionarSucursal(PersistenceManager pm,long pid_sucursal, String pnombre, String pciudad, String pdireccion, int ptamano,String ptipo_cliente)
	{
		 Query q = pm.newQuery(SQL, "INSERT INTO sucursal (ID_SUCURSAL, NOMBRE, CIUDAD, DIRECCION, TAMANO,TIPO_CLIENTE) values (?, ?, ?, ?, ?, ?)");
	         q.setParameters(pid_sucursal, pnombre, pciudad, pdireccion, ptamano,ptipo_cliente);
	        return (long) q.executeUnique();
	}
	
	public VOSucursal darSucursalPorId(PersistenceManager pm, long idSucursal)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM sucursal WHERE ID_SUCURSAL = ?");
		q.setResultClass(VOSucursal.class);
		q.setParameters(idSucursal);
		return (VOSucursal) q.executeUnique();
	}

	public List<VOSucursal> darSucursales(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM sucursal");
		q.setResultClass(VOSucursal.class);
		return q.executeList();
	}
	
	public long limpiarSucursales(PersistenceManager pm)
	{
		long queryResp = 0;
		
			Query queryBorrar = pm.newQuery(SQL,"DELETE FROM sucursal");
			queryResp = (long) queryBorrar.executeUnique();
		
		return queryResp;
		
	}
}
