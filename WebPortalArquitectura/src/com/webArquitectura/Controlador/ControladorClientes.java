package com.webArquitectura.Controlador;

import java.io.IOException;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import com.webArquitectura.Modelo.ModeloClientes;
import com.webArquitectura.Usuario.Clientes;

/**
 * Servlet implementation class ControladorClientes
 */
@WebServlet("/ControladorClientes")
public class ControladorClientes extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//variable de la clase ModeloClientes para tener conexion con la bse de datos
	private ModeloClientes modeloClientes;

	@Resource(name = "jdbc/Clientes")
	private DataSource miPool;

	@Override
	public void init() throws ServletException {

		super.init();

		try {
			modeloClientes = new ModeloClientes(miPool);

		} catch (Exception e) {

			throw new ServletException(e);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// leer el parametro que le llega del formulario
		String elComando = request.getParameter("instruccion");

		// sino se envia el parametro, listar clientes
		if (elComando == null)
			elComando = "listar";

		// Redirigir el flujo de ejecucion al metodo adecuado
		switch (elComando) {

		case "listar":
			obtenerClientes(request, response);
			break;

		case "insertarBBDDCliente":
			agregarCliente(request, response);
			break;

		case "cargar":
			try {
				cargaCliente(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		case "actualizarBBDDCliente":
			try {
				actualizaClientes(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		case "eliminar":
			try {
				eliminarCliente(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		default:
		}
	}

	/**
	 * Metodo para eliminar un cliente de la lista dado su id. A continuacion, se llama al metodo
	 * "obtenerClientes" para que actualice la nueva lista.
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void eliminarCliente(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// capturar el codigo cliente
		int codCliente = Integer.parseInt(request.getParameter("idCliente")); // idCliente viene del parametro pasado en
																				// listaClientes
		// borrar el cliente de la base de datos
		modeloClientes.eliminarCliente(codCliente);

		// volver a la lista de clientes
		obtenerClientes(request, response);

	}

	/**
	 * Metodo que actualiza un cliente segun el id seleccionado.
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void actualizaClientes(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// Leer los datos que le vienen del formulario actualizar
		int codCliente = Integer.parseInt(request.getParameter("id")); // se almacena en la variable el valor que el
																		// usuario meta por teclado
		String nombreCliente = request.getParameter("nombre");
		String apellidoCliente = request.getParameter("apellido");
		String calleCliente = request.getParameter("calle");
		String ciudadCliente = request.getParameter("ciudad");
		String usuarioCliente = request.getParameter("usuario");
		String contrasenaCliente = request.getParameter("password");

		// crear un objeto de tipo Cliente con la info del formulario
		Clientes clienteActualizado = new Clientes(codCliente, nombreCliente, apellidoCliente, calleCliente,
				ciudadCliente, usuarioCliente, contrasenaCliente);

		// actualizar la base de datos con la infor del cliente
		modeloClientes.actualizarCliente(clienteActualizado);

		// volver al listado con la info actualizada
		obtenerClientes(request, response);
	}

	/**
	 * Metodo que carga un cliente en la pagina "actualizarCliente.jsp" y muestra sus datos en pantalla.
	 * Se llama al metodo "getCliente" de la clase ModeloClientes.
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void cargaCliente(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// leer el codigo id del cliente que viene del listado
		int idCliente = Integer.parseInt(request.getParameter("idCliente")); // id Cliente es el nombre que se le ha
																				// dado en ListaClientes como parametro
		// Enviar el codigo del cliente al modelo
		Clientes elCliente = modeloClientes.getCliente(idCliente);

		// colocar atributo correspondiente al codigo del cliente
		request.setAttribute("ClienteActualizar", elCliente);
		// enviar la info al formulario de actualizar jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("/actualizarCliente.jsp");

		dispatcher.forward(request, response); // enviar al formulario
	}

	/**
	 * Metodo para agregar un cliente a la lista de clientes
	 * 
	 * @param request
	 * @param response
	 */
	private void agregarCliente(HttpServletRequest request, HttpServletResponse response) {

		// Leer la info del proyecto que viene del formulario
		int codCliente = Integer.parseInt(request.getParameter("id")); // se almacena en la variable el valor que el
																		// usuario meta por teclado
		String nombreCliente = request.getParameter("nombre");
		String apellidoCliente = request.getParameter("apellido");
		String calleCliente = request.getParameter("calle");
		String ciudadCliente = request.getParameter("ciudad");
		String usuarioCliente = request.getParameter("usuario");
		String contrasenaCliente = request.getParameter("password");

		// crear un objeto de tipo proyecto
		Clientes nuevoCliente = new Clientes(codCliente, nombreCliente, apellidoCliente, calleCliente, ciudadCliente,
				usuarioCliente, contrasenaCliente);

		// enviar el objeto al modelo y despues insertar el objeto cliente en la base
		// de datos

		try {
			modeloClientes.agregarElNuevoCliente(nuevoCliente);
		} catch (Exception e) {
			e.printStackTrace();
		}

		RequestDispatcher m = request.getRequestDispatcher("/validar.jsp");
		try {
			m.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo para obtener todos los clientes de la lista de clientes actual. Se utiliza el metodo
	 * "getClientes" de la clase ModeloClientes.
	 * 
	 * @param request
	 * @param response
	 */
	private void obtenerClientes(HttpServletRequest request, HttpServletResponse response) {

		// obtener una lista de clientes desde el modelo
		List<Clientes> clientes;

		try {
			clientes = modeloClientes.getClientes(); // se almacena el listado de clientes

			// agregar lista de clientes al request
			request.setAttribute("LISTACLIENTES", clientes);

			// enviar ese request a la pagina JSP
			RequestDispatcher miDispatcher = request.getRequestDispatcher("/ListaClientes.jsp");
			miDispatcher.forward(request, response);

		} catch (Exception e) {

			e.printStackTrace();
		}
	}
}
