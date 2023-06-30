<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Formulario de Validación</title>
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
	<h1>Registro completado con éxito</h1>
	<h1></h1>
	<div class="contenedor-formulario contenedor">
		<div class="imagen-formulario"></div>

		<form class="formulario" name="form1" method="post"
			action="index.html">
			<div class="texto-formulario">
				<h2>Usuario creado correctamente</h2>
				<p>Compruebe las credenciales para continuar</p>
			</div>

			<div class="input">
				<input type="submit" value="Comprobar">
				<!-- se crea un boton, submit es para que envie la info del formulario, insertar es el texto -->
			</div>
		</form>
	</div>
</body>
</html>