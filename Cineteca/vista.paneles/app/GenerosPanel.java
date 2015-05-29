package app;
import javax.swing.JPanel;

import java.awt.Rectangle;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class GenerosPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textFiltro;
	private GenerosTabla tbGeneros;

	public GenerosPanel(Usuario user) {
		setBounds(new Rectangle(0, 0, 469, 397));
		setLayout(null);
		
		JLabel lblFiltro = new JLabel("Generos");
		lblFiltro.setBounds(10, 11, 80, 16);
		add(lblFiltro);
		
		textFiltro = new JTextField();
		textFiltro.setBounds(100, 8, 260, 20);
		add(textFiltro);
		textFiltro.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 36, 449, 316);
		add(scrollPane);
		
		tbGeneros = new GenerosTabla(textFiltro.getText());
		scrollPane.setViewportView(tbGeneros);
		
		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.setBounds(370, 7, 89, 23);
		add(btnFiltrar);
		
		JButton btnNuevo = new JButton("Nuevo");
		btnNuevo.setBounds(370, 363, 89, 23);
		add(btnNuevo);
		
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GeneroDialogo dialogo = new GeneroDialogo(0);
				Genero newGenero = dialogo.mostrar();
				if (newGenero!=null) {
					tbGeneros.actualizarTabla(textFiltro.getText());
				}
			}
		});
		
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tbGeneros.actualizarTabla(textFiltro.getText());
			}
		});
	}
}
