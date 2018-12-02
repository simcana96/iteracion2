package uniandes.isis2304.parranderos.interfazApp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import org.datanucleus.identity.IdentityUtils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.InterningXmlVisitor;

import uniandes.isis2304.parranderos.negocio.Carrito;
import uniandes.isis2304.parranderos.negocio.PersonaNatural;
import uniandes.isis2304.parranderos.negocio.ProductoCarrito;
import uniandes.isis2304.parranderos.negocio.VOBodega;
import uniandes.isis2304.parranderos.negocio.VOCarrito;
import uniandes.isis2304.parranderos.negocio.VOCategoria;
import uniandes.isis2304.parranderos.negocio.VOCliente;
import uniandes.isis2304.parranderos.negocio.VOEstante;
import uniandes.isis2304.parranderos.negocio.VOFactura;
import uniandes.isis2304.parranderos.negocio.VOMateriaPrima;
import uniandes.isis2304.parranderos.negocio.VOMateriaPrimaProveedor;
import uniandes.isis2304.parranderos.negocio.VOPedido;
import uniandes.isis2304.parranderos.negocio.VOPersonaNatural;
import uniandes.isis2304.parranderos.negocio.VOProducto;
import uniandes.isis2304.parranderos.negocio.VOProductoBodega;
import uniandes.isis2304.parranderos.negocio.VOProductoCarrito;
import uniandes.isis2304.parranderos.negocio.VOProductoEstante;
import uniandes.isis2304.parranderos.negocio.VOProductoFactura;
import uniandes.isis2304.parranderos.negocio.VOProductoSucursal;
import uniandes.isis2304.parranderos.negocio.VOProveedor;
import uniandes.isis2304.parranderos.negocio.VOSucursal;
import uniandes.isis2304.parranderos.negocio.VOSupermercado;


public class InterfazSupermercado extends JFrame implements ActionListener {


	private final String CONFIG_INTERFAZ = "./src/main/resources/config/InterfazConfigSupermercado.json";

	private final String CONFIG_TABLAS = "./src/main/resources/config/TablasBD_Supermercado.json";

	private VOSupermercado supermercado;

	private JsonObject tableConfig;

	private JsonObject guiConfig;

	private PanelDatos panelDatos;

	private JMenuBar menuBar;

	/**
	 * M�todo Constructor
	 */
	InterfazSupermercado()
	{
		guiConfig = openConfig("Interfaz",CONFIG_INTERFAZ);

		configurarFrame();
		if(guiConfig !=null)
		{
			crearMenu(guiConfig.getAsJsonArray("menuBar"));
		}



		tableConfig = openConfig ("Tablas BD", CONFIG_TABLAS);
		supermercado = new VOSupermercado (tableConfig);

		String path = guiConfig.get("bannerPath").getAsString();
		panelDatos = new PanelDatos();

		setLayout (new BorderLayout());
		add(new JLabel (new ImageIcon (path)), BorderLayout.NORTH);
		add(panelDatos, BorderLayout.CENTER );
	}

	private JsonObject openConfig (String tipo, String archConfig)
	{
		JsonObject config = null;
		try 
		{
			Gson gson = new Gson( );
			FileReader file = new FileReader (archConfig);
			JsonReader reader = new JsonReader ( file );
			config = gson.fromJson(reader, JsonObject.class);
		} 
		catch (Exception e)
		{
			e.printStackTrace ();		
			JOptionPane.showMessageDialog(null, "No se encontr� un archivo de configuraci�n de interfaz v�lido: " + tipo, "SuperAndes App", JOptionPane.ERROR_MESSAGE);
		}	
		return config;
	}


	private void configurarFrame(  )
	{
		int alto = 0;
		int ancho = 0;
		String titulo = "";	

		if ( guiConfig == null )
		{		
			titulo = "SuperAndes APP Default";
			alto = 300;
			ancho = 500;
		}
		else
		{
			titulo = guiConfig.get("title").getAsString();
			alto= guiConfig.get("frameH").getAsInt();
			ancho = guiConfig.get("frameW").getAsInt();
		}

		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setLocation (50,50);
		setResizable( true );
		setBackground( Color.WHITE );

		setTitle( titulo );
		setSize ( ancho, alto);        
	}

	private void crearMenu(  JsonArray jsonMenu )
	{    	
		// Creaci�n de la barra de men�s
		menuBar = new JMenuBar();       
		for (JsonElement men : jsonMenu)
		{
			// Creaci�n de cada uno de los men�s
			JsonObject jom = men.getAsJsonObject(); 

			String menuTitle = jom.get("menuTitle").getAsString();        	
			JsonArray opciones = jom.getAsJsonArray("options");

			JMenu menu = new JMenu( menuTitle);

			for (JsonElement op : opciones)
			{       	
				// Creaci�n de cada una de las opciones del men�
				JsonObject jo = op.getAsJsonObject(); 
				String lb =   jo.get("label").getAsString();
				String event = jo.get("event").getAsString();

				JMenuItem mItem = new JMenuItem( lb );
				mItem.addActionListener( this );
				System.out.println(lb +": "+event);
				mItem.setActionCommand(event);
				System.out.println("Yes");

				menu.add(mItem);
			}       
			menuBar.add( menu );
		}        
		setJMenuBar ( menuBar );	

	}

