package contabilidad.Factorias;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import contabilidad.TransferCuenta;
import Fabrica.Factory_Transfer;
import General.NoFoundException;
import marketing.TAnuncios;
import General.Transfer;

public class FabricaTransferCuenta  implements Factory_Transfer {

	@Override
	public Transfer crearTransfer(ArrayList<Object> info) throws NoFoundException {
	
		
		
			if(info.size() != 3) {
				
				throw new NoFoundException("Los parametros no coinciden con el Transfer");
			}
			
			return new TransferCuenta((String) info.get(0),Integer.parseInt((String) info.get(1)),Double.parseDouble((String) info.get(2)));
			
			
	}

	@Override
	public Transfer cargarTransfer(BufferedReader lector, String linea)throws NoFoundException {
		
		try {
			ArrayList<Object> info = new ArrayList<Object>();
			String oferta = lector.readLine();
			String[] atributos = oferta.split(":");
			for(String i: atributos) {
				info.add(i);
			}
			
			return this.crearTransfer(info);
					
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Ha ocurrido un problema con la lectura del fichero");
		}
		
		return null;
	}

	@Override
	public String getInfo() {
		
		return "Novedades";
	}

}
