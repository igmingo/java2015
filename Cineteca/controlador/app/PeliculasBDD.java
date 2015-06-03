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

	
	//METODO PUBLICO
	
	public ArrayList<Pelicula> recuperaPorFiltro(String filtro) {
		String sql = "SELECT * FROM peliculas, generos " + filtro
				+ " ORDER BY peliculas.titulo";
		System.out.println(sql);
		ArrayList<Pelicula> lista = new ArrayList<>();
		Connection c = new Conexion().getConection();
		if (c != null) {
			try {
				// Crea un ESTAMENTO (comando de ejecucion de un sql)
				Statement comando = c.createStatement();
				ResultSet rs = comando.executeQuery(sql);
				while (rs.next() == true) {
					lista.add(new Pelicula(rs.getInt("id"),
							rs.getString("titulo"),
							rs.getInt("duracion"), 
							rs.getInt("idGen"),
							rs.getString("director"),
							rs.getString("estreno"),
							rs.getString("sinopsis"),
							rs.getString("caratula")
							));
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
	
	public String creaFiltro(Integer id, String titulo,
			String genero, Integer desdeEstreno, Integer hastaEstreno,
			Integer desdeDuracion, Integer hastaDuracion, String director) {
		String filtro = "WHERE ";
		filtro += id!=null?"id=" + id + " ":" peliculas.idGen = generos.idGen ";
		filtro += titulo!=null?"AND peliculas.titulo LIKE '%" + titulo + "%' ":"";
		filtro += genero!=null?"AND generos.genero LIKE '%" + genero + "%' ":"";
		filtro += (desdeEstreno!=null || hastaEstreno!=null) ?"AND peliculas.estreno >=" + desdeEstreno + " AND peliculas.estreno <= " + hastaEstreno +" ":"";
		filtro += (desdeDuracion!=null || hastaDuracion!=null) ?"AND peliculas.duracion >=" + desdeDuracion + " AND peliculas.duracion <= " + hastaDuracion +" ":"";
		filtro += director!=null?"AND peliculas.director LIKE '%" + director + "%' ":"";
		return filtro;
	}
	
	public ArrayList<Pelicula> recuperaPorCampos(Integer id, String titulo,
			String genero, Integer desdeEstreno, Integer hastaEstreno,
			Integer desdeDuracion, Integer hastaDuracion, String director) {
		String filtro = creaFiltro(id, titulo, genero, desdeEstreno,
				hastaEstreno, desdeDuracion, hastaDuracion, director);
		return recuperaPorFiltro(filtro);
	}
	
	public ArrayList<Pelicula> recuperaPorTitulo(String criterio) {
		String filtro = "WHERE peliculas.titulo LIKE '%" + criterio + "%'";
		return recuperaPorFiltro(filtro);
	}
	
	public ArrayList<Pelicula> recuperaTodos(){
		return recuperaPorFiltro("WHERE 1");
	}
	
	public Pelicula recuperaPorId(int id){
		if (id != 0) {
			String filtro = "WHERE peliculas.id = " + id;
			ArrayList<Pelicula> lista = recuperaPorFiltro(filtro);
			return lista.get(0);
		} else {
			return new Pelicula(0);
		}
	}
	
	public int Grabar(Pelicula peli) {
		int respuesta = -1;
		String sql = "";
		if (peli.getId()==0) {
			sql = "INSERT INTO peliculas SET " +
					"peliculas.titulo = '" + peli.getTitulo() + "', " +
					"peliculas.duracion = " + peli.getDuracion() + ", " +
					"peliculas.idGen = " + peli.getIdGenero() + ", " +
					"peliculas.director = '" + peli.getDirector() + "', " +
					"peliculas.estreno = '" + peli.getEstreno() + "', " +
					"peliculas.sinopsis = '" + peli.getSinopsis() + "', " +
					"peliculas.caratula = '" + peli.getCaratula() + "'"
					;
		} else {
			sql = "UPDATE peliculas SET " +
					"peliculas.titulo = '" + peli.getTitulo() + "', " +
					"peliculas.duracion = " + peli.getDuracion() + ", " +
					"peliculas.idGen = " + peli.getIdGenero() + ", " +
					"peliculas.director = '" + peli.getDirector() + "', " +
					"peliculas.estreno = '" + peli.getEstreno() + "', " +
					"peliculas.sinopsis = '" + peli.getSinopsis() + "', " +
					"peliculas.caratula = '" + peli.getCaratula() + "' " +
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

	
	//RECUPERAR TABLAS ESPECIALES
	
	public ArrayList<Vector<Object>> recuperaTablaPeliculas(String filtro) {
			// Devuelve una tabla, o Vector de Vectores de objetos
			// `titulo`, `estreno`, `director`, `id`
			ArrayList<Vector<Object>> tableData = null;
			filtro = "WHERE peliculas.titulo LIKE '%" + filtro + "%'";
			String sql = "SELECT peliculas.titulo, peliculas.estreno, peliculas.director, peliculas.id FROM peliculas " + filtro + " ORDER BY peliculas.titulo";
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

	public ArrayList<Vector<Object>> recuperaTablaConsulta(String filtro) {
			//Crea una tabla especial con algunos datos
			// Devuelve una tabla, o Vector de Vectores de objetos
			// `id`, `titulo`, `estreno`, `director`, genero, duracion
			ArrayList<Vector<Object>> tableData = null;
			String sql = "SELECT * FROM peliculas, generos " + filtro + " ORDER BY peliculas.titulo";
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
						filaData.add(rs.getInt("id"));
						filaData.add(rs.getString("titulo"));
						filaData.add(rs.getString("estreno"));
						filaData.add(rs.getString("director"));
						filaData.add(rs.getString("genero"));
						filaData.add(rs.getString("duracion"));
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
