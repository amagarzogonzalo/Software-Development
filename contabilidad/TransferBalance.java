package contabilidad;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Date;

import marketing.Fecha;
import General.NoFoundException;
import General.Transfer;

public class TransferBalance extends Transfer {
	
	private ArrayList<TransferCuenta> Activo;
	private ArrayList<TransferCuenta> Pasivo;
	private ArrayList<TransferCuenta> PatrimonioNeto;
	private int totalActivo;
	private int totalPasivo;
	private int totalPatrimonioNeto;
	Fecha fecha;
	public TransferBalance(){
		
		Activo= new ArrayList<TransferCuenta>();
		Pasivo= new ArrayList<TransferCuenta>();
		PatrimonioNeto = new ArrayList<TransferCuenta>();
		totalActivo=0;
		totalPasivo=0;
		totalPatrimonioNeto=0;
		
	}	
	public TransferBalance(ArrayList<TransferCuenta> activo,ArrayList<TransferCuenta> pasivo,ArrayList<TransferCuenta> patrimonioNeto, Fecha fecha) throws NoFoundException{
		super();
		Activo = activo;
		Pasivo = pasivo;
		PatrimonioNeto = patrimonioNeto;
		this.fecha = fecha;
		calcularTotales();
		
		
	}
	public ArrayList<TransferCuenta> getActivo() {
		return Activo;
	}
	public ArrayList<TransferCuenta> getPasivo() {
		return Pasivo;
	}
	public ArrayList<TransferCuenta> getPatrimonioNeto() {
		return PatrimonioNeto;
	}
	public Fecha getFecha() {
		return fecha;
	}
	public String FechaString(){
	return fecha.getDay()+ "/"+fecha.getMonth()+"/"+fecha.getYear();
	}
	public void calcularTotales(){
		for(TransferCuenta c: Activo){
			totalActivo+=c.cantidad;
		}
		for(TransferCuenta c: Pasivo){
			totalPasivo+=c.cantidad;
		}
		for(TransferCuenta c: PatrimonioNeto){
			totalPatrimonioNeto+=c.cantidad;
		}
		
	}
	
	public int getTotalActivo(){
		return totalActivo;
	}
	public int getTotalPasivo(){
		return totalPasivo;
	}
	public int getTotalPatrimonioNeto(){
		return totalPatrimonioNeto;
	}
	@Override
	public String guardar() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
}
