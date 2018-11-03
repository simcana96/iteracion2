/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * Licenciado	bajo	el	esquema	Academic Free License versión 2.1
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Parranderos Uniandes
 * @version 1.0
 * @author Germán Bravo
 * Julio de 2018
 * 
 * Revisado por: Claudia Jiménez, Christian Ariza
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.parranderos.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto BAR de Parranderos
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Germán Bravo
 */
class SQLUtil
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra acá para facilitar la escritura de las sentencias
	 */
	private final static String SQL = PersistenciaParranderos.SQL;

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia general de la aplicación
	 */
	private PersistenciaSupermercado pp;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/

	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicación
	 */
	public SQLUtil (PersistenciaSupermercado pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para obtener un nuevo número de secuencia
	 * @param pm - El manejador de persistencia
	 * @return El número de secuencia generado
	 */
	public long nextval (PersistenceManager pm)
	{
        Query q = pm.newQuery(SQL, "SELECT "+ pp.darSeqSupermercado() + ".nextval FROM DUAL");
        q.setResultClass(Long.class);
        long resp = (long) q.executeUnique();
        return resp;
	}

	public long[] limpiarSupermercado(PersistenceManager pm) {
		 Query qBodega = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaBodega());          
	        Query qCategoria = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCategoria() );
	        Query qCliente = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCliente());
	        Query qEmpresa = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaEmpresa());
	        Query qEstante = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaEstante());
	        Query qFactura = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaFactura());
	        Query qMateriaPrima = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaMateriaPrima());
	        Query qMateriaPrimaProveedor = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaMateriaPrimaProveedor());
	        Query qPedido = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPedido());
	        Query qPersonaNatural = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPersonaNatural());
	        Query qProducto = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProducto());
	        Query qProductoBodega = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProductoBodega());
	        Query qProductoEstante = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProductoEstante());
	        Query qProductoFactura = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProductoFactura());
	        Query qProductoSucursal = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProductoSucursal());
	        Query qPromocion = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPromocion());
	        Query qProveedor = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProveedor());
	        Query qSucursal = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaSucursal());
	        
	        

	        long bodegaEliminados = (long) qBodega.executeUnique ();
	        long categoriaEliminados = (long) qCategoria.executeUnique ();
	        long clienteEliminadas = (long) qCliente.executeUnique ();
	        long empresaEliminadas = (long) qEmpresa.executeUnique ();
	        long estanteEliminados = (long) qEstante.executeUnique ();
	        long facturaEliminados = (long) qFactura.executeUnique ();
	        long materiaPrimaEliminados = (long) qMateriaPrima.executeUnique ();
	        long materiaPrimaProveedorEliminados = (long) qMateriaPrimaProveedor.executeUnique ();
	        long pedidoEliminados = (long) qPedido.executeUnique ();
	        long personaNaturalEliminados = (long) qPersonaNatural.executeUnique ();
	        long productoEliminados = (long) qProducto.executeUnique ();
	        long productoBodegaEliminados = (long) qProductoBodega.executeUnique ();
	        long productoEstanteEliminados = (long) qProductoEstante.executeUnique ();
	        long productoFacturaEliminados = (long) qProductoFactura.executeUnique ();
	        long productoSucursalEliminados = (long) qProductoSucursal.executeUnique ();
	        long promocionEliminados = (long) qPromocion.executeUnique ();
	        long proveedorEliminados = (long) qProveedor.executeUnique ();
	        long sucursalEliminados = (long) qSucursal.executeUnique ();
	        
	        return new long[] {bodegaEliminados, categoriaEliminados, clienteEliminadas, empresaEliminadas, 
	        		estanteEliminados, facturaEliminados, materiaPrimaEliminados, materiaPrimaProveedorEliminados,
	        		pedidoEliminados, personaNaturalEliminados, productoEliminados,
	        		productoBodegaEliminados, productoEstanteEliminados, productoFacturaEliminados,
	        		productoSucursalEliminados, promocionEliminados, proveedorEliminados, sucursalEliminados};
		
	}
}


