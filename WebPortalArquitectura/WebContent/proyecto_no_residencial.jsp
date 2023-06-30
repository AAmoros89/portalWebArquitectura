<%@page import="com.webArquitectura.Modelo.ModeloClientes"%>
<%@page import="com.webArquitectura.Modelo.ModeloProyectos"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*, com.webArquitectura.*"%>
<%@ page import="java.sql.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Proyecto No Residencial</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Quicksand:wght@400;700&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="resources/css/normalize.css">
<link rel="stylesheet" href="resources/css/estilo_credenciales.css" />
</head>
<body>

	<%!String contrase�a;%>
	<%!String usuario;%>

	<%
		ModeloClientes clientes = new ModeloClientes();
		ModeloProyectos proyectos = new ModeloProyectos();
		contrase�a = request.getParameter("contrase�a");
		usuario = request.getParameter("usuario");
		String usuario = session.getAttribute("usuario").toString();
		String contrase�a = session.getAttribute("contrase�a").toString();
	%>

	<h1>Proyecto No Residencial</h1>
	<div class="contenedor-formulario contenedor">
		<div class="imagen-formulario"></div>

		<form class="formulario" name="form1" method="get"
			action="ControladorProyectos">
			<input type="hidden" name="instruccion"
				value="insertarBBDDNoResidencial"> <input type="hidden"
				name="id" value="<%=proyectos.idProyectoIncrementable() - 1%>">
			<input type="hidden" name="idUsuario"
				value="<%=clientes.idUsuarioAutentificado(contrase�a, usuario)%>">
			<div class="texto-formulario">
				<h2>Formulario Espec�fico de Proyectos No Residenciales</h2>
				<p>Complete todos sus datos</p>
			</div>
			<div class="input">
				<label for="direccion">Direcci�n</label> <input
					placeholder="Ingresa la direcci�n del edificio" type="text"
					name="direccion" id="direccion">
			</div>

			<div class="input">
				<label for="superficie_t">Superficie del terreno</label> <input
					placeholder="Ingresa la superficie del terreno" type="text"
					name="superficie_t" id="superficie_t">
			</div>

			<div class="input">
				<label for="superficie_e">Superficie del edificio</label> <input
					placeholder="Ingresa la superficie del edificio" type="text"
					name="superficie_e" id="superficie_e">
			</div>

			<div class="input">
				<label for="finalidad">Finalidad de la Obra</label> <input
					placeholder="Finalidad de la obra" type="text" name="finalidad"
					id="finalidad">
			</div>

			<div class="input">
				<input type="submit" value="Pedir Presupuesto">
				<!-- se crea un boton, submit es para que envie la info del formulario, insertar es el texto -->
			</div>
		</form>
	</div>
</body>
</html>