package uniandes.isis2304.parranderos.persistencia;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import org.apache.log4j.Logger;
import org.datanucleus.store.rdbms.query.ForwardQueryResult;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import uniandes.isis2304.parranderos.negocio.Carrito;
import uniandes.isis2304.parranderos.negocio.PersonaNatural;
import uniandes.isis2304.parranderos.negocio.ProductoCarrito;
import uniandes.isis2304.parranderos.negocio.VOBodega;
import uniandes.isis2304.parranderos.negocio.VOCarrito;
import uniandes.isis2304.parranderos.negocio.VOCategoria;
import uniandes.isis2304.parranderos.negocio.VOCliente;
import uniandes.isis2304.parranderos.negocio.VOEmpresa;
import uniandes.isis2304.parranderos.negocio.VOEstante;
import uniandes.isis2304.parranderos.negocio.VOFactura;
import uniandes.isis2304.parranderos.negocio.VOMateriaPrima;
import uniandes.isis2304.parranderos.negocio.VOMateriaPrimaProveedor;
import uniandes.isis2304.parranderos.negocio.VOPedido;
import uniandes.isis2304.parranderos.negocio.VOPersonaNatural;
import uniandes.isis2304.parranderos.negocio.VOProducto;
import uniandes.isis2304.parranderos.negocio.VOProductoBodega;
import uniandes.isis2304.parranderos.negocio.VOProductoEstante;
import uniandes.isis2304.parranderos.negocio.VOProductoFactura;
import uniandes.isis2304.parranderos.negocio.VOProductoSucursal;
import uniandes.isis2304.parranderos.negocio.VOProveedor;
import uniandes.isis2304.parranderos.negocio.VOSucursal;


public class PersistenciaSupermercado {

	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(PersistenciaSupermercado.class.getName());
	
	/**
	 * Cadena para indicar el tipo de sentencias que se va a utilizar en una consulta
	 */
	public static final String SQL = "javax.jdo.query.SQL";

	//Atributos
	private static PersistenciaSupermercado instance;

	private PersistenceManagerFactory pmf;
	private List<String> tablas ; 
	private SQLBodega sqlBodega;
	private SQLCategoria sqlCategoria;
	private SQLCliente sqlCliente;
	private SQLPromocion sqlPromocion;
	private SQLEmpresa sqlEmpresa;
	private SQLProducto sqlProducto;
	private SQLMateriaPrima sqlMateriaPrima;
	private SQLProveedor sqlProveedor;
	private SQLMateriaPrimaProveedor sqlMateriaPrimaProveedor;
	private SQLSucursal sqlSucursal;
	private SQLEstante sqlEstante;
	private SQLPedido sqlPedido;
	private SQLProductoBodega sqlProductoBodega;
	private SQLProductoEstante sqlProductoEstante;
	private SQLProductoSucursal sqlProductoSucursal;
	private SQLFactura sqlFactura;
	private SQLProductoFactura sqlProductoFactura;
	
	
	// Nuevos iteración 2
	private SQLPersona sqlPersona;
	private SQLCarrito sqlCarrito;
	private SQLProductoCarrito sqlProductoCarrito;
	/**

	private SQLPersonaNatural sqlPersonaNatural;
	private SQLPromocion sqlPromocion;

	 */
	private SQLUtil sqlUtil;

	/**
	 * creacion de las tablas en el arreglo
	 */
	private PersistenciaSupermercado()
	{
		pmf = JDOHelper.getPersistenceManagerFactory("Supermercado");
		crearClasesSQL();
		tablas = new ArrayList<>();
		tablas.add("Parranderos_sequence");
		tablas.add("BODEGA");
		tablas.add("CATEGORIA");
		tablas.add("CLIENTE");
		tablas.add("EMPRESA");
		tablas.add("ESTANTE");
		tablas.add("FACTURA");
		tablas.add("MATERIA_PRIMA");
		tablas.add("MATERIA_PRIMA_PROVEEDOR");
		tablas.add("PEDIDO");
		tablas.add("PERSONANATURAL");
		tablas.add("producto");
		tablas.add("PRODUCTO_BODEGA");
		tablas.add("PRODUCTO_ESTANTE");
		tablas.add("PRODUCTO_FACTURA");
		tablas.add("PRODUCTO_SUCURSAL");
		tablas.add("PROMOCION");
		System.out.println("Me estoy actualizando");
		tablas.add("PROVEEDOR");
		tablas.add("SURCURSAL");
		// Nuevos iteración 2
		tablas.add("PERSONA");
		tablas.add("CARRITO");

	}
	/**
	 * creacion de la persistencia
	 * @param tableConfig
	 */
	private PersistenciaSupermercado(JsonObject tableConfig)
	{
		crearClasesSQL();
		tablas = leerNombretablas(tableConfig);
		String unidadPersistencia = tableConfig.get("unidadPersistencia").getAsString();
		pmf = JDOHelper.getPersistenceManagerFactory (unidadPersistencia);

	}

	/**
	 * lee el nombre de las tablas
	 * @param tableConfig
	 * @return
	 */
	private List<String> leerNombretablas(JsonObject tableConfig)
	{
		System.out.println("impresion1"+ tableConfig);

		JsonArray nombres = tableConfig.getAsJsonArray("tablas");
		List<String> resp = new ArrayList<>();
		for(JsonElement nombre : nombres)
		{
			resp.add(nombre.getAsString());
		}
		return resp;
	}
	/**
	 * crea las clases sql
	 */
	private void crearClasesSQL()
	{
		sqlBodega = new SQLBodega(this);
		sqlCategoria = new SQLCategoria(this);
		sqlCliente = new SQLCliente(this);
		sqlEmpresa = new SQLEmpresa(this);
		sqlProducto = new SQLProducto(this);
		sqlMateriaPrima = new SQLMateriaPrima(this);
		sqlMateriaPrimaProveedor = new SQLMateriaPrimaProveedor(this);
		sqlProveedor = new SQLProveedor(this);
		sqlSucursal = new SQLSucursal(this);
		sqlEstante = new SQLEstante(this);
		sqlPedido = new SQLPedido(this);
		sqlProductoBodega = new SQLProductoBodega(this);
		sqlProductoEstante = new SQLProductoEstante(this);
		sqlProductoSucursal = new SQLProductoSucursal(this);
		sqlFactura = new SQLFactura(this);
		sqlProductoFactura = new SQLProductoFactura(this);
		
		//Nuevos iteración 2
		sqlPersona= new SQLPersona(this);
		sqlCarrito = new SQLCarrito(this);
		sqlProductoCarrito = new SQLProductoCarrito(this);
		

		
//		sqlPromocion = new SQLPromocion(this);

		sqlUtil = new SQLUtil(this);
	}

