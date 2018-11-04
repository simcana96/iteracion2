package uniandes.isis2304.parranderos.persistencia;

import java.sql.Timestamp;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.VOProducto;


public class SQLProducto {
private final static String SQL = PersistenciaSupermercado.SQL;
	
	private PersistenciaSupermercado ps;
	
	public SQLProducto(PersistenciaSupermercado ps)
	{
		this.ps=ps;
	}
	
	public long adicionarProducto(PersistenceManager pm,long pid_producto, String pnombre, String pmarca, int pcantidad_presentacion, String punidad_medida, String pespecificacion_empaque, String pcodigo_barras, Timestamp pfecha_vencimiento, String pcategoria)
	{
		Query q = pm.newQuery(SQL,"INSERT INTO producto (id_producto,nombre,marca,unidad_medida,especificacion_empaque,codigo_barras,fecha_vencimiento,categoria_producto,cantidad_presentacion) values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
	         q.setParameters(pid_producto, pnombre, pmarca, punidad_medida, pespecificacion_empaque, pcodigo_barras, pfecha_vencimiento, pcategoria,pcantidad_presentacion);
	        return (long) q.executeUnique();
	}
	
	public VOProducto darProductoPorId(PersistenceManager pm, long idProducto)
	{
		System.out.println(ps.darTablaProducto());
		Query q = pm.newQuery(SQL, "SELECT * FROM "+ ps.darTablaProducto() + " WHERE ID_PRODUCTO = ?");
		q.setResultClass(VOProducto.class);
		q.setParameters(idProducto);
		return (VOProducto) q.executeUnique();
	}
	
	public List<VOProducto> darProductos(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM producto");
		q.setResultClass(VOProducto.class);
		return q.executeList();
	}

	public VOProducto darProductoPorNombre(PersistenceManager pm, String pnombre)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM producto WHERE NOMBRE = ?");
		q.setResultClass(VOProducto.class);
		q.setParameters(pnombre);
		VOProducto resp =  (VOProducto) q.executeUnique();
		if(resp != null)
		{
			return resp;
		}
		else
		{
			return null;
		}
	}
}
