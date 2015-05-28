package app;

public class Usuario {
	//table usuarios
	//*id, nombre, *password, estado, *administrador, *email
	
	private int id;
	private String username;
	private String email;
	private String password;
	private boolean isAdmin;
	private int status;
	
	public Usuario(int id) {
		this.id = id;
	}

	public Usuario(int id, String username, String email, String password,
			boolean isAdmin, int status) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.isAdmin = isAdmin;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
}
