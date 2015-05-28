package app;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PeliculaDialogo extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtId;
	private JTextField txtTitulo;
	private JTextField txtDuracion;
	private GenerosCombo cbGeneros;
	//private JTextField txtIdGen;
	private JTextField txtDirector;
	private JTextField txtEstreno;
	private JTextArea txtSinopsis;


	public PeliculaDialogo(int id) {
		setResizable(false);
		setModal(true);
		setBounds(new Rectangle(0, 0, 600, 360));
		setBounds(new Rectangle(0, 0, 600, 360));
		getContentPane().setLayout(null);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(10, 11, 77, 14);
		getContentPane().add(lblId);
		
		JLabel lblTtulo = new JLabel("T\u00EDtulo");
		lblTtulo.setBounds(10, 36, 77, 14);
		getContentPane().add(lblTtulo);
		
		JLabel lblDuracin = new JLabel("Duraci\u00F3n");
		lblDuracin.setBounds(10, 61, 77, 14);
		getContentPane().add(lblDuracin);
		
		JLabel lblGnero = new JLabel("G\u00E9nero");
		lblGnero.setBounds(10, 86, 77, 14);
		getContentPane().add(lblGnero);
		
		JLabel lblDirector = new JLabel("Director");
		lblDirector.setBounds(10, 111, 77, 14);
		getContentPane().add(lblDirector);
		
		JLabel lblEstreno = new JLabel("Estreno");
		lblEstreno.setBounds(10, 136, 77, 14);
		getContentPane().add(lblEstreno);
		
		JLabel lblSinopsis = new JLabel("Sinopsis");
		lblSinopsis.setBounds(10, 161, 77, 14);
		getContentPane().add(lblSinopsis);
		
		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setText("id");
		txtId.setBounds(97, 8, 114, 20);
		getContentPane().add(txtId);
		txtId.setColumns(10);
		
		txtTitulo = new JTextField();
		txtTitulo.setText("titulo");
		txtTitulo.setBounds(97, 33, 475, 20);
		getContentPane().add(txtTitulo);
		txtTitulo.setColumns(10);
		
		txtDuracion = new JTextField();
		txtDuracion.setText("duracion");
		txtDuracion.setBounds(97, 58, 114, 20);
		getContentPane().add(txtDuracion);
		txtDuracion.setColumns(10);
		
//		txtIdGen = new JTextField();
//		txtIdGen.setText("genero");
//		txtIdGen.setBounds(97, 83, 114, 20);
//		getContentPane().add(txtIdGen);
//		txtIdGen.setColumns(10);
		
		cbGeneros = new GenerosCombo();
		cbGeneros.setBounds(97, 83, 219, 20);
		getContentPane().add(cbGeneros);
		
		txtDirector = new JTextField();
		txtDirector.setText("director");
		txtDirector.setBounds(97, 108, 475, 20);
		getContentPane().add(txtDirector);
		txtDirector.setColumns(10);
		
		txtEstreno = new JTextField();
		txtEstreno.setText("estreno");
		txtEstreno.setBounds(97, 133, 114, 20);
		getContentPane().add(txtEstreno);
		txtEstreno.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(93, 161, 479, 90);
		getContentPane().add(scrollPane);
		
		txtSinopsis = new JTextArea();
		txtSinopsis.setLocation(97, 0);
		txtSinopsis.setText("sinopsis");
		scrollPane.setViewportView(txtSinopsis);
		
		JPanel pnBotones = new JPanel();
		pnBotones.setBounds(107, 263, 370, 60);
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
		setForm(new PeliculasBDD().RecuperaPorId(id));
	}
	
	private void eliminar(int id) {
		int pregunta = JOptionPane.showConfirmDialog(null, "¿Desea eliminar la Película?\n", "Eliminar Pelicula", JOptionPane.OK_CANCEL_OPTION);
		if (pregunta==JOptionPane.OK_OPTION) {
			boolean eliminado = new PeliculasBDD().Eliminar(id);
			mostrarMensaje(eliminado?"Pelicula Eliminada.":"No se ha podido eliminar.");
		}
	}
	
	private void salvar(Pelicula p) {
		if (p!=null) {
			PeliculasBDD db = new PeliculasBDD();
			int newId = db.Grabar(p);
			if (newId>=0) {
				p.setId(newId);
				setForm(p);
				mostrarMensaje("Pelicula añadida correctamento.");
			} else {
				mostrarMensaje("Error al Añadir.");
			}
		}
	}
	
	private void setForm(Pelicula p) {
		if (p!=null) {
			txtId.setText("" + p.getId());
			txtTitulo.setText(p.getTitulo());
			if (p.getDuracion()!=null) {
				txtDuracion.setText("" + p.getDuracion() );
			} else {
				txtDuracion.setText("");
			}
			//txtIdGen.setText("" + p.getIdGenero());
			cbGeneros.recargarCombo(p.getIdGenero());
			txtDirector.setText(p.getDirector());
			txtEstreno.setText(p.getEstreno());
			txtSinopsis.setText(p.getSinopsis());
		} else {
			txtId.setText("");
			txtTitulo.setText("");
			txtDuracion.setText("");
			//txtIdGen.setText("");
			cbGeneros.recargarCombo();
			txtDirector.setText("");
			txtEstreno.setText("");
			txtSinopsis.setText("");
		}
	}
	
	private Pelicula getForm() {
		Pelicula p = null;
		Integer id = Utilidades.validarEntero(txtId.getText());
		if (id!=null) {
			String titulo = Utilidades.validarStringNoNull(txtTitulo.getText());
			Integer duracion = Utilidades.validarEntero(txtDuracion.getText());
			//el combo nos devuelve el id del Genero designado.
			int idGenero = cbGeneros.getSelectedIdGen();
			String director = Utilidades.validarString(txtDirector.getText());
			String estreno = Utilidades.validarString(txtEstreno.getText());
			String sinopsis = Utilidades.validarString(txtSinopsis.getText());
			p = new Pelicula(id, titulo, duracion, idGenero, director, estreno, sinopsis);
		}
		return p;
	}

	private void mostrarMensaje(String string) {
		JOptionPane.showMessageDialog(null, string);
	}
	
	// METODOS PUBLICOS
	public Pelicula mostrar() {
		setVisible(true);
		Pelicula retorno = getForm();
		dispose();
		return retorno;
	}
}
