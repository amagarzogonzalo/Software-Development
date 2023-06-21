package contabilidad.Factorias;





import java.io.BufferedReader;
import java.util.ArrayList;



import java.util.List;

import Fabrica.Factory_Transfer;
import General.NoFoundException;
import General.Transfer;

public class FabricaTransferGeneralContabilidad implements Factory_Transfer{

	private List<Factory_Transfer> factorias;
	
	
	public FabricaTransferGeneralContabilidad() {
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
		
		factorias.add(new FabricaTransferBalance());
		factorias.add(new FabricaTransferLibroDiario());
	
		
	}

	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		return null;
	}



}
