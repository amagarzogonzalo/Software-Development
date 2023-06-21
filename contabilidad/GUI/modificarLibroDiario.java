package contabilidad.GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

import Fabrica.Factory_Transfer;
import General.NoFoundException;
import contabilidad.ControladorContabilidad;
import contabilidad.TransferCuenta;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class modificarLibroDiario  implements Estrategia_GUI {
	

	
	
	private ControladorContabilidad controlador;
	private JButton btnNewButton;
	private JTextField CuentaTF;
	private JTextField CodigoTF;
	private JTextField CantidadTF;
	private JTextField ContraTF;
	private JTextField Cod2TF;
	private JTextField Cantd2TF;
	private ActualizacionesTable tabla;
	private JTextField IdTFd;
	private JTextField IdTFh;
	

	@Override
	public JPanel crearPanelPrincipal(ControladorContabilidad control){
		controlador = control;
		JPanel principal = new JPanel();
		
		principal.setBorder(new EmptyBorder(5, 5, 5, 5));
		principal.setLayout(new BorderLayout(0, 0));
		
		principal.add(panel_arriba(),BorderLayout.NORTH);
	
		principal.add(panel_central(),BorderLayout.CENTER);
		principal.add(panel_abajo(),BorderLayout.SOUTH);
		principal.add(panel_derecha(), BorderLayout.EAST);
		
		
		
		return principal;
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
		return "Modificar Libro Diario";
	}


	@Override
	public JPanel panel_central() {
		// TODO Auto-generated method stub
		return crearTabla(this.controlador.consultaNovedades(),"Novedades");
	}


	@Override
	public JPanel panel_arriba() {
		JPanel panel_arriba = new JPanel();
		JLabel lblModificarLibroDiario = new JLabel("Modificar Libro Diario");
		panel_arriba.add(lblModificarLibroDiario);
		return panel_arriba;
	}


	@Override
	public JPanel panel_abajo() {
		JPanel panelAbajo = new JPanel();
		panelAbajo.setLayout(new BoxLayout(panelAbajo, BoxLayout.Y_AXIS));
		JPanel panel_2 = new JPanel();
		panelAbajo.add(panel_2);
		
		 JLabel lblCuenta = new JLabel("Cuenta");
		panel_2.add(lblCuenta);
		
		 CuentaTF = new JTextField();
		panel_2.add(CuentaTF);
		CuentaTF.setColumns(10);
		JLabel lblId = new JLabel("Id:");
		panel_2.add(lblId);
		IdTFd= new JTextField();
		panel_2.add(IdTFd);
		IdTFd.setColumns(10);
		
		JLabel lblCodigo = new JLabel("Codigo");
		panel_2.add(lblCodigo);
		
		 CodigoTF = new JTextField();
		panel_2.add(CodigoTF);
		CodigoTF.setColumns(10);
		
		JLabel lblCantidad = new JLabel("Cantidad");
		panel_2.add(lblCantidad);
		
		 CantidadTF = new JTextField();
		panel_2.add(CantidadTF);
		CantidadTF.setColumns(10);
		
		 JPanel panel_3 = new JPanel();
		panelAbajo.add(panel_3);
		
		JLabel lblContrapartida = new JLabel("Contrapartida");
		panel_3.add(lblContrapartida);
		
		 ContraTF = new JTextField();
		panel_3.add(ContraTF);
		ContraTF.setColumns(10);
		JLabel lblIh = new JLabel("Id:");
		panel_3.add(lblIh);
		IdTFh= new JTextField();
		panel_3.add(IdTFh);
		IdTFh.setColumns(10);
		
		JLabel lblCod = new JLabel("Cod2");
		panel_3.add(lblCod);
		
		 Cod2TF = new JTextField();
		panel_3.add(Cod2TF);
		Cod2TF.setColumns(10);
		
		JLabel lblCant = new JLabel("Cant2");
		panel_3.add(lblCant);
		
		Cantd2TF = new JTextField();
		panel_3.add(Cantd2TF);
		Cantd2TF.setColumns(10);
		return panelAbajo;
	}


	@Override
	public JPanel panel_derecha() {
		JPanel panel_derecha = new JPanel();
		
		panel_derecha.setLayout(new BoxLayout(panel_derecha, BoxLayout.X_AXIS));
		
		btnNewButton = new JButton("Añadir Cuenta");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
					int cod1 = Integer.parseInt(CodigoTF.getText());
					Double cant1 = Double.parseDouble(CantidadTF.getText());
					int cod2 = Integer.parseInt(Cod2TF.getText());
					Double cant2 = Double.parseDouble(Cantd2TF.getText());
					controlador.ModificarDiario(CuentaTF.getText(),cod1 , cant1, ContraTF.getText(), cod2,(-1)*cant2);
					controlador.eliminarCuentaNovedades(Integer.parseInt(IdTFd.getText()),cant1);
					controlador.eliminarCuentaNovedades(Integer.parseInt(IdTFh.getText()),cant2);
					Cantd2TF.getText();
					CuentaTF.setText("");
					CodigoTF.setText("");	
					CantidadTF.setText("");
					ContraTF.setText("");
					Cod2TF.setText("");
					Cantd2TF.setText("");
					 JOptionPane.showMessageDialog(null,"El libro diario se ha modificado correctamente");
				}catch(NoFoundException t){
					JOptionPane.showMessageDialog(null,t.getMessage());
				}
				catch(Exception ex){
					JOptionPane.showMessageDialog(null,"Formato de Cuentas incorrecto");
				}
			
				
				
			}
		});
		panel_derecha.add(btnNewButton);
		return panel_derecha;
	}


	@Override
	public JPanel panel_izquierda() {
		// TODO Auto-generated method stub
		return null;
	}

}
