<%@page import="com.webArquitectura.Modelo.ModeloCertificados"%>
<%@page import="com.webArquitectura.Modelo.ModeloClientes"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*, com.webArquitectura.*"%>
<%@ page import="java.sql.*"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Formulario de Certificado</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" >
<link
	href="https://fonts.googleapis.com/css2?family=Quicksand:wght@400;700&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="resources/css/normalize.css">
<link rel="stylesheet" href="resources/css/estilo_nuevo_registro.css" />
<!-- hay que especificar el link para usar el css -->
<link rel="stylesheet" href="resources/css/estilo_nuevo_certificado.css" />
</head>
<body>

	<%!String contrase�a;%>
	<%!String usuario;%>

	<%
		ModeloCertificados certificados = new ModeloCertificados();
		ModeloClientes clientes = new ModeloClientes();
		contrase�a = request.getParameter("contrase�a");
		usuario = request.getParameter("usuario");
		String fecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
	%>
	<h1>Da de Alta un Certificado Nuevo</h1>
	<div class="contenedor-formulario contenedor">
		<div class="imagen-formulario"></div>

		<form class="formulario" name="form1" method="get"
			action="ControladorCertificados">
			<input type="hidden" name="instruccion"
				value="insertarCertificadoBBDD">
			<div class="texto-formulario">
				<h2>Formulario de Registro de Certificados</h2>
				<p>Complete todos sus datos</p>
			</div>
			<div class="input">
				<label for="proyecto">ID Certificado</label> <input type="text"
					name="id" id="id"
					value="<%=certificados.idCertificadoIncrementable()%>" readonly>
			</div>

			<div class="input">
				<label for="usuario">ID USUARIO</label> <input type="text"
					name="idUsuario" id="idUsuario"
					value="<%=clientes.idUsuarioAutentificado(contrase�a, usuario)%>">
			</div>

			<div class="input" id="current_date">
				<label for="fechaSolicitud">Fecha Inicio Certificado:</label> <input
					type="text" name="fechaSolicitud" id="fechaSolicitud"
					value="<%=fecha%>">
			</div>

			<div class="input">
				<label for="tipo">Tipo de Certificado</label> <select name="tipo"
					id="tipo">

					<option value="0">Seleccione...</option>
					<option value="Habitabilidad">Habitabilidad</option>
					<option value="Inspecci�n tecnica de edificios">
						Inspecci�n t�cnica de edificios</option>
					<option value="Eficiencia energetica">Eficiencia
						energ�tica</option>
					<option value="Informe pericial">Informe pericial</option>

				</select>
			</div>

			<div class="input">
				<label for="direccion">Direcci�n</label> <input
					placeholder="Ingresa la direcci�n del edificio" type="text"
					name="direccion" id="direccion">
			</div>

			<div class="input">
				<input type="submit" value="A�adir Certificado">
				<!-- se crea un boton, submit es para que envie la info del formulario, insertar es el texto -->
			</div>

		</form>
	</div>

</body>
</html>