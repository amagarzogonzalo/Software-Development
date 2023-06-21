package contabilidad;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import marketing.Fecha;
import General.Almacen_General;
import General.NoFoundException;

public class DaoBalance{

	public boolean CalcularTotales( ArrayList<TransferCuenta> activo,ArrayList<TransferCuenta> pasivo,ArrayList<TransferCuenta> patrimonioNeto){
	
	int TA=0,TP=0,TPN=0;
	for(TransferCuenta c: activo){
		TA+= c.getCantidad();
	}
	for(TransferCuenta d: pasivo){
		TP+= d.getCantidad();
	}
	for(TransferCuenta t: patrimonioNeto){
		TPN+= t.getCantidad();
	}
	if (TA+ TP+TPN==0){
		return true;
	}else{
		return false;
	}
	
	}
	/*
	public Double suma(ArrayList<TransferCuenta> t){
		Double suma=0.0;
		for(TransferCuenta c : t){
			suma = suma + c.getCantidad();
		}
		return suma;
	}*/
	public void crearBalance(Fecha d, ArrayList<TransferCuenta> activo,ArrayList<TransferCuenta> pasivo,ArrayList<TransferCuenta> patrimonioNeto)throws NoFoundException{
		if (CalcularTotales(activo,pasivo,patrimonioNeto)== false){
			throw new NoFoundException("Activo=Pasivo+PatrimonioNeto");
		}else{
			TransferBalance nuevoBalance= new TransferBalance(activo,pasivo,patrimonioNeto,d);
			
			Almacen_General.getInstance().addBalance(nuevoBalance);
			}
		
	}
	public void addBalance(TransferBalance t){
		Almacen_General.getInstance().addBalance(t);
	}
	public  TransferBalance mostrarBalance(Fecha d) {
	
		return Almacen_General.getInstance().getBalance(d);
	}
	/*public Double ConsultaTesoreria(){
		return Almacen_General.getInstance().getTesoreria();
	}*/
	public ArrayList<TransferBalance> ConsultaListaBalances(){
		return Almacen_General.getInstance().getBalances();
	}
	public void guardarBalance(BufferedWriter escritor, TransferBalance balance){
		
		
	try {
		
		escritor.write("Balance\r\n");
		escritor.write(balance.FechaString()+"\r\n");
		escritor.write("Activo\r\n");
		escritor.write(balance.getActivo().size()+"\r\n");
		for(TransferCuenta c:balance.getActivo()){
			escritor.write(c.guardar());
		}
		escritor.write("Pasivo\r\n");
		escritor.write(balance.getPasivo().size()+"\r\n");
		for(TransferCuenta c:balance.getPasivo()){
			escritor.write(c.guardar());
		}
		escritor.write("Patrimonio Neto\r\n");
		escritor.write(balance.getPatrimonioNeto().size()+"\r\n");
		for(TransferCuenta c:balance.getPatrimonioNeto()){
			escritor.write(c.guardar());
		}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "No se ha podido encontrar el fichero");
		}
		
	}
	
	
}
