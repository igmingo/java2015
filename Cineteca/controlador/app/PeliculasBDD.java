package app;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

public class PeliculasBDD {
	
	// peliculas
	// `id`, `titulo`, `duracion`, `idGen`, `director`, `estreno`, `sinopsis`
	// generos
	// `idGen`, `genero`

	public ArrayList<Pelicula> RecuperaTodos (){
		return Recupera("");
	}
	
	public ArrayList<Pelicula> Recupera(String criterio){
		criterio = "WHERE peliculas.titulo LIKE '%" + criterio + "%'";
		String sql = "SELECT * FROM peliculas " + criterio + " ORDER BY peliculas.titulo";
		System.out.println(sql);
		ArrayList<Pelicula> lista = new ArrayList<>(); 
		Connection c = new Conexion().getConection();
		if (c!=null) {
			try {
				// Crea un ESTAMENTO (comando de ejecucion de un sql)
				Statement comando = c.createStatement();
				ResultSet rs = comando.executeQuery(sql);
				while (rs.next() == true) {
					lista.add(new Pelicula(
							rs.getInt("id"),
							rs.getString("titulo"),
							rs.getInt("duracion"),
							rs.getInt("idGen"),
							rs.getString("director"),
							rs.getString("estreno"),
							rs.getString("sinopsis")
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
	
	public Pelicula RecuperaPorId (int id) {
		Pelicula peli = null;
		String 	sql = "SELECT * FROM peliculas " +
				"WHERE peliculas.id = " + id;
		System.out.println(sql);
		// CREO UNA CONEXION
		Connection c = new Conexion().getConection();
		if (c!=null) {
			try {
				// Crea un ESTAMENTO (comando de ejecucion de un sql)
				Statement comando = c.createStatement();
				ResultSet rs = comando.executeQuery(sql);
				if ( rs.first() ) {
					peli = new Pelicula(
							rs.getInt("id"),
							rs.getString("titulo"),
							rs.getInt("duracion"),
							rs.getInt("idGen"),
							rs.getString("director"),
							rs.getString("estreno"),
							rs.getString("sinopsis")
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
		return peli;
	}
	
	public int Grabar(Pelicula peli) {
		int respuesta = -1;
		String sql = "";
		if (peli.getId()==0) {
			sql = "INSERT INTO peliculas " +
					"SET peliculas.titulo = '" + peli.getTitulo() + "'"
					;
		} else {
			sql = "UPDATE peliculas " +
					"SET peliculas.titulo = '" + peli.getTitulo() + "', " +
					"WHERE peliculas.id = " + peli.getId();
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
				if (peli.getId() != 0){
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
		String 	sql = "DELETE FROM peliculas " +
				"WHERE peliculas.id = " + id;
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

	public ArrayList<Vector<Object>> recuperaTablaPeliculas(String criterio) {
			// Devuelve una tabla, o Vector de Vectores de objetos
			// `titulo`, `estreno`, `director`, `id`
			ArrayList<Vector<Object>> tableData = null;
			criterio = "WHERE peliculas.titulo LIKE '%" + criterio + "%'";
			String sql = "SELECT peliculas.titulo, peliculas.estreno, peliculas.director, peliculas.id FROM peliculas " + criterio + " ORDER BY peliculas.titulo";
			System.out.println(sql);
			tableData = new ArrayList<>();
			Connection c = new Conexion().getConection();
			if (c!=null) {
				try {
					Statement comando = c.createStatement();
					ResultSet rs = comando.executeQuery(sql);
					while (rs.next() == true) {
						//Los datos de la fila son un tipo VECTOR
						Vector<Object> filaData = new Vector<>();
						filaData.add(rs.getString("titulo"));
						filaData.add(rs.getString("estreno"));
						filaData.add(rs.getString("director"));
						filaData.add(rs.getInt("id"));
						tableData.add(filaData);
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
			return tableData;
	}
	
}
