package app;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class GenerosTabla extends JTable {
	public GenerosTabla() {
		setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
			},
			new String[] {
				"G\u00E9nero", "ID"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		getColumnModel().getColumn(0).setPreferredWidth(200);
		getColumnModel().getColumn(0).setPreferredWidth(200);
	}

	public void filtrarTabla(String criterio) {
		ArrayList<Vector<Object>> tabla = new GenerosBDD().recuperaTablaGeneros(criterio);
		// Cogel el modelo de datos de la tabla jtDptos
		DefaultTableModel dtm = (DefaultTableModel) getModel();
		//Poner el contador de filas a 0 (elimina lo que tenga la tabla).
		dtm.setRowCount(0);
		//Almacena en la tabla, todas las filas que recupero
		for (Vector<Object> fila : tabla) {
			dtm.addRow(fila);
		}
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -599114548334641747L;

}
