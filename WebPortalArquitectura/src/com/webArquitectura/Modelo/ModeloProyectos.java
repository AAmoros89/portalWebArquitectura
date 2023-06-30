package com.webArquitectura.Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

import com.webArquitectura.Artefacto.Proyecto;


/**
 * Clase donde se definen todas los metodos necesarios para establecer conexion
 * con la base de datos, concretamente con la tabla "Proyectos".
 * 
 * @author aamor
 *
 */
public class ModeloProyectos {
	
	private DataSource origenDatos;

	public ModeloProyectos(DataSource origenDatos) {

		this.origenDatos = origenDatos;
	}
	
	public ModeloProyectos() {	
	}
	
	/**
	 * Metodo que recoge en una lista todos los proyectos existentes en la tabla "Proyectos" de la base de datos.
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Proyecto> getProyectos() throws Exception {
		List<Proyecto> proyectos = new ArrayList<>();
		Connection miConexion = null;
		Statement miStatement = null;
		ResultSet miResultset = null;

		// establecer la conexion
		miConexion = origenDatos.getConnection();

		// crear la sentencia sql
		String instruccion = "SELECT * FROM PROYECTO";

		miStatement = miConexion.createStatement();

		// ejecutar la instruccion sql
		miResultset = miStatement.executeQuery(instruccion);

		while (miResultset.next()) {

			int id = miResultset.getInt(1);
			String nombre = miResultset.getString(2);
			int idUsuario = miResultset.getInt(3);
			String fechaSolicitud = miResultset.getString(4);
			int idArquitecto = miResultset.getInt(5);
			String duracionPrevista = miResultset.getString(6);
			String tipo = miResultset.getString(7);
			String direccion = miResultset.getString(8);
			int superficie_t = miResultset.getInt(9);
			int superficie_e = miResultset.getInt(10);
			int plantas = miResultset.getInt(11);
			int habitaciones = miResultset.getInt(12);
			int banos = miResultset.getInt(13);
			String finalidad = miResultset.getString(14);
			int superficie_r = miResultset.getInt(15);
			int presupuesto = miResultset.getInt(16);
			String fechaRealizacion = miResultset.getString(17);
			String estado = miResultset.getString(18);

			Proyecto temProyecto = new Proyecto(id, nombre, idUsuario, fechaSolicitud, idArquitecto, duracionPrevista,
					tipo, direccion, superficie_t, superficie_e, plantas, habitaciones, banos, finalidad, superficie_r,
					presupuesto, fechaRealizacion, estado); // se le llama al constructor con codigo articulo

			proyectos.add(temProyecto);

		}
		return proyectos;
	}
	
	/**
	 * Metodo que lista los proyectos de los clientes por el id que se le pasa por
	 * parametro
	 * 
	 * @param clienteId
	 * @return
	 * @throws Exception
	 */
	public List<Proyecto> getProyectosClientes(int clienteId) throws Exception { // se le pasa un parametro int para que
																					// saque por pantalla los usuarios
																					// de ese int
		List<Proyecto> proyectos = new ArrayList<>();
		Connection miConexion = null;
		Statement miStatement = null;
		ResultSet miResultset = null;

		// establecer la conexion
		miConexion = origenDatos.getConnection();

		// crear la sentencia sql
		String instruccion = "select  *  from proyecto where idUsuario=" + clienteId;

		miStatement = miConexion.createStatement();

		// ejecutar la instruccion sql
		miResultset = miStatement.executeQuery(instruccion);

		while (miResultset.next()) {

			int id = miResultset.getInt(1);
			String nombre = miResultset.getString(2);
			int idUsuario = miResultset.getInt(3);
			String fechaSolicitud = miResultset.getString(4);
			int idArquitecto = miResultset.getInt(5);
			String duracionPrevista = miResultset.getString(6);
			String tipo = miResultset.getString(7);
			String direccion = miResultset.getString(8);
			int superficie_t = miResultset.getInt(9);
			int superficie_e = miResultset.getInt(10);
			int plantas = miResultset.getInt(11);
			int habitaciones = miResultset.getInt(12);
			int banos = miResultset.getInt(13);
			String finalidad = miResultset.getString(14);
			int superficie_r = miResultset.getInt(15);
			int presupuesto = miResultset.getInt(16);
			String fechaRealizacion = miResultset.getString(17);
			String estado = miResultset.getString(18);

			Proyecto temProyecto = new Proyecto(id, nombre, idUsuario, fechaSolicitud, idArquitecto, duracionPrevista,
					tipo, direccion, superficie_t, superficie_e, plantas, habitaciones, banos, finalidad, superficie_r,
					presupuesto, fechaRealizacion, estado);

			proyectos.add(temProyecto);
		}
		return proyectos;
	}
	
