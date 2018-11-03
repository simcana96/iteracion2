package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.VOMateriaPrimaProveedor;
import uniandes.isis2304.parranderos.negocio.VOProductoFactura;



public class SQLProductoFactura {

private final static String SQL = PersistenciaSupermercado.SQL;
	
	private PersistenciaSupermercado ps;
	
	public SQLProductoFactura(PersistenciaSupermercado ps)
	{
		this.ps=ps;
	}
	
	public long adicionarProductoFactura(PersistenceManager pm,long pid_factura, long pid_producto, int punidades_compradas, int ppuntos_obtenidos)
	{
		 Query q = pm.newQuery(SQL, "INSERT INTO producto_factura (ID_FACTURA, ID_PRODUCTO, UNIDADES_COMPRADAS, PUNTOS_OBTENIDOS) values (?, ?, ?, ?)");
	         q.setParameters(pid_factura, pid_producto, punidades_compradas, ppuntos_obtenidos);
	        return (long) q.executeUnique();
	}
	
	public List<VOProductoFactura> darProductosDeFactura(PersistenceManager pm , long id_factura)
	{
		Query q = pm.newQuery(SQL,"SELECT * FROM producto_factura WHERE ID_FACTURA = ?");
		q.setResultClass(VOProductoFactura.class);
		q.setParameters(id_factura);
		return q.executeList();
	}
	
	public List<VOProductoFactura> darFacturasdeProducto(PersistenceManager pm , long idProducto)
	{
		Query q = pm.newQuery(SQL,"SELECT * FROM producto_factura WHERE ID_PRODUCTO = ?");
		q.setResultClass(VOProductoFactura.class);
		q.setParameters(idProducto);
		return q.executeList();
	}
	
	public List<VOProductoFactura> darProductosDeFacturas(PersistenceManager pm )
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM producto_factura");
		q.setResultClass(VOProductoFactura.class);
		return q.executeList();
	}
	
	public VOProductoFactura darProductoFactura(PersistenceManager pm , long id_producto , long id_factura)
	{
		Query q = pm.newQuery(SQL,"SELECT * FROM producto_factura WHERE ID_PRODUCTO = ? AND ID_FACTURA = ?");
		q.setResultClass(VOMateriaPrimaProveedor.class);
		q.setParameters(id_producto,id_factura);
		return (VOProductoFactura) q.executeUnique();
	}

}
