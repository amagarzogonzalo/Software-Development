package contabilidad.GUI;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import contabilidad.ControladorContabilidad;
import contabilidad.TransferCuenta;


public class ListaActualizacionesModel extends AbstractTableModel {
	private static final long serialVersionUID = -8038529690109122497L;

	private interface FunctionalInterface {
		public String operate(TransferCuenta c);
	}

	private  ArrayList<TransferCuenta> novedades;
	private List<FunctionalInterface> operations;
	private String[] columnNames;

	public ListaActualizacionesModel(ArrayList<TransferCuenta> tabla,String[]columnas) {
		
		this.novedades= tabla;
		this.operations = genOperations();
		//this.columnNames = genColumnNames();
		this.columnNames =columnas;
		this.operations = genOperations();
	
	}

	private List<FunctionalInterface> genOperations() {
		List<FunctionalInterface> operations = new ArrayList<FunctionalInterface>();
		operations.add((TransferCuenta c) -> c.getNombre());
		operations.add((TransferCuenta c) -> Integer.toString(c.getCodigo()));
		operations.add((TransferCuenta c) -> Double.toString((-1)*c.getCantidad()));

		return operations;
	}

	/*private String[] genColumnNames() {
		String[] stringArr = { "Nombre", "Codigo", "Cantidad"};
		return stringArr;
	}*/

	@Override
	public int getRowCount() {
		return novedades.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return operations.get(columnIndex).operate(novedades.get(rowIndex));
	}
}
