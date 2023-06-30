<%@page import="com.webArquitectura.Modelo.ModeloProyectos"%>
<%@page import="com.webArquitectura.Modelo.ModeloClientes"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>

<%@ page import="java.util.*, com.webArquitectura.*"%>

<%@ page import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Nuevo Proyecto</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Quicksand:wght@400;700&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="resources/css/normalize.css">
<!-- hay que especificar el link para usar el css -->
<link rel="stylesheet" href="resources/css/estilo_credenciales.css" />
</head>
<body>

	<%!String contraseña;%>
	<%!String usuario;%>

	<%
		ModeloProyectos proyectos = new ModeloProyectos();
		ModeloClientes clientes = new ModeloClientes();
		contraseña = request.getParameter("contraseña");
		usuario = request.getParameter("usuario");
		String fecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
		String tipo = "";
	%>

	<h1>Da de Alta un Proyecto Nuevo</h1>
	<div class="contenedor-formulario contenedor">
		<div class="imagen-formulario"></div>

		<form class="formulario" name="form1" method="get"
			action="ControladorProyectos">
			<input type="hidden" name="instruccion" value="insertarBBDD">
			<div class="texto-formulario">
				<h2>Formulario de Registro de Proyectos</h2>
				<p>Complete todos sus datos</p>
			</div>
			<div class="input">
				<label for="proyecto">ID Proyecto</label> <input type="text"
					name="id" id="id" value="<%=proyectos.idProyectoIncrementable()%>"
					readonly>
			</div>

			<div class="input">
				<label for="nombre">Nombre</label> <input
					placeholder="Ingresa el Nombre del Proyecto" type="text"
					name="nombre" id="nombre">
			</div>

			<div class="input">
				<label for="usuario">ID USUARIO</label> <input type="text"
					name="idUsuario" id="usuario"
					value="<%=clientes.idUsuarioAutentificado(contraseña, usuario)%>">
			</div>

			<div class="input" id="current_date">
				<label for="fecha">Fecha Inicio Proyecto:</label> <input type="text"
					name="fecha" id="fecha" value="<%=fecha%>">
			</div>

			<div class="input">
				<label for="tipo">Tipo de Proyecto</label> <select name="tipo"
					id="tipo">

					<option value="0">Seleccione...</option>
					<option value="Residencial">Residencial</option>
					<option value="No Residencial">No Residencial</option>
					<option value="De Rehabilitacion">De Rehabilitación</option>

				</select>
			</div>

			<div class="input">
				<input type="submit" value="Continuar">
				<!-- se crea un boton, submit es para que envie la info del formulario -->
			</div>
		</form>
	</div>


</body>
</html>