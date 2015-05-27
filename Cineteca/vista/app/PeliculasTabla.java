package app;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class PeliculasTabla extends JTable {
	public PeliculasTabla() {
		setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
			},
			new String[] {
				"T\u00EDtulo", "A\u00F1o", "Director", "ID"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				true, true, true, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		getColumnModel().getColumn(0).setPreferredWidth(200);
		getColumnModel().getColumn(2).setPreferredWidth(150);
	}
	
	public void filtrarTabla(String criterio) {
		ArrayList<Vector<Object>> tabla = new PeliculasBDD().recuperaTablaPeliculas(criterio);
		DefaultTableModel dtm = (DefaultTableModel) getModel();
		dtm.setRowCount(0);
		for (Vector<Object> fila : tabla) {
			dtm.addRow(fila);
		}
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -371381706871909469L;

}
