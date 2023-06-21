package Fabrica;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

import General.*;
import Mantenimiento.FabricaTransfer_ContratoTaller;
import Mantenimiento.FabricaTransfer_Reparacion;
import Pedido.FabricaTransfer_Calles;
import Pedido.FabricaTransfer_Formularios;
import Pedido.FabricaTransfer_Usuarios;
import Vehiculo.FabricaTransf_Vehiculos;
import contabilidad.Factorias.FabricaTransferBalance;
import contabilidad.Factorias.FabricaTransferCuenta;
import contabilidad.Factorias.FabricaTransferLibroDiario;
import marketing.FabricaTransfer_Anuncios;
import marketing.FabricaTransfer_Empresas;
import marketing.FabricaTransfer_Ofertas;
import personal.FabricaTransferPersonal;
import proveedores.factoryTPedidos;
import proveedores.factoryTProveedores;

public class FabricaTransfer_General implements Factory_Transfer {

	private static final FabricaTransfer_General singletonFabrica_general = new FabricaTransfer_General();
	private List<Factory_Transfer> factorias;
	
	
	private FabricaTransfer_General() {
		super();
		this.factorias = new ArrayList<Factory_Transfer>();
		this.inicializarFactorias();
	}

	@Override
	public Transfer crearTransfer(ArrayList<Object> info) throws NoFoundException {
		
		return null;
	}

	@Override
	public Transfer cargarTransfer(BufferedReader lector, String linea) throws NoFoundException {
		// TODO Auto-generated method stub
		/**
		 * Carga transfers desde una linea y luego llama a crear transefer con la lista completa
		 * Quitar Lsta<String>
		 * 
		 */
		for(Factory_Transfer i: factorias) {
			
			if(i.getInfo().equals(linea) ) {
				return i.cargarTransfer(lector, linea);
				
			}
			
		}
		
		return null;
		
	}
	
	private void inicializarFactorias() {
		
		factorias.add(new FabricaTransfer_Anuncios());
		factorias.add(new FabricaTransfer_Empresas());
		factorias.add(new FabricaTransfer_Ofertas());
		factorias.add(new factoryTPedidos());
		factorias.add(new factoryTProveedores());
		factorias.add(new FabricaTransfer_Calles());
		factorias.add(new FabricaTransfer_Usuarios());
		factorias.add(new FabricaTransfer_Formularios());
		factorias.add(new FabricaTransferPersonal());
		factorias.add(new FabricaTransfer_ContratoTaller());
		factorias.add(new FabricaTransfer_Reparacion());
		factorias.add(new FabricaTransf_Vehiculos());
		factorias.add(new FabricaTransferBalance());
		factorias.add(new FabricaTransferLibroDiario());
		factorias.add(new FabricaTransferCuenta());
		
		
	}

	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static FabricaTransfer_General getInstance() {
		return singletonFabrica_general;
	}

}
