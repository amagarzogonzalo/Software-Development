package contabilidad.Factorias;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import contabilidad.TransferBalance;
import contabilidad.TransferCuenta;
import Fabrica.Factory_Transfer;
import marketing.Fecha;
import General.NoFoundException;
import General.Transfer;

public class FabricaTransferBalance implements Factory_Transfer{
	private FabricaTransferCuenta Fcuenta;

	@Override
	public Transfer crearTransfer(ArrayList<Object> info) throws NoFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transfer cargarTransfer(BufferedReader lector, String linea)throws NoFoundException {
		Fcuenta= new FabricaTransferCuenta();
		ArrayList<TransferCuenta> activo = new ArrayList<TransferCuenta>();
		ArrayList<TransferCuenta> pasivo = new ArrayList<TransferCuenta>();
		ArrayList<TransferCuenta> pn = new ArrayList<TransferCuenta>();
		
		
		try {
			
			ArrayList<Object> info = new ArrayList<Object>();
			String comp = lector.readLine();
			String[] atributos = comp.split("/");
			Fecha fecha = new Fecha( Integer.parseInt(atributos[0]), Integer.parseInt(atributos[1]), Integer.parseInt(atributos[2]) );
			comp= lector.readLine();
			if(comp.equals("Activo")){
				
				comp =lector.readLine();
				int numCuentas =Integer.parseInt(comp);
				comp = lector.readLine();
				for (int i = 0 ; i < numCuentas ; i++ ){
					
					 
					atributos = comp.split(":");
					info.clear();
					for(String j: atributos) {
						info.add(j);
					}					
					activo.add( (TransferCuenta) Fcuenta.crearTransfer(info));
					comp = lector.readLine();
				}
					
				if (comp.equals("Pasivo")){
						comp =lector.readLine();
						 numCuentas =Integer.parseInt(comp);
						 comp = lector.readLine();
						for (int h = 0 ; h < numCuentas ; h++ ){

						
							atributos = comp.split(":");
							info.clear();
							for(String s: atributos) {
								info.add(s);
							}
							
							pasivo.add((TransferCuenta) Fcuenta.crearTransfer(info));
							
							
							comp = lector.readLine();
						}
							if (comp.equals("Patrimonio Neto")){
								comp =lector.readLine();
								 numCuentas =Integer.parseInt(comp);
								
								for (int t = 0 ; t < numCuentas ; t++ ){

									 comp = lector.readLine();
									atributos = comp.split(":");
									info.clear();
									for(String r: atributos) {
										info.add(r);
									}
									
									pn.add((TransferCuenta) Fcuenta.crearTransfer(info));
									
								}
								
							}else{
									throw new NoFoundException("Formato lectura incorecto");
							}
						
						
					}else{
						throw new NoFoundException("Formato lectura incorecto");
					}
		
			
			}else{
				throw new NoFoundException("Formato lectura incorecto");
			}
			return new TransferBalance(activo,pasivo,pn,fecha);
				
		}catch(Exception f){
				JOptionPane.showMessageDialog(null, "Ha ocurrido un problema con la lectura del fichero." + f.getMessage());
		}
			return null;
			
	}

	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		return "Balance";
	}

}
