package General;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import marketing.Controller_Marketing;
import marketing.GUI.Marketing_GUI_Anuncios;
import marketing.GUI.Marketing_GUI_Empresas;
import marketing.GUI.Marketing_GUI_Ofertas;
import marketing.GUI.Marketing_GUI_principal;

public class GeneralGUI extends JFrame {


	private static final long serialVersionUID = 6940101124505133694L;
	private JPanel contentPane, centro;
	private JFrame frame;
	private Controlador_General _ctrl;



	/**
	 * Create the frame.
	 */
	public GeneralGUI(Controlador_General ctrl) {
		this._ctrl = ctrl;
		setTitle("Pick&Go");
		this.frame = this;
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel_centro = new JPanel();
		contentPane.add(panel_centro, BorderLayout.CENTER);
		centro = panel_centro;

		JPanel panel_arriba = new JPanel();
		panel_arriba.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		contentPane.add(panel_arriba, BorderLayout.NORTH);
		panel_arriba.setLayout(new BoxLayout(panel_arriba, BoxLayout.X_AXIS));

		JPanel panel_eleccion = new JPanel();
		panel_eleccion.setMaximumSize(new Dimension(100, 32));
		panel_eleccion.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel_arriba.add(panel_eleccion);

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Contabilidad", "Marketing", "Actividad", "Personal", "Proveedores", "Mantenimiento", "Vehiculos"}));
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch ((String) comboBox.getSelectedItem()) {
				case "Contabilidad": {

					_ctrl.contabilidad();
					
					break;
				}
				case "Marketing": {

					_ctrl.marketing();
					
					break;
				}
				case "Actividad": {

					_ctrl.actividad();
					
					break;
				}
				case "Personal": {

					_ctrl.personal();
					
					break;
				}
				case "Mantenimiento": {

					_ctrl.mantenimiento();
					
					break;
				}
				case "Vehiculos": {

					_ctrl.vehiculos();
					
					break;
				}
				case "Proveedores": {

					_ctrl.proveedor();
					
					break;
				}
				default:
					JOptionPane.showMessageDialog(null,
							"No se esperaba esta una seleccion distinta de \"Contabilidad\", \"Marketing\", \"Actividad\", \"Personal\", \"Proveedores\", \"Mantenimiento\", \"Vehiculos\" ");
				}
			}
		});
		panel_eleccion.add(comboBox);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		panel_arriba.add(horizontalStrut);

		JPanel panel_titulo = new JPanel();
		panel_arriba.add(panel_titulo);

		JLabel lblGestionVtc = new JLabel("PICK & GO");
		lblGestionVtc.setFont(new Font("Tahoma", Font.PLAIN, 30));
		panel_titulo.add(lblGestionVtc);

		JPanel panel_Acabar = new JPanel();
		panel_Acabar.setMaximumSize(new Dimension(100, 47));
		panel_Acabar.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panel_arriba.add(panel_Acabar);

		JButton cerrar = new JButton("");
		cerrar.setIcon(new ImageIcon(Marketing_GUI_principal.class.getResource("/marketing/Iconos/exit.png")));
		cerrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				
				_ctrl.guardarTransfers();
				frame.dispose();
				System.exit(0);
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
		button.setIcon(
				new ImageIcon(Marketing_GUI_principal.class.getResource("/marketing/Iconos/flecha_izquierda.png")));
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
		button_1.setIcon(
				new ImageIcon(Marketing_GUI_principal.class.getResource("/marketing/Iconos/flecha_derecha.png")));
		button_1.setAlignmentX(1.0f);
		panel_delante.add(button_1);

		JPanel panel_derecha = new JPanel();
		contentPane.add(panel_derecha, BorderLayout.EAST);
	}

	private void cambiarPanel(JPanel poner) {

		contentPane.remove(centro);

		this.centro = poner;

		contentPane.add(poner);
		contentPane.invalidate();
		contentPane.validate();

	}

}
