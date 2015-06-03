package app;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTable;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class ConsultasPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JCheckBox chkTitulo;
	private JCheckBox chkGenero;
	private JCheckBox chkEstreno;
	private JCheckBox chkDuracion;
	private JCheckBox chkDirector;
	
	private JTextField txtTitulo;
	private JTextField txtGenero;
	private JTextField txtEstreno1;
	private JTextField txtEstreno2;
	private JTextField txtDuracion1;
	private JTextField txtDuracion2;
	private JTextField txtDirector;

	private ConsultasTabla tbConsultas;

	public ConsultasPanel(Usuario user) {
		setBounds(new Rectangle(0, 0, 469, 397));
		setLayout(null);
		
		JPanel checkBoxes = new JPanel();
		checkBoxes.setBounds(6, 39, 174, 127);
		add(checkBoxes);
		checkBoxes.setLayout(null);
		
		chkTitulo = new JCheckBox("T\u00EDtulo que contenga");
		chkTitulo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				txtTitulo.setEditable(chkTitulo.isSelected());
			}
		});
		chkTitulo.setBounds(0, 0, 174, 23);
		checkBoxes.add(chkTitulo);
		
		chkGenero = new JCheckBox("G\u00E9nero que contenga");
		chkGenero.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				txtGenero.setEditable(chkGenero.isSelected());
			}
		});
		chkGenero.setBounds(0, 26, 174, 23);
		checkBoxes.add(chkGenero);
		
		chkEstreno = new JCheckBox("A\u00F1o entre");
		chkEstreno.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				txtEstreno1.setEditable(chkEstreno.isSelected());
				txtEstreno2.setEditable(chkEstreno.isSelected());
			}
		});
		chkEstreno.setBounds(0, 52, 174, 23);
		checkBoxes.add(chkEstreno);
		
		chkDuracion = new JCheckBox("Duraci\u00F3n entre");
		chkDuracion.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				txtDuracion1.setEditable(chkDuracion.isSelected());
				txtDuracion2.setEditable(chkDuracion.isSelected());
			}
		});
		chkDuracion.setBounds(0, 78, 174, 23);
		checkBoxes.add(chkDuracion);
		chkDirector = new JCheckBox("Director que contenga");
		chkDirector.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				txtDirector.setEditable(chkDirector.isSelected());
			}
		});
		chkDirector.setBounds(0, 104, 174, 23);
		checkBoxes.add(chkDirector);
		
		txtTitulo = new JTextField();
		txtTitulo.setBounds(200, 40, 259, 20);
		add(txtTitulo);
		txtTitulo.setColumns(10);
		
		txtGenero = new JTextField();
		txtGenero.setColumns(10);
		txtGenero.setBounds(200, 66, 259, 20);
		add(txtGenero);
		
		txtEstreno1 = new JTextField();
		txtEstreno1.setColumns(10);
		txtEstreno1.setBounds(200, 92, 114, 20);
		add(txtEstreno1);
		
		txtDuracion1 = new JTextField();
		txtDuracion1.setColumns(10);
		txtDuracion1.setBounds(200, 121, 114, 20);
		add(txtDuracion1);
		
		txtDirector = new JTextField();
		txtDirector.setColumns(10);
		txtDirector.setBounds(200, 147, 259, 20);
		add(txtDirector);
		
		txtEstreno2 = new JTextField();
		txtEstreno2.setColumns(10);
		txtEstreno2.setBounds(345, 92, 114, 20);
		add(txtEstreno2);
		
		txtDuracion2 = new JTextField();
		txtDuracion2.setColumns(10);
		txtDuracion2.setBounds(345, 118, 114, 20);
		add(txtDuracion2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 173, 453, 212);
		add(scrollPane);
		
		tbConsultas = new ConsultasTabla(user, "");
		scrollPane.setViewportView(tbConsultas);
		
		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.setBounds(370, 7, 89, 23);
		add(btnFiltrar);
		
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				aplicarFiltro();
			}
		});
		
	}
	
	protected void aplicarFiltro() {
		tbConsultas.setFiltro(
				null,
				chkTitulo.isSelected() ? txtTitulo.getText().trim() : null,
				chkGenero.isSelected() ? txtGenero.getText().trim() : null,
				chkEstreno.isSelected() ? Utilidades.validarEntero(txtEstreno1.getText()) : null,
				chkEstreno.isSelected() ? Utilidades.validarEntero(txtEstreno2.getText()) : null,
				chkDuracion.isSelected() ? Utilidades.validarEntero(txtDuracion1.getText()) : null,
				chkDuracion.isSelected() ? Utilidades.validarEntero(txtDuracion2.getText()) : null,
				chkDirector.isSelected() ? txtDirector.getText().trim() : null
				);
		System.out.println(tbConsultas.getFiltro());
		tbConsultas.actualizarTabla();
	}
}