	public List<String> darTablas()
	{
		return tablas;
	}
	public String darSeqSupermercado()
	{
		return tablas.get(0);
	}
	public String darTablaBodega()
	{
		return tablas.get(1);
	}
	public String darTablaCategoria()
	{
		return tablas.get(2);
	}
	public String darTablaCliente()
	{
		return tablas.get(3);
	}
	public String darTablaEmpresa()
	{
		return tablas.get(4);
	}
	public String darTablaEstante()
	{
		return tablas.get(5);
	}
	public String darTablaFactura()
	{
		return tablas.get(6);
	}
	public String darTablaMateriaPrima()
	{
		return tablas.get(7);
	}
	public String darTablaMateriaPrimaProveedor()
	{
		return tablas.get(8);
	}
	public String darTablaPedido()
	{
		return tablas.get(9);
	}
	public String darTablaPersonaNatural()
	{
		return tablas.get(9);
	}
	public String darTablaProducto()
	{
		return tablas.get(10);
	}
	public String darTablaProductoBodega()
	{
		return tablas.get(12);
	}
	public String darTablaProductoEstante()
	{
		return tablas.get(13);
	}
	public String darTablaProductoFactura()
	{
		return tablas.get(14);
	}
	public String darTablaProductoSucursal()
	{
		return tablas.get(15);
	}
	public String darTablaPromocion()
	{
		return tablas.get(16);
	}
	public String darTablaProveedor()
	{
		return tablas.get(17);
	}
	public String darTablaSucursal()
	{
		return tablas.get(18);
	}
	public String darTablaPersona()
	{
		return tablas.get(19);
	}
	public String darTablaCarrito()
	{
		return tablas.get(19);
	}
	public String darTablaProductoCarrito()
	{
		return tablas.get(20);
	}


	/**
	 * devuelve la instancia
	 * @return
	 */
	public static PersistenciaSupermercado getInstance()
	{
		if(instance == null)
		{
			instance = new PersistenciaSupermercado();
		}
		return instance;
	}

	/**
	 * devuelve la instancia
	 * @param tableConfig
	 * @return
	 */
	public static PersistenciaSupermercado getInstance(JsonObject tableConfig)
	{
		if(instance == null)
		{
			instance = new PersistenciaSupermercado(tableConfig);
		}
		return instance;
	}
	/**
	 * cierra la unidad de persistencia
	 */
	public void cerrarUnidadPersistencia ()
	{
		pmf.close ();
		instance = null;
	}
	/**
	 * da el siguiente valor para crear un id
	 * @return
	 */
	private long nextval ()
	{
		long resp = sqlUtil.nextval (pmf.getPersistenceManager());
		return resp;
	}

