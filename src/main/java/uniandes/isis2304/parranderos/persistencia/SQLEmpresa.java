package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.VOEmpresa;



public class SQLEmpresa {

	private final static String SQL = PersistenciaSupermercado.SQL;
	
	private PersistenciaSupermercado ps;
	
	public SQLEmpresa(PersistenciaSupermercado ps)
	{
		this.ps=ps;
	}
	
	public long adicionarEmpresa(PersistenceManager pm, long pid_empresa, int pnit, String pdireccion )
	{
		 Query q = pm.newQuery(SQL, "INSERT INTO "+ps.darTablaEmpresa ()   + "(ID_EMPRESA, NIT, DIRECCION) values (?, ?, ?)");
	         q.setParameters(pid_empresa,pnit,pdireccion);
	        return (long) q.executeUnique();
	}
	public List<VOEmpresa> darEmpresas(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + ps.darTablaEmpresa());
		q.setResultClass(VOEmpresa.class);
		return q.executeList();
	}
	
	public VOEmpresa darEmpresaPorId(PersistenceManager pm , long idEmpresa)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + ps.darTablaEmpresa() + " WHERE ID_EMPRESA = ?");
		q.setResultClass(VOEmpresa.class);
		q.setParameters(idEmpresa);
		return (VOEmpresa) q.executeUnique();
	}
}