	/**
	 * Metodo que lista los proyectos asignados a un arquitecto por el id que se le pasa por
	 * parametro
	 * 
	 * @param clienteId
	 * @return
	 * @throws Exception
	 */
	public List<Proyecto> getProyectosArquitecto(int arquitectoId) throws Exception { // se le pasa un parametro int
																						// para que saque por pantalla
																						// los usuarios de ese int
		List<Proyecto> proyectos = new ArrayList<>();
		Connection miConexion = null;
		Statement miStatement = null;
		ResultSet miResultset = null;

		// establecer la conexion
		miConexion = origenDatos.getConnection();

		String instruccion = "select  *  from proyecto where idArquitecto=" + arquitectoId;
		miStatement = miConexion.createStatement();

		// ejecutar la instruccion sql
		miResultset = miStatement.executeQuery(instruccion);

		while (miResultset.next()) {

			int id = miResultset.getInt(1);
			String nombre = miResultset.getString(2);
			int idUsuario = miResultset.getInt(3);
			String fechaSolicitud = miResultset.getString(4);
			int idArquitecto = miResultset.getInt(5);
			String duracionPrevista = miResultset.getString(6);
			String tipo = miResultset.getString(7);
			String direccion = miResultset.getString(8);
			int superficie_t = miResultset.getInt(9);
			int superficie_e = miResultset.getInt(10);
			int plantas = miResultset.getInt(11);
			int habitaciones = miResultset.getInt(12);
			int banos = miResultset.getInt(13);
			String finalidad = miResultset.getString(14);
			int superficie_r = miResultset.getInt(15);
			int presupuesto = miResultset.getInt(16);
			String fechaRealizacion = miResultset.getString(17);
			String estado = miResultset.getString(18);

			Proyecto temProyecto = new Proyecto(id, nombre, idUsuario, fechaSolicitud, idArquitecto, duracionPrevista,
					tipo, direccion, superficie_t, superficie_e, plantas, habitaciones, banos, finalidad, superficie_r,
					presupuesto, fechaRealizacion, estado); // se le llama al constructor con codigo articulo

			proyectos.add(temProyecto);
		}
		return proyectos;
	}
	
