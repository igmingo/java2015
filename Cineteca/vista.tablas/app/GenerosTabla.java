package app;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class GenerosTabla extends JTable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -599114548334641747L;
	private String filtro;
	private Usuario usuario;

	// METODOS CONSTRUCTORES
	public GenerosTabla(Usuario user, String filtro) {
		this.usuario = user;
		this.filtro = filtro;
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
		setAutoCreateRowSorter(true);
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row = getSelectedRow();
				if (row!=-1) {
					int intId = (int) getValueAt(row, 1);
						GeneroDialogo dialog = new GeneroDialogo (usuario, intId);
						Genero g = dialog.mostrar();
						if (g!=null) {
							actualizarTabla(filtro);
						}
				}
			}
		});
		
	}

	public void actualizarTabla(String filtro) {
		this.setFiltro(filtro);
		ArrayList<Vector<Object>> tabla = new GenerosBDD().recuperaTablaGeneros(filtro);
		DefaultTableModel dtm = (DefaultTableModel) getModel();
		dtm.setRowCount(0);
		for (Vector<Object> fila : tabla) {
			dtm.addRow(fila);
		}
	}

	public void agregarUno(Genero newGenero) {
		if (newGenero!=null) {
			DefaultTableModel dtm = (DefaultTableModel) getModel();
			Vector<Object> fila = new Vector<>();
			fila.add(newGenero.getGenero());
			fila.add(newGenero.getId());
			dtm.addRow(fila);
		}
	}

	// METODOS SET Y GET
	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

}
