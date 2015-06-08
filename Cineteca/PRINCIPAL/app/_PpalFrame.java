	package app;
import javax.swing.JFrame;

import java.awt.Rectangle;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTabbedPane;

public class _PpalFrame extends JFrame {
	/**
	 * 
	 */
	
	//public static String CARATULAS_CARPETA = "./covers/";
	private static final long serialVersionUID = 1L;
	private Usuario userPpal = null;
	
	//MENUBAR
	private JMenuBar menuBar;
	private JMenu mnPeliculas;
	private JMenu mnGeneros;
	private JMenu mnUsuarios;
	
	//PANEL1
	private JPanel pnPrincipal;
	
	private JTabbedPane pnTabs;
	private JPanel pnPeliculas;
	private JPanel pnGeneros;
	private JPanel pnUsuarios;
	
	//PANEL2
	private LoginPanel pnLogin;
	private JMenuItem mntmNuevaPelcula;
	private JMenuItem mntmNuevoGnero;
	private JMenuItem mntmNuevoUsuario;
	
	public _PpalFrame() {
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(new Rectangle(0, 0, 500, 500));
		getContentPane().setBounds(new Rectangle(0, 0, 500, 500));
		getContentPane().setLayout(null);
		
		/* PANEL DE LOGIN
		 *
		 **/
		pnLogin = new LoginPanel();
		pnLogin.setVisible(true);
		pnLogin.setBounds(10, 11, 474, 425);
		getContentPane().add(pnLogin);
		pnLogin.setLayout(null);
		
		/* MENU
		 *
		 **/
		menuBar = new JMenuBar();
		menuBar.setVisible(false);
		setJMenuBar(menuBar);
		
		JMenuItem mntmConsultas = new JMenuItem("Consultas");
		menuBar.add(mntmConsultas);
		
		JMenuItem mntmEditarDatos = new JMenuItem("Editar Datos");
		menuBar.add(mntmEditarDatos);
		
		mnPeliculas = new JMenu("Pel\u00EDculas");
		menuBar.add(mnPeliculas);
		JMenuItem mntmPelListado = new JMenuItem("Listado");
		mnPeliculas.add(mntmPelListado);
		
		mntmNuevaPelcula = new JMenuItem("Nueva Pel\u00EDcula");
		mnPeliculas.add(mntmNuevaPelcula);
		
		mnGeneros = new JMenu("G\u00E9neros");
		menuBar.add(mnGeneros);
		JMenuItem mntmGenListado = new JMenuItem("Listado");
		mnGeneros.add(mntmGenListado);
		
		mntmNuevoGnero = new JMenuItem("Nuevo G\u00E9nero");
		mnGeneros.add(mntmNuevoGnero);
		
		mnUsuarios = new JMenu("Usuarios");
		mnUsuarios.setEnabled(false);
		menuBar.add(mnUsuarios);
		JMenuItem mntmListado = new JMenuItem("Listado");
		mnUsuarios.add(mntmListado);
		
		mntmNuevoUsuario = new JMenuItem("Nuevo Usuario");
		mnUsuarios.add(mntmNuevoUsuario);
		
		/* PANEL PRINCIPAL (CONTIENE TABS)
		 *
		 **/
		pnPrincipal = new JPanel();
		pnPrincipal.setVisible(false);
		pnPrincipal.setBounds(10, 11, 474, 425);
		getContentPane().add(pnPrincipal);
		pnPrincipal.setLayout(null);
		
		/* EVENTOS
		 *
		 **/
		
		mntmConsultas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Elimina todos los paneles de pnPrincipal
				pnPrincipal.removeAll();
				pnPrincipal.add(new ConsultasPanel(userPpal));
				pnPrincipal.repaint();
				pnPrincipal.validate();
			}
		});
		/* TABULADORES */
		mntmPelListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cargarTabs();
				cargarPanelesEnTabs();
				pnTabs.setSelectedIndex(0);
			}
		});
		mntmGenListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cargarTabs();
				cargarPanelesEnTabs();
				pnTabs.setSelectedIndex(1);
			}
		});
		
		mntmListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cargarTabs();
				cargarPanelesEnTabs();
				pnTabs.setSelectedIndex(2);
			}
		});
		
		pnLogin.btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				userPpal = pnLogin.login();
				if (userPpal!=null) {
					entrar(userPpal);
					setTitle(userPpal.getName());
					System.out.println(userPpal.getName());
				}
			}
		});
		
		mntmEditarDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarTabs();
				cargarPanelesEnTabs();
				pnTabs.setSelectedIndex(0);
			}
		});
		
		mntmNuevaPelcula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PeliculaDialogo dialogo = new PeliculaDialogo(userPpal, 0);
				Pelicula newPelicula = dialogo.mostrar();
				if (newPelicula!=null) {
					JOptionPane.showMessageDialog(null, "Pelicula añadida.");
				}
			}
		});
		
		mntmNuevoGnero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GeneroDialogo dialogo = new GeneroDialogo(userPpal, 0);
				Genero newGenero = dialogo.mostrar();
				if (newGenero!=null) {
					JOptionPane.showMessageDialog(null, "Género añadido.");
				}
			}
		});
		
		mntmNuevoUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UsuarioDialogo dialogo = new UsuarioDialogo(userPpal, 0);
				Usuario newUsuario = dialogo.mostrar();
				if (newUsuario!=null) {
					JOptionPane.showMessageDialog(null, "Usuario añadido.");
				}
			}
		});
		
	}
	
	private void cargarTabs() {
		pnPrincipal.removeAll();
		/* PESTAÑAS */
		pnTabs = new JTabbedPane(JTabbedPane.TOP);
		pnTabs.setBounds(0, 0, 474, 425);
		pnPrincipal.add(pnTabs);
		
		/* CARGAR PANELES EN PESTAÑAS */
		pnPeliculas = new JPanel();
		pnPeliculas.setLayout(null);
		pnTabs.addTab("Películas", null, pnPeliculas, null);
		pnTabs.setEnabledAt(0, (userPpal.isLoged() && userPpal.getStatus()>0) );
	
		pnGeneros = new JPanel();
		pnGeneros.setLayout(null);
		pnTabs.addTab("Géneros", null, pnGeneros, null);
		pnTabs.setEnabledAt(1, (userPpal.isLoged() && userPpal.getStatus()>0) );
		
		pnUsuarios = new JPanel();
		pnUsuarios.setLayout(null);
		pnTabs.addTab("Usuarios", null, pnUsuarios, null);
		pnTabs.setEnabledAt(2, (userPpal.isLoged() && userPpal.isAdmin() && userPpal.getStatus()>0) );
		
		pnPrincipal.repaint();
		pnPrincipal.validate();
	}

	private void entrar(Usuario userLogeado) {
		boolean logeado = (userLogeado.isLoged() && userLogeado.getStatus()>0);
		if (logeado) {
			cargarTabs();
			cargarPanelesEnTabs();
			menuBar.setVisible(logeado);
			pnLogin.setVisible(!logeado);
			pnPrincipal.setVisible(logeado);
			if (userLogeado.isAdmin()){
				mnPeliculas.setEnabled(logeado);
				mnGeneros.setEnabled(logeado);
				mnUsuarios.setEnabled(logeado);
			} else {
				mnPeliculas.setEnabled(logeado);
				mnGeneros.setEnabled(logeado);
				mnUsuarios.setEnabled(!logeado);
			}
			repaint();
		} else {
			JOptionPane.showMessageDialog(null, "Acceso NO autorizado.", "Error", JOptionPane.WARNING_MESSAGE);
		}
	}

//	protected void mostrarPanel(JPanel p) {
//		panel.removeAll();
//		panel.add(p);
//		panel.repaint();
//		panel.validate();
//	}

	public static void main(String[] args) {
		if(Configuracion.loadConfigMap()){
			_PpalFrame main = new _PpalFrame();
			//System.out.println(Configuracion.getPathCovers().getPath());
			main.mostrar();
		}
	}

	private void cargarPanelesEnTabs(){
		pnPeliculas.removeAll();
		pnPeliculas.add(new PeliculasPanel(userPpal));
		pnGeneros.removeAll();
		pnGeneros.add(new GenerosPanel(userPpal));
		pnUsuarios.removeAll();
		pnUsuarios.add(new UsuariosPanel(userPpal));
		pnPrincipal.repaint();
		pnPrincipal.validate();
	}

	private void mostrar() {
		setVisible(true);
	}
}
