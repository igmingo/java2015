package app;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class PeliculasTabla extends JTable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -371381706871909469L;
	private String filtro;
	private Usuario usuario;
	
	public PeliculasTabla(Usuario user, String filtro) {
		System.out.println("Tabla de " + user.getName());
		this.usuario = user;
		System.out.println("Tabla de " + usuario.getName());
		this.filtro = filtro;
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
		setAutoCreateRowSorter(true);
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row = getSelectedRow();
				if (row!=-1) {
					int intId = (int) getValueAt(row, 3);
						PeliculaDialogo dialog = new PeliculaDialogo (usuario, intId);
						Pelicula p = dialog.mostrar();
						if (p!=null) {
							actualizarTabla(filtro);
						}
				}
			}
		});
	}
	
	public void actualizarTabla(String filtro) {
		this.setFiltro(filtro);
		ArrayList<Vector<Object>> tabla = new PeliculasBDD().recuperaTablaPeliculas(filtro);
		DefaultTableModel dtm = (DefaultTableModel) getModel();
		dtm.setRowCount(0);
		for (Vector<Object> fila : tabla) {
			dtm.addRow(fila);
		}
	}

	public void agregarUno(Pelicula newPelicula) {
		if (newPelicula!=null) {
			DefaultTableModel dtm = (DefaultTableModel) getModel();
			Vector<Object> fila = new Vector<>();
			fila.add(newPelicula.getTitulo());
			fila.add(newPelicula.getEstreno());
			fila.add(newPelicula.getDirector());
			fila.add(newPelicula.getId());
			dtm.addRow(fila);
		}
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

}
