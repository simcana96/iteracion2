package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.VOEstante;



public class SQLEstante {

	private final static String SQL = PersistenciaSupermercado.SQL;
	
	private PersistenciaSupermercado ps;
	
	public SQLEstante(PersistenciaSupermercado ps)
	{
		this.ps=ps;
	}
	
	public long adicionarEstante(PersistenceManager pm, long pid_estante, int pcapacidad_volumen, int pcapacidad_peso, int pvolumen_existencias, String pcategoria_producto, long pid_sucursal )
	{
		 Query q = pm.newQuery(SQL, "INSERT INTO estante (ID_ESTANTE, CAPACIDAD_VOLUMEN, CAPACIDAD_PESO, VOLUMEN_EXISTENCIAS, CATEGORIA_PRODUCTO, ID_SUCURSAL) values (?, ?, ?, ?, ?, ?)");
	         q.setParameters(pid_estante, pcapacidad_volumen, pcapacidad_peso, pvolumen_existencias, pcategoria_producto, pid_sucursal);
	        return (long) q.executeUnique();
	}
	
	public List<VOEstante> darEstantes(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM estante");
		q.setResultClass(VOEstante.class);
		return q.executeList();
	}
	
	public VOEstante darEstantePorId(PersistenceManager pm , long idEstante)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM estante WHERE ID_ESTANTE = ?");
		q.setResultClass(VOEstante.class);
		q.setParameters(idEstante);
		return (VOEstante) q.executeUnique();
	}
	
	public List<VOEstante> darEstantesSucursal(PersistenceManager pm , long idSucursal)
	{
		Query q = pm.newQuery(SQL,"SELECT * FROM estante WHERE ID_SUCURSAL = ?");
		q.setResultClass(VOEstante.class);
		q.setParameters(idSucursal);
		return q.executeList();
	}
}
