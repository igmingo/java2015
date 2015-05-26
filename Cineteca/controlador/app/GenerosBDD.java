package app;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GenerosBDD {

	// peliculas
	// `id`, `titulo`, `duracion`, `idGen`, `director`, `estreno`, `sinopsis`
	// generos
	// `idGen`, `genero`
	
	public ArrayList<Genero> RecuperaTodos (){
		return Recupera("");
	}
	
	public ArrayList<Genero> Recupera(String criterio){
		criterio = "WHERE generos.genero LIKE '%" + criterio + "%'";
		String sql = "SELECT * FROM generos " + criterio + " ORDER BY generos.genero";
		System.out.println(sql);
		ArrayList<Genero> lista = new ArrayList<>(); 
		Connection c = new Conexion().getConection();
		if (c!=null) {
			try {
				// Crea un ESTAMENTO (comando de ejecucion de un sql)
				Statement comando = c.createStatement();
				ResultSet rs = comando.executeQuery(sql);
				while (rs.next() == true) {
					lista.add(new Genero(
							rs.getInt("id"),
							rs.getString("genero")
							)
					);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		try {
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public Genero RecuperaPorId (int id) {
		Genero g = null;
		String 	sql = "SELECT * FROM generos " +
				"WHERE generos.id = " + id;
		System.out.println(sql);
		// CREO UNA CONEXION
		Connection c = new Conexion().getConection();
		if (c!=null) {
			try {
				// Crea un ESTAMENTO (comando de ejecucion de un sql)
				Statement comando = c.createStatement();
				ResultSet rs = comando.executeQuery(sql);
				if ( rs.first() ) {
					g = new Genero(
							rs.getInt("id"),
							rs.getString("genero")
							);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		try {
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return g;
	}
	
	public int Grabar(Genero g) {
		int respuesta = -1;
		String sql = "";
		if (g.getId()==0) {
			sql = "INSERT INTO generos " +
					"SET generos.genero = '" + g.getGenero() + "'"
					;
		} else {
			sql = "UPDATE generos " +
					"SET generos.genero = '" + g.getGenero() + "', " +
					"WHERE generos.id = " + g.getId();
					;
		}
		System.out.println(sql);
		// CREO UNA CONEXION
		Connection c = new Conexion().getConection();
		// SI LA CONEXION ES VALIDA
		if (c!=null) {
			// INTENTA REALIZAR EL SQL
			try {
				// Crea un ESTAMENTO (comando de ejecucion de un sql)
				Statement comando = c.createStatement();
				comando.execute(sql,Statement.RETURN_GENERATED_KEYS);
				// COMPRUEBA si estamos en un Insert o en un Update
				if (g.getId() != 0){
					// ES UN UPDATE
					respuesta = comando.getUpdateCount()>0?0:-1;
				} else {
					// VAMOS A DEVOLVER EL ID GENERADO, pero el EXECUTE devuelve un RESULTSET
					ResultSet resultados = comando.getGeneratedKeys();
					// Si el conjunto de resultados no es nulo, y coge el proximo elemento (el primero)
					if (resultados!=null && resultados.next()) {
						respuesta = resultados.getInt(1);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		//CERRAMOS LA CONEXION
		try {
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return respuesta;
	}
	
	public boolean Eliminar (int id) {
		boolean respuesta = false;
		String 	sql = "DELETE FROM generos " +
				"WHERE generos.id = " + id;
		System.out.println(sql);
		// CREO UNA CONEXION
		Connection c = new Conexion().getConection();
		if (c!=null) {
			try {
				// Crea un ESTAMENTO (comando de ejecucion de un sql)
				Statement comando = c.createStatement();
				if (comando.execute(sql)==false){
					respuesta = comando.getUpdateCount()>0?true:false ;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		try {
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(sql);
		return respuesta;
	}
	
	
}
