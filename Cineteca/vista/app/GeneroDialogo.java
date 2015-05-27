package app;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GeneroDialogo extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtIdGen;
	private JTextField txtGenero;

	public GeneroDialogo(int id) {
		setBounds(new Rectangle(0, 0, 400, 165));
		getContentPane().setLayout(null);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(10, 11, 77, 14);
		getContentPane().add(lblId);
		
		JLabel lblGenero = new JLabel("G\u00E9nero");
		lblGenero.setBounds(10, 36, 77, 14);
		getContentPane().add(lblGenero);
		
		txtIdGen = new JTextField();
		txtIdGen.setEditable(false);
		txtIdGen.setText("id");
		txtIdGen.setBounds(97, 8, 114, 20);
		getContentPane().add(txtIdGen);
		txtIdGen.setColumns(10);
		
		txtGenero = new JTextField();
		txtGenero.setText("titulo");
		txtGenero.setBounds(97, 33, 275, 20);
		getContentPane().add(txtGenero);
		txtGenero.setColumns(10);
		
		JPanel pnBotones = new JPanel();
		pnBotones.setBounds(7, 61, 370, 60);
		getContentPane().add(pnBotones);
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
		
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				salvar(getForm());
				setVisible(false);
			}
		});
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminar(getForm().getId());
				setVisible(false);
			}
		});
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
	}

	private void eliminar(int id) {
		int pregunta = JOptionPane.showConfirmDialog(null, "¿Desea eliminar el Genero?", "Eliminar Genero", JOptionPane.OK_CANCEL_OPTION);
		if (pregunta==JOptionPane.OK_OPTION) {
			boolean eliminado = new GenerosBDD().Eliminar(id);
			mostrarMensaje(eliminado?"Genero Eliminado.":"No se ha podido eliminar.");
		}
	}
	
	private void salvar(Genero g) {
		if (g!=null) {
			GenerosBDD db = new GenerosBDD();
			int newId = db.Grabar(g);
			if (newId>=0) {
				g.setId(newId);
				setForm(g);
				mostrarMensaje("Genero Añadido correctamento.");
			} else {
				mostrarMensaje("Error al Añadir.");
			}
		}
	}
	
	private void setForm(Genero g) {
		if (g!=null) {
			txtIdGen.setText("" + g.getId());
			txtGenero.setText(g.getGenero());
		} else {
			txtIdGen.setText("");
			txtGenero.setText("");
		}
	}
	
	private Genero getForm() {
		Genero g = null;
		Integer id = Utilidades.validarEntero(txtIdGen.getText());
		String genero = Utilidades.validarString(txtGenero.getText());
		if (id!=null) {
			g = new Genero(id, genero);
		}
		return g;
	}
	
	private void mostrarMensaje(String string) {
		JOptionPane.showMessageDialog(null, string);
	}
	
	// METODOS PUBLICOS
	public Integer mostrar() {
		setVisible(true);
		Integer retorno = Utilidades.validarEntero(txtIdGen.getText());
		dispose();
		return retorno;
	}
}
