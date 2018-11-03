package uniandes.isis2304.parranderos.persistencia;

import java.sql.Timestamp;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.VOPedido;


public class SQLPedido {
	private final static String SQL = PersistenciaSupermercado.SQL;
	
	private PersistenciaSupermercado ps;
	
	public SQLPedido(PersistenciaSupermercado ps)
	{
		this.ps=ps;
	}
	
	public long adicionarPedido(PersistenceManager pm,long pid_pedido, Timestamp pfecha_acordada, Timestamp pfecha_entrega, String pestado, int pprecio, int pcalificacion, int pcantidad_pedir, long pid_sucursal, long pid_proveedor, long pid_materiaprima)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO pedido (ID_PEDIDO, FECHA_ACORDADA, FECHA_ENTREGA, ESTADO, PRECIO, CALIFICACION, CANTIDAD_PEDIR, ID_SUCURSAL, ID_PROVEEDOR, ID_MATERIA_PRIMA) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
	         q.setParameters(pid_pedido, pfecha_acordada, pfecha_entrega, pestado, pprecio, pcalificacion, pcantidad_pedir, pid_sucursal, pid_proveedor, pid_materiaprima );
	        return (long) q.executeUnique();
	}
	
	public List<VOPedido> darPedidos(PersistenceManager pm)
	{
		Query q = pm.newQuery("SELECT * FROM pedido");
		q.setResultClass(VOPedido.class);
		return q.executeList();
	}
	
	public List<VOPedido> darPedidosSurcursal(PersistenceManager pm , long id_Sucursal)
	{
		Query q = pm.newQuery("SELECT * FROM pedido WHERE ID_SUCURSAL = ?");
		q.setResultClass(VOPedido.class);
		q.setParameters(id_Sucursal);
		return q.executeList();
	}
	public List<VOPedido> darPedidosProveedor(PersistenceManager pm , long id_Proveedor)
	{
		Query q = pm.newQuery("SELECT * FROM pedido WHERE ID_PROVEEDOR = ?");
		q.setResultClass(VOPedido.class);
		q.setParameters(id_Proveedor);
		return q.executeList();
	}
	
	public List<VOPedido> darPedidosMateriaPrima(PersistenceManager pm , long id_MateriaPrima)
	{
		Query q = pm.newQuery("SELECT * FROM pedido WHERE ID_MATERIA_PRIMA = ?");
		q.setResultClass(VOPedido.class);
		q.setParameters(id_MateriaPrima);
		return q.executeList();
	}
	
	public long registrarLlegadaPedidoASucursal(PersistenceManager pm,long id_Pedido,int calificacion)
	{
		Query q = pm.newQuery(SQL,"UPDATE pedido SET ESTADO = 'Entregado', CALIFICACION = "+ calificacion +" WHERE ID_PEDIDO = '"+id_Pedido+"'");
		//q.setParameters(calificacion,id_Pedido);
		return (long) q.executeUnique();
	}
	
	public VOPedido darPedidoPorId(PersistenceManager pm , long pid_Pedido)
	{
		Query q = pm.newQuery(SQL,"SELECT * FROM pedido WHERE id_pedido ="+pid_Pedido);
		q.setResultClass(VOPedido.class);
		VOPedido resp = (VOPedido) q.executeUnique();
		return resp;
	}

}
