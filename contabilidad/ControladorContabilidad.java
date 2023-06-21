package contabilidad;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JOptionPane;

import contabilidad.Factorias.FabricaTransferGeneralContabilidad;
import marketing.Fecha;
import General.NoFoundException;
import marketing.TAnuncios;
import marketing.TEmpresa_publi;
import marketing.TOfertas;
import General.Transfer;

public class ControladorContabilidad {
	

	private ServicioAplicacion servicios;
	private DaoCuenta DCuenta;
	private DaoLibroDiario DLibro;
	private DaoBalance DBalance;
	private FabricaTransferGeneralContabilidad fabrica_general;
	
	
	public ControladorContabilidad(){
		this.DCuenta = new DaoCuenta();
		this.DLibro = new DaoLibroDiario();
		this.DBalance = new DaoBalance();
		this.servicios = new ServicioAplicacion(DCuenta,DLibro,DBalance);
		this.fabrica_general = new FabricaTransferGeneralContabilidad();
		//cargarTransfers();
		
	}
	
	/*
	 * public void cargarTransfers() { FileReader fr; try { fr = new
	 * FileReader("src/contabilidad/fichero.txt"); BufferedReader lector=new
	 * BufferedReader(fr);
	 * 
	 * String linea = lector.readLine();
	 * 
	 * while(!linea.equals("FIN") ) {
	 * 
	 * Transfer t = fabrica_general.cargarTransfer(lector, linea); if(t instanceof
	 * TransferLibroDiario) { this.nuevoLibroDiario((TransferLibroDiario) t); } else
	 * if(t instanceof TransferBalance) { this.addBalance((TransferBalance) t); }
	 * 
	 * linea = lector.readLine(); }
	 * 
	 * lector.close();
	 * 
	 * 
	 * } catch (FileNotFoundException e) { // TODO Auto-generated catch block
	 * JOptionPane.showMessageDialog(null,
	 * "No se ha encontrado el fichero en la ruta src/contabilidad/fichero.txt"); }
	 * catch (IOException e) { // TODO Auto-generated catch block
	 * JOptionPane.showMessageDialog(null, "Ha ocurrido un error al leer");
	 * 
	 * } catch (Exception e) { JOptionPane.showMessageDialog(null, e.getMessage());
	 * 
	 * }
	 * 
	 * }
	 */
	
	
	public void guardarTransfers(BufferedWriter escritor) {
		this.servicios.guardarLibroDiario(escritor);
		this.servicios.guardarBalances(escritor);
		//this.servicios.guardarNovedades(escritor);

	}
	public TransferLibroDiario consultaDiario() {
		
		TransferLibroDiario LD = new TransferLibroDiario();
			LD =servicios.mostrarDiario();
		
		return LD;
		
	}
	public void nuevoLibroDiario(TransferLibroDiario t){
		servicios.nuevoLibroDiario(t);
	}

	public void ModificarDiario(String nombre1, int cod1, Double cant1,String nombre2, int cod2, Double cant2)throws NoFoundException {
		
			servicios.crearcuenta(nombre1, cod1, cant1, nombre2, cod2, cant2);
		
	}
	public TransferCuenta buscarCuentaTotal(int codigo)throws NoFoundException{
		return servicios.buscarCuentaTotal(codigo);
	}
	public void eliminarCuentaNovedades(int codigo, double cantidad)throws NoFoundException{
		servicios.eliminarCuentaNovedades(codigo, cantidad);
	}
	public void nuevobalance(Fecha d, ArrayList<TransferCuenta> activo,ArrayList<TransferCuenta> pasivo,	ArrayList<TransferCuenta> patrimonioNeto) {
	
		try{
			servicios.crearBalance(d,activo,pasivo,patrimonioNeto);
		}catch(NoFoundException e){
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,e.getMessage());
		}
		
		
	}
	public void addNovedades(TransferCuenta c){
		servicios.addNovedades(c);
	}
	public void addBalance(TransferBalance t){
		servicios.addBalance(t);
	}
	public ArrayList<TransferCuenta> consultaDebe() {
		return DLibro.consultaDebe();
	}
	public ArrayList<TransferCuenta> consultaHaber() {
		return DLibro.consultaHaber();
	}

	public TransferBalance consultaBalance(Fecha d)  {
		// TODO Auto-generated method stub
		return servicios.mostrarBalance(d);
	}

	public Double mostrarTesoreria() {
		
		return this.servicios.tesoreria();
	}
	public  ArrayList<TransferCuenta> consultarTotales(){
		return this.servicios.consultarTotales();
	}
	public ArrayList<TransferBalance> mostrarListaBalances(){
		return this.servicios.mostrarListaBalances();
	}
	public ArrayList<TransferCuenta> consultaNovedades(){
		return this.servicios.mostrarListaNovedades();
	}
}
