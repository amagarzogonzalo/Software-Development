package contabilidad;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import Observadores.Observadores;
import Observadores.implObservadores;
import Vehiculo.transferVehiculo;
import marketing.Fecha;
import marketing.TOfertas;
import personal.TransferP;
import General.Almacen_General;
import General.NoFoundException;


public class ServicioAplicacion implements Observadores {
	
	private DaoCuenta DCuenta;
	private DaoLibroDiario DLibro;
	private DaoBalance DBalance;
	public ServicioAplicacion(DaoCuenta Dcuenta,DaoLibroDiario DLibro,DaoBalance DBalance){
		this.DCuenta=Dcuenta;
		this.DLibro=DLibro;
		this.DBalance=DBalance;
		implObservadores.getInstance().addObserver(this);
	}

	public void crearcuenta(String nombre1, int cod1, Double cant1,String nombre2, int cod2, Double cant2)throws NoFoundException {
		
			TransferCuenta debe=DCuenta.crearCuenta(nombre1, cod1, cant1);
			TransferCuenta haber=  DCuenta.crearCuenta(nombre2,cod2,cant2);
			DLibro.anadirCuenta(debe, haber);
		
		
		
	}
	public TransferLibroDiario mostrarDiario(){
		
		return DLibro.mostrarLibroDiario();
	}

	public void crearBalance(Fecha d, ArrayList<TransferCuenta> activo,ArrayList<TransferCuenta> pasivo,ArrayList<TransferCuenta> patrimonioNeto) throws NoFoundException {
		
		
		 DBalance.crearBalance(d,activo,pasivo,patrimonioNeto);
	}
	public void addBalance(TransferBalance t){
		DBalance.addBalance(t);
	}
	public ArrayList<TransferBalance> mostrarListaBalances(){
		return Almacen_General.getInstance().getBalances();
	}
	public  ArrayList<TransferCuenta> mostrarListaNovedades(){
		return DCuenta.mostrarListaNovedades();
	}
	public TransferBalance mostrarBalance(Fecha d) {
		
		return DBalance.mostrarBalance(d);
	}
	public void addNovedades(TransferCuenta c){
		Almacen_General.getInstance().addNovedadesConta(c);
	}

	public Double tesoreria() {
		
		return this.DLibro.getTesoreria();
	}

	public ArrayList<TransferCuenta> consultarTotales(){
		return DLibro.consultaTotales();
	}
	public TransferCuenta buscarCuentaTotal(int codigo)throws NoFoundException{
		return DLibro.buscarCuentaTotal(codigo);
	}
	public void nuevoLibroDiario (TransferLibroDiario t){
		DLibro.nuevoLibroDiario(t);
	}
	public void guardarLibroDiario(BufferedWriter escritor){
		DLibro.guardar(escritor);
	}
	public void guardarBalances(BufferedWriter escritor){
	
		
		for (TransferBalance b: DBalance.ConsultaListaBalances()){
			
			DBalance.guardarBalance(escritor, b);
		}
	}
	
	public void guardarNovedades(BufferedWriter escritor){
	try {

			
			ArrayList<TransferCuenta> nove =  this.mostrarListaNovedades();
			for(TransferCuenta i: nove) {
				escritor.write("Novedades\r\n");
				escritor.write(i.guardar() + "\r\n");
			}
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "No se ha podido encontrar el fichero");
		}
		
	}

	public void eliminarCuentaNovedades(int codigo,double cantidad)throws NoFoundException {
		DCuenta.eliminarCuentaNovedades(codigo, cantidad);
		
	}







	@Override
	public void pagar(String conceptos, double cuantia) {
		int numero = (int)(Math.random()*500+1);
		Almacen_General.getInstance().addnovedadespago(DCuenta.crearCuenta(conceptos,numero,cuantia));
	}

	@Override
	public void pedidofinalizado() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pagar(String conceptos, int cuantia) {
		int numero = (int)(Math.random()*500+1);
		String cant=Integer.toString(cuantia)+.0;
		Almacen_General.getInstance().addnovedadespago(DCuenta.crearCuenta(conceptos,numero,Double.parseDouble(cant)));
	}

	@Override
	public void EliminacionEmpleado(TransferP personal, int cuantia) {
		this.pagar("Indemnizacion de "+personal.getNombre()+" "+personal.getApellidos(),1000);
	}

	@Override
	public void eliminacionVehiculo(transferVehiculo v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reparacion(transferVehiculo vehiculo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void nuevoProveedor(String nombre) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarProveedor(String nombre) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pedidofinalizado(int numVehiculos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void newEmpleado(TransferP personal) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void nuevoVehiculo(TransferP personal, transferVehiculo v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ingreso(String concepto, int cuantia) {
		
		int numero = (int)(Math.random()*500+1);
		
		Almacen_General.getInstance().addnovedadespago(DCuenta.crearCuenta("Ingreso "+concepto, numero, (double) cuantia));
	
		
		
		
		
	}


	@Override
	public void modificacionDNI(TransferP antes, TransferP despues) {
		// TODO Auto-generated method stub
		
	}

}
