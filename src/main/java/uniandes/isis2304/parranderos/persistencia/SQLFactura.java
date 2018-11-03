package uniandes.isis2304.parranderos.persistencia;

import java.sql.Timestamp;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.VOFactura;



public class SQLFactura {
	private final static String SQL = PersistenciaSupermercado.SQL;
	
	private PersistenciaSupermercado ps;
	
	public SQLFactura(PersistenciaSupermercado ps)
	{
		this.ps=ps;
	}
	
	public long adicionarFactura(PersistenceManager pm,long pid_factura, Timestamp pfecha, long pid_cliente)
	{
		 Query q = pm.newQuery(SQL, "INSERT INTO "+ps.darTablaFactura ()   + "(ID_FACTURA, FECHA_FACTURA, ID_CLIENTE) values (?, ?, ?)");
	         q.setParameters(pid_factura, pfecha, pid_cliente);
	        return (long) q.executeUnique();
	}

	public List<VOFactura> darFacturas(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + ps.darTablaFactura());
		q.setResultClass(VOFactura.class);
		return q.executeList();
	}
	
	public VOFactura darFacturaPorId(PersistenceManager pm , long idFactura)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + ps.darTablaFactura() + " WHERE ID_FACTURA = ?");
		q.setResultClass(VOFactura.class);
		q.setParameters(idFactura);
		return (VOFactura) q.executeUnique();
	}
	
	public List<VOFactura> darFacturasPorCliente(PersistenceManager pm , long idCliente)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM "+ps.darTablaFactura() + " WHERE ID_CLIENTE = ?");
		q.setResultClass(VOFactura.class);
		q.setParameters(idCliente);
		return q.executeList();
	}
}
