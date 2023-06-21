package contabilidad.GUI;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTable;

import contabilidad.ControladorContabilidad;
import contabilidad.TransferCuenta;
import General.NoFoundException;

public interface Estrategia_GUI {
	public JPanel crearPanelPrincipal(ControladorContabilidad control)  ;
	public JPanel crearTabla(ArrayList<TransferCuenta> datos, String nombre);
	public JPanel panel_central();
	public JPanel panel_arriba();
	public JPanel panel_abajo();
	public JPanel panel_derecha();
	public JPanel panel_izquierda();
	public String getInfo();
}
