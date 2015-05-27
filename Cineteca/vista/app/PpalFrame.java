	package app;
import javax.swing.JFrame;

import java.awt.Rectangle;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;

public class PpalFrame extends JFrame {
	
	JPanel panel;
	JTabbedPane tabs;
	JPanel pnPeliculas;
	JPanel pnGeneros;
	
	public PpalFrame() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(new Rectangle(0, 0, 500, 500));
		getContentPane().setBounds(new Rectangle(0, 0, 500, 500));
		getContentPane().setLayout(null);
		
		tabs = new JTabbedPane(JTabbedPane.TOP);
		tabs.setBounds(10, 11, 474, 425);
		getContentPane().add(tabs);
		
		pnPeliculas = new JPanel();
		pnPeliculas.add(new PeliculasPanel());
		
		tabs.addTab("Películas", null, pnPeliculas, null);
		tabs.setEnabledAt(0, true);
		pnPeliculas.setLayout(null);
	
		pnGeneros = new JPanel();
		pnGeneros.add(new GenerosPanel());
		
		tabs.addTab("Géneros", null, pnGeneros, null);
		pnGeneros.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenuItem mntmInfo = new JMenuItem("Videoteca");
		menuBar.add(mntmInfo);
		
		JMenu mnPeliculas = new JMenu("Pel\u00EDculas");
		menuBar.add(mnPeliculas);
		JMenuItem mntmPelListado = new JMenuItem("Listado");
		mnPeliculas.add(mntmPelListado);
		
		JMenu mnGeneros = new JMenu("G\u00E9neros");
		menuBar.add(mnGeneros);
		JMenuItem mntmGenListado = new JMenuItem("Listado");
		mnGeneros.add(mntmGenListado);
		
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
	}

	protected void mostrarPanel(JPanel p) {
		panel.removeAll();
		panel.add(p);
		panel.repaint();
		panel.validate();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		PpalFrame main = new PpalFrame();
		main.mostrar();
	}

	private void mostrar() {
		setVisible(true);
	}
}
