package contabilidad.Factorias;



import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import contabilidad.TransferCuenta;
import contabilidad.TransferLibroDiario;
import Fabrica.Factory_Transfer;
import General.NoFoundException;
import marketing.TAnuncios;
import General.Transfer;

public class FabricaTransferLibroDiario implements Factory_Transfer  {
	
	private FabricaTransferCuenta FCuenta;
	
	public Transfer crearTransfer(ArrayList<TransferCuenta> debe, ArrayList<TransferCuenta> haber) throws NoFoundException {
		try {
			
			return new TransferLibroDiario(debe,haber);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "Ha fallado la creacion del Transfer anuncio " + e.getMessage());
		}
		
		return null;
	}

	@Override
	public Transfer cargarTransfer(BufferedReader lector, String linea)	throws NoFoundException {
		FCuenta = new FabricaTransferCuenta();
		ArrayList<TransferCuenta> debe = new ArrayList<TransferCuenta>();
		ArrayList<TransferCuenta> haber = new ArrayList<TransferCuenta>();;
		
		try {
			ArrayList<Object> info = new ArrayList<Object>();
			String comp = lector.readLine();
			String datos;
			String[] atributos;
			if(comp.equals("Debe")){
				comp =lector.readLine();
				int numCuentas =Integer.parseInt(comp);
				
				for (int i = 0 ; i < numCuentas ; i++ ){
					
					 datos = lector.readLine();
					 atributos = datos.split(":");
					info.clear();
					for(String j: atributos) {
						info.add(j);
					}
					
					debe.add((TransferCuenta) FCuenta.crearTransfer(info));
				}
					comp = lector.readLine();
					if (comp.equals("Haber")){
						comp = lector.readLine();
						numCuentas =Integer.parseInt(comp);
						
						for (int h = 0 ; h < numCuentas ; h++ ){

							datos = lector.readLine();
							atributos = datos.split(":");
							info.clear();
							
							for(String j: atributos) {
								info.add(j);
							}
							
							haber.add((TransferCuenta) FCuenta.crearTransfer(info));
							
						}
					}else{
						throw new NoFoundException("Formato lectura incorecto");
					}
	
				
			}else{
				throw new NoFoundException("Formato lectura incorecto");
			}
				
		}catch(Exception f){
				JOptionPane.showMessageDialog(null, "Ha ocurrido un problema con la lectura del fichero." + f.getMessage());
			}
			
			return this.crearTransfer(debe,haber);
		
	}

	@Override
	public String getInfo() {
		
		return "Libro Diario";
	}

	@Override
	public Transfer crearTransfer(ArrayList<Object> info)
			throws NoFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	

}
