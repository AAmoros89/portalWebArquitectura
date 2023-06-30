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
<link rel="stylesheet"
	href="resources/css/estilo_lista_certificados.css" />
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

	<h1>Esta es tu lista de certificados actuales</h1>
	<div id="main-container">
		<table>
			<thead>
				<tr>
					<!--<td>Codigo Proyecto</td>  -->
					<td>ID</td>
					<td>ID Usuario</td>
					<td>FechaSolicitud</td>
					<td>Tipo de Certificado</td>
					<td>Direccion</td>
					<td>Fecha Visita del Arquitecto</td>
					<td>Estado</td>
					<td>Dar de Baja</td>

				</tr>
			</thead>

			<c:forEach var="temCertificado" items="${LISTACERTIFICADOS}">

				<c:url var="linkTempEliminar" value="ControladorCertificados">
					<!-- se le pasa como valor la clase de controladorClientes -->

					<c:param name="instruccion" value="eliminarCertificado"></c:param>
					<!-- se le pasa como parametro la instruccion -->
					<c:param name="id" value="${temCertificado.id}"></c:param>
					<c:param name="idUsuario" value="${temCertificado.idUsuario}"></c:param>
					<c:param name="fechaSolicitud"
						value="${temCertificado.fechaSolicitud}"></c:param>
					<c:param name="tipo" value="${temCertificado.tipo}"></c:param>
					<c:param name="direccion" value="${temCertificado.direccion}"></c:param>

				</c:url>

				<tr>
					<td>${temCertificado.id}</td>
					<td>${temCertificado.idUsuario}</td>
					<td>${temCertificado.fechaSolicitud}</td>
					<td>${temCertificado.tipo}</td>
					<td>${temCertificado.direccion}</td>
					<td>${temCertificado.fechaVisita}</td>
					<td>${temCertificado.estado}</td>

					<td><a href="${linkTempEliminar}">Eliminar</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>