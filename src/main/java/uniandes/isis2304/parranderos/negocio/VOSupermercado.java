package uniandes.isis2304.parranderos.negocio;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.JsonObject;

import uniandes.isis2304.parranderos.persistencia.PersistenciaSupermercado;



public class VOSupermercado {

	private PersistenciaSupermercado ps;
	private List<Object []> proveedores;
	
	public VOSupermercado()
	{
		ps = PersistenciaSupermercado.getInstance();
	}

	public VOSupermercado(JsonObject tableConfig)
	{
		ps = PersistenciaSupermercado.getInstance(tableConfig);
	}

	public void cerrarUnidadPersistencia()
	{
		ps.cerrarUnidadPersistencia();
	}

	/*
	 * M�todos los requerimientos funcionales
	 */

	/**
	 * Registra un proveedor
	 * @return VOProveedor
	 */
	public VOProveedor adicionarProveedor(long pnit, String pnombre, int pcalificacion)
	{
		VOProveedor proveedor = ps.adicionarProveedor(pnit, pnombre, pcalificacion);
		return proveedor;
	}

	/**
	 * REgistrar Producto
	 * @param pnombre
	 * @param pmarca
	 * @param pcantidad_presentacion
	 * @param punidad_medida
	 * @param pespecificacion_empaque
	 * @param pcodigo_barras
	 * @param pfecha_vencimiento
	 * @param pcategoria
	 * @return VOProducto
	 * @throws Exception 
	 */
	public VOProducto adicionarProducto(String pnombre, String pmarca, int pcantidad_presentacion, String punidad_medida, String pespecificacion_empaque, String pcodigo_barras, long pfecha_vencimiento, String pcategoria) throws Exception
	{

		VOProducto producto = ps.adicionarProducto(pnombre, pmarca, pcantidad_presentacion, punidad_medida, pespecificacion_empaque, pcodigo_barras, pfecha_vencimiento, pcategoria);
		return producto;

	}

	public VOCarrito adicionarCarrito(long idSucursal, long idCliente)
	{
		return ps.adicionarCarrito(idSucursal, idCliente);
	}
	/**
	 * Registra un Cliente nuevo
	 * @param pnombre
	 * @param pcorreo
	 * @param ppuntos
	 * @return VOCliente 
	 */
	public VOCliente adicionarCliente(String pnombre, String pcorreo, int ppuntos)
	{
		VOCliente cliente = ps.adicionarCliente(pnombre, pcorreo, ppuntos);
		return cliente;
	}

	/**
	 * Registrar una Sucursal
	 * @param pnombre
	 * @param pciudad
	 * @param pdireccion
	 * @param ptamano
	 * @param ptipo_cliente
	 * @return VOSucursal
	 */
	public VOSucursal adicionarSucursal(String pnombre, String pciudad, String pdireccion, int ptamano, String ptipo_cliente)
	{
		VOSucursal sucursal = ps.adicionarSucursal(pnombre, pciudad, pdireccion, ptamano, ptipo_cliente);
		return sucursal;
	}

	/**
	 * Registrar una Bodega en una sucursal
	 * @param pcapacidad_volumen
	 * @param pcapacidad_peso
	 * @param pvolumen_existencias
	 * @param pcategoria_producto
	 * @param pid_sucursal
	 * @return VOBodega
	 */
	public VOBodega adicionarBodega(int pcapacidad_volumen, int pcapacidad_peso, int pvolumen_existencias, String pcategoria_producto, long pid_sucursal)
	{
		VOBodega bodega = ps.adicionarBodega(pcapacidad_volumen, pcapacidad_peso, pvolumen_existencias, pcategoria_producto, pid_sucursal);
		return bodega;
	}

	/**
	 * Registrar un estante en una sucursal
	 * @param pcapacidad_volumen
	 * @param pcapacidad_peso
	 * @param pvolumen_existencias
	 * @param pcategoria_producto
	 * @param pid_sucursal
	 * @return VOEstante
	 */
	public VOEstante adicionarEstante(int pcapacidad_volumen, int pcapacidad_peso, int pvolumen_existencias, String pcategoria_producto, long pid_sucursal)
	{
		VOEstante estante= ps.adicionarEstante(pcapacidad_volumen, pcapacidad_peso, pvolumen_existencias, pcategoria_producto, pid_sucursal);
		return estante;
	}

//	/**
//	 * Registrar una promoci�n 
//	 * @param ptipo
//	 * @param punidades_disponibles
//	 * @param pid_producto
//	 * @return VOPromocion 
//	 */
//	public VOPromocion adicionarPromocion(String ptipo, int punidades_disponibles, long pid_producto, long pfecha_vencimiento)
//	{
//		VOPromocion promocion = ps.adicionarPromocion(ptipo, punidades_disponibles, pid_producto, pfecha_vencimiento);
//		return promocion;
//	}

