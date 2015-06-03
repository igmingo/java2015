package app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class configuracion {
	
	private String pathname;
	
	public configuracion() {
		this.pathname = "./config.ini";
	}

	public configuracion(String path) {
		this.pathname = path;
	}

//	private boolean guardar() {	
//		try {
//			FileWriter archivo=null;
//			PrintWriter pw = null;
//			archivo = new FileWriter(pathname);
//			pw = new PrintWriter(archivo);
//			for (int i = 0; i < lista.size(); i++) {
//				String registro = lista.get(i).toSend();
//				pw.println(registro);
//			}
//			archivo.close();
//			pw.close();
//			return true;
//		} catch (Exception e) {
//			return false;
//		}
//	}
	
	public ArrayList<String> cargar() {
		ArrayList<String> lista = new ArrayList<>();
		try {
			File archivo = null;
			FileReader fr = null;
			BufferedReader br = null;
			archivo = new File(pathname);			
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			String registro;
			while ( (registro=br.readLine())!=null ) {
				String[] linea = registro.split("=");
					lista.add(linea[0]);
			}
			br.close();
			fr.close();
			return lista;
		} catch (Exception e) {
			return null;
		}
	}
}
