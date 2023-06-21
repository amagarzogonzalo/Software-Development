package contabilidad.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import marketing.Fecha;
import contabilidad.ControladorContabilidad;
import contabilidad.TransferBalance;
import contabilidad.TransferCuenta;

public class ConsultarGUI  implements Estrategia_GUI{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane, centro;
	private ControladorContabilidad control;
	private JComboBox comboBox;
	private JButton btnLibroDiario;
	private JButton btnBalance;



@Override
public JPanel crearPanelPrincipal(ControladorContabilidad control) {
	JPanel principal = new JPanel();
	this.control= control;
	principal.setLayout(new BorderLayout(0, 0));
	JPanel panel_arriba = new JPanel();
	
	JLabel lblConsultar = new JLabel("Consultar");
	panel_arriba.add(lblConsultar);
	
	 btnLibroDiario = new JButton("Libro Diario");
	panel_arriba.add(btnLibroDiario);
	JButton btnBalance = new JButton("Balance");
	panel_arriba.add(btnBalance);
	
	
	
	JLabel lblNewLabel = new JLabel("Fecha Balance");
	panel_arriba.add(lblNewLabel);
	
	 comboBox = new JComboBox();
	panel_arriba.add(comboBox);
	ArrayList<TransferBalance> x = control.mostrarListaBalances();
	String fechas[];
	for (TransferBalance c : x){
		
		comboBox.addItem(c.FechaString());	
		
	}
	
	
	principal.add(panel_arriba, BorderLayout.NORTH);
	JPanel panel_central = panel_central();
	principal.add(panel_central(), BorderLayout.CENTER);
	
	
	
	btnBalance.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {
			String x =(String) comboBox.getSelectedItem();
			String[] date;
			 int day=0,year=0,month=0;
			 date =x.split("/");
			 day= Integer.parseInt(date[0]);
			 month=Integer.parseInt(date[1]);
			 year=Integer.parseInt(date[2]);	 
			 Fecha d = new Fecha(year,month,day);
			
			
			
			 principal.remove(panel_central);	
			 TransferBalance balance= control.consultaBalance(d);
			 principal.add(new BalanceGUI(control,control.consultaBalance(d)),BorderLayout.CENTER);
			principal.invalidate();
			principal.validate();
			
			}catch(Exception t ){
				JOptionPane.showMessageDialog(null, "No se encontro el balance seleccionado");
			}
			
		}
	});
	btnLibroDiario.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			principal.remove(panel_central);
			principal.add(new mostrarLibroDiario(control),BorderLayout.CENTER);
			principal.invalidate();
			principal.validate();
		}
	});
	
	return principal;
}
@Override
public JPanel crearTabla(ArrayList<TransferCuenta> datos, String nombre) {
	return null;
}
@Override
public JPanel panel_central() {
	
	JPanel panel_central = new JPanel();
		
	
	
	return panel_central;
}
@Override
public JPanel panel_arriba() {

	
	return null;
}
@Override
public JPanel panel_abajo() {
	// TODO Auto-generated method stub
	return new JPanel();
}
@Override
public JPanel panel_derecha() {
	// TODO Auto-generated method stub
	return new JPanel();
}
@Override
public JPanel panel_izquierda() {
	// TODO Auto-generated method stub
	return new JPanel();
}
@Override
public String getInfo() {
	// TODO Auto-generated method stub
	return "Consultar";
}

}
