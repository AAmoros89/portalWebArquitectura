<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*, com.webArquitectura.*"%>
<%@ page import="java.sql.*"%>
<%@page import="com.webArquitectura.Modelo.Conexion"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Formulario de Men�</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Quicksand:wght@400;700&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="resources/css/normalize.css">
<link rel="stylesheet" href="resources/css/estilo_menu.css" />
<!-- hay que especificar el link para usar el css -->
<!--  <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/estilos_menu.css"/> <!-- hay que especificar el link para usar el css -->
</head>
<body>

	<%
	String usuario = request.getParameter("usuario");
	String contrase�a = request.getParameter("contrase�a");
	Conexion conn = new Conexion();
		try {
			
			PreparedStatement pst = conn.conectar().prepareStatement(
					"SELECT usuario, password, id from administrador where usuario=? and password=?");
			pst.setString(1, usuario);
			pst.setString(2, contrase�a);
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				HttpSession sesion = request.getSession();
				sesion.setAttribute("usuario", usuario);
				sesion.setAttribute("contrase�a", contrase�a);
	%>

	<div class="contenedor-formulario contenedor">
		<div class="imagen-formulario"></div>
		<form class="formulario" method="get" action="ControladorClientes">
			<input type="hidden" name="instruccion" value="listar">
			<div class="texto-formulario">
				<h2>Men� de Inicio</h2>
				<p>Seleccione la opci�n</p>
			</div>
			<input type="hidden" name="contrase�a" id="contrase�a"
				value=<%=contrase�a%> /> <input type="hidden" name="usuario"
				id="usuario" value=<%=usuario%> />
			<div class="input">
				<input type="submit" value="Consultar Clientes" />
		</form>

		<form class="formulario" method="get" action="ControladorProyectos">
			<input type="hidden" name="instruccion" value="listarProyectos">
			<div class="input">
				<input type="submit" value="Consultar Proyectos" />
			</div>
			<input type="hidden" name="contrase�a" id="contrase�a"
				value=<%=contrase�a%> /> <input type="hidden" name="usuario"
				id="usuario" value=<%=usuario%> />
		</form>

		<form class="formulario" method="get" action="ControladorCertificados">
			<input type="hidden" name="instruccion" value="listarCertificados">
			<div class="input">
				<input type="submit" value="Consultar Certificados" />
			</div>
			<input type="hidden" name="contrase�a" id="contrase�a"
				value=<%=contrase�a%> /> <input type="hidden" name="usuario"
				id="usuario" value=<%=usuario%> />
		</form>

		<form class="formulario" method="post" action="inserta_arquitecto.jsp">
			<div class="input">
				<input type="submit" value="Nuevo Arquitecto" />
			</div>
			<input type="hidden" name="contrase�a" id="contrase�a"
				value=<%=contrase�a%> /> <input type="hidden" name="usuario"
				id="usuario" value=<%=usuario%> />
		</form>
		<form class="formulario" method="get" action="ControladorArquitectos">
			<input type="hidden" name="instruccion" value="listarArquitectos">
			<div class="input">
				<input type="submit" value="Consultar Arquitectos" />
			</div>

			<input type="hidden" name="contrase�a" id="contrase�a"
				value=<%=contrase�a%> /> <input type="hidden" name="usuario"
				id="usuario" value=<%=usuario%> />
		</form>

		<div class="input">
			<input type="button" value="Cerrar Sesion"
				onclick="window.location.href='indexAdm.html'" />
		</div>

		<%
			} else {
		%>
		<h1>El usuario y la contrase�a introducidos no son v�lidos</h1>
		<h1>Reg�strate para tener acceso</h1>
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