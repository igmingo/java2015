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
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	
	private Usuario user = null;
	
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
		
		mnPeliculas = new JMenu("Pel\u00EDculas");
		menuBar.add(mnPeliculas);
		JMenuItem mntmPelListado = new JMenuItem("Listado");
		mnPeliculas.add(mntmPelListado);
		
		mnGeneros = new JMenu("G\u00E9neros");
		menuBar.add(mnGeneros);
		JMenuItem mntmGenListado = new JMenuItem("Listado");
		mnGeneros.add(mntmGenListado);
		
		mnUsuarios = new JMenu("Usuarios");
		mnUsuarios.setEnabled(false);
		menuBar.add(mnUsuarios);
		JMenuItem mntmListado = new JMenuItem("Listado");
		mnUsuarios.add(mntmListado);
		
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
				pnPrincipal.removeAll();
				pnPrincipal.add(new ConsultasPanel(user));
				pnPrincipal.repaint();
				pnPrincipal.validate();
			}
		});
		/* TABULADORES */
		mntmPelListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pnTabs.setSelectedIndex(0);
			}
		});
		mntmGenListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pnTabs.setSelectedIndex(1);
			}
		});
		
		mntmListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pnTabs.setSelectedIndex(2);
			}
		});
		
		pnLogin.btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				user = pnLogin.login();
				if (user!=null) {
					entrar(user);
					setTitle(user.getName());
					System.out.println(user.getName());
				}
			}
		});
		
	}
	
	private void cargarTabs() {
		pnPrincipal.removeAll();
		/* PESTA�AS */
		pnTabs = new JTabbedPane(JTabbedPane.TOP);
		pnTabs.setBounds(0, 0, 474, 425);
		pnPrincipal.add(pnTabs);
		
		/* CARGAR PANELES EN PESTA�AS */
		pnPeliculas = new JPanel();
		pnPeliculas.setLayout(null);
		pnTabs.addTab("Pel�culas", null, pnPeliculas, null);
		pnTabs.setEnabledAt(0, true);
	
		pnGeneros = new JPanel();
		pnGeneros.setLayout(null);
		pnTabs.addTab("G�neros", null, pnGeneros, null);
		pnTabs.setEnabledAt(1, true);
		
		pnUsuarios = new JPanel();
		pnUsuarios.setLayout(null);
		pnTabs.addTab("Usuarios", null, pnUsuarios, null);
		pnTabs.setEnabledAt(2, false);
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
				pnTabs.setEnabledAt(2, logeado);
			} else {
				mnPeliculas.setEnabled(logeado);
				mnGeneros.setEnabled(logeado);
				mnUsuarios.setEnabled(!logeado);
				pnTabs.setEnabledAt(2, !logeado);
			}
			repaint();
		} else {
			JOptionPane.showMessageDialog(null, "Acceso NO autorizado.", "Error", JOptionPane.WARNING_MESSAGE);
		}
	}

	protected void mostrarPanel(JPanel p) {
		panel.removeAll();
		panel.add(p);
		panel.repaint();
		panel.validate();
	}

	public static void main(String[] args) {
		_PpalFrame main = new _PpalFrame();
		main.mostrar();
	}
	
	private void cargarPanelesEnTabs(){
		pnPeliculas.removeAll();
		pnPeliculas.add(new PeliculasPanel(user));
		pnGeneros.removeAll();
		pnGeneros.add(new GenerosPanel(user));
		pnUsuarios.removeAll();
		pnUsuarios.add(new UsuariosPanel(user));
		pnPrincipal.repaint();
		pnPrincipal.validate();
	}

	private void mostrar() {
		setVisible(true);
	}
}
