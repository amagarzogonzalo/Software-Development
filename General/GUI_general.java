package General;

import java.util.Scanner;



public class GUI_general {

	private Controlador_General _ctrl;
	private Scanner capt;

	public GUI_general(Controlador_General _ctrl) {
		super();
		capt = new Scanner(System.in);
		this._ctrl = _ctrl;
	}
	
	
	public Boolean run() {
		Boolean seguir = true;
		
		System.out.println("Elige entre las siguientes opciones");
		System.out.println("1- Contabilidad");
		System.out.println("2- Marketing");
		System.out.println("3- Actividad");
		System.out.println("4- Personal");
		System.out.println("5- Proveedores");
		System.out.println("6- Mantenimiento");
		System.out.println("7- Vehiculos");
		System.out.println("8- Salir");

		int eleccion = leerOpcionInteger();
		
		if(eleccion >0 && eleccion < 9) {
			
			if( eleccion == subsistemas.CONTABILIDAD.opciones()) {
				try {
					this._ctrl.contabilidad();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.err.println(e.getMessage());
				}
			}
			else if(eleccion == subsistemas.MARKETING.opciones()) {
				try {
					this._ctrl.marketing();
				} catch (Exception e) {
					// TODO: handle exception
					System.err.println(e.getMessage());
				}
			}
			else if(eleccion == subsistemas.ACTIVIDAD.opciones()) {
				try {
					this._ctrl.actividad();
				} catch (Exception e) {
					// TODO: handle exception
					System.err.println(e.getMessage());
				}
			}
			else if(eleccion == subsistemas.PERSONAL.opciones()) {
				try {
					this._ctrl.personal();
				} catch (Exception e) {
					// TODO: handle exception
					System.err.println(e.getMessage());
				}
			}
			else if(eleccion == subsistemas.PROVEEDORES.opciones()) {
				try {
					this._ctrl.proveedor();
				} catch (Exception e) {
					// TODO: handle exception
					System.err.println(e.getMessage());
				}
			}
			else if(eleccion == subsistemas.MANTENIMIENTO.opciones()) {
				try {
					this._ctrl.mantenimiento();
				} catch (Exception e) {
					// TODO: handle exception
					System.err.println(e.getMessage());
				}
			}
			else if(eleccion == subsistemas.VEHICULOS.opciones()) {
				try {
					this._ctrl.vehiculos();
				} catch (Exception e) {
					// TODO: handle exception
					System.err.println(e.getMessage());
				}
			}
			else if(eleccion == subsistemas.SALIR.opciones()) {
				this._ctrl.guardarTransfers();
				return false;
			}	
	
		}
		else {
			System.out.println("Hay que introducir un numero del 1 al 9");
		}
		
		return seguir;
	}
	
	
	
	public int leerOpcionInteger() throws NumberFormatException {
		int tup= 0;
		String lectura = capt.nextLine();

		tup=Integer.parseInt(lectura);
		
		
		return tup;
	}
	
}
