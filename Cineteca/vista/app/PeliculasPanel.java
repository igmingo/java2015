package app;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Rectangle;

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
		lblFiltro.setBounds(10, 11, 52, 16);
		add(lblFiltro);
		
		textFiltro = new JTextField();
		textFiltro.setBounds(72, 8, 288, 20);
		add(textFiltro);
		textFiltro.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 36, 449, 350);
		add(scrollPane);
		
		tbPeliculas = new PeliculasTabla();
		scrollPane.setViewportView(tbPeliculas);
		
		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.setBounds(370, 7, 89, 23);
		add(btnFiltrar);
	}
}
