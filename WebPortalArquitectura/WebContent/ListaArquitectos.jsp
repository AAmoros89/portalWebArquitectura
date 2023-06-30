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
<link rel="preconnect" href="https://fonts.gstatic.com" >
<link
	href="https://fonts.googleapis.com/css2?family=Quicksand:wght@400;700&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="resources/css/normalize.css">
<link rel="stylesheet" href="resources/css/estilo_lista_clientes.css" />
<!-- hay que especificar el link para usar el css -->
<link rel="stylesheet" href="resources/css/estilo_lista_arquitectos.css" />
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
	<h1>Esta es la lista de arquitectos actuales</h1>
	<div id="main-container">

		<table>
			<thead>
				<tr>
					<td>Codigo Arquitecto</td>
					<td>Nombre</td>
					<td>Apellido</td>
					<td>Calle</td>
					<td>Ciudad</td>
					<td>Usuario</td>
					<td>Contraseña</td>
					<td>Acciones</td>
				</tr>
			</thead>

			<c:forEach var="temArquitecto" items="${LISTA_ARQUITECTOS}">

				<!-- link para cada arquitecto con su campo clave -->
				<c:url var="linkTemArquitecto" value="ControladorArquitectos">
					<!-- se le pasa como valor la clase de controladorClientes -->

					<c:param name="instruccion" value="cargarArquitecto"></c:param>
					<!-- se le pasa como parametro la instruccion -->
					<c:param name="idArquitecto" value="${temArquitecto.id}"></c:param>

				</c:url>

				<!-- link para eliminar el registro de cliente con su campo clave -->
				<c:url var="linkTempEliminar" value="ControladorArquitectos">
					<!-- se le pasa como valor la clase de controladorClientes -->

					<c:param name="instruccion" value="eliminarArquitecto"></c:param>
					<!-- se le pasa como parametro la instruccion -->
					<c:param name="idArquitecto" value="${temArquitecto.id}"></c:param>

				</c:url>

				<tr>
					<td>${temArquitecto.id}</td>
					<td>${temArquitecto.nombre}</td>
					<td>${temArquitecto.apellido}</td>
					<td>${temArquitecto.calle}</td>
					<td>${temArquitecto.ciudad}</td>
					<td>${temArquitecto.usuario}</td>
					<td>${temArquitecto.contrasena}</td>
					<!-- el nbsp es para espacios entre ls palabras horizontales -->
					<td><a href="${linkTemArquitecto}">Editar</a>&nbsp;&nbsp;<a
						href="${linkTempEliminar}">Eliminar</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<form class="formulario">
		<div class="input">
			<input type="button" value="Insertar Arquitecto"
				onclick="window.location.href='inserta_arquitecto.jsp'" />
		</div>
	</form>
</body>
</html>