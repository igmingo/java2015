package app;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Rectangle;

public class GeneroDialogo extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtId;
	private JTextField txtGenero;

	public GeneroDialogo(int id) {
		setBounds(new Rectangle(0, 0, 400, 165));
		setLayout(null);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(10, 11, 77, 14);
		add(lblId);
		
		JLabel lblGenero = new JLabel("G\u00E9nero");
		lblGenero.setBounds(10, 36, 77, 14);
		add(lblGenero);
		
		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setText("id");
		txtId.setBounds(97, 8, 114, 20);
		add(txtId);
		txtId.setColumns(10);
		
		txtGenero = new JTextField();
		txtGenero.setText("titulo");
		txtGenero.setBounds(97, 33, 275, 20);
		add(txtGenero);
		txtGenero.setColumns(10);
		
		JPanel pnBotones = new JPanel();
		pnBotones.setBounds(7, 61, 370, 60);
		add(pnBotones);
		pnBotones.setOpaque(false);
		pnBotones.setLayout(null);
		
		JButton btnRemove = new JButton("Eliminar");
		btnRemove.setBounds(138, 17, 98, 26);
		pnBotones.add(btnRemove);
		btnRemove.setVisible(id==0?false:true);
		
		JButton btnSave = new JButton("Grabar");
		btnSave.setBounds(20, 17, 98, 26);
		pnBotones.add(btnSave);
		
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.setBounds(256, 17, 98, 26);
		pnBotones.add(btnCancelar);
	}
}
