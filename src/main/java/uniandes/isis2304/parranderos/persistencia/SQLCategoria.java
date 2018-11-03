package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.VOCategoria;


public class SQLCategoria {
	private final static String SQL = PersistenciaSupermercado.SQL;
	
	private PersistenciaSupermercado ps;
	
	public SQLCategoria(PersistenciaSupermercado ps)
	{
		this.ps=ps;
	}
	
	public long adicionarCategoria(PersistenceManager pm,String pNombre )
	{
		 Query q = pm.newQuery(SQL, "INSERT INTO "+ ps.darCategoria ()   + " (NOMBRE) values (?)");
	         q.setParameters(pNombre);
	        return (long) q.executeUnique();
	}
	
	public VOCategoria darCategoria(PersistenceManager pm , String pNombre)
	{
		Query q = pm.newQuery(SQL,"SELECT * FROM " + ps.darCategoria() + " WHERE NOMBRE = ?");
		q.setResultClass(VOCategoria.class);
		q.setParameters(pNombre);
		return (VOCategoria) q.executeUnique();
	}
	
	public List<VOCategoria> darCategorias(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + ps.darCategoria());
		q.setResultClass(VOCategoria.class);
		return q.executeList();
	}
}
