package com.webArquitectura.Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import com.webArquitectura.Usuario.Arquitecto;

/**
 * Clase donde se definen todas los metodos necesarios para establecer conexion
 * con la base de datos, concretamente con la tabla "Arquitecto".
 * 
 * @author aamor
 *
 */
public class ModeloArquitectos {

	private DataSource origenDatos;

	public ModeloArquitectos(DataSource origenDatos) {

		this.origenDatos = origenDatos;
	}

	public ModeloArquitectos() {
	}

	/**
	 * Metodo que devuelve la lista de arquitectos haciendo una consulta a la base
	 * de datos.
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Arquitecto> getArquitectos() throws Exception {

		List<Arquitecto> arquitectos = new ArrayList<>();
		Connection miConexion = null;
		Statement miStatement = null;
		ResultSet miResultset = null;

		// establecer la conexion
		miConexion = origenDatos.getConnection();

		// crear la sentencia sql
		String instruccion = "SELECT * FROM ARQUITECTO";

		miStatement = miConexion.createStatement();

		// ejecutar la instruccion sql
		miResultset = miStatement.executeQuery(instruccion);

		while (miResultset.next()) {

			int id = miResultset.getInt(1);
			String nombre = miResultset.getString(2);
			String apellido = miResultset.getString(3);
			String calle = miResultset.getString(4);
			String ciudad = miResultset.getString(5);
			String usuario = miResultset.getString(6);
			String contrasena = miResultset.getString(7);

			Arquitecto temArquitecto = new Arquitecto(id, nombre, apellido, calle, ciudad, usuario, contrasena);

			arquitectos.add(temArquitecto);
		}
		return arquitectos;
	}

	/**
	 * Metodo que inserta un arquitecto a tabla Arquitectos de la base de datos.
	 * 
	 * @return
	 * @throws Exception
	 */
	public void agregarElNuevoArquitecto(Arquitecto nuevoArquitecto) throws Exception {

		Connection miConexion = null;
		PreparedStatement miStatement = null;

		// obtener la conexion con la base de datos
		try {
			miConexion = origenDatos.getConnection();
			// crear la instruccion Sql que inserte el proyecto
			String sql = "INSERT INTO ARQUITECTO (ID,FIRSTNAME,LASTNAME,STREET,CITY,USUARIO,PASSWORD) VALUES(?,?,?,?,?,?,?)";

			miStatement = miConexion.prepareStatement(sql);

			// establecer los parametros para el proyecto
			miStatement.setInt(1, nuevoArquitecto.getId());
			miStatement.setString(2, nuevoArquitecto.getNombre());
			miStatement.setString(3, nuevoArquitecto.getApellido());
			miStatement.setString(4, nuevoArquitecto.getCalle());
			miStatement.setString(5, nuevoArquitecto.getCiudad());
			miStatement.setString(6, nuevoArquitecto.getUsuario());
			miStatement.setString(7, nuevoArquitecto.getcontrasena());

			// ejecutar la instruccion sql
			miStatement.execute();

		} catch (Exception e) {
			e.printStackTrace();

		} finally { // para asegurarse de que se va a ejectuar siempre al finalizar
			miStatement.close();
			miConexion.close();
		}
	}

