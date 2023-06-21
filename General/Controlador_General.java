package General;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

import Fabrica.FabricaTransfer_General;
import GUIVehiculo.GUI_Vehiculo;
import Mantenimiento.Controlador;
import Mantenimiento.TContratoTaller;
import Mantenimiento.TReparacion;
import Mantenimiento.VistaMenuMantenimiento;
import Observadores.implObservadores;
import Pedido.Controller_Actividad;
import Pedido.TransferCalles;
import Pedido.TransferPedido;
import Pedido.TransferUsuario;
import Pedido.GUI.GUI_Actividad_Menu;
import Vehiculo.controladorVehiculo;
import Vehiculo.transferVehiculo;
import contabilidad.ControladorContabilidad;
import contabilidad.TransferBalance;
import contabilidad.TransferCuenta;
import contabilidad.TransferLibroDiario;
import contabilidad.GUI.GUI_Contabilidad;
import marketing.Controller_Marketing;
import marketing.TAnuncios;
import marketing.TEmpresa_publi;
import marketing.TOfertas;
import marketing.GUI.Marketing_GUI_principal;
import personal.ControladorPersonal;
import personal.TransferP;
import personal.Vista;
import proveedores.*;

public class Controlador_General {

	private controladorProveedor _ctrlProovedores;
	private controladorVehiculo _ctrlVehiculos;
	private Controller_Marketing _ctrlMarketing;
	private ControladorContabilidad _ctrlContabilidad;
	private Controller_Actividad _ctrlActividad;
	private ControladorPersonal _ctrlPersonal;
	private Controlador _ctrlMantenimiento;

	private vistaProveedores vistaProovedores;
	private GUI_Vehiculo vistaVehiculos;
	private Vista vistaPersonal;
	private VistaMenuMantenimiento vistaMantenimiento;
	private FabricaTransfer_General fabrica_general;
	//private implObservadores observadores;
	
	
	public Controlador_General() {
		super();
		//Fabrica
		this.fabrica_general = FabricaTransfer_General.getInstance();
		//this.observadores = new implObservadores();
		
		this._ctrlProovedores = new controladorProveedor();
		this._ctrlVehiculos = new controladorVehiculo();
		this._ctrlMarketing = new Controller_Marketing();
		this._ctrlContabilidad = new ControladorContabilidad();

		this._ctrlMantenimiento = new Controlador();
		this._ctrlVehiculos = new controladorVehiculo();

		

		this.vistaProovedores = new vistaProveedores(_ctrlProovedores);
		this.vistaVehiculos = new GUI_Vehiculo(_ctrlVehiculos);
		this.vistaMantenimiento = new VistaMenuMantenimiento(_ctrlMantenimiento);
		
		this._ctrlActividad = new Controller_Actividad(); 
		this._ctrlPersonal = new ControladorPersonal(vistaPersonal);
		
		//Llamar a todos los cargar
		this.cargarTransfers();
		
	}

	
	public void guardarTransfers() {
		FileWriter f;
		try {
			f = new FileWriter("src/General/fichero.txt");
			BufferedWriter escritor=new BufferedWriter(f);
			
			this._ctrlVehiculos.guardarTransfers(escritor);
			this._ctrlProovedores.guardarDatos(escritor);
			this._ctrlActividad.guardarTransfers(escritor);
			this._ctrlMantenimiento.guardarTransfers(escritor);
			this._ctrlMarketing.guardarTransfers(escritor);
			this._ctrlPersonal.guardarTransfers(escritor);
			this._ctrlContabilidad.guardarTransfers(escritor);
			
			escritor.write("FIN");
			
			escritor.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "No se ha podido crear el fichero en la ruta src/General/fichero.txt");

		}

	}
	
	public void cargarTransfers() {
		FileReader fr;
		try {
			fr = new FileReader("src/General/fichero.txt");
			BufferedReader lector=new BufferedReader(fr);
			
			String linea = lector.readLine();
			
			int contadorProv = -1;
			
			while(!linea.equals("FIN") ) {
				
				
				
				Transfer t = fabrica_general.cargarTransfer(lector, linea);
				if(t instanceof TOfertas) {
					this._ctrlMarketing.incluirOferta((TOfertas) t);
				}
				else if(t instanceof TAnuncios) {
					this._ctrlMarketing.incluirAnuncio((TAnuncios) t);
				}
				else if(t instanceof TEmpresa_publi) {
					this._ctrlMarketing.incluirEmpresa((TEmpresa_publi) t);
				}
				else if(t instanceof transferProveedores) {
					this._ctrlProovedores.addProveedor((transferProveedores) t);
					contadorProv++;
				}
				else if(t instanceof transferPedidos) {
					this._ctrlProovedores.addPedido((transferPedidos)t, contadorProv);
				}
				else if(t instanceof TransferPedido) {
					this._ctrlActividad.crearPedido((TransferPedido) t);
				}
				else if(t instanceof TransferCalles) {
					this._ctrlActividad.incluirCalleTrans((TransferCalles) t);
				}
				else if(t instanceof TransferUsuario) {
					this._ctrlActividad.incluirUsuarioTrans((TransferUsuario) t);
				}
				else if(t instanceof transferVehiculo){
					this._ctrlVehiculos.incluirVehiculo((transferVehiculo) t);
				}
				else if(t instanceof TContratoTaller){
					this._ctrlMantenimiento.incluirContratoTaller((TContratoTaller) t); 
				}
				else if(t instanceof TReparacion){
					this._ctrlMantenimiento.incluirReparacion((TReparacion) t); 
				}
				else if(t instanceof TransferLibroDiario) {
					this._ctrlContabilidad.nuevoLibroDiario((TransferLibroDiario) t);
				}
				else if(t instanceof TransferBalance) {
					this._ctrlContabilidad.addBalance((TransferBalance) t);
				}
				else if(t instanceof TransferP) {
					this._ctrlPersonal.crear((TransferP) t);
				}else if (t instanceof TransferCuenta){
					this._ctrlContabilidad.addNovedades((TransferCuenta) t);
				}
				//Meter vuestras funciones incluir
				
				linea = lector.readLine();
			}
			
			lector.close();
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "No se ha encontrado el fichero en la ruta src/general/fichero.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Ha ocurrido un error al leer");

		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());

		}
		
	}
	
	

	public void proveedor() {
		//this.vistaProovedores.setVisible(true);
		this.vistaProovedores = new vistaProveedores(_ctrlProovedores);
		this.vistaProovedores.ejecutar();
	}
	
	public void marketing() {
		/*
		 * EventQueue.invokeLater(new Runnable() { public void run() { try {
		 * VistaMarketing window = new VistaMarketing(_ctrlMarketing);
		 * 
		 * } catch (Exception e) { e.printStackTrace(); } } });
		 */
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Marketing_GUI_principal frame = new Marketing_GUI_principal(_ctrlMarketing);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void contabilidad() {
		GUI_Contabilidad vist = new GUI_Contabilidad(this._ctrlContabilidad);
	}
	
	public void actividad() {
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_Actividad_Menu frame = new GUI_Actividad_Menu(_ctrlActividad);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
	public void personal() {
		this.vistaPersonal=new Vista(_ctrlPersonal);
	}
	
	public void vehiculos() {
		this.vistaVehiculos.setVisible(true);
	}
	
	public void mantenimiento() {
		this.vistaMantenimiento.setVisible(true);
	}
	
}
