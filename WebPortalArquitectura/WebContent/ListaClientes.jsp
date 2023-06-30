<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,com.webArquitectura.Usuario.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista de Clientes</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Quicksand:wght@400;700&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="resources/css/normalize.css">
<link rel="stylesheet" href="resources/css/estilo_lista_clientes.css" />
<!-- hay que especificar el link para usar el css -->
</head>

<body>
	<%
		String usuario = session.getAttribute("usuario").toString();
		String contraseña = session.getAttribute("contraseña").toString();
	%>
	<form class="formulario" method="post" action="menuAdm.jsp">

		<input type="hidden" name="contraseña" id="contraseña"
			value=<%=contraseña%> /> <input type="hidden" name="usuario"
			id="usuario" value=<%=usuario%> />
		<div class="input">
			<input type="submit" value="Menú Principal">
		</div>

	</form>
	<h1>Esta es la lista de clientes actuales</h1>
	<div id="main-container">

		<table>
			<thead>
				<tr>
					<td>Codigo Cliente</td>
					<td>Nombre</td>
					<td>Apellido</td>
					<td>Calle</td>
					<td>Ciudad</td>
					<td>Usuario</td>
					<td>Contraseña</td>
					<td>Consultar</td>
					<td>Dar de Baja</td>
					<td>Proyecto</td>
					<td>Certificado</td>
				</tr>
			</thead>

			<c:forEach var="temCliente" items="${LISTACLIENTES}">

				<!-- link para cada cliente con su campo clave -->
				<c:url var="linkTemp" value="ControladorClientes">
					<!-- se le pasa como valor la clase de controladorClientes -->

					<c:param name="instruccion" value="cargar"></c:param>
					<!-- se le pasa como parametro la instruccion -->
					<c:param name="idCliente" value="${temCliente.id}"></c:param>

				</c:url>

				<!-- link para eliminar el registro de cliente con su campo clave -->
				<c:url var="linkTempEliminar" value="ControladorClientes">
					<!-- se le pasa como valor la clase de controladorClientes -->

					<c:param name="instruccion" value="eliminar"></c:param>
					<!-- se le pasa como parametro la instruccion -->
					<c:param name="idCliente" value="${temCliente.id}"></c:param>

				</c:url>
				<c:url var="linkTempNuevoProyecto" value="inserta_proyecto.jsp">
					<!-- se le pasa como valor la clase de controladorClientes -->
				</c:url>
				<c:url var="linkTempNuevoCertificado"
					value="inserta_certificado.jsp">
					<!-- se le pasa como valor la clase de controladorClientes -->
				</c:url>

				<tr>
					<td>${temCliente.id}</td>
					<td>${temCliente.nombre}</td>
					<td>${temCliente.apellido}</td>
					<td>${temCliente.calle}</td>
					<td>${temCliente.ciudad}</td>
					<td>${temCliente.usuario}</td>
					<td>${temCliente.contrasena}</td>
					<td><a href="${linkTemp}">Editar</a></td>
					<td><a href="${linkTempEliminar}">Eliminar</a></td>
					<td><a href="${linkTempNuevoProyecto}">Nuevo Proyecto</a></td>
					<td><a href="${linkTempNuevoCertificado}">Nuevo
							Certificado</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<form class="formulario">
		<div class="input">
			<input type="button" value="Insertar Cliente"
				onclick="window.location.href='inserta_cliente.jsp'" />
		</div>
	</form>
</body>
</html>