	/**
	 * adiciona una bodega
	 * @param pcapacidad_volumen
	 * @param pcapacidad_peso
	 * @param pvolumen_existencias
	 * @param pcategoria_producto
	 * @param pid_sucursal
	 * @return
	 */
	public VOBodega adicionarBodega(int pcapacidad_volumen, int pcapacidad_peso , int pvolumen_existencias , String pcategoria_producto , long pid_sucursal )
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long idBodega = nextval();
			long tuplasInsertadas = sqlBodega.adicionarBodega(pm,idBodega, pcapacidad_volumen, pcapacidad_peso, pvolumen_existencias, pcategoria_producto, pid_sucursal);
			tx.commit();
			return new VOBodega(idBodega, pcapacidad_volumen, pcapacidad_peso, pvolumen_existencias, pcategoria_producto, pid_sucursal);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
		finally 
		{
			if(tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * adciona una categoria
	 * @param pNombre
	 * @return
	 */
	public VOCategoria adicionarCategoria(String pNombre)
	{

		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlCategoria.adicionarCategoria(pm,pNombre);
			tx.commit();
			return new VOCategoria(pNombre);
		}
		catch(Exception e)
		{

			System.out.println("entra al catch");
			e.printStackTrace();
			return null;
		}
		finally
		{
			if(tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}
	/**
	 * adciona un cliente
	 * @param pnombre
	 * @param pcorreo
	 * @param ppuntos
	 * @return
	 */
	public VOCliente adicionarCliente(String pnombre, String pcorreo, int ppuntos)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long id_cliente = nextval();
			long tuplasInsertadas = sqlCliente.adicionarCliente(pm,id_cliente, pnombre, pcorreo, ppuntos);
			
			tx.commit();
			return new VOCliente(id_cliente, pnombre, pcorreo, ppuntos);
		}
		catch(Exception e)
		{

			e.printStackTrace();
			return null;
		}
		finally
		{
			if(tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}
	
	public Carrito adicionarCarrito( long idSucursal, long idPersona )
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long id_carrito = nextval();
			long tuplasInsertadas = sqlCarrito.adicionarCarrito(pm, id_carrito, idSucursal, idPersona );
			log.trace ("Inserción de tipo del carrito con id: " + id_carrito + ": " + tuplasInsertadas + " tuplas insertadas");
			tx.commit();
			return new Carrito( id_carrito, idSucursal, idPersona );
		}
		catch(Exception e)
		{

			e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if(tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public ProductoCarrito adicionarProductoCarrito(long idCarrito, long idProducto,long id_estante, int cantidad)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			
			long tuplasInsertadas = sqlProductoCarrito.adicionarProductoCarrito(pm, idProducto, idCarrito, id_estante, cantidad);
			log.trace ("Inserción de un producto con id: " + idProducto + ". En el carrito: "+ idCarrito  +" "+ tuplasInsertadas + " tuplas insertadas");
			tx.commit();
			return new ProductoCarrito( idProducto, idCarrito, cantidad, id_estante);
		}
		catch(Exception e)
		{

			e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if(tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
				
	}
	/**
	 * adciona una empresa
	 * @param pnit
	 * @param pdireccion
	 * @return
	 */
	public VOEmpresa adicionarEmpresa(int pnit , String pdireccion)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long id_empresa = nextval();
			long tuplasInsertadas = sqlEmpresa.adicionarEmpresa(pm,id_empresa, pnit, pdireccion);
			tx.commit();
			return new VOEmpresa(id_empresa, pnit, pdireccion);
		}
		catch(Exception e)
		{

			e.printStackTrace();
			return null;
		}
		finally
		{
			if(tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * adiciona un estante
	 * @param pcapacidad_volumen
	 * @param pcapacidad_peso
	 * @param pvolumen_existencias
	 * @param pcategoria_producto
	 * @param pid_sucursal
	 * @return
	 */
	public VOEstante adicionarEstante(int pcapacidad_volumen , int pcapacidad_peso , int pvolumen_existencias , String pcategoria_producto,long pid_sucursal)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long id_estante = nextval();
			long tuplasInsertadas = sqlEstante.adicionarEstante(pm,id_estante, pcapacidad_volumen, pcapacidad_peso, pvolumen_existencias, pcategoria_producto, pid_sucursal);
			tx.commit();
			return new VOEstante(id_estante, pcapacidad_volumen, pcapacidad_peso, pvolumen_existencias, pcategoria_producto, pid_sucursal);
		}
		catch(Exception e)
		{

			e.printStackTrace();
			return null;
		}
		finally
		{
			if(tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}
	/**
	 * adiciona una factura
	 * @param pid_cliente
	 * @return
	 */
	public VOFactura adicionarFactura(long pid_cliente)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long id_factura = nextval();
			Timestamp pfecha = new Timestamp(System.currentTimeMillis());
			long tuplasInsertadas = sqlFactura.adicionarFactura(pm,id_factura, pfecha, pid_cliente);
			tx.commit();
			return new VOFactura(id_factura, pfecha, pid_cliente);
		}
		catch(Exception e)
		{

			e.printStackTrace();
			return null;
		}
		finally
		{
			if(tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}
	public VOMateriaPrima adicionarMateriaPrima(String pnombre,long id_producto)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long id_materiaprima = nextval();
			long tuplasInsertadas = sqlMateriaPrima.adicionarMateriaPrima(pm,id_materiaprima, pnombre,id_producto);
			tx.commit();
			return new VOMateriaPrima(id_materiaprima, pnombre,id_producto);
		}
		catch(Exception e)
		{

			e.printStackTrace();
			return null;
		}
		finally
		{
			if(tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}
	/**
	 * adciona una materia prima a un proveedor
	 * @param pid_materiaprima
	 * @param pid_proveedor
	 * @return
	 */
	public VOMateriaPrimaProveedor adicionarMateriaPrimaProveedor(long pid_materiaprima , long pid_proveedor)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlMateriaPrimaProveedor.adicionarMateriaPrimaProveedor(pm,pid_materiaprima, pid_proveedor, 0);
			tx.commit();
			return new VOMateriaPrimaProveedor(pid_proveedor, pid_materiaprima);
		}
		catch(Exception e)
		{

			e.printStackTrace();
			return null;
		}
		finally
		{
			if(tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}
	/**
	 * adiciona pedido
	 * @param pfechaArcodada
	 * @param pprecio
	 * @param pcalificacion
	 * @param pcantidad_pedir
	 * @param pid_sucursal
	 * @param pid_proveedor
	 * @param pid_materiaprima
	 * @return
	 */
	public VOPedido adicionarPedido(long pfechaArcodada , int pprecio , int pcalificacion , int pcantidad_pedir , long pid_sucursal , long pid_proveedor , long pid_materiaprima)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long id_pedido = nextval();
			Timestamp pfecha_acordada = new Timestamp(pfechaArcodada);
			long tuplasInsertadas = sqlPedido.adicionarPedido(pm,id_pedido, pfecha_acordada, new Timestamp(0), "En proceso de entrega", pprecio, pcalificacion, pcantidad_pedir, pid_sucursal, pid_proveedor, pid_materiaprima);
			tx.commit();
			return new VOPedido(id_pedido, pfecha_acordada, new Timestamp(0), "En proceso de entrega", pprecio, pcalificacion, pcantidad_pedir, pid_sucursal, pid_proveedor, pid_materiaprima);
		}
		catch(Exception e)
		{

			e.printStackTrace();
			return null;
		}
		finally
		{
			if(tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}
	/**
	 * adiciona una persona natural
	 * @param pid_persona
	 * @param pcedula
	 * @return
	 */
//	public PersonaNatural adicionarPersona(long pid_persona, int pcedula)
//	{
//		PersistenceManager pm = pmf.getPersistenceManager();
//		Transaction tx=pm.currentTransaction();
//		try
//		{
//			tx.begin();
//			long tuplasInsertadas = sqlPersonaNatural.adicionarPersonaNatural(pm, pid_persona, pcedula);
//			tx.commit();
//			return new PersonaNatural(pcedula);
//		}
//		catch(Exception e)
//		{
//
//			e.printStackTrace();
//			return null;
//		}
//		finally
//		{
//			if(tx.isActive())
//			{
//				tx.rollback();
//			}
//			pm.close();
//		}
//	}
	/**
	 * adiciona una producto
	 * @param pnombre
	 * @param pmarca
	 * @param pcantidad_presentacion
	 * @param punidad_medida
	 * @param pespecificacion_empaque
	 * @param pcodigo_barras
	 * @param pFecha_vencimiento
	 * @param pcategoria
	 * @return
	 */
	public VOProducto adicionarProducto(String pnombre, String pmarca, int pcantidad_presentacion , String punidad_medida , String pespecificacion_empaque , String pcodigo_barras , long pFecha_vencimiento, String pcategoria )
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long pid_producto = nextval();
			Timestamp pfecha_vencimiento = new Timestamp(pFecha_vencimiento);
			System.out.println(pm +" " +pid_producto +  pnombre +  pmarca+  pcantidad_presentacion+  punidad_medida+  pespecificacion_empaque+ pcodigo_barras+ pfecha_vencimiento+ pcategoria);
			long tuplasInsertadas = sqlProducto.adicionarProducto(pm, pid_producto, pnombre, pmarca, pcantidad_presentacion, punidad_medida, pespecificacion_empaque, pcodigo_barras, pfecha_vencimiento, pcategoria);
			tx.commit();
			return new VOProducto(pid_producto, pnombre, punidad_medida, pespecificacion_empaque, pcodigo_barras, pfecha_vencimiento, pcategoria, pcantidad_presentacion);
		}
		catch(Exception e)
		{

			e.printStackTrace();
			return null;
		}
		finally
		{
			if(tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}
	/**
	 * adiciona un producto a una bodega
	 * @param pid_producto
	 * @param pid_bodega
	 * @param punidades_disponibles
	 * @return
	 */
	public VOProductoBodega adicionarProductoBodega(long pid_producto , long pid_bodega , int punidades_disponibles)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlProductoBodega.adicionarProductoBodega(pm, pid_producto, pid_bodega, punidades_disponibles);
			tx.commit();
			return new VOProductoBodega(pid_producto, pid_bodega, punidades_disponibles);
		}
		catch(Exception e)
		{

			e.printStackTrace();
			return null;
		}
		finally
		{
			if(tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}
	/**
	 * adciona un producto a un estante
	 * @param pid_producto
	 * @param pid_estante
	 * @param punidades_disponibles
	 * @param ppunto_abastecimiento
	 * @return
	 */
	public VOProductoEstante adicionarProductoEstante(long pid_producto , long pid_estante , int punidades_disponibles , int ppunto_abastecimiento)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlProductoEstante.adicionarProductoEstante(pm, pid_producto, pid_estante, punidades_disponibles, ppunto_abastecimiento);
			tx.commit();
			return new VOProductoEstante(pid_producto, pid_estante, punidades_disponibles, ppunto_abastecimiento);
		}
		catch(Exception e)
		{

			e.printStackTrace();
			return null;
		}
		finally
		{
			if(tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}
	/**
	 * adiciona un producto a una factura
	 * @param pid_factura
	 * @param pid_producto
	 * @param punidades_compradas
	 * @param ppuntos_obtenidos
	 * @return
	 */
	public VOProductoFactura adicionarProductoFactura(long pid_factura , long pid_producto , int punidades_compradas , int ppuntos_obtenidos)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlProductoFactura.adicionarProductoFactura(pm, pid_factura, pid_producto, punidades_compradas, ppuntos_obtenidos);
			tx.commit();
			return new VOProductoFactura(pid_factura, pid_producto, punidades_compradas, ppuntos_obtenidos);
		}
		catch(Exception e)
		{

			e.printStackTrace();
			return null;
		}
		finally
		{
			if(tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}
	/**
	 * adiciona un producto a una sucursal
	 * @param pid_producto
	 * @param pid_sucursal
	 * @param pprecioAsignado
	 * @param pnivel_reorden
	 * @return
	 */
	public VOProductoSucursal adicionarProductoSucursal(long pid_producto , long pid_sucursal , int pprecioAsignado , int pnivel_reorden)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlProductoSucursal.adicionarProductoSucursal(pm, pid_producto, pid_sucursal, pprecioAsignado, pnivel_reorden);
			tx.commit();
			return new VOProductoSucursal(pid_producto, pid_sucursal, pprecioAsignado, pnivel_reorden);
		}
		catch(Exception e)
		{

			e.printStackTrace();
			return null;
		}
		finally
		{
			if(tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}
	/**
	 * adiciona una promocion 
	 * @param ptipo
	 * @param punidades_disponibles
	 * @param pid_producto
	 * @param fechavencimiento
	 * @return
	 */
//	public VOPromocion adicionarPromocion(String ptipo , int punidades_disponibles , long pid_producto ,long fechavencimiento)
//	{
//		PersistenceManager pm = pmf.getPersistenceManager();
//		Transaction tx=pm.currentTransaction();
//		try
//		{
//			tx.begin();
//			Timestamp fecha = new Timestamp(fechavencimiento);
//			long pid_promocion = nextval();
//			long tuplasInsertadas = sqlPromocion.adicionarPromocion(pm, pid_promocion, ptipo, punidades_disponibles, pid_producto, fecha);
//			tx.commit();
//			return new VOPromocion(pid_promocion, ptipo, punidades_disponibles, pid_producto, fecha);
//		}
//		catch(Exception e)
//		{
//
//			e.printStackTrace();
//			return null;
//		}
//		finally
//		{
//			if(tx.isActive())
//			{
//				tx.rollback();
//			}
//			pm.close();
//		}
//	}
	/**
	 * adiciona un proveedor
	 * @param pnit
	 * @param pnombre
	 * @param pcalificacion
	 * @return
	 */
	public VOProveedor adicionarProveedor(long pnit , String pnombre, int pcalificacion)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long pid_proveedor = nextval();
			long tuplasInsertadas = sqlProveedor.adicionarProveedor(pm, pnit, pnombre, pcalificacion);
			tx.commit();
			return new VOProveedor(pnit, pnombre, pcalificacion);
		}
		catch(Exception e)
		{

			e.printStackTrace();
			return null;
		}
		finally
		{
			if(tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}
	/**
	 * adiciona una sucursal
	 * @param pnombre
	 * @param pciudad
	 * @param pdireccion
	 * @param ptamano
	 * @param ptipo_cliente
	 * @return
	 */
	public VOSucursal adicionarSucursal(String pnombre , String pciudad , String pdireccion , int ptamano, String ptipo_cliente)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long pid_sucursal = nextval();
			long tuplasInsertadas = sqlSucursal.adicionarSucursal(pm, pid_sucursal, pnombre, pciudad, pdireccion, ptamano,ptipo_cliente);
			tx.commit();
			return new VOSucursal(pid_sucursal, pnombre, pciudad, pdireccion, ptamano, ptipo_cliente);
		}
		catch(Exception e)
		{

			e.printStackTrace();
			return null;
		}
		finally
		{
			if(tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}
	/**
	 * da la categoria segun su nombre
	 * @param pNombre
	 * @return
	 */
	public VOCategoria darCategoria(String pNombre)
	{
		return sqlCategoria.darCategoria(pmf.getPersistenceManager(), pNombre);
	}
	
	/**
	 * Da el cliente según su id.  
	 * @param pid Id del cliente. 
	 * @return
	 */
	public VOCliente darClientePorCorreo(String pCorreo) {
		
		return sqlCliente.darClientePorCorreo(pmf.getPersistenceManager(), pCorreo);
	}
	
	public VOCarrito darCarrito(long id_Carrito) {
	
		return sqlCarrito.darCarritoPorId(pmf.getPersistenceManager(),id_Carrito);
	}
	
	public Long darEstanteDelProducto( long id_producto, long id_sucursal) {
		System.out.println(id_producto + " "+ id_sucursal);
		Query as= pmf.getPersistenceManager().newQuery(SQL,"select b.id_estante from Estante b, producto_estante c where b.id_estante = c.id_estante and id_sucursal = "+ id_sucursal+ " and id_producto = "+ id_producto);
		as.setParameters(id_sucursal,id_producto);
		
		
		System.out.println(as.execute().toString());
		String resultado1 = as.execute().toString().substring(1,3);
		System.out.println(resultado1);
		Long resultado = Long.parseLong(resultado1);
		return resultado;
	}
	/**
	 * da un producto por nombre
	 * @param pNombre
	 * @return
	 */
	public VOProducto darProductoPorNombre (String pNombre)
	{

		VOProducto producto = sqlProducto.darProductoPorNombre(pmf.getPersistenceManager(),pNombre);
		return producto; 
	}

	/**
	 * da una materia proma por nombre
	 * @param pNombre
	 * @return
	 */
	public VOMateriaPrima darMateriaPrimaPorNombre(String pNombre)
	{
		VOMateriaPrima resp = sqlMateriaPrima.darMateriaPrimaPorNombre(pmf.getPersistenceManager(), pNombre);
		return resp;
	}

	public long [] limpiarSupermercado ()
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long [] resp = sqlUtil.limpiarSupermercado(pm);
			tx.commit ();
			return resp;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return new long[] {-1, -1, -1, -1, -1, -1, -1};
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}

	}

	/**
	 * limpia todas las bodegas
	 * @return
	 */
	public long limpiarBodegas()
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long  resp = sqlBodega.limpiarBodegas(pm);
			tx.commit ();
			return resp;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return-1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}
	/**
	 * limpia las sucursales
	 * @return
	 */
	public long limpiarSucursales()
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long  resp = sqlSucursal.limpiarSucursales(pm);
			tx.commit ();
			return resp;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return-1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}


	/**
	 * limpia los proveedores
	 * @return
	 */
	public long limpiarProveedores()
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long  resp = sqlProveedor.limpiarProveedores(pm);
			tx.commit ();
			return resp;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return-1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}
	/**
	 * da los proveedores
	 * @return
	 */
	public List<VOProveedor> darProveedores ()
	{
		return sqlProveedor.darProveedores(pmf.getPersistenceManager());
	}
	/**
	 * da los proveedores por id
	 * @param pnit
	 * @return
	 */
	public VOProveedor darProveedorPorId(long pnit)
	{
		return sqlProveedor.darProveedorPorId(pmf.getPersistenceManager(), pnit);
	}
	/**
	 * da las categorias
	 * @return
	 */
	public List<VOCategoria> darCategorias ()
	{
		return sqlCategoria.darCategorias(pmf.getPersistenceManager());
	}
	/**
	 * da las sucursales por id
	 * @param id_sucursal
	 * @return
	 */
	public VOSucursal darSucursalPorId(long id_sucursal)
	{
		return sqlSucursal.darSucursalPorId(pmf.getPersistenceManager(), id_sucursal);
	}
	/**
	 * da la materia prima por id
	 * @param pid_materiaPrima
	 * @return
	 */
	public VOMateriaPrima darMateriaPrimaPorId(long pid_materiaPrima)
	{
		return sqlMateriaPrima.darMateriaPrimaPorId(pmf.getPersistenceManager(), pid_materiaPrima);
	}
	/**
	 * actualiza la llegada de un pedido 
	 * @param pid_Pedido
	 * @param pcalificacion
	 * @return
	 */
	public long actualizarLlegadaPedido(long pid_Pedido , int pcalificacion)
	{
		return sqlPedido.registrarLlegadaPedidoASucursal(pmf.getPersistenceManager(), pid_Pedido, pcalificacion);
	}
	/**
	 * da un pedido segun su id
	 * @param pid_pedido
	 * @return
	 */
	public VOPedido darPedidoPorId(long pid_pedido)
	{
		VOPedido resp = sqlPedido.darPedidoPorId(pmf.getPersistenceManager(), pid_pedido);
		return (VOPedido) resp;
	}
	/**
	 * da un producto por id
	 * @param pid_producto
	 * @return
	 */
	public VOProducto darProductoPorId(long pid_producto)
	{
		return sqlProducto.darProductoPorId(pmf.getPersistenceManager(), pid_producto);
	}
	
