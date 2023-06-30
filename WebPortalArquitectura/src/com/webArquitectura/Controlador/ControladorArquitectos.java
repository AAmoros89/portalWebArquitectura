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
import com.webArquitectura.Modelo.ModeloArquitectos;
import com.webArquitectura.Usuario.Arquitecto;

/**
 * Servlet implementation class ControladorArquitectos
 */
@WebServlet("/ControladorArquitectos")
public class ControladorArquitectos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//variable de la clase ModeloArquitectos para tener conexion con la bse de datos
	private ModeloArquitectos modeloArquitectos;

	@Resource(name = "jdbc/Clientes")
	private DataSource miPool;

	@Override
	public void init() throws ServletException {
		super.init();

		try {
			modeloArquitectos = new ModeloArquitectos(miPool);

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

		// sino se envia el parametro, listar arquitectos
		if (elComando == null)
			elComando = "listarArquitectos";

		// Redirige el flujo de ejecucion al metodo adecuado
		switch (elComando) {

		case "listarArquitectos":
			obtenerArquitectos(request, response);
			break;
			
		case "eliminarArquitecto":

			try {
				eliminarArquitecto(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
			
		case "actualizarBBDDArquitecto":
			try {
				actualizaArquitectos(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
			
		case "insertarBBDDArquitecto":
			agregarArquitecto(request, response);
			break;
			
		case "cargarArquitecto":
			try {
				cargaArquitecto(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		default:
		}
	}
	
	/**
	 * Metodo para obtener un listado de todos los arquitectos registrados.
	 * Llama al metodo "getArquitectos" del modelo.
	 * 
	 * @param request
	 * @param response
	 */
	private void obtenerArquitectos(HttpServletRequest request, HttpServletResponse response) {

		// obtener una lista de arquitectos desde el modelo
		List<Arquitecto> arquitectos;

		try {
			arquitectos = modeloArquitectos.getArquitectos(); // se almacena el listado de arquitectos

			// agrega lista de arquitectos al request
			request.setAttribute("LISTA_ARQUITECTOS", arquitectos);

			// envia ese request a la pagina JSP
			RequestDispatcher miDispatcher = request.getRequestDispatcher("/ListaArquitectos.jsp");
			miDispatcher.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo para eliminar un arquitecto de la lista. Llama al metodo "eliminarArquitecto" del modelo.
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void eliminarArquitecto(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// captura el codigo del arquitecto
		int codArquitecto = Integer.parseInt(request.getParameter("idArquitecto")); // idArquitecto viene del parametro
																					// pasado en listaArquitectos
		// borrar el arqitecto de la base de datos
		modeloArquitectos.eliminarArquitecto(codArquitecto);

		// volver a la lista de arquitectos
		obtenerArquitectos(request, response);
	}
	
	/**
	 * Metodo que actualiza un arquitecto dado su codigo de identificacion.
	 * Llama al metodo "actualizarArquitecto" del modelo.
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void actualizaArquitectos(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// Leer los datos que le vienen del formulario actualizar
		int codArquitecto = Integer.parseInt(request.getParameter("id")); // se almacena en la variable el valor que el
																		// usuario meta por teclado
		String nombreArquitecto = request.getParameter("nombre");
		String apellidoArquitecto = request.getParameter("apellido");
		String calleArquitecto = request.getParameter("calle");
		String ciudadArquitecto = request.getParameter("ciudad");
		String usuarioArquitecto = request.getParameter("usuario");
		String contrasenaArquitecto = request.getParameter("password");

		// crear un objeto de tipo Arquietcto con la info del formulario
		Arquitecto ArquitectoActualizado = new Arquitecto(codArquitecto, nombreArquitecto, apellidoArquitecto, calleArquitecto,
				ciudadArquitecto, usuarioArquitecto, contrasenaArquitecto);

		// actualizar la base de datos con la infor del arquitecto
		modeloArquitectos.actualizarArquitecto(ArquitectoActualizado);

		// volver al listado con la info actualizada
		obtenerArquitectos(request, response);
	}
	
	/**
	 * Metodo para agregar un arquitecto a la lista. Llama al metodo "agregarElNuevoArquitecto" del modelo.
	 * 
	 * @param request
	 * @param response
	 */
	private void agregarArquitecto(HttpServletRequest request, HttpServletResponse response) {

		// Leer la info del proyecto que viene del formulario
		int codArquitecto = Integer.parseInt(request.getParameter("id")); // se almacena en la variable el valor que el
																		// usuario meta por teclado
		String nombreArquitecto = request.getParameter("nombre");
		String apellidoArquitecto = request.getParameter("apellido");
		String calleArquitecto = request.getParameter("calle");
		String ciudadArquitecto = request.getParameter("ciudad");
		String usuarioArquitecto = request.getParameter("usuario");
		String contrasenaArquitecto = request.getParameter("password");

		// crear un objeto de tipo proyecto
		Arquitecto nuevoArquitecto = new Arquitecto(codArquitecto, nombreArquitecto, apellidoArquitecto, calleArquitecto,
				ciudadArquitecto, usuarioArquitecto, contrasenaArquitecto);

		// enviar el objeto al modelo y despues insertar el objeto proyecto en la base
		// de datos

		try {
			modeloArquitectos.agregarElNuevoArquitecto(nuevoArquitecto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// volver a listar la tabla de arquitectos
		obtenerArquitectos(request, response);
	}
	
	/**
	 * Metodo que carga en la pagina "actualizarArquitecto.jsp" los datos del arquitecto seleccionado.
	 * Para ello, llama al metodo "getArquitecto" del modelo.
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void cargaArquitecto(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// leer el codigo id del arquitecto que viene del listado
		int idArquitecto = Integer.parseInt(request.getParameter("idArquitecto")); // idArquitecto es el nombre que se le
																					// ha dado en ListaArquitectos como
																					// parametro
		// Enviar el codigo del arquitecto al modelo
		Arquitecto elArquitecto = modeloArquitectos.getArquitecto(idArquitecto);

		// colocar atributo correspondiente al codigo del arquitecto
		request.setAttribute("ArquitectoActualizar", elArquitecto);
		// enviar la info al formulario de actualizar jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("/actualizarArquitecto.jsp");
		dispatcher.forward(request, response); // enviar al formulario
	}

}
