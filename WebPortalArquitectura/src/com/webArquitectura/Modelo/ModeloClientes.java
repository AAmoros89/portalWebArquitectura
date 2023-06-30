package com.webArquitectura.Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import javax.sql.DataSource;
import com.webArquitectura.Usuario.Clientes;

/**
 * Clase donde se definen todas los metodos necesarios para establecer conexion
 * con la base de datos, concretamente con la tabla "Cliente7".
 * 
 * @author aamor
 *
 */
public class ModeloClientes {

	private DataSource origenDatos;

	public ModeloClientes(DataSource origenDatos) {

		this.origenDatos = origenDatos;
	}

	/**
	 * Constructor vacio de la clase que se utiliza para llamar a los metodos "obtenerUsuario" y "obtenercontrasenaUsuario".
	 */
	public ModeloClientes() {
	}

	/**
	 * Metodo que selecciona de la tabla "cliente7" todos los registros y los añade a la lista.
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Clientes> getClientes() throws Exception {

		List<Clientes> clientes = new ArrayList<>();
		Connection miConexion = null;
		Statement miStatement = null;
		ResultSet miResultset = null;

		// establecer la conexion
		miConexion = origenDatos.getConnection();

		// crear la sentencia sql
		String instruccion = "SELECT * FROM CLIENTE7";

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

			Clientes temCliente = new Clientes(id, nombre, apellido, calle, ciudad, usuario, contrasena);

			clientes.add(temCliente);

		}
		return clientes;
	}

	/**
	 * Metodo que agrega un nuevo cliente a la base de datos.
	 * 
	 * @param nuevoCliente
	 * @throws Exception
	 */
	public void agregarElNuevoCliente(Clientes nuevoCliente) throws Exception {

		Connection miConexion = null;
		PreparedStatement miStatement = null;

		// obtener la conexion con la base de datos
		try {
			miConexion = origenDatos.getConnection();

			// crear la instruccion Sql que inserte el proyecto
			String sql = "INSERT INTO CLIENTE7 (ID,FIRSTNAME,LASTNAME,STREET,CITY,USUARIO,PASSWORD) VALUES(?,?,?,?,?,?,?)";

			miStatement = miConexion.prepareStatement(sql);

			// establecer los parametros para el proyecto
			miStatement.setInt(1, nuevoCliente.getId());
			miStatement.setString(2, nuevoCliente.getNombre());
			miStatement.setString(3, nuevoCliente.getApellido());
			miStatement.setString(4, nuevoCliente.getCalle());
			miStatement.setString(5, nuevoCliente.getCiudad());
			miStatement.setString(6, nuevoCliente.getUsuario());
			miStatement.setString(7, nuevoCliente.getcontrasena());

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
	 * Metodo que selecciona de la tabla "cliente7" de la base de datos segun su id.
	 * 
	 * @param idCliente
	 * @return
	 */
	public Clientes getCliente(int idCliente) {

		Clientes elCliente = null;
		Connection miConexion = null;
		PreparedStatement miStatement = null;
		ResultSet miResultset = null;

		int codCliente = idCliente;

		try {
			// establecer la conexion con la base de datos
			miConexion = origenDatos.getConnection();

			// crear el sql que busque el cliente con el codigo del cliente que se le haya
			// enviado
			String sql = "SELECT * FROM CLIENTE7 WHERE ID=?";
			// crear la consulta preparada
			miStatement = miConexion.prepareStatement(sql);
			// establecer los parametros
			miStatement.setInt(1, codCliente); // en la primera posicion del ? de arriba va el codigo cliente
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

				elCliente = new Clientes(id, nombre, apellido, calle, ciudad, usuario, contrasena);

			} else {

				throw new Exception("No se ha encontrado al cliente con el codigo= " + codCliente);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return elCliente;
	}

	/**
	 * Metodo que actualiza un cliente de la tabla "cliente7" de la base de datos segun su id.
	 * 
	 * @param clienteActualizado
	 * @throws Exception
	 */
	public void actualizarCliente(Clientes clienteActualizado) throws Exception {
		Connection miConexion = null;
		PreparedStatement miStatement = null;
		// establecer la conexion con la base de datos
		try {
			miConexion = origenDatos.getConnection();
			// crear la sentencia sql
			String sql = "UPDATE CLIENTE7 SET FIRSTNAME=?, LASTNAME=?, STREET=?, CITY=?, USUARIO=?, PASSWORD=? WHERE ID=?";
			// crear la consulta preparada
			miStatement = miConexion.prepareStatement(sql);
			// establecer los parametros
			miStatement.setString(1, clienteActualizado.getNombre());
			miStatement.setString(2, clienteActualizado.getApellido());
			miStatement.setString(3, clienteActualizado.getCalle());
			miStatement.setString(4, clienteActualizado.getCiudad());
			miStatement.setString(5, clienteActualizado.getUsuario());
			miStatement.setString(6, clienteActualizado.getcontrasena());
			miStatement.setInt(7, clienteActualizado.getId());
			// ejecutar la instruccion sql
			miStatement.execute();
		} finally { // para asegurarse de que se va a ejectuar siempre al finalizar
			miStatement.close();
			miConexion.close();
		}
	}

	/**
	 * Metodo que elimina un cliente de la base de datos.
	 * 
	 * @param codCliente
	 * @throws Exception
	 */
	public void eliminarCliente(int codCliente) throws Exception {
		Connection miConexion = null;
		PreparedStatement miStatement = null;
		// establecer la conexion con la base de datos
		try {
			miConexion = origenDatos.getConnection();
			// crear la instruccion sql de eliminacion
			String sql = "DELETE FROM CLIENTE7 WHERE ID=?";

			// preparar la consulta
			miStatement = miConexion.prepareStatement(sql);
			// establecer parametros de consulta
			miStatement.setInt(1, codCliente);
			// Ejecutar la sentencia sql
			miStatement.execute();
		} finally { // para asegurarse de que se va a ejectuar siempre al finalizar
			miStatement.close();
			miConexion.close();
		}
	}

	/**
	 * Metodo que selecciona el id el ultimo registro de un cliente registrado y le suma 1.
	 * 
	 * @return
	 */
	public int idIncrementable() {

		int id = 1;
		PreparedStatement ps = null;
		ResultSet rs = null;

		Conexion db = new Conexion();

		try {
			ps = db.conectar().prepareStatement("select max (p.id) +1 from cliente7 p");
			rs = ps.executeQuery();

			while (rs.next()) {
				id = rs.getInt(1);
			}

		} catch (Exception e) {

		}
		return id;
	}

	/**
	 * Metodo que selecciona el id de un usuario segun su usuario y contrasena registradas.
	 * 
	 * @param contrasena
	 * @param usuario
	 * @return
	 */
	public int idUsuarioAutentificado(String contrasena, String usuario) {

		int id = 1;
		PreparedStatement ps = null;
		ResultSet rs = null;

		Conexion db = new Conexion();

		try {
			ps = db.conectar().prepareStatement(
					"select id from cliente7 where usuario='" + usuario + "' and password='" + contrasena + "'");

			// establecer los parametros
			rs = ps.executeQuery();

			while (rs.next()) {

				id = rs.getInt(1);
			}

		} catch (Exception e) {

		}
		return id;
	}

	/**
	 * Metodo que obtiene una contrasena de la tabla cliente7 segun el usuario registrado en el sistema.
	 * 
	 * @param usuario
	 * @return
	 */
	public String obtenercontrasenaUsuario(int usuario) {

		String contrasena = "";
		PreparedStatement ps = null;
		ResultSet rs = null;

		Conexion db = new Conexion();

		try {
			ps = db.conectar().prepareStatement("select password from cliente7 where id=" + usuario + "");

			// establecer los parametros
			rs = ps.executeQuery();

			while (rs.next()) {

				contrasena = rs.getString(1);
			}

		} catch (Exception e) {

		}
		return contrasena;
	}

	/**
	 * Metodo que obtiene el nombre de usuario segun el id registrado.
	 * 
	 * @param id
	 * @return
	 */
	public String obtenerUsuario(int id) {

		String contrasena = "";
		PreparedStatement ps = null;
		ResultSet rs = null;

		Conexion db = new Conexion();

		try {
			ps = db.conectar().prepareStatement("select usuario from cliente7 where id=" + id + "");

			// establecer los parametros
			rs = ps.executeQuery();

			while (rs.next()) {

				contrasena = rs.getString(1);
			}

		} catch (Exception e) {

		}
		return contrasena;
	}
}
