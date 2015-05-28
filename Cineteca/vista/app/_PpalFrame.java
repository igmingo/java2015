	package app;
import javax.swing.JFrame;

import java.awt.Rectangle;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;

public class _PpalFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	
	//MENUBAR
	private JMenuBar menuBar;
	private JMenu mnPeliculas;
	private JMenu mnGeneros;
	private JMenu mnUsuarios;
	
	//PANEL1
	private JPanel pnPrincipal;
	private JTabbedPane tabs;
	private JPanel pnPeliculas;
	private JPanel pnGeneros;
	private JPanel pnUsuarios;
	
	//PANEL2
	private JPanel pnLogin;
	private JTextField txtEmail;
	private JPasswordField txtPassword;
	
	public boolean logeado;
	
	public _PpalFrame() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(new Rectangle(0, 0, 500, 500));
		getContentPane().setBounds(new Rectangle(0, 0, 500, 500));
		getContentPane().setLayout(null);
		
		pnLogin = new JPanel();
		pnLogin.setVisible(true);
		pnLogin.setBounds(10, 11, 474, 425);
		getContentPane().add(pnLogin);
		pnLogin.setLayout(null);
		
		JLabel lblUsuariocorreo = new JLabel("Correo electr\u00F3nico");
		lblUsuariocorreo.setBounds(112, 112, 250, 26);
		pnLogin.add(lblUsuariocorreo);
		lblUsuariocorreo.setFont(new Font("Dialog", Font.PLAIN, 20));
		
		txtEmail = new JTextField();
		txtEmail.setBounds(112, 150, 250, 30);
		pnLogin.add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblContrasea.setBounds(112, 206, 174, 26);
		pnLogin.add(lblContrasea);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(111, 244, 251, 30);
		pnLogin.add(txtPassword);
		txtPassword.setColumns(10);
		
		JButton btnLogin = new JButton("Entrar");
		btnLogin.setBounds(192, 322, 89, 30);
		pnLogin.add(btnLogin);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				login();
			}
		});
		
		pnPrincipal = new JPanel();
		pnPrincipal.setVisible(false);
		pnPrincipal.setBounds(10, 11, 474, 425);
		getContentPane().add(pnPrincipal);
		pnPrincipal.setLayout(null);
		
		tabs = new JTabbedPane(JTabbedPane.TOP);
		tabs.setBounds(0, 0, 474, 425);
		pnPrincipal.add(tabs);
		
		pnPeliculas = new JPanel();
		pnPeliculas.add(new PeliculasPanel());
		pnPeliculas.setLayout(null);
		tabs.addTab("Películas", null, pnPeliculas, null);
		tabs.setEnabledAt(0, true);
	
		pnGeneros = new JPanel();
		pnGeneros.add(new GenerosPanel());
		pnGeneros.setLayout(null);
		tabs.addTab("Géneros", null, pnGeneros, null);
		tabs.setEnabledAt(1, true);
		
		pnUsuarios = new JPanel();
		pnUsuarios.add(new UsuariosPanel());
		pnUsuarios.setLayout(null);
		tabs.addTab("Usuarios", null, pnUsuarios, null);
		tabs.setEnabledAt(2, false);
		
		menuBar = new JMenuBar();
		menuBar.setVisible(false);
		setJMenuBar(menuBar);
		
		JMenuItem mntmInfo = new JMenuItem("Videoteca");
		menuBar.add(mntmInfo);
		
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
		
		mntmPelListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tabs.setSelectedIndex(0);
			}
		});
		mntmGenListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tabs.setSelectedIndex(1);
			}
		});
		
		mntmListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tabs.setSelectedIndex(2);
			}
		});
	}

	protected void mostrarPanel(JPanel p) {
		panel.removeAll();
		panel.add(p);
		panel.repaint();
		panel.validate();
	}
	
	private void login() {
		Usuario userLogeado = new UsuariosBDD().login(txtEmail.getText(), new String(txtPassword.getPassword()));
		logeado = (userLogeado!=null && userLogeado.getStatus()>0);
		menuBar.setVisible(logeado);
		pnLogin.setVisible(!logeado);
		pnPrincipal.setVisible(logeado);
		if (logeado) {
			if (userLogeado.isAdmin()){
				mnPeliculas.setEnabled(logeado);
				mnGeneros.setEnabled(logeado);
				mnUsuarios.setEnabled(logeado);
				tabs.setEnabledAt(2, logeado);
			} else {
				mnPeliculas.setEnabled(logeado);
				mnGeneros.setEnabled(logeado);
				mnUsuarios.setEnabled(!logeado);
				tabs.setEnabledAt(2, !logeado);
			}
			repaint();
		} else {
			JOptionPane.showMessageDialog(null, "Acceso NO autorizado.", "Error", JOptionPane.WARNING_MESSAGE);
		}
	}

	public static void main(String[] args) {
		_PpalFrame main = new _PpalFrame();
		main.mostrar();
	}

	private void mostrar() {
		setVisible(true);
	}
}
