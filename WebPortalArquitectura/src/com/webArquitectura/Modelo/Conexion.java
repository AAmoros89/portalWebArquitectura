package com.webArquitectura.Modelo;

import java.sql.*;

/**
 * Clase que permite conectarse a la base de datos, especificando el contralador y la url.
 * 
 * @author aamor
 */
public class Conexion {

	private static final String CONTROLADOR = "org.hsqldb.jdbcDriver";
	private static final String URL = "jdbc:hsqldb:hsql://localhost/";

	/**
	 * Metodo que devuelve la conexion a la base de datos.
	 * 
	 * @return
	 */
	public Connection conectar() {

		Connection conexion = null;

		try {
			try {
				Class.forName(CONTROLADOR);
				conexion = DriverManager.getConnection(URL);
				System.out.println("Conexión lista");
				
			} catch (ClassNotFoundException e) {
				System.out.println("Conexión erronea");
				e.printStackTrace();
			}
		} catch (SQLException e) {
			System.out.println("Conexión erronea");
		}
		return conexion;
	}
}
