package contabilidad;

import java.io.BufferedWriter;
import java.util.ArrayList;

import General.Transfer;

public class TransferLibroDiario extends Transfer {
	
	

	private ArrayList<TransferCuenta> debe;
	private ArrayList<TransferCuenta> haber;
	private ArrayList<TransferCuenta> totales;

	public TransferLibroDiario(){
		debe=new ArrayList<TransferCuenta>();
		haber=new ArrayList<TransferCuenta>();
		totales=new ArrayList<TransferCuenta>();
	}
	
	public TransferLibroDiario(ArrayList<TransferCuenta> debe,ArrayList<TransferCuenta> haber){
		PGC pgc= new PGC();
		this.debe=debe;
		this.haber= haber;	
		totales=pgc.getPGC();
		calculoTotales(debe);
		calculoTotales(haber);
		
	}

	public void anadircuenta(TransferCuenta cuenta,	TransferCuenta contrapartida2) {
		debe.add(cuenta);
		haber.add(contrapartida2);
		addTotal(cuenta);
		addTotal(contrapartida2);
		
	}
	
	public void addTotal(TransferCuenta c){
		int i =0;
		boolean cont = false;
		
		while(i<totales.size()&&cont==false){
			if(c.getCodigo()==totales.get(i).getCodigo()){
				cont = true;
				double cant=totales.get(i).getCantidad();
				totales.get(i).setCantidad(cant+c.getCantidad());
			}
			i++;
		}
		if (cont ==false){
			totales.add(c);
		}
		
		
	}
	public void calculoTotales(ArrayList<TransferCuenta> d){
		for (TransferCuenta c : d){
			int i =0;
			boolean cont=false;
			while (i < totales.size()&& cont==false){
				if(c.getCodigo()==totales.get(i).getCodigo()){
					cont = true;
					totales.get(i).setCantidad(totales.get(i).getCantidad()+c.getCantidad());
				}
				i++;
			}
			if (cont ==false){
				totales.add(c);
			}
			
		}
		
	}
	public ArrayList<TransferCuenta> getDebe() {
		return debe;
	}

	public ArrayList<TransferCuenta> getHaber() {
		return haber;
	}
	public ArrayList<TransferCuenta> getTotales(){
		return totales;
	}

	@Override
	public String guardar() {
		// TODO Auto-generated method stub
		return null;
	}
	public String toString(){
		
		
		return" ";
		
		
		
	}
	

}