	public static void main( String[] args )
	{
		try
		{
			// Unifica la interfaz para Mac y para Windows.
			UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName( ) );
			InterfazSupermercado interfaz = new InterfazSupermercado();
			interfaz.setVisible(true);
		}
		catch( Exception e )
		{
			e.printStackTrace( );
		}
	}


	/**
	 * adiciona una categoria dada
	 */
	public void adicionarCategoria( )
	{
		try 
		{
			String nombreTipo = JOptionPane.showInputDialog (this, "Nombre de la categoria?", "Adicionar categoria", JOptionPane.QUESTION_MESSAGE);

			if (nombreTipo != null)
			{
				System.out.println("aqui si" + nombreTipo + "  " + supermercado);
				VOCategoria tb = supermercado.adicionarCategoria (nombreTipo);
				if (tb == null)
				{
					throw new Exception ("No se pudo crear una categoria con nombre: " + nombreTipo);
				}
				String resultado = "En adicionarCategoria\n\n";
				resultado += "Categoria adicionada exitosamente: " + tb;
				resultado += "\n Operacion terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operacion cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	/**
	 * adiciona cliente
	 */
	public void adicionarCliente()
	{
		try
		{
			String pnombre = JOptionPane.showInputDialog(this,"Nombre del cliente?","Adicionar Cliente",JOptionPane.QUESTION_MESSAGE);
			String correo = JOptionPane.showInputDialog(this,"Correo del cliente?","Adicionar Cliente",JOptionPane.QUESTION_MESSAGE);
			String puntos = JOptionPane.showInputDialog(this,"Numero de puntos?","Adicionar Cliente",JOptionPane.QUESTION_MESSAGE);
			if(pnombre != null && correo != null && puntos != null)
			{
				VOCliente cliente = supermercado.adicionarCliente(pnombre, correo, Integer.parseInt(puntos));
				if (cliente == null)
				{
					throw new Exception ("No se pudo crear un cliente. ");
				}
				String resultado = "En adicionar Cliente\n\n";
				resultado += "Cliente adicionado exitosamente: " + cliente;
				resultado += "\n Operacion terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				throw new Exception ("No se pudo crea el cliente");
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void adicionarProducto()
	{
		try
		{
			String pcategoria = JOptionPane.showInputDialog (this, "Nombre de la categoria?", "Adicionar categoria", JOptionPane.QUESTION_MESSAGE);
			if(supermercado.darCategoria(pcategoria) != null)
			{
				String pnombre = JOptionPane.showInputDialog (this, "Nombre producto?", "Adicionar nombre", JOptionPane.QUESTION_MESSAGE);
				String pmarca = JOptionPane.showInputDialog (this, "Nombre de la marca?", "Adicionar marca", JOptionPane.QUESTION_MESSAGE);
				String cantidad_presentacion = JOptionPane.showInputDialog (this, "Cantidad presentacion?", "Adicionar cantidad presentacion", JOptionPane.QUESTION_MESSAGE);
				String punidad_medida = JOptionPane.showInputDialog (this, "Unidad de medida?", "Adicionar unidad de medida", JOptionPane.QUESTION_MESSAGE);
				String pespecificacion_empaque = JOptionPane.showInputDialog (this, "Especificacion de empaque?", "Adicionar especificacion empaque", JOptionPane.QUESTION_MESSAGE);
				String pcodigo_barras = JOptionPane.showInputDialog (this, "Codigo de barras en hexadecimal?", "Adicionar codigo de barras", JOptionPane.QUESTION_MESSAGE);
				String fechaVencimiento = JOptionPane.showInputDialog (this, "Fecha de vencimiento?", "Adicionar fecha de vencimiento", JOptionPane.QUESTION_MESSAGE);
				int pcantidad_presentacion = Integer.parseInt(cantidad_presentacion);
				long pfecha_vencimiento = Long.parseLong(fechaVencimiento);

				VOProducto prod = null; //supermercado.darProductoPorNombre(pnombre);
				if( prod != null && prod.getCantidad_presentacion() == pcantidad_presentacion)
				{
					throw new Exception("El producto no se puede crear");
				}
				else
				{
					supermercado.adicionarProducto(pnombre, pmarca, pcantidad_presentacion, punidad_medida, pespecificacion_empaque, pcodigo_barras, pfecha_vencimiento, pcategoria);
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * adiciona materia prima
	 */
	public void adicionarMateriaPrima()
	{
		try
		{
			String nombreMateria = JOptionPane.showInputDialog(this,"Nombre de la materia Prima?","Adicionar Materia prima",JOptionPane.QUESTION_MESSAGE);
			if(nombreMateria != null)
			{
				VOProducto productoAsociado = supermercado.darProductoPorNombre(nombreMateria);

				VOMateriaPrima materiaPrima = supermercado.adicionarMateriaPrima(nombreMateria, productoAsociado.getId_producto());
				if (materiaPrima == null)
				{
					throw new Exception ("No se pudo crear una materia prima con nombre: " + nombreMateria);
				}
				String resultado = "En adicionar materia prima\n\n";
				resultado += "Materia prima adicionada exitosamente: " + materiaPrima;
				resultado += "\n Operacion terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operacion cancelada por el usuario");
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * adiciona proveedor
	 */
	public void adicionarProveedor()
	{
		try
		{
			String nit = JOptionPane.showInputDialog(this,"Nit del proveedor?","Adicionar Proveedor",JOptionPane.QUESTION_MESSAGE);
			String pnombre = JOptionPane.showInputDialog(this,"Nombre del proveedor?","Adicionar Proveedor",JOptionPane.QUESTION_MESSAGE);
			String calificacion = JOptionPane.showInputDialog(this,"Calificacion del proveedor?","Adicionar Proveedor",JOptionPane.QUESTION_MESSAGE);
			if(nit != null & pnombre != null && calificacion != null)
			{
				long pnit = Long.parseLong(nit);
				int pcalificacion = Integer.parseInt(calificacion);
				VOProveedor proveedor = supermercado.darProveedorPorId(pnit);
				if (proveedor == null)
				{
					proveedor = supermercado.adicionarProveedor(pnit, pnombre, pcalificacion);
					if (proveedor == null)
					{
						throw new Exception ("No se pudo crear una proveedor con nombre: " + pnombre);
					}
					String resultado = "En adicionar proveedor\n\n";
					resultado += "Proveedor adicionada exitosamente: " + proveedor;
					resultado += "\n Operacion terminada";
					panelDatos.actualizarInterfaz(resultado);
				}

			}
			else
			{
				panelDatos.actualizarInterfaz("Operacion cancelada por el usuario");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * adiciona materia prima a un proveedor
	 */
	public void adicionarMateriaPrimaAUnProveedor()
	{
		try
		{
			long pidProveedor = Long.parseLong(JOptionPane.showInputDialog(this,"Nit del proveedor?","Adicionar Materia prima a proveedor",JOptionPane.QUESTION_MESSAGE));
			String nombreMateriaPrima = JOptionPane.showInputDialog(this,"Nombre de la materia prima ?","Adicionar Materia prima a proveedor",JOptionPane.QUESTION_MESSAGE);
			VOProveedor proveedor = supermercado.darProveedorPorId(pidProveedor);
			VOMateriaPrima materiaPrima = supermercado.darMateriaPrimaPorNombre(nombreMateriaPrima);
			if(proveedor != null && materiaPrima != null)
			{
				VOMateriaPrimaProveedor adicion = supermercado.adicionarMateriaPrimaProveedor(materiaPrima.getId_materia_prima(), proveedor.getNit());
				if (adicion == null)
				{
					throw new Exception ("No se pudo crear una materia prima para un proveedor. ");
				}
				String resultado = "En adicionar MateriaPrimaProveedor\n\n";
				resultado += "MateriaPrimaProveedor adicionada exitosamente: " + proveedor;
				resultado += "\n Operacion terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				throw new Exception ("No se pudo crea la meteria prima para un proveedor");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * adciona sucursal
	 */
	public void adicionarSucursal()
	{
		try
		{
			String pnombre = JOptionPane.showInputDialog(this,"Nombre de la sucursal?","Adicionar Sucursal",JOptionPane.QUESTION_MESSAGE);
			String ciudad = JOptionPane.showInputDialog(this,"Ciudad de la sucursal?","Adicionar Sucursal",JOptionPane.QUESTION_MESSAGE);
			String direccion = JOptionPane.showInputDialog(this,"Direccion sucursal?","Adicionar Sucursal",JOptionPane.QUESTION_MESSAGE);
			String tamano = JOptionPane.showInputDialog(this,"Tamano de la sucursal?","Adicionar Sucursal",JOptionPane.QUESTION_MESSAGE);
			String tipoCliente = JOptionPane.showInputDialog(this,"Tipo de cliente?","Adicionar Sucursal",JOptionPane.QUESTION_MESSAGE);
			if(pnombre != null && ciudad != null && direccion != null && tamano != null && tipoCliente != null)
			{
				VOSucursal sucursal = supermercado.adicionarSucursal(pnombre, ciudad, direccion, Integer.parseInt(tamano), tipoCliente);
				if (sucursal == null)
				{
					throw new Exception ("No se pudo crear una sucursal. ");
				}
				String resultado = "En adicionar Sucursal\n\n";
				resultado += "Sucursal adicionada exitosamente: " + sucursal.getId_sucursal();
				resultado += "\n Operacion terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				throw new Exception ("No se pudo crea la sucursal");
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * adciona bodega a una sucursal
	 */
	public void adicionarBodega()
	{
		try
		{
			int capacidad_volumen = Integer.parseInt(JOptionPane.showInputDialog(this,"Capacidad de volumen de la bodega?","Adicionar Bodega",JOptionPane.QUESTION_MESSAGE));
			int capacidad_peso = Integer.parseInt(JOptionPane.showInputDialog(this,"Capacidad de peso de la bodega?","Adicionar Bodega",JOptionPane.QUESTION_MESSAGE));
			int volumen_existencias = Integer.parseInt(JOptionPane.showInputDialog(this,"Volumen de existencias del producto?","Adicionar Bodega",JOptionPane.QUESTION_MESSAGE));
			String categoria_producto = JOptionPane.showInputDialog(this,"Categoria del producto?","Adicionar Bodega",JOptionPane.QUESTION_MESSAGE);
			long id_sucursal = Long.parseLong(JOptionPane.showInputDialog(this,"id Sucursal?","Adicionar Bodega",JOptionPane.QUESTION_MESSAGE));

			if(capacidad_volumen != 0 && capacidad_peso != 0 && volumen_existencias != 0 && categoria_producto != null && id_sucursal != 0 )
			{
				if(supermercado.darSucursalPorId(id_sucursal) != null)
				{
					VOBodega bodega = supermercado.adicionarBodega(capacidad_volumen, capacidad_peso, volumen_existencias, categoria_producto, id_sucursal);
					if (bodega == null)
					{
						throw new Exception ("No se pudo crear una bodega. ");
					}
					String resultado = "En adicionar Bodega\n\n";
					resultado += "Bodega adicionada exitosamente: " + bodega.getId_sucursal();
					resultado += "\n Operacion terminada";
					panelDatos.actualizarInterfaz(resultado);
				}
			}
			else
			{
				throw new Exception ("No se pudo crea la sucursal");
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}


	/**
	 * adiciona estante a una sucursal
	 */
	public void adicionarEstante()
	{
		try
		{
			int capacidad_volumen = Integer.parseInt(JOptionPane.showInputDialog(this,"Capacidad de volumen del estante?","Adicionar Estante",JOptionPane.QUESTION_MESSAGE));
			int capacidad_peso = Integer.parseInt(JOptionPane.showInputDialog(this,"Capacidad de peso del estante?","Adicionar Estante",JOptionPane.QUESTION_MESSAGE));
			int volumen_existencias = Integer.parseInt(JOptionPane.showInputDialog(this,"Volumen de existencias del producto?","Adicionar Estante",JOptionPane.QUESTION_MESSAGE));
			String categoria_producto = JOptionPane.showInputDialog(this,"Categoria del producto?","Adicionar Estante",JOptionPane.QUESTION_MESSAGE);
			long id_sucursal = Long.parseLong(JOptionPane.showInputDialog(this,"id Sucursal?","Adicionar Estante",JOptionPane.QUESTION_MESSAGE));

			if(capacidad_volumen != 0 && capacidad_peso != 0 && volumen_existencias != 0 && categoria_producto != null && id_sucursal != 0 )
			{
				if(supermercado.darSucursalPorId(id_sucursal) != null)
				{
					VOEstante estante = supermercado.adicionarEstante(capacidad_volumen, capacidad_peso, volumen_existencias, categoria_producto, id_sucursal);
					if (estante == null)
					{
						throw new Exception ("No se pudo crear un estante. ");
					}
					String resultado = "En adicionar Estante\n\n";
					resultado += "Estante adicionada exitosamente: " + estante.getId_sucursal();
					resultado += "\n Operacion terminada";
					panelDatos.actualizarInterfaz(resultado);
				}
			}
			else
			{
				throw new Exception ("No se pudo crea la sucursal");
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * registra un pedido a un proveedor
	 */
	public void registrarPedido()
	{
		try
		{
			String fechaArcodada = JOptionPane.showInputDialog(this,"Fecha acordada de entrega?","Adicionar Pedido",JOptionPane.QUESTION_MESSAGE);
			String precio = JOptionPane.showInputDialog(this,"Precio acordado?","Adicionar Pedido",JOptionPane.QUESTION_MESSAGE);
			String calificacion = JOptionPane.showInputDialog(this,"Calificacion del pedido?","Adicionar Pedido",JOptionPane.QUESTION_MESSAGE);
			String cantidad_pedir = JOptionPane.showInputDialog(this,"Cantidad del producto a pedir?","Adicionar Pedido",JOptionPane.QUESTION_MESSAGE);
			String id_sucursal = JOptionPane.showInputDialog(this,"Id de la sucursal?","Adicionar Pedido",JOptionPane.QUESTION_MESSAGE);
			String id_proveedor = JOptionPane.showInputDialog(this,"id del proveedor?","Adicionar Pedido",JOptionPane.QUESTION_MESSAGE);
			String id_materiaPrima = JOptionPane.showInputDialog(this,"Id de la materia prima a pedir?","Adicionar Pedido",JOptionPane.QUESTION_MESSAGE);

			if(fechaArcodada != null && precio != null && calificacion != null && cantidad_pedir != null && id_sucursal != null && id_proveedor != null && id_materiaPrima != null)
			{
				long pfechaAcordada = Long.parseLong(fechaArcodada);
				int pprecio = Integer.parseInt(precio);
				int pcalificacion = Integer.parseInt(calificacion);
				int pcantidad_pedir = Integer.parseInt(cantidad_pedir);
				long pid_sucursal = Integer.parseInt(id_sucursal);
				long pid_proveedor = Integer.parseInt(id_proveedor);
				long pid_materiaprima = Integer.parseInt(id_materiaPrima);
				if(supermercado.darProveedorPorId(pid_proveedor) != null && supermercado.darSucursalPorId(pid_sucursal) != null && supermercado.darMateriaPrimaPorId(pid_materiaprima) != null)
				{
					VOPedido nuevoPedido = supermercado.adicionarPedido(pfechaAcordada, pprecio, pcalificacion, pcantidad_pedir, pid_sucursal, pid_proveedor, pid_materiaprima);
					if (nuevoPedido == null)
					{
						throw new Exception ("No se pudo crear un pedido. ");
					}
					String resultado = "En adicionar Pedido\n\n";
					resultado += "Pedido adicionada exitosamente: " + nuevoPedido.getId_pedido();
					resultado += "\n Operacion terminada";
					panelDatos.actualizarInterfaz(resultado);
				}
				else
				{
					throw new Exception ("No se pudo crear el pedido");
				}

			}
			else
			{
				throw new Exception ("No se pudo crear el pedido");
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}


	/**
	 * registra la llegada de un pedido a una sucursal
	 */
	public void llegadaPedido()
	{
		try
		{
			String id_pedido = JOptionPane.showInputDialog(this,"Id del pedido?","Actualizado Pedido",JOptionPane.QUESTION_MESSAGE);
			String calificacion = JOptionPane.showInputDialog(this,"Calificacion del pedido?","Actualizar Pedido",JOptionPane.QUESTION_MESSAGE);
			String id_estante = JOptionPane.showInputDialog(this,"Id del estante que se agrega el producto?","Actualizar Pedido",JOptionPane.QUESTION_MESSAGE);
			String id_bodega = JOptionPane.showInputDialog(this,"Id de la bodega que se agrega el producto?","Actualizar Pedido",JOptionPane.QUESTION_MESSAGE);
			String id_sucursal = JOptionPane.showInputDialog(this,"Id de la sucursal que se agrega el producto?","Actualizar Pedido",JOptionPane.QUESTION_MESSAGE);

			if(id_pedido != null && calificacion != null && id_estante != null && id_bodega != null && id_sucursal != null)
			{
				long pid_pedido = Long.parseLong(id_pedido);
				int pcalificacion = Integer.parseInt(calificacion);
				long pedido = supermercado.actualizarLlegadaPedido(pid_pedido, pcalificacion);
				long pid_estante = Long.parseLong(id_estante);
				long pid_bodega = Long.parseLong(id_bodega);
				long pid_sucursal = Long.parseLong(id_sucursal);
				VOPedido pedidoCompleto = supermercado.darPedidoPorId(pid_pedido);
				VOMateriaPrima mp = supermercado.darMateriaPrimaPorId(pedidoCompleto.getId_materia_prima());
				VOProductoEstante productoEstante = supermercado.adicionarProductoEstante(mp.getId_producto(), pid_estante,  (int) Math.round(0.3*pedidoCompleto.getCantidad_pedir()), pedidoCompleto.getCantidad_pedir());
				VOProductoSucursal productoSucursal = supermercado.adicionarProductoSucursal(mp.getId_producto(), pid_sucursal, pedidoCompleto.getPrecio(), pedidoCompleto.getCantidad_pedir());
				VOProductoBodega productoBodega = supermercado.adicionarProductoBodega(mp.getId_producto(), pid_bodega, (int) Math.round( 0.7*pedidoCompleto.getCantidad_pedir()));
				if (pedido == 0 || productoEstante == null || productoBodega == null || productoSucursal == null)
				{
					throw new Exception ("No se pudo crear un pedido. ");
				}
				String resultado = "En actualizar Pedido\n\n";
				resultado += "Pedido actualizado exitosamente: " + pedido;
				resultado += "\n Operacion terminada";
				panelDatos.actualizarInterfaz(resultado);

			}
			else
			{
				throw new Exception ("No se pudo actualizar el pedido");
			}


		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * registra la compra de un cliente
	 */
	public void adicionarCompra()
	{
		try
		{
			String id_Cliente = JOptionPane.showInputDialog(this,"Id del cliente?","Adicionar Venta",JOptionPane.QUESTION_MESSAGE);
			String id_Sucursal = JOptionPane.showInputDialog(this,"Id de la sucursal?","Adicionar Venta",JOptionPane.QUESTION_MESSAGE);
			String id_Estante = JOptionPane.showInputDialog(this,"Id del estante?","Adicionar Venta",JOptionPane.QUESTION_MESSAGE);
			String id_Producto = JOptionPane.showInputDialog(this,"Id del producto?","Adicionar Venta",JOptionPane.QUESTION_MESSAGE);
			String unidadesCompradas = JOptionPane.showInputDialog(this,"Unidades vendidas?","Adicionar Venta",JOptionPane.QUESTION_MESSAGE);

			if(id_Cliente != null && id_Sucursal != null && id_Estante != null && id_Producto != null && unidadesCompradas != null)
			{
				long pid_cliente = Long.parseLong(id_Cliente);
				long pid_sucursal = Long.parseLong(id_Sucursal);
				long pid_estante = Long.parseLong(id_Estante);
				long pid_producto = Long.parseLong(id_Producto);
				int punidades_compradas = Integer.parseInt(unidadesCompradas);
				VOFactura nuevaFactura = supermercado.adicionarFactura(pid_cliente);
				VOProducto productoComprado = supermercado.darProductoPorId(pid_producto);
				VOProductoFactura nuevaVenta = supermercado.adicionarProductoFactura(nuevaFactura.getId_factura(), pid_producto, punidades_compradas, 5);
				long productoEstanteMenos = supermercado.actualizarUnaVenta(pid_producto, pid_estante,punidades_compradas);
				if(nuevaFactura == null || productoComprado == null || nuevaVenta == null || productoEstanteMenos == 0)
				{
					throw new Exception ("No se pudo crear una factura. ");
				}
				String resultado = "En adicionar Factura\n\n";
				resultado += "Factura creada exitosamente: " + nuevaFactura;
				resultado += "\n Operacion terminada";
				panelDatos.actualizarInterfaz(resultado);
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * Asigna un carrito de compra a un cliente
	 */
	
	public void asignarCarrito()
	{
		try
		{
			String id_Cliente = JOptionPane.showInputDialog(this,"Id del cliente?","Adicionar Venta",JOptionPane.QUESTION_MESSAGE);
			
			if(id_Cliente != null)
			{
				long id = Long.parseLong(id_Cliente);
				
				
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	//------------------------------------------------------------------
  	// Metodos iteracion 2
  	//------------------------------------------------------------------
  	public void solicitarCarrito()
  	{
  		String correoCliente = JOptionPane.showInputDialog (this, "¿Cual es tu correo?", "Adicionar carrito a cliente", JOptionPane.QUESTION_MESSAGE);
  		
  			
  			VOCliente cliente = supermercado.darClientePorCorreo(correoCliente);
  			if( cliente != null)
  			{
  				String sucursal = JOptionPane.showInputDialog (this, "¿En que sucursal te encuentras? Escribe el id", "Adicionar carrito a cliente", JOptionPane.QUESTION_MESSAGE);
  				if( isId(sucursal))
  				{
  					Long id_sucursal = Long.parseLong(sucursal);
  					VOSucursal sucursal1 = supermercado.darSucursalPorId(id_sucursal);
  					if( sucursal1 != null)
  					{
  						VOCarrito carrito = supermercado.adicionarCarrito(id_sucursal, cliente.getId_cliente());
  						panelDatos.actualizarInterfaz("El carrito con " + carrito.getId_Carrito() +" se ha asignado al cliente " + cliente.getNombre() );
  					}
  					else
  					{
  						JOptionPane.showMessageDialog(null, "La sucursal no está registrada en la base de datos, intente con un numero válido", "Supermercado App", JOptionPane.ERROR_MESSAGE);
  					}
  				}
  				else
  				{
  					JOptionPane.showMessageDialog(null, "No es un numero entero válido por favor intente de nuevo", "Supermercado App", JOptionPane.ERROR_MESSAGE);
  				}
  				
  			}
  			else
  			{
  				JOptionPane.showMessageDialog(null, "Su cedula no está registrada en la base de datos, intente con un numero válido", "Supermercado App", JOptionPane.ERROR_MESSAGE);
  			}
  		
  		
  	}
  	
  	public void adicionarProductoCarrito()
  	{
  		
  		String idCarrito = JOptionPane.showInputDialog (this, "¿Cual es tu id_carrito?", "Adicionar producto al carro", JOptionPane.QUESTION_MESSAGE);
  		if( isId(idCarrito))
  		{
  			Long id_Carrito = Long.parseLong(idCarrito);
  			VOCarrito carro = supermercado.darcarrito(id_Carrito);
  			// Preguntar el id Del producto 
  			
  			if( carro != null)
  			{
  				String idProducto = JOptionPane.showInputDialog (this, "¿Cual es tu id_producto?", "Adicionar producto al carro", JOptionPane.QUESTION_MESSAGE);
  				if( isId(idProducto) )
  	  			{
  	  				Long id_Producto = Long.parseLong(idProducto);
  	  				System.out.println(supermercado.darProductoPorId(id_Producto));
  	  				VOProducto producto = supermercado.darProductoPorId(id_Producto);
  	  				if( producto != null)
  	  				{
  	  					String pCantidad = JOptionPane.showInputDialog (this, "¿Cantidad del producto?", "Adicionar producto al carro", JOptionPane.QUESTION_MESSAGE);
  	  	  				if( isNumeric(pCantidad))
  	  	  				{
  	  	  					int cantidad = Integer.parseInt(pCantidad);
  	  	  					Long estante = supermercado.darEstantePorIdProducto(producto.getId_producto(),carro.getId_Sucursal());
  	  	  					VOProductoCarrito pc = supermercado.adicionarProductoCarrito(carro.getId_Carrito(), producto.getId_producto(), estante, cantidad);
  	  	  					supermercado.actualizarUnaVenta(producto.getId_producto(), estante, cantidad);
  	  	  					panelDatos.actualizarInterfaz("El producto con " + producto.getId() +" se ha colocado en el carrito " + id_Carrito );
  	  	  				}
  	  	  				else
  	  	  				{
  	  	  					JOptionPane.showMessageDialog(null, "Lo ingresado no es un numero, intente con un numero válido", "Supermercado App", JOptionPane.ERROR_MESSAGE);
  	  	  				}
  	  				}
  	  				else
  	  				{
  	  					JOptionPane.showMessageDialog(null, "Lo ingresado no es un id de producto válido, intente con un id de producto válido", "Supermercado App", JOptionPane.ERROR_MESSAGE);
  	  				}
  	  				
  	  			}
  	  			else
  	  			{
  	  				JOptionPane.showMessageDialog(null, "Lo ingresado no es un numero, intente con un numero válido", "Supermercado App", JOptionPane.ERROR_MESSAGE);
  	  			}
  			}
  			else
  			{
  				JOptionPane.showMessageDialog(null, "Lo ingresado no es un id de carrito válido, intente con un id de carrito válido", "Supermercado App", JOptionPane.ERROR_MESSAGE);
  			}
  		}
  		else
  		{
  			JOptionPane.showMessageDialog(null, "Lo ingresado no es un numero, intente con un numero válido", "Supermercado App", JOptionPane.ERROR_MESSAGE);
		}
  	}

  	public void devolverProductoCarrito()
  	{
  		
  		String idCarrito = JOptionPane.showInputDialog (this, "¿Cual es tu id_carrito?", "Devolver producto del carro", JOptionPane.QUESTION_MESSAGE);
  		if( isId(idCarrito))
  		{
  			Long id_Carrito = Long.parseLong(idCarrito);
  			VOCarrito carro = supermercado.darcarrito(id_Carrito);
  			// Preguntar el id Del producto 
  			
  			if( carro != null)
  			{
  				String idProducto = JOptionPane.showInputDialog (this, "¿Cual es tu id_producto?", "Devolver producto del carro", JOptionPane.QUESTION_MESSAGE);
  				if( isId(idProducto) )
  	  			{
  	  				Long id_Producto = Long.parseLong(idProducto);
  	  				VOProducto producto = supermercado.darProductoPorId(id_Producto);
  	  				if( producto != null)
  	  				{
  	  					String pCantidad = JOptionPane.showInputDialog (this, "¿Cantidad del producto a devolver?", "Devolver producto del carro", JOptionPane.QUESTION_MESSAGE);
  	  	  				if( isNumeric(pCantidad))
  	  	  				{
  	  	  					int cantidad = Integer.parseInt(pCantidad);
  	  	  					Long estante = supermercado.darEstantePorIdProducto(producto.getId_producto(),carro.getId_Sucursal());
  	  	  					ProductoCarrito pc = supermercado.darProductoCarrito(id_Producto, id_Carrito );
  	  	  					if( cantidad >= pc.getCantidad_Productos())
  	  	  					{
  	  	  						cantidad = pc.getCantidad_Productos();
  	  	  						supermercado.eliminarProductoCarrito(id_Producto);
  	  	  					}
  	  	  					else
  	  	  					{
  	  	  						supermercado.actualizarProductoCarrito(id_Producto, cantidad, carro.getId_Carrito());
  	  	  					}
  	  	  					if( cantidad <= 0)
  	  	  					{
  	  	  						cantidad = 0;
  	  	  					}
  	  	  					supermercado.actualizarEstanteProducto(id_Producto, estante , cantidad );
  	  	  					panelDatos.actualizarInterfaz("El producto con " + producto.getId() +" se ha quitado del carrito " + id_Carrito + 
  	  	  							" y devuelto al estante "+ estante);
  	  	  				}
  	  	  				else
  	  	  				{
  	  	  					JOptionPane.showMessageDialog(null, "Lo ingresado no es un numero, intente con un numero válido", "SuperMercado App", JOptionPane.ERROR_MESSAGE);
  	  	  				}
  	  				}
  	  				else
  	  				{
  	  					JOptionPane.showMessageDialog(null, "Lo ingresado no es un id de producto válido, intente con un id de producto válido", "SuperMercado App", JOptionPane.ERROR_MESSAGE);
  	  				}
  	  				
  	  			}
  	  			else
  	  			{
  	  				JOptionPane.showMessageDialog(null, "Lo ingresado no es un numero, intente con un numero válido", "SuperMercado App", JOptionPane.ERROR_MESSAGE);
  	  			}
  			}
  			else
  			{
  				JOptionPane.showMessageDialog(null, "Lo ingresado no es un id de carrito válido, intente con un id de carrito válido", "Supermercado App", JOptionPane.ERROR_MESSAGE);
  			}
  		}
  		else
  		{
  			JOptionPane.showMessageDialog(null, "Lo ingresado no es un numero, intente con un numero válido", "Supermercado App", JOptionPane.ERROR_MESSAGE);
		}
  	}
  	//-------------------------------------------------------------------
  	// Métodos adicionales 
  	//-------------------------------------------------------------------
  	public static boolean isNumeric(String cadena) {

        boolean resultado;

        try {
            Integer.parseInt(cadena);
            resultado = true;
        } catch (NumberFormatException excepcion) {
            resultado = false;
        }

        return resultado;
    }
  	public static boolean isId(String cadena) {

        boolean resultado;

        try {
            Long.parseLong(cadena);
            resultado = true;
        } catch (NumberFormatException excepcion) {
            resultado = false;
        }

        return resultado;
    }
        

	/**
	 * genera la consulta 1
	 */
	public void consulta1()
	{
		try
		{
			String fechaInicio = JOptionPane.showInputDialog(this,"Fecha de Inicio (ej. 1/1/2017)","Consulta 6",JOptionPane.QUESTION_MESSAGE);
			String fechaFin = JOptionPane.showInputDialog(this,"Fecha de Fin (ej. 1/1/2019)","Consulta 6",JOptionPane.QUESTION_MESSAGE);
			
			
			List<Object[]> respConsulta1 = supermercado.consulta1(fechaInicio, fechaFin);
			String resultado = "En Consulta 1\n\n";
			System.out.println(resultado);
			if(respConsulta1 == null)
			{
				resultado+= "No hay todavia compras hechas."+ "\n";
			}
			else
			{
				for(int i = 0 ; i < respConsulta1.size();i++)
				{
					Object[] a = respConsulta1.get(i);
					resultado += "Sucursal: "+a[0] + " Suma de ventas: " + a[1] + "\n";
				}
			}
			resultado += "\n Operacion terminada";
			panelDatos.actualizarInterfaz(resultado);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void consulta2()
	{
		try
		{
			List<Object[]> respConsulta2 = supermercado.consulta2();
			String resultado = "En Consulta 2\n\n";
			System.out.println(resultado);
			if(respConsulta2 == null)
			{
				resultado+= "No hay ningún dato que cumpla con esas características."+"\n";
			}
			else
			{
				for(int i = 0 ; i < respConsulta2.size();i++)
				{
					Object[] a = respConsulta2.get(i);
					resultado += "Nombre: "+a[0] + "\n";
				}
			}
			resultado += "\n Operacion terminada";
			panelDatos.actualizarInterfaz(resultado);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void consulta3()
	{
		try
		{
			List<Object[]> respConsulta = supermercado.consulta3();
			String resultado = "En Consulta 3\n\n";
			System.out.println(resultado);
			if(respConsulta == null)
			{
				resultado+= "No hay ningún dato que cumpla con esas características"+ "\n";
			}
			else
			{
				for(int i = 0 ; i < respConsulta.size();i++)
				{
					Object[] a = respConsulta.get(i);
					resultado += "Nombre: "+a[0]+"\n" + "Ocupación estante: "+a[1]+"\n"+ "Ocupación Bodega: "+ a[2]+"\n";
				}
			}
			resultado += "\n Operacion terminada";
			panelDatos.actualizarInterfaz(resultado);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void consulta4()
	{
		try
		{
			String parametro = JOptionPane.showInputDialog(this,"Nombre del producto?","Consulta 4",JOptionPane.QUESTION_MESSAGE);
			
			List<Object[]> respConsulta = supermercado.consulta4(parametro);
			String resultado = "En Consulta 4\n\n";
			System.out.println(resultado);
			if(respConsulta == null || respConsulta.size()==0)
			{
				resultado+= "No hay ningún dato que cumpla con esas características."+ "\n";
			}
			else
			{
				for(int i = 0 ; i < respConsulta.size();i++)
				{
					Object[] a = respConsulta.get(i);
					resultado += "Nombre: "+a[0]+"\n" + "Ocupación estante: "+a[1]+"\n"+ "Ocupación Bodega: "+ a[2]+"\n";
				}
			}
			resultado += "\n Operacion terminada";
			panelDatos.actualizarInterfaz(resultado);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public void consulta5()
	{
		try
		{
			List<Object[]> respConsulta = supermercado.consulta5();
			String resultado = "En Consulta 5\n\n";
			System.out.println(resultado);
			if(respConsulta == null || respConsulta.size()==0)
			{
				resultado+= "No hay ningún dato que cumpla con esas características."+ "\n";
			}
			else
			{
				for(int i = 0 ; i < respConsulta.size();i++)
				{
					Object[] a = respConsulta.get(i);
					resultado += "Id del Pedido: "+a[0]+"\n" + "Nombre: "+a[1]+"\n"+ "Fecha entrega: "+ a[2]+"\n"+ "Nombre: "+ a[3]+ "\n"+ "Cantidad pedido: "+a[4]+"\n";
				}
			}
			resultado += "\n Operacion terminada";
			panelDatos.actualizarInterfaz(resultado);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void consulta6()
	{
		try
		{
			String arametro1 = JOptionPane.showInputDialog(this,"Id del Cliente?","Consulta 6",JOptionPane.QUESTION_MESSAGE);
			String parametro2 = JOptionPane.showInputDialog(this,"Fecha de Inicio (ej. 1/1/2017)","Consulta 6",JOptionPane.QUESTION_MESSAGE);
			String parametro3 = JOptionPane.showInputDialog(this,"Fecha de Fin (ej. 1/1/2019)","Consulta 6",JOptionPane.QUESTION_MESSAGE);
			
			long parametro1 = Long.parseLong(arametro1);

			
			
			List<Object[]> respConsulta = supermercado.consulta6(parametro1, parametro2, parametro3);
			String resultado = "En Consulta 6\n\n";
			System.out.println(resultado);
			if(respConsulta == null || respConsulta.size()==0)
			{
				resultado+= "No hay ningún dato que cumpla con esas características"+ "\n";
			}
			else
			{
				for(int i = 0 ; i < respConsulta.size();i++)
				{
					Object[] a = respConsulta.get(i);
					resultado += "Id del Pedido: "+a[0]+"\n" + "Nombre: "+a[1]+"\n"+ "Fecha entrega: "+ a[2]+"\n"+ "Nombre: "+ a[3]+ "\n"+ "Cantidad pedido: "+a[4]+"\n";
				}
			}
			resultado += "\n Operacion terminada";
			panelDatos.actualizarInterfaz(resultado);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void consulta7()
	{
		String fechaInicio = JOptionPane.showInputDialog (this, "Fecha de Inicio", "Fecha de inicio", JOptionPane.QUESTION_MESSAGE);
		String fechaFin = JOptionPane.showInputDialog (this, "Fecha de Fin", "Fecha de fin", JOptionPane.QUESTION_MESSAGE);
		String id_Producto = JOptionPane.showInputDialog (this, "Id producto", "Id Producto", JOptionPane.QUESTION_MESSAGE);
		
		String message = "GROUP BY";
		JCheckBox checkbox = new JCheckBox("Fecha");
		JCheckBox checkbox1 = new JCheckBox("ID_SUCURSAL");
		JCheckBox checkbox2 = new JCheckBox("SUM(CANTIDAD_PRODUCTO_COMPRADO");
		Object[] params = {message, checkbox, checkbox1, checkbox2};
		JOptionPane.showConfirmDialog(this, params, "Order BY", JOptionPane.YES_NO_OPTION);
		
		String message1 = "ORDER BY";
		
		
		JCheckBox OBNombre = new JCheckBox("Nombre");
		JCheckBox OBCorreo = new JCheckBox("Nombre");
		JCheckBox OBFecha = new JCheckBox("Fecha");
		JCheckBox OBID_Sucursal = new JCheckBox("ID_SUCURSAL");
		JCheckBox OB_SUM = new JCheckBox("SUM(CANTIDAD_PRODUCTO_COMPRADO");
		JCheckBox OBCantidad = new JCheckBox("CANTIDAD_PRODUCTO_COMPRADO");
		if(checkbox.isSelected() | checkbox1.isSelected() | checkbox1.isSelected() )
		{
			
			if(checkbox.isSelected() && checkbox1.isSelected() && checkbox2.isSelected() )
			{
				Object[] paramsOB = {message1,  OBFecha, OBID_Sucursal, OB_SUM};
			}
			else if  (checkbox.isSelected() && checkbox1.isSelected())
			{
				Object[] paramsOB = {message1,  OBFecha, OBID_Sucursal};
			}
			else if ( checkbox1.isSelected() && checkbox2.isSelected())
			{
				Object[] paramsOB = {message1,  OBID_Sucursal, OB_SUM};
			}
			JOptionPane.showConfirmDialog(this, paramsOB, "Order BY", JOptionPane.YES_NO_OPTION);
		}
		else
		{
			
			Object[] paramsOB = {message1, OBNombre, OBCorreo, OBFecha, OBID_Sucursal, OB_SUM, OBCantidad};
			JOptionPane.showConfirmDialog(this, paramsOB, "Order BY", JOptionPane.YES_NO_OPTION);
		}
		System.out.println(OBNombre.isSelected() + " " + checkbox2.isSelected());
		
	}
	@Override
	public void actionPerformed(ActionEvent pEvento) {
		// TODO Auto-generated method stub
		String evento = pEvento.getActionCommand( );		
		try 
		{
			Method req = InterfazSupermercado.class.getMethod ( evento );			
			req.invoke ( this );
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
	}
}
