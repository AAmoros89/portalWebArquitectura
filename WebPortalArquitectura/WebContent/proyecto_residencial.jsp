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
<title>Proyecto Residencial</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Quicksand:wght@400;700&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="resources/css/normalize.css">
<link rel="stylesheet" href="resources/css/estilo_credenciales.css" />
<!-- hay que especificar el link para usar el css -->
</head>
<body>

	<%!String contraseña;%>
	<%!String usuario;%>

	<%
		ModeloClientes clientes = new ModeloClientes();
		ModeloProyectos proyectos = new ModeloProyectos();
		contraseña = request.getParameter("contraseña");
		usuario = request.getParameter("usuario");
		String usuario = session.getAttribute("usuario").toString();
		String contraseña = session.getAttribute("contraseña").toString();
	%>

	<h1>Proyecto Residencial</h1>
	<div class="contenedor-formulario contenedor">
		<div class="imagen-formulario"></div>

		<form class="formulario" name="form1" method="get"
			action="ControladorProyectos">
			<input type="hidden" name="instruccion"
				value="insertarBBDDResidencial"> <input type="hidden"
				name="id" value="<%=proyectos.idProyectoIncrementable() - 1%>">
			<input type="hidden" name="idUsuario"
				value="<%=clientes.idUsuarioAutentificado(contraseña, usuario)%>">
			<div class="texto-formulario">
				<h2>Formulario Específico de Proyectos Residenciales</h2>
				<p>Complete todos sus datos</p>
			</div>
			<div class="input">
				<label for="direccion">Dirección</label> <input
					placeholder="Ingresa la dirección del edificio" type="text"
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
				<label for="plantas">Plantas</label> <input
					placeholder="Número de Plantas del edificio" type="text"
					name="plantas" id="plantas">
			</div>

			<div class="input">
				<label for="habitaciones">Habitaciones</label> <input
					placeholder="Número de habitaciones" type="text"
					name="habitaciones" id="habitaciones">
			</div>

			<div class="input">
				<label for="baños">Baños</label> <input
					placeholder="Número de Baños" type="text" name="banos" id="banos">
			</div>

			<div class="input">
				<input type="submit" value="Pedir Presupuesto">
				<!-- se crea un boton, submit es para que envie la info del formulario, insertar es el texto -->
			</div>
		</form>
	</div>
</body>
</html>