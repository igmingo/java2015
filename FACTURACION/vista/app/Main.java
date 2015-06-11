/*
Cuatro tablas
Facturas
Conceptos
Clientes
Productos
 */

package app;

import javax.swing.JFrame;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;

public class Main extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel pnPrincipal;
	
	public Main() {
		setResizable(false);
		setBounds(0, 0, 800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		pnPrincipal = new JPanel();
		pnPrincipal.setBounds(10, 11, 774, 528);
		getContentPane().add(pnPrincipal);
		pnPrincipal.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnProductos = new JMenu("Productos");
		menuBar.add(mnProductos);
		JMenuItem mntmNuevoProducto = new JMenuItem("Nuevo Producto");
		mntmNuevoProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ProductoDialogo dialogo = new ProductoDialogo(0);
				dialogo.mostrar();
			}
		});
		mnProductos.add(mntmNuevoProducto);
		JMenuItem mntmListadoDeProductos = new JMenuItem("Listado de productos");
		mntmListadoDeProductos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarPanel("Productos");
			}
		});
		mnProductos.add(mntmListadoDeProductos);
		
		JMenu mnNewMenu = new JMenu("Clientes");
		menuBar.add(mnNewMenu);
		JMenuItem mntmNuevoCliente = new JMenuItem("Nuevo Cliente");
		mnNewMenu.add(mntmNuevoCliente);
		JMenuItem mntmListadoDeClientes = new JMenuItem("Listado de Clientes");
		mntmListadoDeClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarPanel("Clientes");
			}
		});
		mnNewMenu.add(mntmListadoDeClientes);
		
		JMenu mnFacturas = new JMenu("Facturas");
		menuBar.add(mnFacturas);
		JMenuItem mntmNuevaFactura = new JMenuItem("Nueva Factura");
		mnFacturas.add(mntmNuevaFactura);
		JMenuItem mntmListadoDeFacturas = new JMenuItem("Listado de Facturas");
		mnFacturas.add(mntmListadoDeFacturas);
		mntmListadoDeFacturas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mostrarPanel("Facturas");
			}
		});
	}

	protected void mostrarPanel(String string) {
		pnPrincipal.removeAll();
		pnPrincipal.add(new Paneles(string));
		pnPrincipal.repaint();
		pnPrincipal.validate();
		
	}

	public static void main(String[] args) {
		Main main = new Main();
		main.mostrar();
	}
	
	private void mostrar() {
		setVisible(true);
	}
}
