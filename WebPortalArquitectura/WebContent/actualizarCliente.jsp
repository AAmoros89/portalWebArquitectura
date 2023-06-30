<%@page import="com.webArquitectura.Modelo.ModeloClientes"%>
<%@page import="com.webArquitectura.Artefacto.Proyecto"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="java.util.*, com.webArquitectura.*"%>

<%@ page import="java.sql.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Formulario de Actualización</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Quicksand:wght@400;700&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="resources/css/normalize.css">
<link rel="stylesheet" href="resources/css/estilo_nuevo_registro.css" />
<!-- hay que especificar el link para usar el css -->
</head>
<body>

	<h1>Actualiza el Cliente</h1>
	<div class="contenedor-formulario contenedor">
		<div class="imagen-formulario"></div>
		<form class="formulario" name="form1" method="get"
			action="ControladorClientes">
			<input type="hidden" name="instruccion" value="actualizarBBDDCliente">

			<!-- se le pasa como valor oculto el id del cliente, el .id es el nombre que se le ha dado en la clase clientes -->
			<input type="hidden" name="id" value="${ClienteActualizar.id}" />
			<!-- dentro del controlador, en el metodo de carga cliente se le da elnombre de ClienteActualizar -->
			<div class="texto-formulario">
				<h2>Formulario de Actualización</h2>
				<p>Edita los campos</p>
			</div>

			<div class="input">
				<label for="nombre">Nombre</label> <input type="text" name="nombre"
					id="nombre" value="${ClienteActualizar.nombre}" />
				<!-- con esto se crear el cuadro de texto -->
			</div>

			<div class="input">
				<label for="apellido">Apellido</label> <input type="text"
					name="apellido" id="apellido" value="${ClienteActualizar.apellido}" />
				<!-- con esto se crear el cuadro de texto -->
			</div>

			<div class="input">
				<label for="calle">Calle</label> <input type="text" name="calle"
					id="calle" value="${ClienteActualizar.calle}" />
				<!-- con esto se crear el cuadro de texto -->
			</div>

			<div class="input">
				<label for="ciudad">Ciudad</label> <input type="text" name="ciudad"
					id="ciudad" value="${ClienteActualizar.ciudad}" />
				<!-- con esto se crear el cuadro de texto -->
			</div>

			<div class="input">
				<label for="usuario">Usuario</label> <input type="text"
					name="usuario" id="usuario" value="${ClienteActualizar.usuario}" />
				<!-- con esto se crear el cuadro de texto -->
			</div>

			<div class="input">
				<label for="contraseña">Contraseña</label> <input type="password"
					name="password" id="password"
					value="${ClienteActualizar.contrasena}" />
				<!-- con esto se crear el cuadro de texto -->
			</div>

			<div class="input">
				<input type="submit" value="Actualizar Cliente">
				<!-- se crea un boton, submit es para que envie la info del formulario, insertar es el texto -->
			</div>

		</form>
	</div>
</body>
</html>