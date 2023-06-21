package contabilidad;

import java.util.ArrayList;

public class PGC {
	
	private ArrayList<TransferCuenta> pgc;
	public PGC(){
		pgc= new ArrayList<TransferCuenta>();
		cargar();
	}
	public void cargar(){
		pgc.add(new TransferCuenta("Tesoreria",570,0.0));
		pgc.add(new TransferCuenta("Clientes",430,0.0));
		pgc.add(new TransferCuenta("CapitalSocial",100,0.0));
		pgc.add(new TransferCuenta("Reservas",11,0.0));
		pgc.add(new TransferCuenta("ResultadosEjercicio",11,0.0));
		pgc.add(new TransferCuenta("Provisiones",14,0.0));
		pgc.add(new TransferCuenta("DeudasL/P",171,0.0));
		pgc.add(new TransferCuenta("Mobiliario",216,0.0));
		pgc.add(new TransferCuenta("Elementos de Transporte",218,0.0));
		pgc.add(new TransferCuenta("Amortizacion acumulada inmovilizado",28,0.0));
		pgc.add(new TransferCuenta("Deterioro de valor",29,0.0));
		pgc.add(new TransferCuenta("Existencias",3,0.0));
		pgc.add(new TransferCuenta("Proveedores",40,0.0));
		pgc.add(new TransferCuenta("Clientes",40,0.0));
		pgc.add(new TransferCuenta("640",40,0.0));
		pgc.add(new TransferCuenta("Deudas c/p",521,0.0));
		pgc.add(new TransferCuenta("Gastos",6,0.0));
		pgc.add(new TransferCuenta("Publicidad",627,0.0));
		pgc.add(new TransferCuenta("Indemnizaciones",641,0.0));
		pgc.add(new TransferCuenta("Ingresos",7,0.0));
		
	}
	public ArrayList<TransferCuenta> getPGC(){
		return pgc;
	}
	
	
	
	

}
