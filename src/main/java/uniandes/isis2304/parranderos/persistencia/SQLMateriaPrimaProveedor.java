package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.VOMateriaPrimaProveedor;


public class SQLMateriaPrimaProveedor {
	private final static String SQL = PersistenciaSupermercado.SQL;
	
	private PersistenciaSupermercado ps;
	
	public SQLMateriaPrimaProveedor(PersistenciaSupermercado ps)
	{
		this.ps=ps;
	}
	
	public long adicionarMateriaPrimaProveedor(PersistenceManager pm,long pid_materiaprima, long pid_proveedor, int pcalificacion) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO materia_prima_proveedor (ID_MATERIAPRIMA, ID_PROVEEDOR, CALIFICACION) values (?, ?, ?)");
	         q.setParameters(pid_materiaprima, pid_proveedor, pcalificacion);
	        return (long) q.executeUnique();
	}
	

	public List<VOMateriaPrimaProveedor> darMateriaPrimaDeProveedor(PersistenceManager pm , long id_proveedor)
	{
		Query q = pm.newQuery(SQL,"SELECT * FROM materia_prima_proveedor WHERE ID_PROVEEDOR = ?");
		q.setResultClass(VOMateriaPrimaProveedor.class);
		q.setParameters(id_proveedor);
		return q.executeList();
	}
	
	public List<VOMateriaPrimaProveedor> darProveedoresDeMateriaPrima(PersistenceManager pm , long idMateriaPrima)
	{
		Query q = pm.newQuery(SQL,"SELECT * FROM materia_prima_proveedor WHERE ID_MATERIAPRIMA = ?");
		q.setResultClass(VOMateriaPrimaProveedor.class);
		q.setParameters(idMateriaPrima);
		return q.executeList();
	}
	
	public List<VOMateriaPrimaProveedor> darMateriasPrimasDeProveedores(PersistenceManager pm )
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM materia_prima_proveedor");
		q.setResultClass(VOMateriaPrimaProveedor.class);
		return q.executeList();
	}
	
	public VOMateriaPrimaProveedor darMateriaPrimaProveedor(PersistenceManager pm , long id_MateriaPrima , long id_Proveedor)
	{
		Query q = pm.newQuery(SQL,"SELECT * FROM materia_prima_proveedor WHERE ID_MATERIAPRIMA = ? AND ID_PROVEEDOR = ?");
		q.setResultClass(VOMateriaPrimaProveedor.class);
		q.setParameters(id_MateriaPrima,id_Proveedor);
		return (VOMateriaPrimaProveedor) q.executeUnique();
	}
	

}
