package contabilidad;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import General.Almacen_General;
import General.NoFoundException;
import marketing.TOfertas;



public class DaoLibroDiario {
	public void anadirCuenta(TransferCuenta debe,TransferCuenta haber)throws NoFoundException{
	
		
		revisarCuenta(debe);
		revisarCuenta(haber);
		Almacen_General.getInstance().getLibroDiario().anadircuenta(debe, haber);
		
	}
	
	public  TransferLibroDiario mostrarLibroDiario(){
		
		return Almacen_General.getInstance().getLibroDiario();
	}
	public ArrayList<TransferCuenta> consultaDebe(){
		return Almacen_General.getInstance().getLibroDiario().getDebe();
	}
	public ArrayList<TransferCuenta> consultaHaber(){
		return Almacen_General.getInstance().getLibroDiario().getHaber();
	}
	public ArrayList<TransferCuenta> consultaTotales(){
		return Almacen_General.getInstance().getLibroDiario().getTotales();
	}
	public void nuevoLibroDiario(TransferLibroDiario t){
		Almacen_General.getInstance().nuevoLibroDiario(t);
	}
	public  Double getTesoreria() {
		ArrayList<TransferCuenta> Totales =Almacen_General.getInstance().getLibroDiario().getTotales();
		boolean encontrado=false;
		int i =0;
		Double tesoreria=0.0;
		while(i<Totales.size()&& !encontrado){
			
			if (Totales.get(i).codigo == 570 ){
				encontrado=true;
				tesoreria=Totales.get(i).getCantidad();
			}
			
			i++;
		}
		return tesoreria;
	}
	public  TransferCuenta buscarCuentaTotal(int codigo)throws NoFoundException{
		ArrayList<TransferCuenta> Totales =Almacen_General.getInstance().getLibroDiario().getTotales();
		boolean encontrado= false;
		int i =0;
		TransferCuenta c = new TransferCuenta();
		while (i<Totales.size() && encontrado==false){
			if (Totales.get(i).getCodigo() ==codigo){
				encontrado= true;
				c=Totales.get(i);
			}
			i++;
		}
		if (encontrado == false){
			throw new NoFoundException("No se ha encontrado la cuenta seleccionada");
		}
		
		return c;
	}
	public void revisarCuenta(TransferCuenta cuenta)throws NoFoundException{
		ArrayList<TransferCuenta> Totales =Almacen_General.getInstance().getLibroDiario().getTotales();
		boolean encontrado= false;
	
		int i =0;
		
		while (i<Totales.size() && encontrado==false){
			if (Totales.get(i).getNombre().equals(cuenta.getNombre())){
				encontrado= true;
				if (Totales.get(i).getCodigo()!=cuenta.getCodigo()){
					throw new NoFoundException("No puede haber dos cuentas con el mismo nombre pero distinto código");
				}
			}
			i++;
		}
		
		
	}
	public void guardar(BufferedWriter escritor){
		
		
		try {

			TransferLibroDiario libro = this.mostrarLibroDiario();
			escritor.write("Libro Diario\r\n");
			escritor.write("Debe\r\n");
			escritor.write(libro.getDebe().size()+"\r\n");
			for(TransferCuenta c: libro.getDebe()){
				escritor.write(c.guardar());
			}
			escritor.write("Haber\r\n");
			escritor.write(libro.getHaber().size()+"\r\n");
			for(TransferCuenta c: libro.getHaber()){
				escritor.write(c.guardar());
			}
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "No se ha podido encontrar el fichero");
			}
			
		
				
		
		
		
	}
	
}
