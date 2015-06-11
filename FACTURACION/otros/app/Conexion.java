package app;



import java.sql.*;
import java.util.logging.*;

public class Conexion 
{
	private Connection conexionActual = null;
		 
	public Connection getConection() 
	{
		if (conexionActual == null) 
		{
			try 
			{
				Driver driver = new com.mysql.jdbc.Driver();
				DriverManager.registerDriver(driver);
				// El el nombre de la base de datos es 'agenda', 'root' es el administrador de la BD, '' sin contrase�a
				conexionActual = DriverManager.getConnection("jdbc:mysql://localhost:3306/facturacion", "root", "");
		    }
		    catch (SQLException ex) 
		    {
		    	//ex.printStackTrace();
		    	//Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
		    	return null;
		    }
		 }
	    return conexionActual;
	}

}
