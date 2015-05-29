package app;

import java.util.ArrayList;

import javax.swing.JComboBox;

public class GenerosCombo extends JComboBox<Genero> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void recargarCombo() {
		recargarCombo(0);
	}
	
	public int getSelectedIdGen() {
		//Devuelve el ID del Genero selecionado en el Combo
		return getItemAt(getSelectedIndex()).getId();
	}
	
	public void recargarCombo(Integer idGenero) {
		//Carga todos los Generos del Repositorio GenerosBDD, y deja seleccionado el que tiene el IdGen
		if (idGenero!=null){
			int idSel = idGenero;
			int pos = 0;
			removeAllItems();
			ArrayList<Genero> lista = new GenerosBDD().RecuperaTodos();
			for (Genero dpto : lista) {
				addItem(dpto);
				if (dpto.getId() == idSel) {
					setSelectedIndex(pos);
				}
				pos++;
			}
		}
	}

}
