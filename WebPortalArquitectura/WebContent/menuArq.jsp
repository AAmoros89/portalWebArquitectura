<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*, com.webArquitectura.*"%>
<%@ page import="java.sql.*"%>
<%@page import="com.webArquitectura.Modelo.ModeloArquitectos"%>
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
<!-- se especifica el link para usar el css -->
</head>
<body>
	<%
		ModeloArquitectos arquitectos = new ModeloArquitectos();
		String usuario = request.getParameter("usuario");
		String contrase�a = request.getParameter("contrase�a");
		Conexion conn = new Conexion();
		try {
			
			//Class.forName("org.hsqldb.jdbc.JDBCDriver");
			//Connection conn = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/");
			PreparedStatement pst = conn.conectar().prepareStatement(
					"SELECT usuario, password, id from arquitecto where usuario=? and password=?");
			pst.setString(1, usuario);
			pst.setString(2, contrase�a);
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				HttpSession sesion = request.getSession();
				sesion.setAttribute("usuario", usuario);
				sesion.setAttribute("contrase�a", contrase�a);
	%>
	<h1>
		Bienvenido de nuevo
		<%=usuario%></h1>
	<div class="contenedor-formulario contenedor">
		<div class="imagen-formulario"></div>
		<form class="formulario" method="get" action="ControladorProyectos">
			<input type="hidden" name="instruccion"
				value="listarProyectosArquitecto">
			<div class="texto-formulario">
				<h2>Men� de Inicio</h2>
				<p>Seleccione la opci�n</p>
			</div>
			<input type="hidden" name="idArquitecto"
				value="<%=arquitectos.idArquitectoAutentificado(contrase�a, usuario)%>">
			<input type="hidden" name="contrase�a" id="contrase�a"
				value=<%=contrase�a%> /> <input type="hidden" name="usuario"
				id="usuario" value=<%=usuario%> />
			<div class="input">
				<input type="submit" value="Consultar Proyectos" />
		</form>

		<form class="formulario" method="get" action="ControladorCertificados">
			<input type="hidden" name="instruccion"
				value="listarCertificadosArquitecto"> <input type="hidden"
				name="idArquitecto"
				value="<%=arquitectos.idArquitectoAutentificado(contrase�a, usuario)%>">

			<div class="input">
				<input type="submit" value="Consultar Certificados" />
			</div>

			<input type="hidden" name="contrase�a" id="contrase�a"
				value=<%=contrase�a%> /> <input type="hidden" name="usuario"
				id="usuario" value=<%=usuario%> />
		</form>
		<div class="input">
			<input type="button" value="Cerrar Sesion"
				onclick="window.location.href='indexArq.html'" />
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