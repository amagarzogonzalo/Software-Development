package contabilidad.GUI;

import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import General.NoFoundException;
import contabilidad.ControladorContabilidad;
import contabilidad.TransferCuenta;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class PantallaPago implements Estrategia_GUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	private JPanel principal;
	private JTextField NombreTF;
	private JTextField CantidadTF;
	private JTextField IdTF;
	private JTextField CodigoTF;
	private ActualizacionesTable tabla;
	private ControladorContabilidad control;
	
	
	


	@Override
	public JPanel crearPanelPrincipal(ControladorContabilidad control) {
		JPanel principal= new JPanel();
		this.control= control;
		principal.setLayout(new BorderLayout(0, 0));
		principal.add(panel_central(), BorderLayout.CENTER);
		principal.add(panel_arriba(), BorderLayout.NORTH);
		principal.add(panel_izquierda(), BorderLayout.WEST);
		principal.add(panel_derecha(),BorderLayout.EAST);
		principal.add(panel_abajo(),BorderLayout.SOUTH);
		
		return principal;
	}
	public JPanel panel_central(){
		
		
		return crearTabla(this.control.consultaNovedades(),"Novedades");
	}
	public JPanel panel_arriba(){
		JPanel panel_arriba = new JPanel();
		JLabel lblRealizarPago = new JLabel("Realizar Pago");
		panel_arriba.add(lblRealizarPago);
		
		
		return panel_arriba;
	}
public JPanel panel_izquierda(){

		
		return new JPanel();
}
public JPanel panel_derecha(){
	JPanel panel_derecha = new JPanel();
	
	panel_derecha.setLayout(new BoxLayout(panel_derecha, BoxLayout.Y_AXIS));
	
	JPanel panel = new JPanel();
	panel_derecha.add(panel);
	
	JLabel lblNombre = new JLabel("Nombre:");
	panel.add(lblNombre);
	
	NombreTF = new JTextField();
	panel.add(NombreTF);
	NombreTF.setColumns(10);
	
	
	JPanel panell = new JPanel();
	JLabel lblId = new JLabel("Identificador:");
	panell.add(lblId);
	IdTF= new JTextField();
	panell.add(IdTF);
	IdTF.setColumns(10);
	panel_derecha.add(panell);
	
	JPanel panel_1 = new JPanel();
	panel_derecha.add(panel_1);
	
	JLabel lblNewLabel_1 = new JLabel("Cantidad:");
	panel_1.add(lblNewLabel_1);
	
	CantidadTF = new JTextField();
	panel_1.add(CantidadTF);
	CantidadTF.setColumns(10);
	
	JPanel panel_2 = new JPanel();
	panel_derecha.add(panel_2);
	
	JLabel lblCodigo = new JLabel("Codigo:");
	panel_2.add(lblCodigo);
	
	CodigoTF = new JTextField();
	panel_2.add(CodigoTF);
	CodigoTF.setColumns(10);
	return panel_derecha;
}
public JPanel panel_abajo(){
	JPanel panel_abajo = new JPanel();
	
	
	JLabel lblFondos = new JLabel("Fondos:");
	panel_abajo.add(lblFondos);
	
	JLabel TesoreriaLabel = new JLabel(Double.toString(control.mostrarTesoreria()));
	panel_abajo.add(TesoreriaLabel);
	
	JButton btnConfirmarPago = new JButton("Confirmar Pago");
	btnConfirmarPago.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			
			try {
				
			
			if ( CantidadTF.getText()== "" || NombreTF.getText()== "" ||CodigoTF.getText()== "" ){
				JOptionPane.showMessageDialog(null, "Cammpo Incompleto");
				
			}
				Double cantidad =Double.parseDouble(CantidadTF.getText());
				if(control.mostrarTesoreria()< cantidad){
					JOptionPane.showMessageDialog(null, "No se puede realizar pago.No hay suficiente dinero en tesoreria.");
				}else{
					control.eliminarCuentaNovedades(Integer.parseInt(IdTF.getText()), cantidad);
					control.ModificarDiario(NombreTF.getText(), Integer.parseInt(CodigoTF.getText()), cantidad, "Tesoreria", 570,(-1)*cantidad);
					System.out.println("El pago ha sido realizado correctamente");
					JOptionPane.showMessageDialog(null, "El pago ha sido realizado correctamente");
					NombreTF.setText("");
					CantidadTF.setText("");
					IdTF.setText("");
					CodigoTF.setText("");
				}
				
			}catch(NoFoundException t){
				JOptionPane.showMessageDialog(null,t.getMessage());
			}catch(Exception f){
				JOptionPane.showMessageDialog(null, "Formato incorrecto Nombre<string>Cantidad<int>Codigo<int>");
			}
		
		
		}
	});
	panel_abajo.add(btnConfirmarPago);
	
	
	
	return panel_abajo;
}
	

	@Override
	public JPanel crearTabla(ArrayList<TransferCuenta> datos, String nombre) {

		String[] column = {"Cuenta","Id","Cantidad"};
		TablaAbstracta mT = new TablaAbstracta(datos,column);
		tabla = new ActualizacionesTable(mT,nombre);
		return tabla;
	}


	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		return "Realizar Pago";
	}

}
