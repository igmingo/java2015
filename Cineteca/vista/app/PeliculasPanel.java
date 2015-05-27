package app;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PeliculasPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textFiltro;
	private PeliculasTabla tbPeliculas;

	public PeliculasPanel() {
		setBounds(new Rectangle(0, 0, 469, 397));
		setLayout(null);
		
		JLabel lblFiltro = new JLabel("Pel\u00EDculas");
		lblFiltro.setBounds(10, 11, 78, 16);
		add(lblFiltro);
		
		textFiltro = new JTextField();
		textFiltro.setBounds(100, 8, 260, 20);
		add(textFiltro);
		textFiltro.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 36, 449, 315);
		add(scrollPane);
		
		tbPeliculas = new PeliculasTabla(textFiltro.getText());
		scrollPane.setViewportView(tbPeliculas);
		
		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.setBounds(370, 7, 89, 23);
		add(btnFiltrar);
		
		JButton btnNuevo = new JButton("Nuevo");
		btnNuevo.setBounds(370, 363, 89, 23);
		add(btnNuevo);
		
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PeliculaDialogo dialogo = new PeliculaDialogo(0);
				Pelicula newPelicula = dialogo.mostrar();
				if (newPelicula!=null) {
					tbPeliculas.actualizarTabla(textFiltro.getText());
				}
			}
		});
		
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tbPeliculas.actualizarTabla(textFiltro.getText());
			}
		});
	}
}