	/**
	 * Metodo que selecciona un proyecto de la lista de proyectos segun un id.
	 * 
	 * @param idProyecto
	 * @return
	 */
	public Proyecto getProyecto(int idProyecto) {
		Proyecto elProyecto = null;
		Connection miConexion = null;
		PreparedStatement miStatement = null;
		ResultSet miResultset = null;

		int codProyecto = idProyecto;

		try {
			// establecer la conexion con la base de datos
			miConexion = origenDatos.getConnection();

			// crear el sql que busque el cliente con el codigo del cliente que se le haya
			// enviado
			String sql = "SELECT * FROM PROYECTO WHERE ID=?";
			// crear la consulta preparada
			miStatement = miConexion.prepareStatement(sql);
			// establecer los parametros
			miStatement.setInt(1, codProyecto); // en la primera posicion del ? de arriba va el codigo proyecto
			// Ejecutar la consulta
			miResultset = miStatement.executeQuery();
			// obtener los datos de respuesta
			if (miResultset.next()) {

				int id = miResultset.getInt(1);
				String nombre = miResultset.getString(2);
				int idUsuario = miResultset.getInt(3);
				String fecha = miResultset.getString(4);
				int idArquitecto = miResultset.getInt(5);
				String duracionPrevista = miResultset.getString(6);
				String tipo = miResultset.getString(7);
				String direccion = miResultset.getString(8);
				int superficie_t = miResultset.getInt(9);
				int superficie_e = miResultset.getInt(10);
				int plantas = miResultset.getInt(11);
				int habitaciones = miResultset.getInt(12);
				int banos = miResultset.getInt(13);
				String finalidad = miResultset.getString(14);
				int superficie_r = miResultset.getInt(15);
				int presupuesto = miResultset.getInt(16);
				String fechaRealizacion = miResultset.getString(17);
				String estado = miResultset.getString(18);

				elProyecto = new Proyecto(id, nombre, idUsuario, fecha, idArquitecto, duracionPrevista, tipo, direccion,
						superficie_t, superficie_e, plantas, habitaciones, banos, finalidad, superficie_r, presupuesto,
						fechaRealizacion, estado); 
			} else {

				throw new Exception("No se ha encontrado al cliente con el codigo= " + codProyecto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return elProyecto;
	}

	/**
	 * Metodo para agregar un Proyecto a un cliente ya registrado
	 * 
	 * @param nuevoProyecto
	 * @throws Exception
	 */
	public void agregarElNuevoProyecto(Proyecto nuevoProyecto) throws Exception {
		
		Connection miConexion = null;
		PreparedStatement miStatement = null;

		// obtener la conexion con la base de datos
		try {
			miConexion = origenDatos.getConnection();

			// crear la instruccion Sql que inserte el proyecto
			String sql = "INSERT INTO PROYECTO (ID,NOMBRE,IDUSUARIO,FECHASOLICITUD,TIPO) VALUES(?,?,?,?,?)";

			miStatement = miConexion.prepareStatement(sql);

			// establecer los parametros para el proyecto
			miStatement.setInt(1, nuevoProyecto.getId());
			miStatement.setString(2, nuevoProyecto.getNombre());
			miStatement.setInt(3, nuevoProyecto.getIdUsuario());
			miStatement.setString(4, nuevoProyecto.getFecha());
			miStatement.setString(5, nuevoProyecto.getTipo());

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
	 * Metodo que elimina un proyecto de la base de datos pasandole un id como parametro.
	 * 
	 * @param codProyecto
	 * @throws Exception
	 */
	public void eliminarProyecto(int codProyecto) throws Exception {
		Connection miConexion = null;
		PreparedStatement miStatement = null;
		// establecer la conexion con la base de datos
		try {
			miConexion = origenDatos.getConnection();
			// crear la instruccion sql de eliminacion
			String sql = "DELETE FROM PROYECTO WHERE ID=?";

			// preparar la consulta
			miStatement = miConexion.prepareStatement(sql);
			// establecer parametros de consulta
			miStatement.setInt(1, codProyecto);
			// Ejecutar la sentencia sql
			miStatement.execute();
		} finally { // para asegurarse de que se va a ejectuar siempre al finalizar
			miStatement.close();
			miConexion.close();
		}
	}
	
	/**
	 * Metodo que actualiza un proyecto de la tabla "proyecto" de la base de datos.
	 * 
	 * @param proyectoActualizado
	 * @throws Exception
	 */
	public void actualizarProyectoResidencial(Proyecto proyectoActualizado) throws Exception {
		
		Connection miConexion = null;
		PreparedStatement miStatement = null;
		// establecer la conexion con la base de datos
		try {
			miConexion = origenDatos.getConnection();
			// crear la sentencia sql
			String sql = "UPDATE PROYECTO SET DIRECCION=?, SUPERFICIE_TERRENO=?, SUPERFICIE_EDIFICIO=?, PLANTAS=?, HABITACIONES=?, BAÑOS=?, ESTADO=? WHERE ID=?";
			// crear la consulta preparada
			miStatement = miConexion.prepareStatement(sql);
			// establecer los parametros
			miStatement.setString(1, proyectoActualizado.getDireccion());
			miStatement.setInt(2, proyectoActualizado.getSuperficie_t());
			miStatement.setInt(3, proyectoActualizado.getSuperficie_e());
			miStatement.setInt(4, proyectoActualizado.getPlantas());
			miStatement.setInt(5, proyectoActualizado.getHabitaciones());
			miStatement.setInt(6, proyectoActualizado.getbanos());
			miStatement.setString(7, proyectoActualizado.getEstado());
			miStatement.setInt(8, proyectoActualizado.getId());
			// ejecutar la instruccion sql
			miStatement.execute();
		} finally { // para asegurarse de que se va a ejectuar siempre al finalizar
			miStatement.close();
			miConexion.close();
		}

	}

	/**
	 * Metodo que actualiza un proyecto de la tabla "proyecto" de la base de datos.
	 * 
	 * @param proyectoActualizado
	 * @throws Exception
	 */
	public void actualizarProyectoNoResidencial(Proyecto proyectoActualizado) throws Exception {
		// TODO Auto-generated method stub
		Connection miConexion = null;
		PreparedStatement miStatement = null;
		// establecer la conexion con la base de datos
		try {
			miConexion = origenDatos.getConnection();
			// crear la sentencia sql
			String sql = "UPDATE PROYECTO SET DIRECCION=?, SUPERFICIE_TERRENO=?, SUPERFICIE_EDIFICIO=?, FINALIDAD=?, ESTADO=? WHERE ID=?";
			// crear la consulta preparada
			miStatement = miConexion.prepareStatement(sql);
			// establecer los parametros
			miStatement.setString(1, proyectoActualizado.getDireccion());
			miStatement.setInt(2, proyectoActualizado.getSuperficie_t());
			miStatement.setInt(3, proyectoActualizado.getSuperficie_e());
			miStatement.setString(4, proyectoActualizado.getFinalidad());
			miStatement.setString(5, proyectoActualizado.getEstado());
			miStatement.setInt(6, proyectoActualizado.getId());

			// ejecutar la instruccion sql
			miStatement.execute();
		} finally { // para asegurarse de que se va a ejectuar siempre al finalizar
			miStatement.close();
			miConexion.close();
		}
	}

	/**
	 * Metodo que actualiza un proyecto de la tabla "proyecto" de la base de datos.
	 * 
	 * @param proyectoActualizado
	 * @throws Exception
	 */
	public void actualizarProyectoRehabilitacion(Proyecto proyectoActualizado) throws Exception {
		// TODO Auto-generated method stub
		Connection miConexion = null;
		PreparedStatement miStatement = null;
		// establecer la conexion con la base de datos
		try {
			miConexion = origenDatos.getConnection();
			// crear la sentencia sql
			String sql = "UPDATE PROYECTO SET DIRECCION=?, SUPERFICIE_REFORMA=?, ESTADO=? WHERE ID=?";
			// crear la consulta preparada
			miStatement = miConexion.prepareStatement(sql);
			// establecer los parametros
			miStatement.setString(1, proyectoActualizado.getDireccion());
			miStatement.setInt(2, proyectoActualizado.getSuperficie_r());
			miStatement.setString(3, proyectoActualizado.getEstado());
			miStatement.setInt(4, proyectoActualizado.getId());
			// ejecutar la instruccion sql
			miStatement.execute();
		} finally { // para asegurarse de que se va a ejectuar siempre al finalizar
			miStatement.close();
			miConexion.close();
		}
	}

	/**
	 * Metodo que actualiza un proyecto de la tabla "proyecto" de la base de datos.
	 * 
	 * @param proyectoActualizado
	 * @throws Exception
	 */
	public void actualizarProyecto(Proyecto proyectoActualizado) throws Exception {
		Connection miConexion = null;
		PreparedStatement miStatement = null;
		// establecer la conexion con la base de datos
		try {
			miConexion = origenDatos.getConnection();
			// crear la sentencia sql
			String sql = "UPDATE PROYECTO SET NOMBRE=?, IDARQUITECTO=?, ESTADO=? WHERE ID=?";
			// crear la consulta preparada
			miStatement = miConexion.prepareStatement(sql);
			// establecer los parametros
			miStatement.setString(1, proyectoActualizado.getNombre());
			miStatement.setInt(2, proyectoActualizado.getidArquitecto());
			miStatement.setString(3, proyectoActualizado.getEstado());
			miStatement.setInt(4, proyectoActualizado.getId());
			// ejecutar la instruccion sql
			miStatement.execute();
		} finally { // para asegurarse de que se va a ejectuar siempre al finalizar
			miStatement.close();
			miConexion.close();
		}
	}

	/**
	 * Metodo que actualiza un proyecto de la tabla "proyecto" de la base de datos.
	 * 
	 * @param proyectoActualizado
	 * @throws Exception
	 */
	public void actualizarProyectoArq(Proyecto proyectoActualizado) throws Exception {
		Connection miConexion = null;
		PreparedStatement miStatement = null;
		// establecer la conexion con la base de datos
		try {
			miConexion = origenDatos.getConnection();
			// crear la sentencia sql
			String sql = "UPDATE PROYECTO SET DURACIONPREVISTA=?, PRESUPUESTO=?, FECHAREALIZACION=?, ESTADO=? WHERE ID=?";
			// crear la consulta preparada
			miStatement = miConexion.prepareStatement(sql);
			// establecer los parametros
			miStatement.setString(1, proyectoActualizado.getDuracionPrevista());
			miStatement.setInt(2, proyectoActualizado.getPresupuesto());
			miStatement.setString(3, proyectoActualizado.getFechaRealizacion());
			miStatement.setString(4, proyectoActualizado.getEstado());
			miStatement.setInt(5, proyectoActualizado.getId());
			// ejecutar la instruccion sql
			miStatement.execute();
		} finally { // para asegurarse de que se va a ejectuar siempre al finalizar
			miStatement.close();
			miConexion.close();
		}
	}
	
	/**
	 * Metodo que obtiene el id maximo de la tabla proyecto y le suma 1.
	 * 
	 * @return
	 */
	public int idProyectoIncrementable() {

		int id = 1;
		PreparedStatement ps = null;
		ResultSet rs = null;

		Conexion db = new Conexion();

		try {
			ps = db.conectar().prepareStatement("select max (p.id) +1 from proyecto p");
			rs = ps.executeQuery();

			while (rs.next()) {

				id = rs.getInt(1);
			}
		} catch (Exception e) {

		}
		return id;
	}
}
