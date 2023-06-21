package contabilidad;

import General.Transfer;

public class TransferCuenta extends Transfer{
	

	String nombre;
	int codigo;
	Double cantidad;
	
	public TransferCuenta(){
		this.nombre="s";
		this.codigo=0;
		this.cantidad=0.0;
	}
	public TransferCuenta(String nom, int cod,Double cant){		
		this.nombre=nom;
		this.codigo=cod;
		this.cantidad=cant;		
	}
	public String getNombre() {
		return nombre;
	}
	public int getCodigo() {
		return codigo;
	}
	public Double getCantidad() {
		return cantidad;
	}
	public void setCantidad(Double cant){
		this.cantidad=cant;
	}
	@Override
	public String toString() {
		return "Cuenta [nombre=" + nombre + ", codigo=" + codigo+ ", cantidad=" + cantidad + "]";
	}
	@Override
	public String guardar() {
		// TODO Auto-generated method stub
		return  nombre + ":" + codigo+ ":" + cantidad + "\r\n";
	}

}
