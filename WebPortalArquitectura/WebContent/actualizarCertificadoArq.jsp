<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com">
	<link href="https://fonts.googleapis.com/css2?family=Quicksand:wght@400;700&display=swap" rel="stylesheet">
	<link rel="stylesheet" href="resources/css/normalize.css">
	<link rel="stylesheet" href="resources/css/estilo_nuevo_registro.css"/>
	<link rel="stylesheet" href="resources/css/estilo_actualizar_certificado.css"/> <!-- hay que especificar el link para usar el css -->
</head>
<body>

	<h1>Actualiza el Certificado</h1>
	<div class= "contenedor-formulario contenedor">
		<div class="imagen-formulario">
				
		</div>
		<form class="formulario" name="form1" method="get" action="ControladorCertificados">
			<input type="hidden" name="instruccion" value="actualizarCertificadoArq">

			<!-- se le pasa como valor oculto el id del cliente, el .id es el nombre que se le ha dado en la clase clientes -->
			<input type="hidden" name="id" value="${CertificadoActualizar.id}"/> <!-- dentro del controlador, en el metodo de carga cliente se le da elnombre de ClienteActualizar -->
			<div class="texto-formulario">
				<h2>Formulario de Actualización</h2>
				<p>Edita los campos </p>
			</div>
			
			<div class = "input">
				<label for="idUsuario">ID Usuario</label>
				<input  type="text" name="idUsuario" id="idUsuario" value="${CertificadoActualizar.idUsuario}"/> <!-- con esto se crear el cuadro de texto -->	
			</div>		
						
			<div class = "input">
				<label for="fecha">Fecha</label>
				<input  type="text" name="fecha" id="fecha" value="${CertificadoActualizar.fechaSolicitud}"/> <!-- con esto se crear el cuadro de texto -->	
			</div>
			
			<div class = "input">
				<label for="arquitecto">Asignar Arquitecto</label>
				<input  type="text" name="arquitecto" id="arquitecto" value="${CertificadoActualizar.idArquitecto}"/> <!-- con esto se crear el cuadro de texto -->	
			</div>
			
			<div class = "input">
				<label for="fechaVisita">Fecha de Visita a la vivienda</label>
				<input  type="text" name="fechaVisita" id="fechaVisita" value="${CertificadoActualizar.fechaVisita}"/> <!-- con esto se crear el cuadro de texto -->	
			</div>
			
						
			<div class = "input">
				<input type="submit" value="Actualizar Certificado"> <!-- se crea un boton, submit es para que envie la info del formulario, insertar es el texto -->			
			</div>		
						
		</form>
	</div>
</body>
</html>