package com.webArquitectura.Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

import com.webArquitectura.Artefacto.Certificado;

/**
 * Clase donde se definen todas los metodos necesarios para establecer conexion
 * con la base de datos, concretamente con la tabla "Certificado".
 * 
 * @author aamor
 *
 */
public class ModeloCertificados {

	private DataSource origenDatos;

	public ModeloCertificados(DataSource origenDatos) {

		this.origenDatos = origenDatos;
	}

	public ModeloCertificados() {
	}

	/**
	 * Metodo que selecciona de la tabla "certificado", los que esten asociados a un
	 * arquitecto segun su id.
	 * 
	 * @param arquitectoId
	 * @return
	 * @throws Exception
	 */
	public List<Certificado> getCertificadosArquitecto(int arquitectoId) throws Exception {

		List<Certificado> certificados = new ArrayList<>();
		Connection miConexion = null;
		Statement miStatement = null;
		ResultSet miResultset = null;

		// establecer la conexion
		miConexion = origenDatos.getConnection();

		// crear la sentencia sql
		String instruccion = "select  *  from certificado where idArquitecto=" + arquitectoId;
		miStatement = miConexion.createStatement();

		// ejecutar la instruccion sql
		miResultset = miStatement.executeQuery(instruccion);

		while (miResultset.next()) {

			int id = miResultset.getInt(1);
			int idUsuario = miResultset.getInt(2);
			String fechaSolicitud = miResultset.getString(3);
			int idArquitecto = miResultset.getInt(4);
			String tipo = miResultset.getString(5);
			String direccion = miResultset.getString(6);
			String fechaVisita = miResultset.getString(7);
			int presupuesto = miResultset.getInt(8);
			String fechaEmision = miResultset.getString(9);
			String categoria = miResultset.getString(10);
			String estado = miResultset.getString(11);

			Certificado temCertificado = new Certificado(id, idUsuario, fechaSolicitud, idArquitecto, tipo, direccion,
					fechaVisita, presupuesto, fechaEmision, categoria, estado); // se le llama al constructor con codigo

			certificados.add(temCertificado);
		}
		return certificados;
	}

	/**
	 * Metodo que lista los certificados de cada cliente segun su id que se le pasa
	 * por parametro
	 * 
	 * @param clienteId
	 * @return
	 * @throws Exception
	 */
	public List<Certificado> getCertificadosClientes(int clienteId) throws Exception { // se le pasa un parametro int
																						// para que saque por pantalla
																						// los usuarios de ese int
		List<Certificado> certificados = new ArrayList<>();
		Connection miConexion = null;
		Statement miStatement = null;
		ResultSet miResultset = null;

		// establecer la conexion
		miConexion = origenDatos.getConnection();

		// crear la sentencia sql
		String instruccion = "select  *  from certificado where idUsuario=" + clienteId;

		miStatement = miConexion.createStatement();

		// ejecutar la instruccion sql
		miResultset = miStatement.executeQuery(instruccion);

		while (miResultset.next()) {

			int id = miResultset.getInt(1);
			int idUsuario = miResultset.getInt(2);
			String fechaSolicitud = miResultset.getString(3);
			String tipo = miResultset.getString(5);
			String direccion = miResultset.getString(6);
			String fechaVisita = miResultset.getString(7);
			String estado = miResultset.getString(11);

			Certificado temCertificado = new Certificado(id, idUsuario, fechaSolicitud, tipo, direccion, fechaVisita,
					estado);

			certificados.add(temCertificado);
		}
		return certificados;
	}

