<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*, com.webArquitectura.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*,com.webArquitectura.Usuario.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista de Proyectos</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Quicksand:wght@400;700&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="resources/css/normalize.css">
<link rel="stylesheet" href="resources/css/estilo_lista_proyectos.css" />
<!-- hay que especificar el link para usar el css -->
</head>
<body>

	<%
		String usuario = session.getAttribute("usuario").toString();
		String contraseña = session.getAttribute("contraseña").toString();
	%>

	<form class="formulario" method="post" action="menuCliente.jsp">
		<input type="hidden" name="contraseña" id="contraseña"
			value=<%=contraseña%> /> <input type="hidden" name="usuario"
			id="usuario" value=<%=usuario%> />
		<div class="input">
			<input type="submit" value="Menú Principal">
		</div>
	</form>

	<h1>Esta es tu lista de proyectos actuales</h1>
	<div id="main-container">
		<table>
			<thead>
				<tr>
					<!--<td>Codigo Proyecto</td>  -->
					<td>ID</td>
					<td>Nombre</td>
					<td>FechaSolicitud</td>
					<td>Duración Prevista</td>
					<td>Tipo de Proyecto</td>
					<td>Dirección</td>
					<td>Superficie del terreno</td>
					<td>Superficie del edificio</td>
					<td>Plantas</td>
					<td>Habitaciones</td>
					<td>Baños</td>
					<td>Finalidad</td>
					<td>Superficie de reforma</td>
					<td>Presupuesto</td>
					<td>Recha de Realización</td>
					<td>Estado</td>
					<td>Acción</td>
				</tr>
			</thead>

			<c:forEach var="temProyecto" items="${LISTAPROYECTOS}">
				<c:url var="linkTempEliminar" value="ControladorProyectos">
					<!-- se le pasa como valor la clase de controladorClientes -->
					<c:param name="instruccion" value="eliminarProyecto"></c:param>
					<!-- se le pasa como parametro la instruccion -->
					<c:param name="id" value="${temProyecto.id}"></c:param>
					<c:param name="idUsuario" value="${temProyecto.idUsuario}"></c:param>
					<c:param name="fecha" value="${temProyecto.fecha}"></c:param>
					<c:param name="tipo" value="${temProyecto.tipo}"></c:param>
				</c:url>

				<tr>
					<td>${temProyecto.id}</td>
					<td>${temProyecto.nombre}</td>
					<td>${temProyecto.fecha}</td>
					<td>${temProyecto.duracionPrevista}</td>
					<td>${temProyecto.tipo}</td>
					<td>${temProyecto.direccion}</td>
					<td>${temProyecto.superficie_t}</td>
					<td>${temProyecto.superficie_e}</td>
					<td>${temProyecto.plantas}</td>
					<td>${temProyecto.habitaciones}</td>
					<td>${temProyecto.banos}</td>
					<td>${temProyecto.finalidad}</td>
					<td>${temProyecto.superficie_r}</td>
					<td>${temProyecto.presupuesto}</td>
					<td>${temProyecto.fechaRealizacion}</td>
					<td>${temProyecto.estado}</td>
					<td><a href="${linkTempEliminar}">Eliminar</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>