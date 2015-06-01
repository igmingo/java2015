package app;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class ConsultasTabla extends JTable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -371381706871909469L;
	private String filtro;
	private Usuario usuario;
	
	public ConsultasTabla(Usuario user, String filtro) {
		this.usuario = user;
		this.filtro = filtro;
		setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
			},
			new String[] {
				"ID", "T\u00EDtulo", "A\u00F1o", "Director", "G\u00E9nero", "Duraci\u00F3n"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, String.class, String.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		getColumnModel().getColumn(0).setPreferredWidth(50);
		getColumnModel().getColumn(1).setPreferredWidth(150);
		getColumnModel().getColumn(2).setPreferredWidth(50);
		getColumnModel().getColumn(3).setPreferredWidth(150);
		getColumnModel().getColumn(4).setPreferredWidth(100);
		getColumnModel().getColumn(5).setPreferredWidth(50);
		setAutoCreateRowSorter(true);
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row = getSelectedRow();
				if (row!=-1) {
					int intId = (int) getValueAt(row, 0);
						PeliculaDialogo dialog = new PeliculaDialogo (usuario, intId);
						Pelicula p = dialog.mostrar();
						if (p!=null) {
							actualizarTabla();
						}
				}
			}
		});
	}
	
	
	
	public void actualizarTabla() {
		ArrayList<Vector<Object>> tabla = new PeliculasBDD().recuperaTablaConsulta(this.filtro);
		DefaultTableModel dtm = (DefaultTableModel) getModel();
		dtm.setRowCount(0);
		for (Vector<Object> fila : tabla) {
			dtm.addRow(fila);
		}
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}
	
	public void setFiltro(Integer id, String titulo,
			String genero, Integer desdeEstreno, Integer hastaEstreno,
			Integer desdeDuracion, Integer hastaDuracion, String director) {
		this.filtro = new PeliculasBDD().creaFiltro(id, titulo, genero, desdeEstreno,
				hastaEstreno, desdeDuracion, hastaDuracion, director);
	}

}
