package contabilidad.GUI;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import contabilidad.ControladorContabilidad;



public class ActualizacionesTable extends JPanel {


	private static final long serialVersionUID = -3561504278904781532L;

		ActualizacionesTable(AbstractTableModel lam, String nombre) {
			setLayout(new BorderLayout());
			setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2), nombre,
					TitledBorder.LEFT, TitledBorder.TOP));
			JTable table = new JTable(lam);
			JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
					JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

			this.add(scrollPane);
		}

	}