	public ProductoCarrito darProductoCarrito(Long id_Producto, Long id_Carrito) 
	{
		return sqlProductoCarrito.darProductoCarrito(pmf.getPersistenceManager(), id_Producto, id_Carrito);
	}
	/**
	 * actualiza una venta
	 * @param pid_producto
	 * @param pid_estante
	 * @param unidades_compradas
	 * @return
	 */
	public long actualizarUnaVenta(long pid_producto , long pid_estante,int unidades_compradas)
	{
		return sqlProductoEstante.actualizarUnaVenta(pmf.getPersistenceManager(), pid_producto, pid_estante, unidades_compradas);
	}
	/**
	 * finaliza una promocion 
	 * @param pid_promocion
	 * @return
	 */
//	public long finalizarPromocion(long pid_promocion)
//	{
//		return sqlPromocion.deletePromocion(pmf.getPersistenceManager(), pid_promocion);
//	}
	/**
	 * genera la consulta 1
	 * @return
	 */
	public List<Object[]> consulta1(String fechaInicio, String fechaFin)
	{
		System.out.println("Entra");
		Query q = (pmf.getPersistenceManager()).newQuery(SQL,"SELECT T1.s, T1.suma FROM( SELECT ps.ID_SUCURSAL s, pf.id_factura fa, SUM(ps.PRECIO_ASIGNADO*pf.UNIDADES_COMPRADAS) suma FROM PRODUCTO_FACTURA pf INNER JOIN PRODUCTO_SUCURSAL ps ON pf.ID_PRODUCTO = ps.ID_PRODUCTO GROUP BY ps.ID_SUCURSAL, pf.ID_FACTURA) T1 INNER JOIN FACTURA ON factura.id_factura = T1.fa WHERE factura.fecha_factura BETWEEN ? AND ? GROUP BY T1.s, T1.suma");
		q.setParameters(fechaInicio, fechaFin);
		return q.executeList();
	}
	/**
	 * genera la consulta 2
	 * @return
	 */
	public List<Object[]> consulta2()
	{
		Query q = pmf.getPersistenceManager().newQuery(SQL,"SELECT prod2.nombre FROM (SELECT pf.id_producto idproducto,count(pf.id_factura) FROM( SELECT prod.id_producto FROM PRODUCTO prod INNER JOIN promocion prom ON prod.id_producto = prom.id_producto) T1 INNER JOIN PRODUCTO_FACTURA pf ON t1.id_producto = pf.id_producto GROUP BY pf.id_producto) T2 INNER JOIN PRODUCTO prod2 ON t2.idproducto = prod2.id_producto WHERE ROWNUM <= 20");
		return q.executeList();
	}
	/**
	 * genera la consulta 3
	 * @return
	 */
	public List<Object[]> consulta3()
	{
		Query q = pmf.getPersistenceManager().newQuery(SQL,"WITH parte1 AS(SELECT s.nombre nom1,s.id_sucursal idSu1, T1.ocupacionE ocupE FROM( SELECT e.id_sucursal, sum(e.volumen_existencias/e.capacidad_volumen) ocupacionE FROM ESTANTE e  GROUP BY e.id_sucursal) T1 INNER JOIN sucursal s ON t1.id_sucursal=s.id_sucursal) , parte2 AS( SELECT s.nombre nom2 ,s.id_sucursal idSu2, T1.ocupacionB ocupB FROM( SELECT e.id_sucursal, sum(e.volumen_existencias/e.capacidad_volumen) ocupacionB FROM BODEGA e  GROUP BY e.id_sucursal) T1 INNER JOIN sucursal s ON t1.id_sucursal=s.id_sucursal ) SELECT  parte1.nom1 NombreSurcursal , parte1.ocupe Ocupacion_Estante , parte2.ocupb Ocupacion_Bodega FROM parte1 INNER JOIN parte2 ON parte1.idsu1 = parte2.idsu2    ");
		return (List <Object[]>) q.executeList();
	}
	/**
	 * genera la consulta 4
	 * @return
	 */
	public List<Object[]> consulta4(String pnombre)
	{
		Query q = pmf.getPersistenceManager().newQuery(SQL," SELECT * FROM PRODUCTO WHERE NOMBRE = ?");
		q.setParameters(pnombre);
		return q.executeList();
	}
	/**
	 * genera la consulta 5
	 * @return
	 */
	public List<Object[]> consulta5()
	{
		Query q = pmf.getPersistenceManager().newQuery(SQL,"SELECT t1.id_pedido,t1.nombre,t1.fecha_entrega,mp.nombre,t1.cantidad_pedir FROM (SELECT ped.id_pedido,ped.id_proveedor,ped.id_materia_prima mp1,ped.fecha_entrega,ped.cantidad_pedir,prov.nombre,prov.calificacion FROM PEDIDO ped INNER JOIN PROVEEDORES prov ON ped.id_proveedor = prov.nit WHERE ped.estado = 'Entregado') T1 INNER JOIN materia_prima mp ON t1.mp1 = mp.id_materia_prima ORDER BY t1.nombre DESC");
		return q.executeList();
	}
	/**
	 * genera la consulta 6
	 * @return
	 */
	public List<Object[]> consulta6(long pid_Cliente , String fechaInicio , String fechaFin)
	{
		Query q = pmf.getPersistenceManager().newQuery(SQL, "SELECT t4 .nombre Nombre_Cliente ,t4.correo Correo_Cliente ,t4.puntosobt Puntos_Obtenidos,prod.nombre Producto_Comprado ,t4.undscompra Unidades_Compradas FROM(SELECT clientes.nombre nombre ,clientes.correo correo,t2.idprod2,t2.undscompradas undscompra ,t2.puntosobten puntosobt FROM (SELECT T1.idcliente idcliente2 ,pf.id_producto idprod2 ,pf.unidades_compradas undscompradas ,pf.puntos_obtenidos puntosobten FROM(SELECT fac.id_factura , fac.id_cliente idcliente FROM FACTURA fac WHERE fac.id_cliente = ? AND fac.fecha_factura BETWEEN ? AND ?)T1 INNER JOIN PRODUCTO_FACTURA pf ON t1.id_factura = pf.id_factura) T2 INNER JOIN CLIENTE clientes ON t2.idcliente2 = clientes.id_cliente) T4 INNER JOIN PRODUCTO prod ON t4.idprod2 = prod.id_producto");
		q.setParameters(21 , fechaInicio , fechaFin);
		return (List<Object[]>) q.executeList();
	}

