package contabilidad.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;







import marketing.FabricaTransfer_Anuncios;
import marketing.FabricaTransfer_Empresas;
import marketing.FabricaTransfer_Ofertas;
import Fabrica.Factory_Transfer;
import marketing.GUI.Marketing_GUI_principal;
import contabilidad.ControladorContabilidad;

public class GUI_Contabilidad extends JFrame {

	private List<Estrategia_GUI> factorias;
	
	
	private JPanel contentPane, centro;
	private JFrame frame;
	private ControladorContabilidad control;
	
	public GUI_Contabilidad(ControladorContabilidad ctrl) {
		super("Physics Simulator");
		control = ctrl;
		this.factorias = new ArrayList<Estrategia_GUI>();
		this.inicializarFactorias();
		initGUI();
		prepareFrame();
	}
	public void initGUI() {
		
		
		setTitle("Contabilidad");
		this.frame = this;
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setMinimumSize(new Dimension(600, 500));
		frame.setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel_centro = new JPanel();
		contentPane.add(panel_centro, BorderLayout.CENTER);
		centro = panel_centro;
		
		JPanel panel_arriba = new JPanel();
		panel_arriba.setBorder(new LineBorder(new Color(0,0,0),1,true));
		contentPane.add(panel_arriba, BorderLayout.NORTH);
		panel_arriba.setLayout(new BoxLayout(panel_arriba, BoxLayout.X_AXIS));
		
		JPanel panel_eleccion = new JPanel();
		panel_eleccion.setMaximumSize(new Dimension(100,32));
		panel_eleccion.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel_arriba.add(panel_eleccion);
		
		final JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Realizar Pago", "Modificar Libro Diario", "Consultar","Nuevo Balance"}));
		comboBox.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	
		    	for(Estrategia_GUI i: factorias) {
					
					if(i.getInfo().equals(comboBox.getSelectedItem()) ) {
						cambiarPanel(i.crearPanelPrincipal(control));
						
					}
					}
			
		    }
		});
		panel_eleccion.add(comboBox);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		panel_arriba.add(horizontalStrut);
		
		JPanel panel_titulo = new JPanel();
		panel_arriba.add(panel_titulo);
		
		
		JLabel lblGestinDeContabilidad = new JLabel("GESTION DE CONTABILIDAD");
		lblGestinDeContabilidad.setFont(new Font("Tahoma", Font.PLAIN, 30));
		panel_titulo.add(lblGestinDeContabilidad);
		
		JPanel panel_Acabar = new JPanel();
		panel_Acabar.setMaximumSize(new Dimension(100, 47));
		panel_Acabar.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panel_arriba.add(panel_Acabar);
		
		JButton cerrar = new JButton("");
		cerrar.setIcon(new ImageIcon(Marketing_GUI_principal.class.getResource("/marketing/Iconos/exit.png")));
		cerrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//_ctrlMarketing.guardarTransfers();
				frame.dispose();
				
			}
		});
		panel_Acabar.add(cerrar);
		JPanel panel_izquierda = new JPanel();
		contentPane.add(panel_izquierda, BorderLayout.WEST);
		
		JPanel panel_debajo = new JPanel();
		contentPane.add(panel_debajo, BorderLayout.SOUTH);
		panel_debajo.setLayout(new BoxLayout(panel_debajo, BoxLayout.X_AXIS));
		
		JPanel panel_atras = new JPanel();
		panel_debajo.add(panel_atras);
		panel_atras.setLayout(new BoxLayout(panel_atras, BoxLayout.X_AXIS));
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button.setIcon(new ImageIcon(Marketing_GUI_principal.class.getResource("/marketing/Iconos/flecha_izquierda.png")));
		panel_atras.add(button);
		
		JPanel panel_foto = new JPanel();
		panel_debajo.add(panel_foto);
		
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(Marketing_GUI_principal.class.getResource("/marketing/Iconos/hombres.png")));
		label_1.setAlignmentX(0.5f);
		panel_foto.add(label_1);
		
		JPanel panel_delante = new JPanel();
		panel_debajo.add(panel_delante);
		panel_delante.setLayout(new BoxLayout(panel_delante, BoxLayout.X_AXIS));
		
		JButton button_1 = new JButton("");
		button_1.setIcon(new ImageIcon(Marketing_GUI_principal.class.getResource("/marketing/Iconos/flecha_derecha.png")));
		button_1.setAlignmentX(1.0f);
		panel_delante.add(button_1);
		
		JPanel panel_derecha = new JPanel();
		contentPane.add(panel_derecha, BorderLayout.EAST);
		
		
	}
private void cambiarPanel(JPanel poner) {
		
		contentPane.remove(centro);
		
		this.centro= poner;
		
		contentPane.add(poner);
		contentPane.invalidate();
		contentPane.validate();
		
		
}
private void prepareFrame() {
	setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	try {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	} catch (Exception ignored) {
	}
	pack();
	//setMinimumSize(new Dimension(getSize().width, 220));
	//setLocationRelativeTo(null);
	setVisible(true);
}
private void inicializarFactorias() {
	
	factorias.add(new modificarLibroDiario());
	factorias.add(new PantallaPago());
	factorias.add(new NuevoBalance());
	factorias.add(new ConsultarGUI());
}
}
