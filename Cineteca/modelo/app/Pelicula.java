package app;

public class Pelicula {
	//peliculas
	//*`id`, *`titulo`, `duracion`, *`idGen`, `director`, `estreno`, `sinopsis`
	//generos
	//`idGen`, `genero`
	
//	CLASE PELICULA
	private int id;
	private String titulo;
	private Integer duracion;
	private int idGenero;
	private String director;
	private String estreno;
	private String sinopsis;
	private String caratula;
	
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
			String director, String estreno, String sinopsis, String caratula) {
		this.id = id;
		this.titulo = titulo;
		this.duracion = duracion;
		this.idGenero = idGenero;
		this.director = director;
		this.estreno = estreno;
		this.sinopsis = sinopsis;
		this.caratula = caratula;
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

	public String getCaratula() {
		return caratula;
	}

	public void setCaratula(String caratula) {
		this.caratula = caratula;
	}
	
}
