<%@page import="com.webArquitectura.Modelo.ModeloArquitectos"%>
<%@page import="com.webArquitectura.Artefacto.Proyecto"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import = "java.util.*, com.webArquitectura.*" %>
 
<%@ page import = "java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Formulario de Registro here</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com">
	<link href="https://fonts.googleapis.com/css2?family=Quicksand:wght@400;700&display=swap" rel="stylesheet">
	<link rel="stylesheet" href="resources/css/normalize.css">
	<link rel="stylesheet" href="resources/css/estilo_nuevo_registro.css"/> <!-- hay que especificar el link para usar el css -->
<!--  <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/estilos.css"/> <!-- hay que especificar el link para usar el css -->
</head>
<body>

 <%
 		ModeloArquitectos arquitectos = new ModeloArquitectos();
 			
 		Class.forName("org.hsqldb.jdbc.JDBCDriver");
 		Connection conn = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/");
 		PreparedStatement pst = conn.prepareStatement("select max (p.id) +1 from arquitecto p");		
 		ResultSet rs = pst.executeQuery();		
 %>
 
 <h1>Regístrate</h1>
	<div class= "contenedor-formulario contenedor">
		<div class="imagen-formulario">
				
		</div>
		
		<form class="formulario" name="form1" method="get" action="ControladorArquitectos">
		<input type="hidden" name="instruccion" value="insertarBBDDArquitecto">
			<div class="texto-formulario">
				<h2>Formulario de Registro</h2>
				<p>Rellene todos sus datos </p>
			</div>
			<div class = "input">
				<label for="usuario">ID Arquitecto</label>
				<input  type ="text" name="id" id="id" value="<%= arquitectos.idArquitectoIncrementable() %>" readonly />
			</div>
			
			<div class = "input">
				<label for="nombre">Nombre</label>
				<input placeholder ="Ingresa tu Nombre" type="text" name="nombre" id="nombre"/>
			</div>
			
			<div class = "input">
				<label for="apellido">Apellido</label>
				<input placeholder ="Ingresa tu Apellido" type="text" name="apellido" id="apellido"/>
			</div>
			
			<div class = "input">
				<label for="calle">Dirección</label>
				<input placeholder ="Ingresa tu Dirección" type="text" name="calle" id="calle"/>
			</div>
			
			<div class = "input">
				<label for="ciudad">Ciudad</label>
				<input placeholder ="Ingresa tu Ciudad" type="text" name="ciudad" id="ciudad"/>
			</div>
			
			<div class = "input">
				<label for="usuario">Usuario</label>
				<input placeholder ="Ingresa tu Nombre de Usuario" type="text" name="usuario" id="usuario"/>
			</div>
			
			<div class = "input">
				<label for="contraseña">Contraseña</label>
				<input placeholder ="Ingresa tu Contraseña" type="password" name="password" id="password"/>
			</div>
			
			<div class = "input">
				<input type="submit" value="Registrarse"> <!-- se crea un boton, submit es para que envie la info del formulario, insertar es el texto -->			
			</div>
					
		</form>	
	</div>
</body>
</html>