	/**
	 * Metodo para agregar un certificado a un cliente ya registrado
	 * 
	 * @param nuevoCliente
	 * @throws Exception
	 */
	public void agregarElNuevoCertificado(Certificado nuevoCertificado) throws Exception {

		Connection miConexion = null;
		PreparedStatement miStatement = null;

		// obtener la conexion con la base de datos
		try {
			miConexion = origenDatos.getConnection();
			// crear la instruccion Sql que inserte el proyecto
			String sql = "INSERT INTO CERTIFICADO (ID, IDUSUARIO, FECHASOLICITUD, TIPO, DIRECCION) VALUES(?,?,?,?,?)";
			miStatement = miConexion.prepareStatement(sql);

			// establecer los parametros para el proyecto
			miStatement.setInt(1, nuevoCertificado.getId());
			miStatement.setInt(2, nuevoCertificado.getIdUsuario());
			miStatement.setString(3, nuevoCertificado.getFechaSolicitud());
			miStatement.setString(4, nuevoCertificado.getTipo());
			miStatement.setString(5, nuevoCertificado.getDireccion());
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
	 * Metodo que elimina un certificado de la base de datos segun su id.
	 * 
	 * @param codCertificado
	 * @throws Exception
	 */
	public void eliminarCertificado(int codCertificado) throws Exception {
		Connection miConexion = null;
		PreparedStatement miStatement = null;
		// establecer la conexion con la base de datos
		try {
			miConexion = origenDatos.getConnection();
			// crear la instruccion sql de eliminacion
			String sql = "DELETE FROM CERTIFICADO WHERE ID=?";

			// preparar la consulta
			miStatement = miConexion.prepareStatement(sql);
			// establecer parametros de consulta
			miStatement.setInt(1, codCertificado);
			// Ejecutar la sentencia sql
			miStatement.execute();
		} finally { // para asegurarse de que se va a ejectuar siempre al finalizar
			miStatement.close();
			miConexion.close();
		}
	}

	/**
	 * Metodo que lista todos los certificados que esten registrados en la tabla
	 * "certificados".
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Certificado> getCertificados() throws Exception {

		List<Certificado> certificados = new ArrayList<>();
		Connection miConexion = null;
		Statement miStatement = null;
		ResultSet miResultset = null;

		// establecer la conexion
		miConexion = origenDatos.getConnection();

		// crear la sentencia sql
		String instruccion = "SELECT * FROM CERTIFICADO";
		miStatement = miConexion.createStatement();

		// ejecutar la instruccion sql
		miResultset = miStatement.executeQuery(instruccion);

		while (miResultset.next()) {

			int id = miResultset.getInt(1);
			int idUsuario = miResultset.getInt(2);
			String fechaSolicitud = miResultset.getString(3);
			int idArquitecto = miResultset.getInt(4);
			String tipo = miResultset.getString(5);
			String direccion = miResultset.getString(6);
			String fechaVisita = miResultset.getString(7);
			int presupuesto = miResultset.getInt(8);
			String fechaEmision = miResultset.getString(9);
			String categoria = miResultset.getString(10);
			String estado = miResultset.getString(11);

			Certificado temCertificado = new Certificado(id, idUsuario, fechaSolicitud, idArquitecto, tipo, direccion,
					fechaVisita, presupuesto, fechaEmision, categoria, estado);
			certificados.add(temCertificado);
		}
		return certificados;
	}

	/**
	 * Metodo selecciona un certificado de la tabla "certificado" de la base de
	 * datos segun su id.
	 * 
	 * @param idCertificado
	 * @return
	 */
	public Certificado getCertificado(int idCertificado) {
		Certificado elCertificado = null;
		Connection miConexion = null;
		PreparedStatement miStatement = null;
		ResultSet miResultset = null;

		int codCertificado = idCertificado;

		try {
			// establecer la conexion con la base de datos
			miConexion = origenDatos.getConnection();

			// crear el sql que busque el cliente con el codigo del cliente que se le haya
			// enviado
			String sql = "SELECT * FROM CERTIFICADO WHERE ID=?";
			// crear la consulta preparada
			miStatement = miConexion.prepareStatement(sql);
			// establecer los parametros
			miStatement.setInt(1, codCertificado); // en la primera posicion del ? de arriba va el codigo cliente
			// Ejecutar la consulta
			miResultset = miStatement.executeQuery();
			// obtener los datos de respuesta
			if (miResultset.next()) {

				int id = miResultset.getInt(1);
				int idUsuario = miResultset.getInt(2);
				String fecha = miResultset.getString(3);
				int idArquitecto = miResultset.getInt(4);
				String tipo = miResultset.getString(5);
				String direccion = miResultset.getString(6);
				String fechaVisita = miResultset.getString(7);
				int presupuesto = miResultset.getInt(8);
				String fechaEmision = miResultset.getString(9);
				String categoria = miResultset.getString(10);
				String estado = miResultset.getString(11);

				elCertificado = new Certificado(id, idUsuario, fecha, idArquitecto, tipo, direccion, fechaVisita,
						presupuesto, fechaEmision, categoria, estado);
			} else {

				throw new Exception("No se ha encontrado al cliente con el codigo= " + codCertificado);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return elCertificado;
	}

	/**
	 * Metodo que selecciona un certificado de la tabla "certificado" de la base de
	 * datos segun su id.
	 * 
	 * @param idCertificado
	 * @return
	 */
	public Certificado getPresupCertificado(int idCertificado) {
		Certificado elCertificado = null;
		Connection miConexion = null;
		PreparedStatement miStatement = null;
		ResultSet miResultset = null;

		int codCertificado = idCertificado;

		try {
			// establecer la conexion con la base de datos
			miConexion = origenDatos.getConnection();

			// crear el sql que busque el cliente con el codigo del cliente que se le haya
			// enviado
			String sql = "SELECT * FROM CERTIFICADO WHERE ID=?";
			// crear la consulta preparada
			miStatement = miConexion.prepareStatement(sql);
			// establecer los parametros
			miStatement.setInt(1, codCertificado); // en la primera posicion del ? de arriba va el codigo cliente
			// Ejecutar la consulta
			miResultset = miStatement.executeQuery();
			// obtener los datos de respuesta
			if (miResultset.next()) {

				int id = miResultset.getInt(1);
				int idUsuario = miResultset.getInt(2);
				String fecha = miResultset.getString(3);
				int idArquitecto = miResultset.getInt(4);
				String tipo = miResultset.getString(5);
				String direccion = miResultset.getString(6);
				String fechaVisita = miResultset.getString(7);
				int presupuesto = miResultset.getInt(8);
				String fechaEmision = miResultset.getString(9);
				String categoria = miResultset.getString(10);
				String estado = miResultset.getString(11);

				elCertificado = new Certificado(id, idUsuario, fecha, idArquitecto, tipo, direccion, fechaVisita,
						presupuesto, fechaEmision, categoria, estado);

			} else {

				throw new Exception("No se ha encontrado al cliente con el codigo= " + codCertificado);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return elCertificado;
	}

	/**
	 * Metodo que muestra el certificado segun su id.
	 * 
	 * @param idCertificado
	 * @return
	 */
	public Certificado getEmitirCertificado(int idCertificado) {
		Certificado elCertificado = null;
		Connection miConexion = null;
		PreparedStatement miStatement = null;
		ResultSet miResultset = null;

		int codCertificado = idCertificado;

		try {
			// establecer la conexion con la base de datos
			miConexion = origenDatos.getConnection();
			// crear el sql que busque el cliente con el codigo del cliente que se le haya
			// enviado
			String sql = "SELECT * FROM CERTIFICADO WHERE ID=?";
			// crear la consulta preparada
			miStatement = miConexion.prepareStatement(sql);
			// establecer los parametros
			miStatement.setInt(1, codCertificado); // en la primera posicion del ? de arriba va el codigo cliente
			// Ejecutar la consulta
			miResultset = miStatement.executeQuery();
			// obtener los datos de respuesta
			if (miResultset.next()) {

				int id = miResultset.getInt(1);
				int idUsuario = miResultset.getInt(2);
				String fecha = miResultset.getString(3);
				int idArquitecto = miResultset.getInt(4);
				String tipo = miResultset.getString(5);
				String direccion = miResultset.getString(6);
				String fechaVisita = miResultset.getString(7);
				int presupuesto = miResultset.getInt(8);
				String fechaEmision = miResultset.getString(9);
				String categoria = miResultset.getString(10);
				String estado = miResultset.getString(11);

				elCertificado = new Certificado(id, idUsuario, fecha, idArquitecto, tipo, direccion, fechaVisita,
						presupuesto, fechaEmision, categoria, estado); // se le llama al constructor con codigo articulo

			} else {
				throw new Exception("No se ha encontrado al cliente con el codigo= " + codCertificado);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return elCertificado;
	}

	/**
	 * Metodo que actualiza un certificado segun su id.
	 * 
	 * @param certificadoActualizado
	 * @throws Exception
	 */
	public void actualizarCertificado(Certificado certificadoActualizado) throws Exception {
		Connection miConexion = null;
		PreparedStatement miStatement = null;
		// establecer la conexion con la base de datos
		try {
			miConexion = origenDatos.getConnection();
			// crear la sentencia sql
			String sql = "UPDATE CERTIFICADO SET IDARQUITECTO=?, ESTADO=? WHERE ID=?";
			// crear la consulta preparada
			miStatement = miConexion.prepareStatement(sql);
			// establecer los parametros
			miStatement.setInt(1, certificadoActualizado.getIdArquitecto());
			miStatement.setString(2, certificadoActualizado.getEstado());
			miStatement.setInt(3, certificadoActualizado.getId());
			// ejecutar la instruccion sql
			miStatement.execute();
		} finally { // para asegurarse de que se va a ejectuar siempre al finalizar
			miStatement.close();
			miConexion.close();
		}

	}

	/**
	 * Metodo que actualiza un certificado de un arquitecto segun su id.
	 * 
	 * @param certificadoActualizado
	 * @throws Exception
	 */
	public void actualizarCertificadoArq(Certificado certificadoActualizado) throws Exception {
		Connection miConexion = null;
		PreparedStatement miStatement = null;
		// establecer la conexion con la base de datos
		try {
			miConexion = origenDatos.getConnection();
			// crear la sentencia sql
			String sql = "UPDATE CERTIFICADO SET FECHAVISITA=?, ESTADO=? WHERE ID=?";
			// crear la consulta preparada
			miStatement = miConexion.prepareStatement(sql);
			// establecer los parametros
			miStatement.setString(1, certificadoActualizado.getFechaVisita());
			miStatement.setString(2, certificadoActualizado.getEstado());
			miStatement.setInt(3, certificadoActualizado.getId());
			// ejecutar la instruccion sql
			miStatement.execute();
		} finally { // para asegurarse de que se va a ejectuar siempre al finalizar
			miStatement.close();
			miConexion.close();
		}
	}

	/**
	 * Metodo que actualiza el presupuesto y el estado de un certificado segun el
	 * id.
	 * 
	 * @param certificadoActualizado
	 * @throws Exception
	 */
	public void actualizarPresupCertificadoArq(Certificado certificadoActualizado) throws Exception {
		Connection miConexion = null;
		PreparedStatement miStatement = null;
		// establecer la conexion con la base de datos
		try {
			miConexion = origenDatos.getConnection();
			// crear la sentencia sql
			String sql = "UPDATE CERTIFICADO SET PRESUPUESTO=?, ESTADO=? WHERE ID=?";
			// crear la consulta preparada
			miStatement = miConexion.prepareStatement(sql);
			// establecer los parametros
			miStatement.setInt(1, certificadoActualizado.getPresupuesto());
			miStatement.setString(2, certificadoActualizado.getEstado());
			miStatement.setInt(3, certificadoActualizado.getId());
			// ejecutar la instruccion sql
			miStatement.execute();
		} finally { // para asegurarse de que se va a ejectuar siempre al finalizar
			miStatement.close();
			miConexion.close();
		}
	}

	/**
	 * Metodo que actualiza la fecha de emision, categoria y estado de un
	 * certificado segun un id.
	 * 
	 * @param certificadoActualizado
	 * @throws Exception
	 */
	public void actualizarEmitirCertificadoArq(Certificado certificadoActualizado) throws Exception {
		Connection miConexion = null;
		PreparedStatement miStatement = null;
		// establecer la conexion con la base de datos
		try {
			miConexion = origenDatos.getConnection();
			// crear la sentencia sql
			String sql = "UPDATE CERTIFICADO SET FECHAEMISION=?, CATEGORIA=?, ESTADO=? WHERE ID=?";
			// crear la consulta preparada
			miStatement = miConexion.prepareStatement(sql);
			// establecer los parametros
			miStatement.setString(1, certificadoActualizado.getFechaEmision());
			miStatement.setString(2, certificadoActualizado.getCategoria());
			miStatement.setString(3, certificadoActualizado.getEstado());
			miStatement.setInt(4, certificadoActualizado.getId());
			// ejecutar la instruccion sql
			miStatement.execute();
		} finally { // para asegurarse de que se va a ejectuar siempre al finalizar
			// miStatement.close();
			miConexion.close();
		}
	}

	/**
	 * Metodo que selecciona el id del ultimo certificado registrado y le suma 1.
	 * 
	 * @return
	 */
	public int idCertificadoIncrementable() {

		int id = 1;
		PreparedStatement ps = null;
		ResultSet rs = null;

		Conexion db = new Conexion();

		try {
			ps = db.conectar().prepareStatement("select max (p.id) +1 from certificado p");
			rs = ps.executeQuery();

			while (rs.next()) {

				id = rs.getInt(1);
			}

		} catch (Exception e) {
		}
		return id;
	}
}