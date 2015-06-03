package app;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class PeliculaDialogo extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtId;
	private JTextField txtTitulo;
	private JTextField txtDuracion;
	private GenerosCombo cbGeneros;
	private JTextField txtDirector;
	private JTextField txtEstreno;
	private JTextArea taSinopsis;
	private Usuario usuario;
	private Caratula lblCaratula;

	public PeliculaDialogo(Usuario user, int id) {
		this.usuario = user;
		setTitle(usuario.getName());
		setResizable(false);
		setModal(true);
		setBounds(new Rectangle(0, 0, 875, 450));
		getContentPane().setLayout(null);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(10, 11, 77, 14);
		getContentPane().add(lblId);
		
		JLabel lblTtulo = new JLabel("T\u00EDtulo");
		lblTtulo.setBounds(10, 36, 77, 14);
		getContentPane().add(lblTtulo);
		
		JLabel lblDuracin = new JLabel("Duraci\u00F3n");
		lblDuracin.setBounds(371, 92, 77, 14);
		getContentPane().add(lblDuracin);
		
		JLabel lblGnero = new JLabel("G\u00E9nero");
		lblGnero.setBounds(10, 92, 77, 14);
		getContentPane().add(lblGnero);
		
		JLabel lblDirector = new JLabel("Director");
		lblDirector.setBounds(204, 64, 83, 14);
		getContentPane().add(lblDirector);
		
		JLabel lblEstreno = new JLabel("Estreno");
		lblEstreno.setBounds(10, 64, 77, 14);
		getContentPane().add(lblEstreno);
		
		JLabel lblSinopsis = new JLabel("Sinopsis");
		lblSinopsis.setBounds(10, 120, 77, 14);
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
		txtDuracion.setBounds(458, 89, 114, 20);
		getContentPane().add(txtDuracion);
		txtDuracion.setColumns(10);
		
		cbGeneros = new GenerosCombo();
		cbGeneros.setBounds(97, 89, 219, 20);
		getContentPane().add(cbGeneros);
		
		txtDirector = new JTextField();
		txtDirector.setText("director");
		txtDirector.setBounds(291, 61, 281, 20);
		getContentPane().add(txtDirector);
		txtDirector.setColumns(10);
		
		txtEstreno = new JTextField();
		txtEstreno.setText("estreno");
		txtEstreno.setBounds(97, 61, 58, 20);
		getContentPane().add(txtEstreno);
		txtEstreno.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(97, 118, 475, 221);
		getContentPane().add(scrollPane);
		
		taSinopsis = new JTextArea();
		taSinopsis.setWrapStyleWord(true);
		taSinopsis.setLineWrap(true);
		taSinopsis.setLocation(97, 0);
		taSinopsis.setText("sinopsis");
		scrollPane.setViewportView(taSinopsis);
		
		JPanel pnBotones = new JPanel();
		pnBotones.setBounds(97, 351, 370, 60);
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
		
		lblCaratula = new Caratula("Car\u00E1tula");
		lblCaratula.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				elegirCaratula();
			}
		});
		lblCaratula.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblCaratula.setHorizontalAlignment(SwingConstants.CENTER);
		lblCaratula.setBounds(590, 11, 270, 400);
		getContentPane().add(lblCaratula);
		
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
		setForm(new PeliculasBDD().recuperaPorId(id));
	}
	
	protected void elegirCaratula() {
		JFileChooser fc = new JFileChooser();
		File dir = new File("C:/windows/");
		fc.setCurrentDirectory(dir);
		
		FileFilter ff = null;
//		ff = new FileNameExtensionFilter("Archivo de Video", "avi");
		fc.addChoosableFileFilter(ff);
		ff = new FileNameExtensionFilter("Archivos gráficos", "png", "gif", "jpg", "jpeg");
		fc.addChoosableFileFilter(ff);
		//Así quita la posibilidad de que elija otras extensiones
		fc.setAcceptAllFileFilterUsed(false);
		int respuesta = fc.showOpenDialog(null);
		
		if (respuesta == JFileChooser.APPROVE_OPTION) {
			File archivo = fc.getSelectedFile();
			lblCaratula.setImagenCaratula(archivo, lblCaratula.getWidth(), lblCaratula.getHeight());
		}
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
			txtDuracion.setText(p.getDuracion()!=null?"" + p.getDuracion():"");
			cbGeneros.recargarCombo(p.getIdGenero());
			txtDirector.setText(p.getDirector()!=null?p.getDirector():"");
			txtEstreno.setText(p.getEstreno()!=null?p.getEstreno():"");
			taSinopsis.setText(p.getSinopsis()!=null?p.getSinopsis():"");
			lblCaratula.setCaratulaByPath(_PpalFrame.CARATULAS_CARPETA + p.getCaratula(), lblCaratula.getWidth(), lblCaratula.getHeight());
		} else {
			txtId.setText("");
			txtTitulo.setText("");
			txtDuracion.setText("");
			cbGeneros.recargarCombo();
			txtDirector.setText("");
			txtEstreno.setText("");
			taSinopsis.setText("");
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
			String sinopsis = Utilidades.validarString(taSinopsis.getText());
			String caratula = lblCaratula.getNombreCaratula();
			p = new Pelicula(id, titulo, duracion, idGenero, director, estreno, sinopsis, caratula);
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