	/**
	 * Registrar categoria
	 * @param pNombre
	 * @return VOCategoria
	 */
	public VOCategoria adicionarCategoria(String pNombre)
	{
		System.out.println("VOSUPERMERCADO categoria1");
		VOCategoria categoria = ps.adicionarCategoria(pNombre);
		System.out.println("VOSUPERMERCADO categoria2");
		return categoria;
	}

	/**
	 * Registrar una Persona Natural
	 * @param pid_persona
	 * @param pcedula
	 * @return VOPersonaNatural
	 */
	public VOPersonaNatural adicionarPersonaNatural(long pid_persona, int pCedula, String pCorreo, String pNombre)
	{
		VOPersonaNatural persona = ps.adicionarPersona(pid_persona, pCedula, pCorreo, pNombre);
		return persona;
	}

	/**
	 * Registrar Empresa
	 * @param pnit
	 * @param pdireccion
	 * @return VOEmpresa
	 */
	public VOEmpresa adicionarEmpresa(int pnit, String pdireccion)
	{
		VOEmpresa empresa = ps.adicionarEmpresa(pnit, pdireccion);
		return empresa;
	}


	/**
	 * Registro de una factura
	 * @param pid_cliente
	 * @return VOFactura
	 */
	public VOFactura adicionarFactura(long pid_cliente)
	{
		VOFactura factura = ps.adicionarFactura(pid_cliente);
		return factura;
	}

	/**
	 * Registrar Materia Prima
	 * @param pnombre
	 * @return VOMateria Prima
	 */
	public VOMateriaPrima adicionarMateriaPrima(String pnombre,long pid_producto)
	{
		VOMateriaPrima materia = ps.adicionarMateriaPrima(pnombre,pid_producto);
		return materia;
	}

	/**
	 * Registrar Pedido
	 * @param pfechaAcordada
	 * @param pprecio
	 * @param pcalificacion
	 * @param pcantidad_pedir
	 * @param pid_sucursal
	 * @param pid_proveedor
	 * @param pid_materiaprima
	 * @return VOPedido
	 */
	public VOPedido adicionarPedido(long pfechaAcordada, int pprecio, int pcalificacion, int pcantidad_pedir, long pid_sucursal, long pid_proveedor, long pid_materiaprima )
	{
		VOPedido pedido = ps.adicionarPedido(pfechaAcordada, pprecio, pcalificacion, pcantidad_pedir, pid_sucursal, pid_proveedor, pid_materiaprima);
		return pedido;
	}


	// EMPIEZAN LAS RELACIONES 

	/**
	 * Registro en Materia Prima vs Proveedor
	 * @param pid_materiaprima
	 * @param pid_proveedor
	 * @return VOMateriaPrimaProveedor
	 */
	public VOMateriaPrimaProveedor adicionarMateriaPrimaProveedor(long pid_materiaprima, long pid_proveedor)
	{
		VOMateriaPrimaProveedor materia = ps.adicionarMateriaPrimaProveedor(pid_materiaprima, pid_proveedor);
		return materia;
	}

	/**
	 * Registro en Prodcuto vs Bodega
	 * @param pid_producto
	 * @param pid_bodega
	 * @param punidades_disponibles
	 * @return VOProductoBodega
	 */
	public VOProductoBodega adicionarProductoBodega(long pid_producto, long pid_bodega, int punidades_disponibles)
	{
		VOProductoBodega producto =ps.adicionarProductoBodega(pid_producto, pid_bodega, punidades_disponibles);
		return producto;
	}

	/**
	 * Registro producto vs estante
	 * @param pid_producto
	 * @param pid_estante
	 * @param punidades_disponibles
	 * @param ppuntos
	 * @param ppunto_abastecimiento
	 * @return VOProductoEstante
	 */
	public VOProductoEstante adicionarProductoEstante(long pid_producto, long pid_estante, int punidades_disponibles, int ppunto_abastecimiento)
	{
		VOProductoEstante producto = ps.adicionarProductoEstante(pid_producto, pid_estante, punidades_disponibles, ppunto_abastecimiento);
		return producto;
	}

