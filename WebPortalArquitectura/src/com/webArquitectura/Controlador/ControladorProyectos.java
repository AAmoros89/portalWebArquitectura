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

import com.webArquitectura.Artefacto.Proyecto;
import com.webArquitectura.Modelo.ModeloProyectos;

/**
 * Servlet implementation class ControladorProyectos
 */
@WebServlet("/ControladorProyectos")
public class ControladorProyectos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//variable de la clase ModeloProyectos para tener conexion con la bse de datos
	private ModeloProyectos modeloProyectos;

	@Resource(name = "jdbc/Clientes")
	private DataSource miPool;

	@Override
	public void init() throws ServletException {
		super.init();

		try {
			modeloProyectos = new ModeloProyectos(miPool);

		} catch (Exception e) {

			throw new ServletException(e);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			// leer el parametro que le llega del formulario
			String elComando = request.getParameter("instruccion");
			String idUsuario = request.getParameter("idUsuario");
			String idArquitecto = request.getParameter("idArquitecto");

			// sino se envia el parametro, listar proyectos
			if (elComando == null)
				elComando = "listarProyectos";

			// Redirigir el flujo de ejecucion al metodo adecuado
			switch (elComando) {

			case "listarProyectos":
				obtenerProyectos(request, response);
				break;
				
			case "actualizarProyectoAdm":

				try {
					actualizaProyecto(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				
			case "eliminarProyectoAdm":

				try {
					eliminarProyecto(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				
			case "eliminarProyecto":

				try {
					eliminarProyectoCliente(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				
			case "listarProyectosCliente":
				obtenerProyectosCliente(request, response, Integer.parseInt(idUsuario));
				break;
				
			case "insertarBBDDResidencial":

				try {
					actualizaProyectoResidencial(request, response);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				break;
				
			case "insertarBBDDNoResidencial":

				try {
					actualizaProyectoNoResidencial(request, response);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				break;

			case "insertarBBDDRehabilitacion":

				try {
					actualizaProyectoRehabilitacion(request, response);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				break;
				
			case "cargarProyectoAdm":

				try {
					cargaProyecto(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				
			case "cargarProyectoArq":

				try {
					cargaProyectoArquitecto(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				
			case "actualizarProyectoArq":

				try {
					actualizaProyectoArq(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				
			case "listarProyectosArquitecto":
				obtenerProyectosArquitecto(request, response, Integer.parseInt(idArquitecto));
				break;
				
			case "insertarBBDD":
				agregarProyectoCliente(request, response);
				break;
					
			default:
			}
	}

	/**
	 * Metodo para obtener todos los proyectos actuales de la lista.
	 * 
	 * @param request
	 * @param response
	 */
	private void obtenerProyectos(HttpServletRequest request, HttpServletResponse response) {

		// obtener una lista de proyectos desde el modelo
		List<Proyecto> proyectos;

		try {
			proyectos = modeloProyectos.getProyectos(); // se almacena el listado de clientes

			// agregar lista de proyectos al request
			request.setAttribute("LISTAPROYECTOS", proyectos);

			// enviar ese request a la pagina JSP
			RequestDispatcher miDispatcher = request.getRequestDispatcher("/ListaProyectosAdm.jsp");
			miDispatcher.forward(request, response);

		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo que elimina un proyecto de la lista dado su id.
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void eliminarProyecto(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// capturar el codigo del proyecto
		int codProyecto = Integer.parseInt(request.getParameter("id")); // id viene del parametro pasado en
																		// listaProyectos
		// borrar el proyecto de la base de datos
		modeloProyectos.eliminarProyecto(codProyecto);

		// volver a la lista de proyectos
		obtenerProyectos(request, response);
	}
	
	/**
	 * Metodo que actualiza un proyecto segun los datos insertados en la vista.
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void actualizaProyecto(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// Leer los datos que le vienen del formulario actualizar
		int codProyecto = Integer.parseInt(request.getParameter("id")); // se almacena en la variable el valor que el
																		// usuario meta por teclado
		String nombreProyecto = request.getParameter("nombre");
		int codidUsuario = Integer.parseInt(request.getParameter("idUsuario"));
		String fecha = request.getParameter("fecha");
		int idArquitecto = Integer.parseInt(request.getParameter("arquitecto"));
		String estado = request.getParameter("estado");

		// crear un objeto de tipo Proyecto con la info del formulario
		Proyecto proyectoActualizado = new Proyecto(codProyecto, nombreProyecto, codidUsuario, fecha, idArquitecto,
				estado);

		// actualizar la base de datos con la infor del proyecto
		modeloProyectos.actualizarProyecto(proyectoActualizado);

		// volver al listado con la info actualizada
		obtenerProyectos(request, response);
	}
	
	/**
	 * Metod que elimina un proyecto de un cliente dado su id.
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void eliminarProyectoCliente(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// capturar el codigo cliente
		int codProyecto = Integer.parseInt(request.getParameter("id")); // id viene del parametro pasado en
																		// listaClientes
		int idUsuarioProyecto = Integer.parseInt(request.getParameter("idUsuario"));
		// borrar el cliente de la base de datos
		modeloProyectos.eliminarProyecto(codProyecto);

		// volver a la lista de proyectos
		obtenerProyectosCliente(request, response, idUsuarioProyecto);
	}
	
	/**
	 * Metodo que obtiene todos los proyectos pertenecientes a un cliente y los muestra en la vista "ListaProyectos.jsp"
	 * 
	 * @param request
	 * @param response
	 * @param clienteId
	 */
	private void obtenerProyectosCliente(HttpServletRequest request, HttpServletResponse response, int clienteId) {

		// obtener una lista de clientes desde el modelo
		List<Proyecto> proyectos;

		try {
			proyectos = modeloProyectos.getProyectosClientes(clienteId); // se almacena el listado de clientes

			// agregar lista de clientes al request
			request.setAttribute("LISTAPROYECTOS", proyectos);
			// enviar ese request a la pagina JSP
			RequestDispatcher miDispatcher = request.getRequestDispatcher("/ListaProyectos.jsp");
			miDispatcher.forward(request, response);

		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo que actualiza un proyecto de tipo Residencial con los valores recogidos de la vista. A continuacion,
	 * llama a la lista de proyectos ya actualizada.
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void actualizaProyectoResidencial(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// Leer los datos que le vienen del formulario actualizar
		int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
		int id = Integer.parseInt(request.getParameter("id"));
		String direccion = request.getParameter("direccion"); // se almacena en la variable el valor que el usuario meta
																// por teclado
		int superficie_t = Integer.parseInt(request.getParameter("superficie_t"));
		int superficie_e = Integer.parseInt(request.getParameter("superficie_e"));
		int plantas = Integer.parseInt(request.getParameter("plantas"));
		int habitaciones = Integer.parseInt(request.getParameter("habitaciones"));
		int banos = Integer.parseInt(request.getParameter("banos"));
		String estado = "Petición Enviada";

		// crear un objeto de tipo Proyecto con la info del formulario
		Proyecto proyectoActualizado = new Proyecto(id, direccion, superficie_t, superficie_e, plantas, habitaciones,
				banos, estado);

		// actualizar la base de datos con la infor del proyecto
		modeloProyectos.actualizarProyectoResidencial(proyectoActualizado);

		// volver al listado con la info actualizada
		obtenerProyectosCliente(request, response, idUsuario);
	}
	
	/**
	 * Metodo que actualiza un proyecto de tipo No  Residencial con los valores recogidos de la vista. A continuacion,
	 * llama a la lista de proyectos ya actualizada.
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void actualizaProyectoNoResidencial(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// Leer los datos que le vienen del formulario actualizar
		int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
		int id = Integer.parseInt(request.getParameter("id"));
		String direccion = request.getParameter("direccion"); // se almacena en la variable el valor que el usuario meta
																// por teclado
		int superficie_t = Integer.parseInt(request.getParameter("superficie_t"));
		int superficie_e = Integer.parseInt(request.getParameter("superficie_e"));
		String finalidad = request.getParameter("finalidad");
		String estado = "Petición Enviada";

		// crear un objeto de tipo Proyecto con la info del formulario
		Proyecto proyectoActualizado = new Proyecto(id, direccion, superficie_t, superficie_e, finalidad, estado);

		// actualizar la base de datos con la infor del proyecto
		modeloProyectos.actualizarProyectoNoResidencial(proyectoActualizado);

		// volver al listado con la info actualizada
		obtenerProyectosCliente(request, response, idUsuario);
	}

	/**
	 * Metodo que actualiza un proyecto de tipo Rehabilitacion con los valores recogidos de la vista. A continuacion,
	 * llama a la lista de proyectos ya actualizada.
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void actualizaProyectoRehabilitacion(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// Leer los datos que le vienen del formulario actualizar
		int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
		int id = Integer.parseInt(request.getParameter("id"));
		String direccion = request.getParameter("direccion"); // se almacena en la variable el valor que el usuario meta
																// por teclado
		int superficie_r = Integer.parseInt(request.getParameter("superficie_r"));
		String estado = "Petición Enviada";

		// crear un objeto de tipo Proyecto con la info del formulario
		Proyecto proyectoActualizado = new Proyecto(id, direccion, superficie_r, estado);

		// actualizar la base de datos con la infor del proyecto
		modeloProyectos.actualizarProyectoRehabilitacion(proyectoActualizado);

		// volver al listado con la info actualizada
		obtenerProyectosCliente(request, response, idUsuario);
	}

	/**
	 * Metodo que permite actualizar un proyecto a el arquitecto con los valores recogidos de la vista. A continuacion,
	 * llama a la lista de proyectos ya actualizada.
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void actualizaProyectoArq(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// Leer los datos que le vienen del formulario actualizar
		int codProyecto = Integer.parseInt(request.getParameter("id")); // se almacena en la variable el valor que el
																		// usuario meta por teclado
		String nombreProyecto = request.getParameter("nombre");
		int codIdUsuario = Integer.parseInt(request.getParameter("idUsuario"));
		String fecha = request.getParameter("fecha");
		int idArquitecto = Integer.parseInt(request.getParameter("arquitecto"));
		String duracionPrevista = request.getParameter("duracionPrevista");
		String tipo = request.getParameter("tipo");
		int presupuesto = Integer.parseInt(request.getParameter("presupuesto"));
		String fechaRealizacion = request.getParameter("fechaRealizacion");
		String estado = "Presupuesto Entregado";

		// crear un objeto de tipo Proyecto con la info del formulario
		Proyecto proyectoActualizado = new Proyecto(codProyecto, nombreProyecto, codIdUsuario, fecha, idArquitecto,
				duracionPrevista, tipo, presupuesto, fechaRealizacion, estado);

		// actualizar la base de datos con la infor del proyecto
		modeloProyectos.actualizarProyectoArq(proyectoActualizado);

		// volver al listado con la info actualizada
		obtenerProyectosArquitecto(request, response, idArquitecto);
	}

	/**
	 * Metodo que carga en la vista "actualizarProyecto" los valores registrados en la base de datos. Utiliza el metodo
	 * "getProyecto" de la clase ModeloProyectos.
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void cargaProyecto(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// leer el codigo id del cliente que viene del listado
		int idProyecto = Integer.parseInt(request.getParameter("id")); // id Cliente es el nombre que se le ha dado en
																		// ListaClientes como parametro
		// Enviar el codigo del cliente al modelo
		Proyecto elProyecto = modeloProyectos.getProyecto(idProyecto);

		// colocar atributo correspondiente al codigo del cliente
		request.setAttribute("ProyectoActualizar", elProyecto);
		// enviar la info al formulario de actualizar jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("/actualizarProyecto.jsp");
		dispatcher.forward(request, response); // enviar al formulario
	}
	
	/**
	 * Metodo que carga en la vista "actualizarProyectoArq" al arquitecto los valores registrados en la base de datos. Utiliza el metodo
	 * "getProyecto" de la clase ModeloProyectos.
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void cargaProyectoArquitecto(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// leer el codigo id del cliente que viene del listado
		int idProyecto = Integer.parseInt(request.getParameter("id")); // id Cliente es el nombre que se le ha dado en
																		// ListaClientes como parametro
		// Enviar el codigo del cliente al modelo
		Proyecto elProyecto = modeloProyectos.getProyecto(idProyecto);

		// colocar atributo correspondiente al codigo del cliente
		request.setAttribute("ProyectoActualizar", elProyecto);
		// enviar la info al formulario de actualizar jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("/actualizarProyectoArq.jsp");
		dispatcher.forward(request, response); // enviar al formulario
	}
	
	/**
	 * Metodo que obtiene todos los preoyectos asignados a un arquitecto.
	 * 
	 * @param request
	 * @param response
	 * @param IdArquitecto
	 */
	private void obtenerProyectosArquitecto(HttpServletRequest request, HttpServletResponse response,
			int IdArquitecto) {
		
		// obtener una lista de clientes desde el modelo
		List<Proyecto> proyectos;

		try {
			proyectos = modeloProyectos.getProyectosArquitecto(IdArquitecto); // se almacena el listado de proyectos

			// agregar lista de proyectos al request
			request.setAttribute("LISTAPROYECTOS", proyectos);
			// enviar ese request a la pagina JSP
			RequestDispatcher miDispatcher = request.getRequestDispatcher("/ListaProyectosArq.jsp");
			miDispatcher.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo que se utiliza para insertar en la base de datos un nuevo proyecto a un cliente dado su id.
	 * Segun elija el tipo de proyecto que desea, se reedirige a una pagina jsp diferente para seguir rellenando datos.
	 * 
	 * @param request
	 * @param response
	 */
	private void agregarProyectoCliente(HttpServletRequest request, HttpServletResponse response) {

		// Leer la info del proyecto que viene del formulario
		int codProyecto = Integer.parseInt(request.getParameter("id")); // se almacena en la variable el valor que el usuario meta por teclado
		String nombreProyecto = request.getParameter("nombre");
		int idUsuarioProyecto = Integer.parseInt(request.getParameter("idUsuario"));
		String fechaSolicitud = request.getParameter("fecha");
		String tipo = request.getParameter("tipo");

		// crear un objeto de tipo proyecto
		Proyecto nuevoProyecto = new Proyecto(codProyecto, nombreProyecto, idUsuarioProyecto, fechaSolicitud, tipo);

		// enviar el objeto al modelo y despues insertar el objeto proyecto en la base
		// de datos

		try {
			modeloProyectos.agregarElNuevoProyecto(nuevoProyecto);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		try {
			if (tipo.equals("Residencial")) {
				RequestDispatcher miDispatcher = request.getRequestDispatcher("/proyecto_residencial.jsp");

				miDispatcher.forward(request, response);

			} else if (tipo.equals("No Residencial")) {
				RequestDispatcher miDispatcher = request.getRequestDispatcher("/proyecto_no_residencial.jsp");
				miDispatcher.forward(request, response);
			} else {
				RequestDispatcher miDispatcher = request.getRequestDispatcher("/proyecto_rehabilitacion.jsp");
				miDispatcher.forward(request, response);
			}
		} catch (ServletException e) {

			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
