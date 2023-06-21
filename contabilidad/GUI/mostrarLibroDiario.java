package contabilidad.GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JLabel;

import contabilidad.ControladorContabilidad;
import contabilidad.TransferCuenta;
import contabilidad.TransferLibroDiario;


import javax.swing.JTable;
import javax.swing.table.TableModel;

import java.awt.CardLayout;

public class mostrarLibroDiario extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ControladorContabilidad control;
	private JPanel principal, centro;
	private ActualizacionesTable tablaD;
	private ActualizacionesTable tablaH;

	private Object [][] datos;;
	private String [] columnas = {"Cuenta","Codigo","Cantidad"};
	
	
	public mostrarLibroDiario(ControladorContabilidad controller) {
	this.control =controller;
		
		this.principal = this;
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel_derecha = new JPanel();
		add(panel_derecha, BorderLayout.EAST);
		
		JPanel panel_arriba = new JPanel();
		add(panel_arriba, BorderLayout.NORTH);
		
		JLabel lblLibroDiario = new JLabel("Libro Diario");
		panel_arriba.add(lblLibroDiario);
		
		JPanel panel_abajo = new JPanel();
		add(panel_abajo, BorderLayout.SOUTH);
		
		JPanel panel_izquierda = new JPanel();
		add(panel_izquierda, BorderLayout.WEST);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		
		String[] column = {"Cuenta","Codigo","Cantidad"};
		TablaAbstracta md = new TablaAbstracta(control.consultaDebe(),column);
		tablaD = new ActualizacionesTable(md,"Debe");
		
		
		ListaActualizacionesModel mh = new ListaActualizacionesModel(control.consultaHaber(),column);
		tablaH = new ActualizacionesTable(mh,"Haber");
		panel.add(tablaD, BorderLayout.CENTER);
		
		
		panel.add(tablaH, BorderLayout.CENTER);
		

		
		}
}