	/**
	 * Registro en producto vs factura
	 * @param pid_factura
	 * @param pid_producto
	 * @param punidades_compradas
	 * @param ppuntos_obtenidos
	 * @return VOProductoFactura
	 */
	public VOProductoFactura adicionarProductoFactura(long pid_factura, long pid_producto, int punidades_compradas, int ppuntos_obtenidos)
	{
		VOProductoFactura producto = ps.adicionarProductoFactura(pid_factura, pid_producto, punidades_compradas, ppuntos_obtenidos);
		return producto;
	}

	/**
	 * Registro en producto vs sucursal
	 * @param pid_producto
	 * @param pid_sucursal
	 * @param pprecioAsignado
	 * @param pnivel_reorden
	 * @return VOProductoSucursal
	 */
	public VOProductoSucursal adicionarProductoSucursal(long pid_producto, long pid_sucursal, int pprecioAsignado, int pnivel_reorden)
	{
		VOProductoSucursal producto = ps.adicionarProductoSucursal(pid_producto, pid_sucursal, pprecioAsignado, pnivel_reorden);
		return producto;
	}

	public VOCategoria darCategoria(String pnombre)
	{
		return ps.darCategoria(pnombre);
	}
	
	public VOCliente darClientePorCorreo(String pCorreo)
	{
		return ps.darClientePorCorreo(pCorreo);
	}
	
	
	
	public VOProducto darProductoPorNombre(String pNombre)
	{
		return ps.darProductoPorNombre(pNombre);
	}
	
	public VOMateriaPrima darMateriaPrimaPorNombre(String pNombre)
	{
		return ps.darMateriaPrimaPorNombre(pNombre);
	}
	
	public VOProveedor darProveedorPorId(long pnit)
	{
		return ps.darProveedorPorId(pnit);
	}
	
	public List<VOProveedor> darVOProveedores ()
	{       
        List<VOProveedor> voProveedores = new LinkedList<VOProveedor> ();
        for (VOProveedor prov : ps.darProveedores())
        {
        	voProveedores.add (prov);
        }
        return voProveedores;
	}
	
	public List<VOCategoria> darVOCategorias ()
	{       
        List<VOCategoria> voCategorias = new LinkedList<VOCategoria> ();
        for (VOCategoria cat : ps.darCategorias())
        {
        	voCategorias.add (cat);
        }
        return voCategorias;
	}
	
	public VOSucursal darSucursalPorId(long id_sucursal)
	{
		return ps.darSucursalPorId(id_sucursal);
	}
	
	public VOMateriaPrima darMateriaPrimaPorId(long pid_materiaPrima)
	{
		return ps.darMateriaPrimaPorId(pid_materiaPrima);
	}
	public long actualizarLlegadaPedido(long pid_Pedido, int calificacion)
	{
		return ps.actualizarLlegadaPedido(pid_Pedido, calificacion);
	}
	public VOPedido darPedidoPorId(long pid_pedido)
	{
		return ps.darPedidoPorId(pid_pedido);
	}
	public VOProducto darProductoPorId(long pid_producto)
	{
		return ps.darProductoPorId(pid_producto);
	}
	public long actualizarUnaVenta(long pid_producto , long pid_estante, int unidades_compradas)
	{
		return ps.actualizarUnaVenta(pid_producto, pid_estante,unidades_compradas);
	}
	public long [] limpiarSupermercado ()
	{
        long [] borrrados = ps.limpiarSupermercado();	
        return borrrados;
	}
//	public long finalizarPromocion(long pid_promocion)
//	{
//		return ps.finalizarPromocion(pid_promocion);
//	}
	public List<Object []> consulta1(String fechaInicio, String fechaFin)
	{
		return  ps.consulta1(fechaInicio, fechaFin);
	}
	public List<Object[]> consulta2()
	{
		return ps.consulta2();
	}
	public List<Object[]> consulta3()
	{
		return ps.consulta3();
	}
	public List<Object[]> consulta4(String pnombre)
	{
		return ps.consulta4(pnombre);
	}
	public List consulta5()
	{
		return ps.consulta5();
	}
	public List consulta6(long pid_Cliente , String fechaInicio, String fechaFin )
	{
		return ps.consulta6(pid_Cliente, fechaInicio, fechaFin);
	}
	
	public long limpiarBodegas()
	{
		long borradas = ps.limpiarBodegas();
		return borradas;
	}
	
	public long limpiarSucursales()
	{
		long borradas = ps.limpiarSucursales();
		return borradas;
	}
	public long limpiarProveedores()
	{
		long borradas = ps.limpiarProveedores();
		return borradas;
	}
}


