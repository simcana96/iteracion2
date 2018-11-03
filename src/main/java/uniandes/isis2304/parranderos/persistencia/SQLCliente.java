package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.VOCliente;



public class SQLCliente {
	private final static String SQL = PersistenciaSupermercado.SQL;
	
	private PersistenciaSupermercado ps;
	
	public SQLCliente(PersistenciaSupermercado ps)
	{
		this.ps=ps;
	}
	
	public long adicionarCliente(PersistenceManager pm,long pid_cliente, String pnombre, String pcorreo, int ppuntos )
	{
		 Query q = pm.newQuery(SQL, "INSERT INTO "+ps.darCliente ()   + "(ID_CLIENTE, NOMBRE, CORREO, PUNTOS) values (?, ?, ?, ?)");
	         q.setParameters(pid_cliente, pnombre, pcorreo, ppuntos);
	        return (long) q.executeUnique();
	}
	
	public List<VOCliente> darClientes(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + ps.darCliente());
		q.setResultClass(VOCliente.class);
		return q.executeList();
	}
	
	public VOCliente darClientePorId(PersistenceManager pm , long idCliente)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + ps.darCliente() + " WHERE ID_CLIENTE = ?");
		q.setResultClass(VOCliente.class);
		q.setParameters(idCliente);
		return (VOCliente) q.executeUnique();
	}

}