	/**
	 * Metodo que selecciona un arquitecto de la tabla "Arquitecto" de la base de datos.
	 * 
	 * @param idArquitecto
	 * @return
	 */
	public Arquitecto getArquitecto(int idArquitecto) {

		Arquitecto elArquitecto = null;
		Connection miConexion = null;
		PreparedStatement miStatement = null;
		ResultSet miResultset = null;

		int codArquitecto = idArquitecto;

		try {
			// establecer la conexion con la base de datos
			miConexion = origenDatos.getConnection();

			// crear el sql que busque el cliente con el codigo del cliente que se le haya
			// enviado
			String sql = "SELECT * FROM ARQUITECTO WHERE ID=?";
			// crear la consulta preparada
			miStatement = miConexion.prepareStatement(sql);
			// establecer los parametros
			miStatement.setInt(1, codArquitecto); // en la primera posicion del ? de arriba va el codigo cliente
			// Ejecutar la consulta
			miResultset = miStatement.executeQuery();
			// obtener los datos de respuesta
			if (miResultset.next()) {

				int id = miResultset.getInt(1);
				String nombre = miResultset.getString(2);
				String apellido = miResultset.getString(3);
				String calle = miResultset.getString(4);
				String ciudad = miResultset.getString(5);
				String usuario = miResultset.getString(6);
				String contrasena = miResultset.getString(7);

				elArquitecto = new Arquitecto(id, nombre, apellido, calle, ciudad, usuario, contrasena);

			} else {

				throw new Exception("No se ha encontrado al cliente con el codigo= " + codArquitecto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return elArquitecto;
	}

	/**
	 * Metodo que actualiza los campos de la tabla de arquitectos de la base de datos.
	 * 
	 * @param arquitectoActualizado
	 * @throws Exception
	 */
	public void actualizarArquitecto(Arquitecto arquitectoActualizado) throws Exception {
		Connection miConexion = null;
		PreparedStatement miStatement = null;
		// establecer la conexion con la base de datos
		try {
			miConexion = origenDatos.getConnection();
			// crear la sentencia sql
			String sql = "UPDATE ARQUITECTO SET FIRSTNAME=?, LASTNAME=?, STREET=?, CITY=?, USUARIO=?, PASSWORD=? WHERE ID=?";
			// crear la consulta preparada
			miStatement = miConexion.prepareStatement(sql);
			// establecer los parametros
			miStatement.setString(1, arquitectoActualizado.getNombre());
			miStatement.setString(2, arquitectoActualizado.getApellido());
			miStatement.setString(3, arquitectoActualizado.getCalle());
			miStatement.setString(4, arquitectoActualizado.getCiudad());
			miStatement.setString(5, arquitectoActualizado.getUsuario());
			miStatement.setString(6, arquitectoActualizado.getcontrasena());
			miStatement.setInt(7, arquitectoActualizado.getId());
			// ejecutar la instruccion sql
			miStatement.execute();
		} finally { // para asegurarse de que se va a ejectuar siempre al finalizar
			miStatement.close();
			miConexion.close();
		}
	}

	/**
	 * Metodo que elimina un arquitecto de la base de datos dado un id.
	 * 
	 * @param codArquitecto
	 * @throws Exception
	 */
	public void eliminarArquitecto(int codArquitecto) throws Exception {
		Connection miConexion = null;
		PreparedStatement miStatement = null;
		// establecer la conexion con la base de datos
		try {
			miConexion = origenDatos.getConnection();
			// crear la instruccion sql de eliminacion
			String sql = "DELETE FROM ARQUITECTO WHERE ID=?";

			// preparar la consulta
			miStatement = miConexion.prepareStatement(sql);
			// establecer parametros de consulta
			miStatement.setInt(1, codArquitecto);
			// Ejecutar la sentencia sql
			miStatement.execute();
		} finally { // para asegurarse de que se va a ejectuar siempre al finalizar
			miStatement.close();
			miConexion.close();

		}
	}

	/**
	 * Metodo que selecciona el ultimo id de un arquitecto registrado y le suma 1.
	 * 
	 * @return
	 */
	public int idArquitectoIncrementable() {

		int id = 1;
		PreparedStatement ps = null;
		ResultSet rs = null;

		Conexion db = new Conexion();

		try {
			ps = db.conectar().prepareStatement("select max (p.id) +1 from arquitecto p");
			rs = ps.executeQuery();

			while (rs.next()) {

				id = rs.getInt(1);
			}
		} catch (Exception e) {

		}
		return id;
	}

	/**
	 * Metodo que selecciona el id de un arquitecto segun la contrasena y el usuario regristrado.
	 * 
	 * @param contrasena
	 * @param usuario
	 * @return
	 */
	public int idArquitectoAutentificado(String contrasena, String usuario) {

		int id = 1;
		PreparedStatement ps = null;
		ResultSet rs = null;

		Conexion db = new Conexion();

		try {
			ps = db.conectar().prepareStatement(
					"select id from arquitecto where usuario='" + usuario + "' and password='" + contrasena + "'");

			// establecer los parametros
			rs = ps.executeQuery();

			while (rs.next()) {

				id = rs.getInt(1);
			}
		} catch (Exception e) {

		}
		return id;
	}
}
