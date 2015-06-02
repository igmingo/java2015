package app;

import java.awt.Image;
import java.io.File;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Caratula extends JLabel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public File archivoCaratula;

	public Caratula() {
		super();
		// TODO Apéndice de constructor generado automáticamente
	}

	public Caratula(Icon arg0, int arg1) {
		super(arg0, arg1);
		// TODO Apéndice de constructor generado automáticamente
	}

	public Caratula(Icon arg0) {
		super(arg0);
		// TODO Apéndice de constructor generado automáticamente
	}

	public Caratula(String arg0, Icon arg1, int arg2) {
		super(arg0, arg1, arg2);
		// TODO Apéndice de constructor generado automáticamente
	}

	public Caratula(String text, int horizontalAlignment) {
		super(text, horizontalAlignment);
		// TODO Apéndice de constructor generado automáticamente
	}

	public Caratula(String text) {
		super(text);
		// TODO Apéndice de constructor generado automáticamente
	}

	public void setImagenCaratula(File archivoCaratula, int w, int h) {
		setArchivoCaratula(archivoCaratula);
		setSize(w, h);
		
		setToolTipText(getNombreCaratula());
		try {
			setIcon(new ImageIcon (new ImageIcon(archivoCaratula.getPath()).getImage().getScaledInstance(w,h, Image.SCALE_SMOOTH)));
			setText(getNombreCaratula());
		} catch (Exception e) {
			setText("Error.");
			setIcon(null);
		}
	}

	public void setCaratulaByPath(String path, int w, int h) {
		File archivo = new File(path);
		if (archivo.exists()) {
			setImagenCaratula(archivo, w, h);
		} else {
			setText("Caratula no encontrada.");
			setIcon(null);
		}
	}
	
	public String getRutaCaratula() {
		return archivoCaratula.getName();
	}
	
	public String getNombreCaratula() {
		return archivoCaratula.getName();
	}
	
	//Getters y Setters

	public File getArchivoCaratula() {
		return archivoCaratula;
	}

	public void setArchivoCaratula(File archivoCaratula) {
		this.archivoCaratula = archivoCaratula;
	}
	
}
