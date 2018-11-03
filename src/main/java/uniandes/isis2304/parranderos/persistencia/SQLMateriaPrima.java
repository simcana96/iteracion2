package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.VOMateriaPrima;


public class SQLMateriaPrima {
	private final static String SQL = PersistenciaSupermercado.SQL;
	
	private PersistenciaSupermercado ps;
	
	public SQLMateriaPrima(PersistenciaSupermercado ps)
	{
		this.ps=ps;
	}
	
	public long adicionarMateriaPrima(PersistenceManager pm,long pid_materiaprima, String pnombre,long pid_producto )
	{
		 Query q = pm.newQuery(SQL, "INSERT INTO  materia_prima (ID_MATERIA_PRIMA, NOMBRE, ID_PRODUCTO) values (?, ?, ?)");
	         q.setParameters(pid_materiaprima, pnombre,pid_producto);
	        return (long) q.executeUnique();
	}

	public List<VOMateriaPrima> darMateriasPrimas(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM materia_prima");
		q.setResultClass(VOMateriaPrima.class);
		return q.executeList();
	}
	
	public VOMateriaPrima darMateriaPrimaPorId(PersistenceManager pm , long idMateriaPrima)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM materia_prima WHERE ID_MATERIA_PRIMA = ?");
		q.setResultClass(VOMateriaPrima.class);
		q.setParameters(idMateriaPrima);
		return (VOMateriaPrima) q.executeUnique();
	}
	public VOMateriaPrima darMateriaPrimaPorNombre(PersistenceManager pm , String pNombre)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM materia_prima WHERE NOMBRE = ?");
		q.setResultClass(VOMateriaPrima.class);
		q.setParameters(pNombre);
		return (VOMateriaPrima) q.executeUnique();
	}
}
