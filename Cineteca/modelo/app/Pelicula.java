package app;

public class Pelicula {
	//peliculas
	//*`id`, *`titulo`, `duracion`, *`idGen`, `director`, `estreno`, `sinopsis`
	//generos
	//`idGen`, `genero`
	
//	CLASE PELICULA
	int id;
	String titulo;
	Integer duracion;
	int idGenero;
	String director;
	String estreno;
	String sinopsis;
	
	public Pelicula() {
	}
	
	public Pelicula(int id) {
		this.id = id;
		this.titulo = "Sin título";
		this.idGenero = 0;
	}
	
	public Pelicula(int id, String titulo, int idGenero) {
		this.id = id;
		this.titulo = titulo;
		this.idGenero = idGenero;
	}

	public Pelicula(int id, String titulo, Integer duracion, int idGenero,
			String director, String estreno, String sinopsis) {
		this.id = id;
		this.titulo = titulo;
		this.duracion = duracion;
		this.idGenero = idGenero;
		this.director = director;
		this.estreno = estreno;
		this.sinopsis = sinopsis;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Integer getDuracion() {
		return duracion;
	}
	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}
	public int getIdGenero() {
		return idGenero;
	}
	public void setIdGenero(int idGenero) {
		this.idGenero = idGenero;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getEstreno() {
		return estreno;
	}
	public void setEstreno(String estreno) {
		this.estreno = estreno;
	}
	public String getSinopsis() {
		return sinopsis;
	}
	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}
	
}
