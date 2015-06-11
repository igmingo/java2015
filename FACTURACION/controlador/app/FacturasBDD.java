package app;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

public class FacturasBDD {
	// TABLA facturas BASE DE DATOS
//	id int(10) UNSIGNED No auto_increment
//	clienteId int(10) UNSIGNED No clientes -> id
//	numero int(11) No
//	fecha date No
//	porcDescuento double No
//	porcRecargoEquivalencia	double No
//	impTotal double No
//	impRecargo double No
//	impIva double No
//	dirCorreo mediumtext No
//	dirFactura mediumtext No
//	dirEnvio mediumtext No
//	cobrada tinyint(1) No
	
	//METODO PUBLICO
	
	public ArrayList<Factura> recuperaPorFiltro(String filtro) {
		String sql = "SELECT * FROM facturas " + filtro
				+ " ORDER BY facturas.numero";
		System.out.println(sql);
		ArrayList<Factura> lista = new ArrayList<>();
		Connection c = new Conexion().getConection();
		if (c != null) {
			try {
				// Crea un ESTAMENTO (comando de ejecucion de un sql)
				Statement comando = c.createStatement();
				ResultSet rs = comando.executeQuery(sql);
				while (rs.next() == true) {
					int id = rs.getInt("id");
					int clientes = rs.getInt("clienteId");
					int numero = rs.getInt("numero");
					Date fecha = rs.getDate("fecha");
					double porcDescuento = rs.getDouble("porcDescuento");
					double porcRecargoEquivalencia = rs.getDouble("porcRecargoEquivalencia");
					double impTotal = rs.getDouble("impTotal");
					double impRecargo = rs.getDouble("impRecargo");
					double impIva = rs.getDouble("impIva");
					String dirCorreo = rs.getString("dirCorreo");
					String dirFactura = rs.getString("dirFactura");
					String dirEnvio = rs.getString("dirEnvio");
					boolean cobrada = rs.getBoolean("cobrada");
					lista.add(new Factura(id, clientes, numero, fecha, porcDescuento, porcRecargoEquivalencia, impTotal, impRecargo, impIva, dirCorreo, dirFactura, dirEnvio, cobrada));
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
	
	public ArrayList<Factura> recuperaPorNumero(String criterio) {
		String filtro = "WHERE facturas.numero LIKE '%" + criterio + "%' ";
		//filtro += "OR productos.descripcion LIKE '%" + criterio + "%'";
		return recuperaPorFiltro(filtro);
	}
	
	public ArrayList<Factura> recuperaTodos(){
		return recuperaPorFiltro("WHERE 1");
	}
	
	public Factura recuperaPorId(int id){
		if (id != 0) {
			String filtro = "WHERE facturas.id = " + id;
			ArrayList<Factura> lista = recuperaPorFiltro(filtro);
			return lista.get(0);
		} else {
			Factura p = new Factura();
			p.setId(0);
			return p;
		}
	}
	
	public int grabar(Factura fac) {
		int respuesta = -1;
		String sql = "";
		if (fac.getId()==0) {
			
//			id int(10) UNSIGNED No auto_increment
//			clienteId int(10) UNSIGNED No clientes -> id
//			numero int(11) No
//			fecha date No
//			porcDescuento double No
//			porcRecargoEquivalencia	double No
//			impTotal double No
//			impRecargo double No
//			impIva double No
//			dirCorreo mediumtext No
//			dirFactura mediumtext No
//			dirEnvio mediumtext No
//			cobrada tinyint(1) No
			
			sql = "INSERT INTO facturas SET " +
					"facturas.clienteId = " + fac.getClienteId() + ", " +
					"facturas.numero = " + fac.getNumero() + ", " +
					"facturas.fecha = '" + Utilidades.fechaToSQL(fac.getFecha()) + "', " +
					"facturas.porcDescuento = " + fac.getPorcDescuento() + ", " +
					"facturas.porcRecargoEquivalencia = " + fac.getPorcRecargoEquivalencia() + ", " +
					"facturas.impTotal = " + fac.getImpTotal() + ", " +
					"facturas.impRecargo = " + fac.getImpRecargo() + ", " +
					"facturas.impIva = " + fac.getImpIva() + ", " +
					"facturas.dirCorreo = '" + fac.getDirCorreo() + "', " +
					"facturas.dirFactura = '" + fac.getDirFactura() + "', " +
					"facturas.dirEnvio = '" + fac.getDirEnvio() + "', " +
					"facturas.cobrada = " + fac.isCobrada()
					;
		} else {
			sql = "UPDATE facturas SET " +
					"facturas.clienteId = " + fac.getClienteId() + ", " +
					"facturas.numero = " + fac.getNumero() + ", " +
					"facturas.fecha = '" + Utilidades.fechaToSQL(fac.getFecha()) + "', " +
					"facturas.porcDescuento = " + fac.getPorcDescuento() + ", " +
					"facturas.porcRecargoEquivalencia = " + fac.getPorcRecargoEquivalencia() + ", " +
					"facturas.impTotal = " + fac.getImpTotal() + ", " +
					"facturas.impRecargo = " + fac.getImpRecargo() + ", " +
					"facturas.impIva = " + fac.getImpIva() + ", " +
					"facturas.dirCorreo = '" + fac.getDirCorreo() + "', " +
					"facturas.dirFactura = '" + fac.getDirFactura() + "', " +
					"facturas.dirEnvio = '" + fac.getDirEnvio() + "', " +
					"facturas.cobrada = " + fac.isCobrada() + " " +
					"WHERE facturas.id = " + fac.getId()
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
				if (fac.getId() != 0){
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
	
	public boolean eliminar (int id) {
		boolean respuesta = false;
		String 	sql = "DELETE FROM facturas " +
				"WHERE facturas.id = " + id;
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
	
	
	//RECUPERAR TABLAS ESPECIALES
	
	
	/** Recoge los datos de la BBDD para una Jtable con Factura, Fecha, Importe Total, Cobrada
	 * @param filtro
	 * @return devuelve datos Jtabla, es decir una array de vectores, ArrayList<Vector<Object>>
	 */
	public ArrayList<Vector<Object>> recuperaTablaFacturas(String filtro) {
		ArrayList<Vector<Object>> tableData = null;
		ArrayList<Factura> lista = recuperaPorFiltro(filtro);
		tableData = new ArrayList<>();
		for (Factura factura : lista) {
			Vector<Object> filaData = new Vector<>();
			filaData.add(factura);
			filaData.add(factura.getFecha());
			filaData.add(factura.getImpTotal());
			filaData.add(factura.isCobrada());
			tableData.add(filaData);
		}
		return tableData;
	}
	
//	public ArrayList<Vector<Object>> recuperaTablaFacturas2(String filtro) {
//			// TABLA facturas BASE DE DATOS
//			//id int(10) UNSIGNED No auto_increment
//			//numero int(11) No
//			//fecha date No
//			//impTotal double No
//			//cobrada tinyint(1) No
//			ArrayList<Vector<Object>> tableData = null;
//			filtro = "WHERE facturas.numero LIKE '%" + filtro + "%'";
//			String sql = "SELECT facturas.id, facturas.numero, facturas.fecha, facturas.impTotal, facturas.cobrada FROM facturas " + filtro + " ORDER BY facturas.numero";
//			System.out.println(sql);
//			tableData = new ArrayList<>();
//			Connection c = new Conexion().getConection();
//			if (c!=null) {
//				try {
//					Statement comando = c.createStatement();
//					ResultSet rs = comando.executeQuery(sql);
//					while (rs.next() == true) {
//						//Los datos de la fila son un tipo VECTOR
//						Vector<Object> filaData = new Vector<>();
//						filaData.add(rs.getInt("id"));
//						filaData.add(rs.getInt("numero"));
//						filaData.add(rs.getDate("fecha"));
//						filaData.add(rs.getDouble("impTotal"));
//						filaData.add(rs.getBoolean("cobrada"));
//						tableData.add(filaData);
//					}
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//			try {
//				c.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//			return tableData;
//	}
	
}
