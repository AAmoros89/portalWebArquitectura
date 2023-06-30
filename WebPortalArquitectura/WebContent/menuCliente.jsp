<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*, com.webArquitectura.*"%>
<%@ page import="java.sql.*"%>
<%@page import="com.webArquitectura.Modelo.ModeloClientes"%>
<%@page import="com.webArquitectura.Modelo.Conexion"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Formulario de Menú</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Quicksand:wght@400;700&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="resources/css/normalize.css">
<link rel="stylesheet" href="resources/css/estilo_menu.css" />
</head>
<body>

	<%
		ModeloClientes clientes = new ModeloClientes();
		String usuario = request.getParameter("usuario");
		String contraseña = request.getParameter("contraseña");
		Conexion conn = new Conexion();
		try {

			PreparedStatement ps = conn.conectar()
					.prepareStatement("SELECT usuario, password, id from cliente7 where usuario=? and password=?");
			ps.setString(1, usuario);
			ps.setString(2, contraseña);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				HttpSession sesion = request.getSession();
				sesion.setAttribute("usuario", usuario);
				sesion.setAttribute("contraseña", contraseña);
	%>

	<h1>
		Bienvenido de nuevo
		<%=usuario%></h1>
	<div class="contenedor-formulario contenedor">
		<div class="imagen-formulario"></div>
		<form class="formulario" method="post" action="inserta_proyecto.jsp">
			<div class="texto-formulario">
				<h2>Menú de Inicio</h2>
				<p>Seleccione la opción</p>
			</div>

			<input type="hidden" name="contraseña" id="contraseña"
				value=<%=contraseña%> /> <input type="hidden" name="usuario"
				id="usuario" value=<%=usuario%> />

			<div class="input">
				<input type="submit" value="Solicitar Proyecto" />
		</form>

		<form class="formulario" method="post"
			action="inserta_certificado.jsp">

			<div class="input">
				<input type="submit" value="Solicitar Certificado" />
			</div>

			<input type="hidden" name="contraseña" id="contraseña"
				value=<%=contraseña%> /> <input type="hidden" name="usuario"
				id="usuario" value=<%=usuario%> />
		</form>

		<form class="formulario" method="get" action="ControladorProyectos">
			<input type="hidden" name="instruccion"
				value="listarProyectosCliente"> <input type="hidden"
				name="idUsuario"
				value="<%=clientes.idUsuarioAutentificado(contraseña, usuario)%>">

			<div class="input">
				<input type="submit" value="Consultar Proyectos" />
			</div>

			<input type="hidden" name="contraseña" id="contraseña"
				value=<%=contraseña%> /> <input type="hidden" name="usuario"
				id="usuario" value=<%=usuario%> />
		</form>

		<form class="formulario" method="get" action="ControladorCertificados">
			<input type="hidden" name="instruccion"
				value="listarCertificadosCliente"> <input type="hidden"
				name="idUsuario"
				value="<%=clientes.idUsuarioAutentificado(contraseña, usuario)%>">
			<div class="input">
				<input type="submit" value="Consultar Certificados" />
			</div>

			<input type="hidden" name="contraseña" id="contraseña"
				value=<%=contraseña%> /> <input type="hidden" name="usuario"
				id="usuario" value=<%=usuario%> />
		</form>
		<div class="input">
			<input type="button" value="Cerrar Sesion"
				onclick="window.location.href='index.html'" />
		</div>
		<%
			} else {
		%>
		<h1>El usuario y la contraseña introducidos no son válidos</h1>
		<h1>Regístrate para tener acceso</h1>
		<div class="input">
			<input type="submit" value="Nuevo Cliente"
				onclick="window.location.href='inserta_cliente.jsp'" />
		</div>
		<%
			}
			} catch (Exception e) {
			}
		%>
	</div>
</body>
</html>