	public List<Object> consulta7(Date date, Date dateFinal, Long id, String select, String group, String order) {
		if( !group.equals("") && !order.equals(""))
		{
			Query q = pmf.getPersistenceManager().newQuery(SQL, "SELECT nombre, correo "+ select +
					" FROM A_Cliente C , A_Factura F, A_Itemfactura Ifa WHERE F.Idcliente = C.Id And "
					+ "F.Id = Ifa.Idfactura And Ifa.Idproducto = "+ "'"+id+"' "+  "And F.Fecha > '"
					+ (date.getDay()+1) + "-"+ (date.getMonth()+1)+"-"+(date.getYear()+1900)
					+ "' And F.Fecha < '"
					+ (dateFinal.getDay()+1) + "-"+ (dateFinal.getMonth()+1)+"-"+(dateFinal.getYear()+1900)
					+ "' GROUP BY NOMBRE, CORREO " + group + " ORDER BY "+ order);
			return (List<Object>) q.executeList();
		}
		else if( !group.equals("") && order.equals(""))
		{
			Query q = pmf.getPersistenceManager().newQuery(SQL, "SELECT nombre, correo "+ select +
					" FROM A_Cliente C , A_Factura F, A_Itemfactura Ifa WHERE F.Idcliente = C.Id And "
					+ "F.Id = Ifa.Idfactura And Ifa.Idproducto = "+ "'"+id+"' "+  "And F.Fecha > '"
					+ (date.getDay()+1) + "-"+ (date.getMonth()+1)+"-"+(date.getYear()+1900)
					+ "' And F.Fecha < '"
					+ (dateFinal.getDay()+1) + "-"+ (dateFinal.getMonth()+1)+"-"+(dateFinal.getYear()+1900)
					+ "' GROUP BY NOMBRE, CORREO " + group);
			return (List<Object>) q.executeList();
		}
		else if( !order.equals(""))
		{
			Query q = pmf.getPersistenceManager().newQuery(SQL, "SELECT nombre, correo "+ select +
					" FROM A_Cliente C , A_Factura F, A_Itemfactura Ifa WHERE F.Idcliente = C.Id And "
					+ "F.Id = Ifa.Idfactura And Ifa.Idproducto = "+ "'"+id+"' "+  "And F.Fecha > '"
					+ (date.getDay()+1) + "-"+ (date.getMonth()+1)+"-"+(date.getYear()+1900)
					+ "' And F.Fecha < '"
					+ (dateFinal.getDay()+1) + "-"+ (dateFinal.getMonth()+1)+"-"+(dateFinal.getYear()+1900)
					+ "'" + " ORDER BY "+ order);
			return (List<Object>) q.executeList();
		}
		else
		{
			Query q = pmf.getPersistenceManager().newQuery(SQL, "SELECT nombre, correo "+ select +
					" FROM A_Cliente C , A_Factura F, A_Itemfactura Ifa WHERE F.Idcliente = C.Id And "
					+ "F.Id = Ifa.Idfactura And Ifa.Idproducto = "+ "'"+id+"' "+  "And F.Fecha > '"
					+ (date.getDay()+1) + "-"+ (date.getMonth()+1)+"-"+(date.getYear()+1900)
					+ "' And F.Fecha < '"
					+ (dateFinal.getDay()+1) + "-"+ (dateFinal.getMonth()+1)+"-"+(dateFinal.getYear()+1900)
					+ "'" );
			return (List<Object>) q.executeList();
		}
		
	}
	public List<Object> consulta8(Date date, Date dateFinal, Long id, String select, String group, String order) 
	{
		if( !group.equals("") && !order.equals(""))
		{
			Query q = pmf.getPersistenceManager().newQuery(SQL,"SELECT " +select+" FROM (SELECT id, nombre, correo FROM A_Cliente MINUS( SELECT c.id, nombre, correo FROM A_Cliente C , A_Factura F, A_Itemfactura Ifa WHERE "
					+ "F.Idcliente = C.Id And F.Id = Ifa.Idfactura And Ifa.Idproducto =" + id + " And F.Fecha > " + "'" + (date.getDay()+ 1) + "-" + (date.getMonth()+1)+ "-" + (date.getYear() +1900)+ "'" 
					+ " And F.Fecha < " + "'" + (dateFinal.getDay()+1) + "-" + (dateFinal.getMonth()+1)+ "-" + (dateFinal.getYear()+1900) + "')" 
					+  ")  GROUP BY nombre " + " ORDER BY" + order
					+ "");
			System.out.println("SELECT " +select+" FROM (SELECT id, nombre, correo FROM A_Cliente MINUS( SELECT c.id, nombre, correo FROM A_Cliente C , A_Factura F, A_Itemfactura Ifa WHERE "
					+ "F.Idcliente = C.Id And F.Id = Ifa.Idfactura And Ifa.Idproducto =" + id + " And F.Fecha > " + "'" + (date.getDay()+ 1) + "-" + (date.getMonth()+1)+ "-" + (date.getYear() +1900)+ "'" 
					+ " And F.Fecha < " + "'" + (dateFinal.getDay()+1) + "-" + (dateFinal.getMonth()+1)+ "-" + (dateFinal.getYear()+1900) + "')" 
					+  ")  GROUP BY nombre " + " ORDER BY" + order
					+ "");
			return (List<Object>) q.executeList();
		}
		else if( !group.equals("") && order.equals(""))
		{
			Query q = pmf.getPersistenceManager().newQuery(SQL,"SELECT " +select+" FROM (SELECT id, nombre, correo FROM A_Cliente MINUS( SELECT c.id, nombre, correo FROM A_Cliente C , A_Factura F, A_Itemfactura Ifa WHERE "
					+ "F.Idcliente = C.Id And F.Id = Ifa.Idfactura And Ifa.Idproducto =" + id + " And F.Fecha > " + "'" + (date.getDay()+ 1) + "-" + (date.getMonth()+1)+ "-" + (date.getYear() +1900)+ "')" 
					+ " And F.Fecha < " + "'" + (dateFinal.getDay()+1) + "-" + (dateFinal.getMonth()+1)+ "-" + (dateFinal.getYear()+1900) + "')" 
					+  "  GROUP BY nombre ");
			System.out.println("SELECT " +select+" FROM (SELECT id, nombre, correo FROM A_Cliente MINUS( SELECT c.id, nombre, correo FROM A_Cliente C , A_Factura F, A_Itemfactura Ifa WHERE "
					+ "F.Idcliente = C.Id And F.Id = Ifa.Idfactura And Ifa.Idproducto =" + id + " And F.Fecha > " + "'" + (date.getDay()+ 1) + "-" + (date.getMonth()+1)+ "-" + (date.getYear() +1900)+ "')" 
					+ " And F.Fecha < " + "'" + (dateFinal.getDay()+1) + "-" + (dateFinal.getMonth()+1)+ "-" + (dateFinal.getYear()+1900) + "')" 
					+  "  GROUP BY nombre ");
			return (List<Object>) q.executeList();
			
		}
		else if( !order.equals(""))
		{
			Query q = pmf.getPersistenceManager().newQuery(SQL,"SELECT " +select+" FROM (SELECT id, nombre, correo FROM A_Cliente MINUS( SELECT c.id, nombre, correo FROM A_Cliente C , A_Factura F, A_Itemfactura Ifa WHERE "
					+ "F.Idcliente = C.Id And F.Id = Ifa.Idfactura And Ifa.Idproducto =" + id + " And F.Fecha > " + "'" + (date.getDay()+ 1) + "-" + (date.getMonth()+1)+ "-" + (date.getYear() +1900)+ "'" 
					+ " And F.Fecha < " + "'" + (dateFinal.getDay()+1) + "-" + (dateFinal.getMonth()+1)+ "-" + (dateFinal.getYear()+1900) + "')" 
					+  "ORDER BY" + order
					+ ")");
			System.out.println("SELECT " +select+" FROM (SELECT id, nombre, correo FROM A_Cliente MINUS( SELECT c.id, nombre, correo FROM A_Cliente C , A_Factura F, A_Itemfactura Ifa WHERE "
					+ "F.Idcliente = C.Id And F.Id = Ifa.Idfactura And Ifa.Idproducto =" + id + " And F.Fecha > " + "'" + (date.getDay()+ 1) + "-" + (date.getMonth()+1)+ "-" + (date.getYear() +1900)+ "'" 
					+ " And F.Fecha < " + "'" + (dateFinal.getDay()+1) + "-" + (dateFinal.getMonth()+1)+ "-" + (dateFinal.getYear()+1900) + "')" 
					+  "ORDER BY" + order
					+ ")");
			return (List<Object>) q.executeList();
		}
		else
		{
			Query q = pmf.getPersistenceManager().newQuery(SQL,"SELECT " +select+" FROM (SELECT id, nombre, correo FROM A_Cliente MINUS( SELECT c.id, nombre, correo FROM A_Cliente C , A_Factura F, A_Itemfactura Ifa WHERE "
					+ "F.Idcliente = C.Id And F.Id = Ifa.Idfactura And Ifa.Idproducto =" + id + " And F.Fecha > " + "'" + (date.getDay()+ 1) + "-" + (date.getMonth()+1)+ "-" + (date.getYear() +1900)+ "'" 
					+ " And F.Fecha < " + "'" + (dateFinal.getDay()+1) + "-" + (dateFinal.getMonth()+1)+ "-" + (dateFinal.getYear()+1900) + "')" 
					+ ") ");
			System.out.println("SELECT " +select+" FROM (SELECT id, nombre, correo FROM A_Cliente MINUS( SELECT c.id, nombre, correo FROM A_Cliente C , A_Factura F, A_Itemfactura Ifa WHERE "
					+ "F.Idcliente = C.Id And F.Id = Ifa.Idfactura And Ifa.Idproducto =" + id + " And F.Fecha > " + "'" + (date.getDay()+ 1) + "-" + (date.getMonth()+1)+ "-" + (date.getYear() +1900)+ "'" 
					+ " And F.Fecha < " + "'" + (dateFinal.getDay()+1) + "-" + (dateFinal.getMonth()+1)+ "-" + (dateFinal.getYear()+1900) + "')" 
					+ ") ");
			return (List<Object>) q.executeList();
		}
		
	}
	public void solicitarCarrito()

