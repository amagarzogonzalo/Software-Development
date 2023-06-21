package contabilidad;

import java.util.ArrayList;

import General.Almacen_General;
import General.NoFoundException;

public class DaoCuenta {

	public static void anadir(TransferCuenta cuenta,TransferCuenta contrapartida) {
		
		//Almacen_General.getInstance().anadirTotal(cuenta, contrapartida);
		Almacen_General.getInstance().anadircuentasDiario(cuenta,contrapartida);
	}
	
	public TransferCuenta crearCuenta(String nom, int cod,Double cant){
		return new TransferCuenta(nom,cod,cant);
	}
	public void eliminarCuentaNovedades(int codigo,double cantidad)throws NoFoundException {
		TransferCuenta cuenta=Almacen_General.getInstance().getCuentaNovedades(codigo);
		if (cuenta!= null){
			if ( cuenta.getCantidad() != cantidad){
				throw new NoFoundException("La cantidad no coincide con la cuenta de novedades");
			}
		}		
		Almacen_General.getInstance().eliminarCuentaNovedades(codigo);
		
	}
	public  ArrayList<TransferCuenta> mostrarListaNovedades(){
		return Almacen_General.getInstance().consultaNovedades();
	}

}
