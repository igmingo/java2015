package app;
import javax.swing.JPanel;

import java.awt.Rectangle;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JButton;


public class GenerosPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textFiltro;
	private GenerosTabla tbGeneros;

	public GenerosPanel() {
		setBounds(new Rectangle(0, 0, 469, 397));
		setLayout(null);
		
		JLabel lblFiltro = new JLabel("Generos");
		lblFiltro.setBounds(10, 11, 41, 16);
		add(lblFiltro);
		
		textFiltro = new JTextField();
		textFiltro.setBounds(63, 8, 297, 20);
		add(textFiltro);
		textFiltro.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 36, 449, 350);
		add(scrollPane);
		
		tbGeneros = new GenerosTabla();
		scrollPane.setViewportView(tbGeneros);
		
		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.setBounds(370, 7, 89, 23);
		add(btnFiltrar);
	}
}
