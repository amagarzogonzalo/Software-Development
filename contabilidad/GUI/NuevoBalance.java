package contabilidad.GUI;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JButton;

import contabilidad.ControladorContabilidad;
import contabilidad.TransferCuenta;
import marketing.Fecha;
import General.Almacen_General;
import General.NoFoundException;

import javax.swing.JTable;
import javax.swing.JTextField;

public class NuevoBalance  implements Estrategia_GUI{

	
	private ArrayList<TransferCuenta> activo; 
	private ArrayList<TransferCuenta> pasivo; 
	private ArrayList<TransferCuenta> patrimonioNeto; 
	private ControladorContabilidad control;
	private ActualizacionesTable tabla;
	private ActualizacionesTable tabla2;
	
	private JTextField FechaTF;
	private JTextField CodigoTF;
	
	@Override
	public JPanel crearPanelPrincipal(ControladorContabilidad control) {
		this.control = control;
		JPanel principal = new JPanel();
		activo =  new ArrayList<TransferCuenta>();
		pasivo = new ArrayList<TransferCuenta>();
		patrimonioNeto = new ArrayList<TransferCuenta>();
		principal.setLayout(new BorderLayout(0, 0));

		principal.add(panel_arriba(), BorderLayout.NORTH);	
		principal.add(panel_derecha(), BorderLayout.EAST);	
		
		principal.add(panel_abajo(), BorderLayout.SOUTH);
		
		principal.add(panel_central(), BorderLayout.CENTER);
		
		return principal;
	}
	@Override
	public JPanel crearTabla(ArrayList<TransferCuenta> datos, String nombre) {
		String[] column = {"Cuenta","Codigo","Cantidad"};
		TablaAbstracta mT = new TablaAbstracta(datos,column);
		tabla = new ActualizacionesTable(mT,nombre);
		return tabla;
	}
	@Override
	public JPanel panel_central() {
		
		return crearTabla(this.control.consultarTotales(),"Totales");
		
	}
	@Override
	public JPanel panel_arriba() {
		JPanel panel_arriba = new JPanel();
		
		JLabel lblNuevoBalance = new JLabel("Nuevo Balance");
		panel_arriba.add(lblNuevoBalance);
		
		
		return panel_arriba;
	}
	@Override
	public JPanel panel_abajo() {
		JPanel panel_2 = new JPanel();
		
		
		JLabel lblNewLabel = new JLabel("Codigo de la cuenta");
		panel_2.add(lblNewLabel);
		
		CodigoTF = new JTextField();
		panel_2.add(CodigoTF);
		CodigoTF.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		panel_2.add(comboBox);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Activo", "Pasivo", "Patrimonio Neto"}));
		
		JButton btnAadircuenta = new JButton("AñadirCuenta");
		btnAadircuenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
				if (comboBox.getSelectedItem()== "Activo"){
					TransferCuenta c=control.buscarCuentaTotal(Integer.parseInt(CodigoTF.getText()));

					
					activo.add(c);
				}else if(comboBox.getSelectedItem()== "Pasivo"){
					TransferCuenta c= control.buscarCuentaTotal(Integer.parseInt(CodigoTF.getText()));
					pasivo.add(c);
				}else{
					TransferCuenta c= control.buscarCuentaTotal(Integer.parseInt(CodigoTF.getText()));
					patrimonioNeto.add(c);
				}
				}catch(NoFoundException n){
					JOptionPane.showMessageDialog(null,n.getMessage());
					
				}
				
				
				
				
			}
		});
		panel_2.add(btnAadircuenta);
		
		
		return panel_2;
	}
	@Override
	public JPanel panel_derecha() {
		ControladorContabilidad controlador = this.control;
		JPanel panel_derecha = new JPanel();
		panel_derecha.setLayout(new BoxLayout(panel_derecha, BoxLayout.Y_AXIS));
		//add(panel_derecha(), BorderLayout.EAST);
		
		JPanel panel_4 = new JPanel();
		panel_derecha.add(panel_4);
		
		JLabel lblFecha = new JLabel("Fecha:");
		panel_4.add(lblFecha);
		
		JPanel panel_8 = new JPanel();
		
		panel_derecha.add(panel_8);
		
		FechaTF = new JTextField();
		panel_8.add(FechaTF);
		FechaTF.setColumns(10);
		
		JPanel panel_5 = new JPanel();
		panel_derecha.add(panel_5);
		
		JButton btnCrearBalance = new JButton("Crear Balance");
		btnCrearBalance.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				try{
					String x;
					int day=0,year=0,month=0;
					 String[] date;
					x= FechaTF.getText();
					 date =x.split("/");
					 day= Integer.parseInt(date[0]);
					 month=Integer.parseInt(date[1]);
					 year=Integer.parseInt(date[2]);	 
					 Fecha d = new Fecha(year,month,day); 
					 controlador.nuevobalance(d,activo,pasivo,patrimonioNeto);
					 JOptionPane.showMessageDialog(null,"Balance creado correctamente");
				}catch(NumberFormatException n){
					JOptionPane.showMessageDialog(null,"Formato de fecha incorrecto d/m/y");
				}
			
				
			}
		});
		panel_5.add(btnCrearBalance);
		
		
		return panel_derecha;
	}
	@Override
	public JPanel panel_izquierda() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		return "Nuevo Balance";
	}

}
