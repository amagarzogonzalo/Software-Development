package contabilidad.GUI;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import contabilidad.ControladorContabilidad;
import contabilidad.TransferBalance;
import contabilidad.TransferCuenta;

import javax.swing.table.DefaultTableModel;

import marketing.Fecha;
import General.NoFoundException;
import javax.swing.JTextField;

public class BalanceGUI extends JPanel  {

	/**
	 * Create the panel.
	 */
	private JPanel  centro;
	private JTable table;
	private ActualizacionesTable tablaA;
	private ActualizacionesTable tablaP;
	private ActualizacionesTable tablaPN;
	private ControladorContabilidad control;
	
	public BalanceGUI(ControladorContabilidad control,TransferBalance balance) {
		
		this.control = control;
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel_central = new JPanel();
		add(panel_central, BorderLayout.CENTER);
		centro = panel_central;
		panel_central.setLayout(new BorderLayout(0, 0));
	
		//mostrarTotales mT = new mostrarTotales(control,balance.getActivo());
		//tabla = new ActualizacionesTotales(control,mT,"Totales");
		
		//panel_central.add(tabla, BorderLayout.CENTER);
		
		JPanel panel_arriba = new JPanel();
		add(panel_arriba, BorderLayout.NORTH);
		
		JLabel lblConsultaBalance = new JLabel("CONSULTA BALANCE");
		panel_arriba.add(lblConsultaBalance);
		
		JPanel panel_abajo = new JPanel();
		add(panel_abajo, BorderLayout.SOUTH);
		
		JLabel lblTotalActivo = new JLabel("Total Activo:");
		panel_abajo.add(lblTotalActivo);
		
		JLabel TActivo = new JLabel(Integer.toString(balance.getTotalActivo()));
		panel_abajo.add(TActivo);
		
		JLabel lblTotalPasivo = new JLabel("Total Pasivo:");
		panel_abajo.add(lblTotalPasivo);
		
		JLabel TPasivo = new JLabel(Integer.toString((-1)*balance.getTotalPasivo()));
		panel_abajo.add(TPasivo);
		
		JLabel lblTotalPn = new JLabel("Total PN:");
		panel_abajo.add(lblTotalPn);
		
		JLabel TPN = new JLabel(Integer.toString((-1)*balance.getTotalPatrimonioNeto()));
		panel_abajo.add(TPN);
		
		
		JPanel panel_izquierda = new JPanel();
		add(panel_izquierda, BorderLayout.WEST);
		
		JPanel panel_derecha = new JPanel();
		add(panel_derecha, BorderLayout.EAST);
		
		String[] column = {"Cuenta","Codigo","Cantidad"};
		TablaAbstracta mA = new TablaAbstracta(balance.getActivo(),column);
		
		tablaA = new ActualizacionesTable(mA,"Activo");
		panel_central.add(tablaA, BorderLayout.WEST);

		ListaActualizacionesModel mP = new ListaActualizacionesModel(balance.getPasivo(),column);
		tablaP = new ActualizacionesTable(mP,"Pasivo");
		panel_central.add(tablaP, BorderLayout.CENTER);
		
		ListaActualizacionesModel mN = new ListaActualizacionesModel(balance.getPatrimonioNeto(),column);
		tablaPN = new ActualizacionesTable(mN,"Patrimonio Neto");
		panel_central.add(tablaPN, BorderLayout.EAST);
		
		
		
	}
	

}
