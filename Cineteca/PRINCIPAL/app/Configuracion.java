package app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Configuracion {
	
	private static String pathname = "./config.cfg";
	private static Map<String,String> configMap;

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
	
	public static boolean loadConfigMap() {
		Map<String,String> lista = new HashMap<String,String>();
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
					lista.put(linea[0].trim(), linea[1].trim() );
			}
			br.close();
			fr.close();
			setConfigMap(lista);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static File getPathCovers() {
		String pathCovers = null;
		pathCovers = getConfigMap().get("pathCovers");
		File path = new File(pathCovers);
		if(path.exists()) {
			return path;
		} else {
			return null;
		}
	}

	public static void setPathCovers(String pathCovers) {
		getConfigMap().put("pathCovers", pathCovers);
	}

	//GETTER Y SETTERS
	
	public static String getPathname() {
		return pathname;
	}

	public static void setPathname(String path) {
		pathname = path;
	}
	
	public static Map<String,String> getConfigMap() {
		return configMap;
	}

	public static void setConfigMap(Map<String,String> configMap) {
		Configuracion.configMap = configMap;
	}

	@Override
	public String toString() {
		String salida = "";
		Iterator<Entry<String,String>> it = getConfigMap().entrySet().iterator();
		while (it.hasNext()) {
		  Entry<String,String> e = it.next();
		  salida += e.getKey() + ":" + e.getValue() + "\n";
		}
		return salida;
	}
	
}
