package app;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

// TABLA facturasdetalle BASE DE DATOS
//id int(10) UNSIGNED No auto_increment
//facturaId int(10) UNSIGNED No facturas -> id
//prodId int(10) UNSIGNED No productos -> id
//prodNombre varchar(30) No
//prodPrecio double No
//prodIva double No
//cantidad int(11) No

public class FacturasDetalleTabla extends JTable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -371381706871909469L;
	private String filtro;
	
	public FacturasDetalleTabla(String f) {
		this.filtro = f;
		setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
			},
			new String[] {		
				"Detalle", "Precio", "IVA", "Cantidad"
			}
		) {
			Class[] columnTypes = new Class[] {
				FacturaDetalle.class, Double.class, Double.class, Integer.class
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
		setAutoCreateRowSorter(true);
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row = getSelectedRow();
				if (row!=-1) {
					FacturaDetalle fd = (FacturaDetalle) getValueAt(row, 0);
						FacturaDetalleDialogo dialog = new FacturaDetalleDialogo (fd);
						FacturaDetalle c = dialog.mostrar();
						if (c!=null) {
							actualizarTabla();
						}
				}
			}
		});
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public void actualizarTablaPorFacturaId(Integer id) {
		if (id!=null && id!= 0) {
			filtro = "WHERE facturasdetalle.facturaId = " + id;
			actualizarTabla();
		} else {
			filtro = "";
		}
	}
	
	public void actualizarTabla() {
		ArrayList<Vector<Object>> tabla = new FacturasDetallesBDD().recuperaTablaFacturaDetalles(filtro);
		DefaultTableModel dtm = (DefaultTableModel) getModel();
		dtm.setRowCount(0);
		for (Vector<Object> fila : tabla) {
			dtm.addRow(fila);
		}
	}

}