	{
		
	}
	
	public void actualizarEstanteProducto(Long id_Producto, Long estante, int cantidad) {
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin(); 
            
            sqlProductoEstante.acutalizarCantidadEnEstante(pm,id_Producto, estante, cantidad);
            tx.commit();
            log.trace ("Actuzalización estante: " + estante+ " del producto " + id_Producto  + " una cantidad de " + cantidad);
            
        }
        catch (Exception e)
        {
        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
		
	}
	public VOPersonaNatural adicionarPersona(long pid_persona, int pCedula, String pCorreo, String pNombre) {
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin(); 
            
            long tuplasInsertadas = sqlPersona.adicionarPersona(pm,  pNombre,  pCorreo, pCedula);
            tx.commit();
            log.trace ("Inserción Persona: " + pNombre+ ": " + tuplasInsertadas + " tuplas insertadas");
            return new PersonaNatural(pNombre,  pCorreo, pCedula);
        }
        catch (Exception e)
        {
        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	/**
	 * Extrae el mensaje de la exception JDODataStoreException embebido en la Exception e, que da el detalle específico del problema encontrado
	 * @param e - La excepción que ocurrio
	 * @return El mensaje de la excepción JDO
	 */
	private String darDetalleException(Exception e) 
	{
		String resp = "";
		if (e.getClass().getName().equals("javax.jdo.JDODataStoreException"))
		{
			JDODataStoreException je = (javax.jdo.JDODataStoreException) e;
			return je.getNestedExceptions() [0].getMessage();
		}
		return resp;
	}
	public void eliminarProductoCarrito(Long id_Producto) {
		
		sqlProductoCarrito.eliminarProducto(pmf.getPersistenceManager(), id_Producto);
	}
	public void actualizarProductoCarrito(Long id_Producto , int cantidad, Long id_Carrito) {
		sqlProductoCarrito.actualizarCarrito(pmf.getPersistenceManager(), id_Producto, cantidad, id_Carrito);
		
	}
	
	
	
	
	
	
	

}
