package app;

import javax.swing.JDialog;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.Rectangle;

import javax.swing.JCheckBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Date;

import com.toedter.calendar.JDateChooser;

import javax.swing.JSpinner;

import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.SpinnerNumberModel;
import java.awt.GridLayout;
import javax.swing.SwingConstants;

// TABLA facturas BASE DE DATOS
//id int(10) UNSIGNED No auto_increment
//clienteId int(10) UNSIGNED No clientes -> id
//numero int(11) No
//fecha date No
//porcDescuento double No
//porcRecargoEquivalencia	double No
//impTotal double No
//impRecargo double No
//impIva double No
//dirCorreo mediumtext No
//dirFactura mediumtext No
//dirEnvio mediumtext No
//cobrada tinyint(1) No

public class FacturaDialogo extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Factura factura;
	
	private JTextField txtId;
	private JTextField txtNumero;
	private JDateChooser dateFecha;
	private ClientesCombo cbCliente;
	private FacturasDetalleTabla tbFacturaDetalles;
	private JSpinner numPorcDescuento;
	private JSpinner numPorcRecargoequivalencia;
	private JSpinner numImptotal;
	private JSpinner numImprecargo;
	private JSpinner numImpiva;
	private JTextField txtDirCorreo;
	private JTextField txtDirFactura;
	private JTextField txtDirEnvio;
	private JCheckBox chkbxCobrada;

	public FacturaDialogo(Factura fac) {
		setResizable(false);
		this.factura = fac;
		setBounds(new Rectangle(0, 0, 700, 600));
		setTitle("Factura");
		setModal(true);
		getContentPane().setLayout(null);
		
		JPanel pnButtons = new JPanel();
		pnButtons.setBounds(10, 529, 674, 31);
		getContentPane().add(pnButtons);
		
		JButton btnGrabar = new JButton("Guardar");
		pnButtons.add(btnGrabar);
		JButton btnEliminar = new JButton("Eliminar");
		pnButtons.add(btnEliminar);
		JButton btnCancelar = new JButton("Cancelar");
		pnButtons.add(btnCancelar);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 674, 507);
		getContentPane().add(panel);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblId = new JLabel("id");
		panel.add(lblId, "2, 2, right, default");
		
		txtId = new JTextField();
		txtId.setText("id");
		panel.add(txtId, "4, 2, fill, default");
		txtId.setColumns(10);
		
		JLabel lblNfactura = new JLabel("N\u00BA Factura");
		panel.add(lblNfactura, "2, 4, right, default");
		
		txtNumero = new JTextField();
		txtNumero.setText("numFactura");
		panel.add(txtNumero, "4, 4, fill, default");
		txtNumero.setColumns(10);
		
		JLabel lblFechaDeFactura = new JLabel("Fecha de Factura");
		panel.add(lblFechaDeFactura, "2, 6, right, default");
		
		dateFecha = new JDateChooser();
		panel.add(dateFecha, "4, 6, fill, default");
		
		JLabel lblCliente = new JLabel("Cliente");
		panel.add(lblCliente, "2, 8, right, default");
		
		cbCliente = new ClientesCombo();
		panel.add(cbCliente, "4, 8, fill, default");
		
		JLabel lblProductos = new JLabel("Productos");
		panel.add(lblProductos, "2, 10, right, default");
		
		JPanel pnFacturaDetalles = new JPanel();
		panel.add(pnFacturaDetalles, "4, 10, fill, fill");
		pnFacturaDetalles.setLayout(new BorderLayout(0, 0));
		
		tbFacturaDetalles = new FacturasDetalleTabla("" + factura.getId());
		pnFacturaDetalles.add(tbFacturaDetalles, BorderLayout.CENTER);
		
		JButton btnAgregar = new JButton("A\u00F1adir Producto");
		pnFacturaDetalles.add(btnAgregar, BorderLayout.SOUTH);
		
		JLabel lblImporte = new JLabel("Importe");
		panel.add(lblImporte, "2, 12, right, default");
		
		JPanel pnDescuentos = new JPanel();
		panel.add(pnDescuentos, "4, 12, fill, fill");
		pnDescuentos.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblImporteRecago = new JLabel("Recargo");
		pnDescuentos.add(lblImporteRecago);
		
		numImprecargo = new JSpinner();
		numImprecargo.setMinimumSize(new Dimension(50, 20));
		numImprecargo.setModel(new SpinnerNumberModel(new Double(0), null, null, new Double(1)));
		pnDescuentos.add(numImprecargo);
		
		JLabel lblDescuento = new JLabel("% Descuento");
		pnDescuentos.add(lblDescuento);
		
		numPorcDescuento = new JSpinner();
		numPorcDescuento.setModel(new SpinnerNumberModel(new Double(0), null, null, new Double(1)));
		pnDescuentos.add(numPorcDescuento);
		
		JLabel lblRecargoEquivalencia = new JLabel("% Recargo Equivalencia");
		pnDescuentos.add(lblRecargoEquivalencia);
		
		numPorcRecargoequivalencia = new JSpinner();
		numPorcRecargoequivalencia.setModel(new SpinnerNumberModel(new Double(0), null, null, new Double(1)));
		pnDescuentos.add(numPorcRecargoequivalencia);
		
		JLabel lblImporteIva = new JLabel("IVA");
		pnDescuentos.add(lblImporteIva);
		
		numImpiva = new JSpinner();
		numImpiva.setModel(new SpinnerNumberModel(new Double(0), null, null, new Double(1)));
		pnDescuentos.add(numImpiva);
		
		JLabel lblImporteTotal = new JLabel("TOTAL");
		lblImporteTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		pnDescuentos.add(lblImporteTotal);
		
		numImptotal = new JSpinner();
		numImptotal.setModel(new SpinnerNumberModel(new Double(0), null, null, new Double(1)));
		pnDescuentos.add(numImptotal);
		
		JLabel lblDireccin = new JLabel("Direcci\u00F3n");
		panel.add(lblDireccin, "2, 14, right, default");
		
		JPanel pnDirecciones = new JPanel();
		panel.add(pnDirecciones, "4, 14, fill, fill");
		pnDirecciones.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblDireccinDeFacturacin = new JLabel("Direcci\u00F3n de Facturaci\u00F3n");
		pnDirecciones.add(lblDireccinDeFacturacin);
		
		txtDirFactura = new JTextField();
		pnDirecciones.add(txtDirFactura);
		txtDirFactura.setText("dirFactura");
		txtDirFactura.setColumns(10);
		
		JLabel lblDireccinDeEnvo = new JLabel("Direcci\u00F3n de Env\u00EDo");
		pnDirecciones.add(lblDireccinDeEnvo);
		
		txtDirEnvio = new JTextField();
		pnDirecciones.add(txtDirEnvio);
		txtDirEnvio.setText("dirEnvio");
		txtDirEnvio.setColumns(10);
		
		JLabel lblDireccinDeCorreo = new JLabel("Direcci\u00F3n de Correo");
		pnDirecciones.add(lblDireccinDeCorreo);
		
		txtDirCorreo = new JTextField();
		pnDirecciones.add(txtDirCorreo);
		txtDirCorreo.setText("dirCorreo");
		txtDirCorreo.setColumns(10);
		
		JLabel lblCobrada = new JLabel("Cobro");
		panel.add(lblCobrada, "2, 18, right, default");
		
		chkbxCobrada = new JCheckBox();
		chkbxCobrada.setText("\u00BFLa factura est\u00E1 cobrada?");
		panel.add(chkbxCobrada, "4, 18, fill, default");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				factura = null;
				setVisible(false);
			}
		});
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminar();
				setVisible(false);
			}
		});
		
		btnGrabar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				guardar();
				setVisible(false);
			}
		});
		
		if (factura!=null && factura.getId()>0) {
			factura = new FacturasBDD().recuperaPorId(factura.getId());
		} else {
			factura = null;
		}
		setForm();
		
	}
	
	private void eliminar() {
		Integer id = Utilidades.validarEntero(txtId.getText());
		if (id!=null) {
			int pregunta = JOptionPane.showConfirmDialog(null, "¿Desea eliminar la factura?\n", "Eliminar Factura", JOptionPane.OK_CANCEL_OPTION);
			if (pregunta==JOptionPane.OK_OPTION) {
				boolean eliminado = new ProductosBDD().eliminar(id);
				if (eliminado) {
					factura = null;
					mostrarMensaje("Factura Eliminada.");
				} else {
					mostrarMensaje("No se ha podido eliminar.");
				}
			}
		}
	}
	
	private void guardar() {
		factura = getForm();
		if (factura!=null) {
			int newId = new FacturasBDD().grabar(factura);
			if (newId>=0) {
				factura.setId(newId);
				setForm();
				mostrarMensaje("Factura guardada correctamente.");
			} else {
				mostrarMensaje("Error al guardar.");
			}
		} else {
			mostrarMensaje("El formulario no es correcto.");
		}
	}

	/**
	 * Rellena los datos de la Factura en el Formulario
	 * @param factura es la instancia del la Factura con el que vamos a rellenar el forumulario. Si es null, se rellena un formulario con id = 0;
	 */
	private void setForm() {
		// TABLA facturas BASE DE DATOS
		//id int(10) UNSIGNED No auto_increment
		//clienteId int(10) UNSIGNED No clientes -> id
		//numero int(11) No
		//fecha date No
		//porcDescuento double No
		//porcRecargoEquivalencia	double No
		//impTotal double No
		//impRecargo double No
		//impIva double No
		//dirCorreo mediumtext No
		//dirFactura mediumtext No
		//dirEnvio mediumtext No
		//cobrada tinyint(1) No
		if (factura!=null) {
			txtId.setText(""+ factura.getId());
			txtNumero.setText(""+ factura.getNumero());
			dateFecha.setDate(factura.getFecha());
			cbCliente.setSelectedId(factura.getClienteId());
			tbFacturaDetalles.actualizarTablaPorId(factura.getId());
			numPorcDescuento.setValue(factura.getPorcDescuento());
			numPorcRecargoequivalencia.setValue(factura.getPorcRecargoEquivalencia());
			numImptotal.setValue(factura.getImpTotal());
			numImprecargo.setValue(factura.getImpRecargo());
			numImpiva.setValue(factura.getImpIva());
			txtDirCorreo.setText(factura.getDirCorreo());
			txtDirFactura.setText(factura.getDirFactura());
			txtDirEnvio.setText(factura.getDirEnvio());
			chkbxCobrada.setSelected(factura.isCobrada());
		} else {
			txtId.setText("0");
			txtNumero.setText("");
			dateFecha.setDate(new Date());
			cbCliente.setSelectedId(null);
			tbFacturaDetalles.actualizarTablaPorId(null);
			numPorcDescuento.setValue(0);
			numPorcRecargoequivalencia.setValue(0);
			numImptotal.setValue(0);
			numImprecargo.setValue(0);
			numImpiva.setValue(0);
			txtDirCorreo.setText("");
			txtDirFactura.setText("");
			txtDirEnvio.setText("");
			chkbxCobrada.setSelected(false);
		}
	}
	/**
	 * Recoge el formulario y crea una instancia de Factura
	 * @return Retorna una instacia de Factura. Null si el formulario esta incorrecto.
	 */
	private Factura getForm() {
		Factura fac = null;
		
		Integer id = Utilidades.validarEntero(txtId.getText());
		Integer clienteId = cbCliente.getSelectedId();
		Integer numero = Utilidades.validarEntero(txtNumero.getText());
		Date fecha = dateFecha.getDate();
		Double porcDescuento = Utilidades.validarDouble(""
				+ numPorcDescuento.getValue());
		Double porcRecargoEquivalencia = Utilidades.validarDouble(""
				+ numPorcRecargoequivalencia.getValue());
		Double impTotal = Utilidades.validarDouble("" + numImptotal.getValue());
		Double impRecargo = Utilidades.validarDouble(""
				+ numImptotal.getValue());
		Double impIva = Utilidades.validarDouble("" + numImptotal.getValue());
		String dirCorreo = txtDirCorreo.getText();
		String dirFactura = txtDirFactura.getText();
		String dirEnvio = txtDirEnvio.getText();
		Boolean cobrada = chkbxCobrada.isSelected();
		try {
			fac = new Factura(id, clienteId, numero, fecha, porcDescuento,
					porcRecargoEquivalencia, impTotal, impRecargo, impIva,
					dirCorreo, dirFactura, dirEnvio, cobrada);
		} catch (Exception e) {
			mostrarMensaje("Error de formulario");
		}
		return fac;
	}
	
	private void mostrarMensaje(String string) {
		JOptionPane.showMessageDialog(null, string);
	}
	
	/**
	 * Hace visible el diálogo Modal para editar un Cliente. Si se hace invisible el Dialogo, se cierra y elimina de la memoria.
	 * @return Retorna la instacia del Cliente que se ha guardado
	 */
	public Factura mostrar() {
		setVisible(true);
		dispose();
		return factura;
	}
}
