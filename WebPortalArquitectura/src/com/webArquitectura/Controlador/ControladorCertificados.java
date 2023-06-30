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

import com.webArquitectura.Artefacto.Certificado;
import com.webArquitectura.Modelo.ModeloCertificados;

/**
 * Servlet implementation class ControladorCertificados
 */
@WebServlet("/ControladorCertificados")
public class ControladorCertificados extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//variable de la clase ModeloCertificados para tener conexion con la bse de datos
	private ModeloCertificados modeloCertificados;
       
	@Resource(name = "jdbc/Clientes")
	private DataSource miPool;

	@Override
	public void init() throws ServletException {
		super.init();

		try {
			modeloCertificados = new ModeloCertificados(miPool);

		} catch (Exception e) {

			throw new ServletException(e);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// leer los parametros que le llegan del formulario
		String elComando = request.getParameter("instruccion");
		String idUsuario = request.getParameter("idUsuario");
		String idArquitecto = request.getParameter("idArquitecto");

		// sino se envia el parametro, listar certificados
		if (elComando == null)
			elComando = "listarCertificados";

		// Redirigir el flujo de ejecucion al metodo adecuado
		switch (elComando) {

		 case "listarCertificados":
				obtenerCertificados(request, response);
				break;

			case "listarCertificadosArquitecto":
				obtenerCertificadosArquitecto(request, response, Integer.parseInt(idArquitecto));
				break;

			case "listarCertificadosCliente":
				obtenerCertificadoCliente(request, response, Integer.parseInt(idUsuario));
				break;
		
			case "insertarCertificadoBBDD":
				agregarCertificadoCliente(request, response);
				break;
				
			case "eliminarCertificado":

				try {
					eliminarCertificadoCliente(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;

			case "eliminarCertificadoAdm":

				try {
					eliminarCertificado(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;

			case "cargarCertificadoAdm":

				try {
					cargaCertificado(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;

			case "cargarCertificadoArq":

				try {
					cargaCertificadoArq(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;

			case "presupuestarCertificadoArq":

				try {
					cargaPresupCertificadoArq(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;

			case "emitirCertificadoArq":

				try {
					emitirCertificadoArq(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				
			case "actualizarCertificadoAdm":

				try {
					actualizaCertificado(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;

			case "actualizarCertificadoArq":

				try {
					actualizaCertificadoArq(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;

			case "actualizarPresupCertificadoArq":

				try {
					actualizaPresupCertificadoArq(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;

			case "actualizarEmitirCertificadoArq":

				try {
					actualizaEmitirCertificadoArq(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
		default:
		}
	}
	
	/**
	 * Metodo para eliminar el certificado de un cliente. Para ello, se le pasa el id del certificado
	 * y se llama al metodo "eliminarCertificado" de la clase ModeloCertificados. A continuacion, se llama al
	 * metodo "obtenerCertificadoCliente" para que actualice la nueva lista del usuario.
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void eliminarCertificadoCliente(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// capturar el codigo del certificado
		int codCertificado = Integer.parseInt(request.getParameter("id")); // id viene del parametro pasado en
																			// listaCertificados
		int idUsuarioCertificado = Integer.parseInt(request.getParameter("idUsuario"));
		// borrar el certificado de la base de datos
		modeloCertificados.eliminarCertificado(codCertificado);

		// volver a la lista de certificados del usuario
		obtenerCertificadoCliente(request, response, idUsuarioCertificado);
	}

	/**
	 * Metodo para eliminar el certificado de un cliente. Para ello, se le pasa el id del certificado
	 * y se llama al metodo "eliminarCertificado" de la clase ModeloCertificados.
	 * A continuacion, se llama al metodo "obtenerCertificado" para que actualice la nueva lista de todos
	 * los certificados.
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void eliminarCertificado(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// capturar el codigo del certificado
		int codCertificado = Integer.parseInt(request.getParameter("id")); // id viene del parametro pasado en
																			// listaCertificados

		// borrar el certificado de la base de datos
		modeloCertificados.eliminarCertificado(codCertificado);

		// volver a la lista de certificados
		obtenerCertificados(request, response);
	}
	
	/**
	 * Metodo que permite actualizar un certificado que ya exita. Se llama al metodo "actualizarCertificado" de la
	 * clase ModeloCertificados. 
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void actualizaCertificado(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// Leer los datos que le vienen del formulario actualizar
		int codCertificado = Integer.parseInt(request.getParameter("id")); // se almacena en la variable el valor que el
																			// usuario meta por teclado
		int codidUsuario = Integer.parseInt(request.getParameter("idUsuario"));
		String fecha = request.getParameter("fechaSolicitud");
		int idArquitecto = Integer.parseInt(request.getParameter("arquitecto"));
		String estado = "Arquitecto Asignado";

		// crear un objeto de tipo Cliente con la info del formulario
		Certificado certificadoActualizado = new Certificado(codCertificado, codidUsuario, fecha, idArquitecto, estado);

		// actualizar la base de datos con la infor del certificado
		modeloCertificados.actualizarCertificado(certificadoActualizado);

		// volver al listado con la info actualizada
		obtenerCertificados(request, response);
	}

	/**
	 * Metodo que permite actualizar un certificado que ya exita. Se llama al metodo "actualizarCertificadoArq" de la
	 * clase ModeloCertificados. Este metodo se utiliza para que un Arquitecto pueda actualizar los nuevos registros de un
	 * certificado.
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void actualizaCertificadoArq(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// Leer los datos que le vienen del formulario actualizar
		int codCertificado = Integer.parseInt(request.getParameter("id")); // se almacena en la variable el valor que el
																			// usuario meta por teclado
		int codidUsuario = Integer.parseInt(request.getParameter("idUsuario"));
		String fecha = request.getParameter("fechaSolicitud");
		int idArquitecto = Integer.parseInt(request.getParameter("arquitecto"));
		String fechaVisita = request.getParameter("fechaVisita");
		String estado = "pendiente visita";

		// crear un objeto de tipo Cliente con la info del formulario
		Certificado certificadoActualizado = new Certificado(codCertificado, codidUsuario, fecha, fechaVisita, estado);

		// actualizar la base de datos con la infor del cliente
		modeloCertificados.actualizarCertificadoArq(certificadoActualizado);

		// volver al listado con la info actualizada
		obtenerCertificadosArquitecto(request, response, idArquitecto);
	}

	/**
	 * Metodo que permite actualizar un certificado que ya exita. Se llama al metodo "actualizarPresupCertificadoArq" de la
	 * clase ModeloCertificados. Este metodo se utiliza para que un Arquitecto pueda dar un presupuesto a un cliente.
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void actualizaPresupCertificadoArq(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// Leer los datos que le vienen del formulario actualizar
		int codCertificado = Integer.parseInt(request.getParameter("id")); // se almacena en la variable el valor que el
																			// usuario meta por teclado
		int codidUsuario = Integer.parseInt(request.getParameter("idUsuario"));
		String fecha = request.getParameter("fechaSolicitud");
		int idArquitecto = Integer.parseInt(request.getParameter("arquitecto"));
		int presupuesto = Integer.parseInt(request.getParameter("presupuesto"));
		String estado = "Presupuesto entregado";

		// crear un objeto de tipo Cliente con la info del formulario
		Certificado certificadoActualizado = new Certificado(codCertificado, codidUsuario, fecha, idArquitecto,
				presupuesto, estado);

		// actualizar la base de datos con la infor del cliente
		modeloCertificados.actualizarPresupCertificadoArq(certificadoActualizado);

		// volver al listado con la info actualizada
		obtenerCertificadosArquitecto(request, response, idArquitecto);
	}

	/**
	 * Metodo que permite actualizar un certificado que ya exita. Se llama al metodo "actualizarEmitirCertificadoArq" de la
	 * clase ModeloCertificados. Este metodo se utiliza para que un Arquitecto pueda emtir un certificado a un cliente.
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void actualizaEmitirCertificadoArq(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// Leer los datos que le vienen del formulario actualizar
		int codCertificado = Integer.parseInt(request.getParameter("id")); // se almacena en la variable el valor que el
																			// usuario meta por teclado
		int codidUsuario = Integer.parseInt(request.getParameter("idUsuario"));
		String fecha = request.getParameter("fechaSolicitud");
		int idArquitecto = Integer.parseInt(request.getParameter("arquitecto"));
		int presupuesto = Integer.parseInt(request.getParameter("presupuesto"));
		String fechaEmision = request.getParameter("fechaEmision");
		String categoria = request.getParameter("categoria");
		String estado = "Certificado emitido";

		// crear un objeto de tipo Cliente con la info del formulario
		Certificado certificadoActualizado = new Certificado(codCertificado, codidUsuario, fecha, idArquitecto,
				presupuesto, fechaEmision, categoria, estado);

		// actualizar la base de datos con la infor del cliente
		modeloCertificados.actualizarEmitirCertificadoArq(certificadoActualizado);

		// volver al listado con la info actualizada
		obtenerCertificadosArquitecto(request, response, idArquitecto);
	}
	
	/**
	 * Metodo que carga en la pagina "actualizarCertificado.jsp" los valores del certificado seleccionado.
	 * Para ello, utiliza el metodo "getCertificado" de la clase ModeloCertificados.
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void cargaCertificado(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// leer el codigo id del certificado que viene del listado
		int idCertificado = Integer.parseInt(request.getParameter("id")); // id es el nombre que se le ha dado
																			// en ListaCertificados como parametro
		// Enviar el codigo del cliente al modelo
		Certificado elCertificado = modeloCertificados.getCertificado(idCertificado);

		// colocar atributo correspondiente al codigo del certificado
		request.setAttribute("CertificadoActualizar", elCertificado);
		// enviar la info al formulario de actualizarCertificado.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("/actualizarCertificado.jsp");

		dispatcher.forward(request, response); // enviar al formulario
	}

	/**
	 * Metodo que carga en la pagina "actualizarCertificadoArq.jsp" los valores del certificado seleccionado.
	 * Para ello, utiliza el metodo "getCertificado" de la clase ModeloCertificados.
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void cargaCertificadoArq(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// leer el codigo id del certificado que viene del listado
		int idCertificado = Integer.parseInt(request.getParameter("id")); // id es el nombre que se le ha dado
																			// en ListaCertificadosArq como parametro
		// Enviar el codigo del certificado al modelo
		Certificado elCertificado = modeloCertificados.getCertificado(idCertificado);

		// colocar atributo correspondiente al codigo del certificado
		request.setAttribute("CertificadoActualizar", elCertificado);
		// enviar la info al formulario de actualizarCertificadoArq.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("/actualizarCertificadoArq.jsp");

		dispatcher.forward(request, response); // enviar al formulario
	}

	/**
	 * Metodo que carga en la pagina "actualizarPresupCertificadoArq.jsp" los valores del presupuesto del certificado seleccionado.
	 * Para ello, utiliza el metodo "getPresupCertificado" de la clase ModeloCertificados.
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void cargaPresupCertificadoArq(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// leer el codigo id del cliente que viene del listado
		int idCertificado = Integer.parseInt(request.getParameter("id")); // id es el nombre que se le ha dado
																			// en ListaClientes como parametro
		// Enviar el codigo del cliente al modelo
		Certificado elCertificado = modeloCertificados.getPresupCertificado(idCertificado);

		// colocar atributo correspondiente al codigo del cliente
		request.setAttribute("CertificadoActualizar", elCertificado);
		// enviar la info al formulario de actualizar jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("/actualizarPresupCertificadoArq.jsp");

		dispatcher.forward(request, response); // enviar al formulario
	}

	/**
	 * Metodo que carga en la pagina "actualizarPresupCertificadoArq.jsp" los valores del presupuesto del certificado seleccionado.
	 * Para ello, utiliza el metodo "getPresupCertificado" de la clase ModeloCertificados.
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void emitirCertificadoArq(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// leer el codigo id del certificado que viene del listado
		int idCertificado = Integer.parseInt(request.getParameter("id")); // id es el nombre que se le ha dado
																			// en ListaCertificados como parametro
		// Enviar el codigo del certificado al modelo
		Certificado elCertificado = modeloCertificados.getEmitirCertificado(idCertificado);

		// colocar atributo correspondiente al codigo del cliente
		request.setAttribute("CertificadoActualizar", elCertificado);
		// enviar la info al formulario de actualizar jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("/emitirCertificado.jsp");
		dispatcher.forward(request, response); // enviar al formulario
	}
	
	/**
	 * Metodo que se utiliza para dar de alta un nuevo certificado a un cliente.
	 * Se llama al metodo "agregarElNuevoCertificado" de la clase ModeloCertificados.
	 * 
	 * @param request
	 * @param response
	 */
	private void agregarCertificadoCliente(HttpServletRequest request, HttpServletResponse response) {
		// Leer la info del certificado que viene del formulario
		int codCertificado = Integer.parseInt(request.getParameter("id")); // se almacena en la variable el valor que el
																			// usuario meta por teclado
		int idUsuarioCertificado = Integer.parseInt(request.getParameter("idUsuario"));
		String fechaSolicitud = request.getParameter("fechaSolicitud");
		String tipo = request.getParameter("tipo");
		String direccion = request.getParameter("direccion");
		String fechaVisita = request.getParameter("fechaVisita");
		String estado = "Visita concertada";

		// crear un objeto de tipo certificado
		Certificado nuevoCertificado = new Certificado(codCertificado, idUsuarioCertificado, fechaSolicitud, tipo,
				direccion, fechaVisita, estado);

		// enviar el objeto al modelo y despues insertar el objeto certificado en la base
		// de datos
		try {
			modeloCertificados.agregarElNuevoCertificado(nuevoCertificado);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// se va a la lista de certificados creados
		obtenerCertificadoCliente(request, response, idUsuarioCertificado);
	}

	/**
	 * Metodo para obtener los certificados de cada cliente segun su id
	 * 
	 * @param request
	 * @param response
	 * @param clienteId
	 */
	private void obtenerCertificadoCliente(HttpServletRequest request, HttpServletResponse response, int certificadoId) {
		// obtener una lista de clientes desde el modelo
		List<Certificado> certificados;

		try {
			certificados = modeloCertificados.getCertificadosClientes(certificadoId); // se almacena el listado de clientes
			// agregar lista de clientes al request
			request.setAttribute("LISTACERTIFICADOS", certificados);
			// enviar ese request a la pagina JSP
			RequestDispatcher miDispatcher = request.getRequestDispatcher("/ListaCertificados.jsp");
			miDispatcher.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo para obtener todos los certificados que existan.
	 * 
	 * @param request
	 * @param response
	 */
	private void obtenerCertificados(HttpServletRequest request, HttpServletResponse response) {

		// obtener una lista de certificados desde el modelo
		List<Certificado> certificados;

		try {
			certificados = modeloCertificados.getCertificados(); // se almacena el listado de clientes

			// agregar lista de certificados al request
			request.setAttribute("LISTACERTIFICADOS", certificados);

			// enviar ese request a la pagina JSP
			RequestDispatcher miDispatcher = request.getRequestDispatcher("/ListaCertificadosAdm.jsp");
			miDispatcher.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo para obtener todos los certificados del arquitecto segun su id.
	 * 
	 * @param request
	 * @param response
	 * @param IdArquitecto
	 */
	private void obtenerCertificadosArquitecto(HttpServletRequest request, HttpServletResponse response,
			int IdArquitecto) {
		
		// obtener una lista de clientes desde el modelo
		List<Certificado> certificados;

		try {
			certificados = modeloCertificados.getCertificadosArquitecto(IdArquitecto); // se almacena el listado de
																						// certificados
			// agregar lista de clientes al request
			request.setAttribute("LISTACERTIFICADOS", certificados);

			// enviar ese request a la pagina JSP
			RequestDispatcher miDispatcher = request.getRequestDispatcher("/ListaCertificadosArq.jsp");

			miDispatcher.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
