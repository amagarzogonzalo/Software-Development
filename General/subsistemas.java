package General;

public enum subsistemas {
	CONTABILIDAD(1),
	MARKETING(2),
	ACTIVIDAD(3),
	PERSONAL(4),
	PROVEEDORES(5),
	MANTENIMIENTO(6),
	VEHICULOS(7),
	SALIR(8);
	
	private int numero;


	subsistemas(int i) {
		this.numero = i;
	}
	
	public int opciones(){
		return numero;
	}
}
