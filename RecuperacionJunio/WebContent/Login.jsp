<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@page import="javax.servlet.http.HttpServletRequest" %>
	<%@page import="javax.servlet.ServletRequest" %>
	<fmt:setBundle basename="interface" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
	<div>
		<h1>Bienvenido</h1>

		<%  HttpServletRequest httpRequest = (HttpServletRequest) request;
			String loginURI = httpRequest.getContextPath() + "/Login"; 
		

			%>
		<form action="<%=loginURI %>"  method="post">
			<fieldset>
				<legend>Login</legend>
				<label>Usuario:</label><br> 
				<input type="text" name="username"
				placeholder="nombre de usuario" required autofocus> <br> 
				<label>Contraseña:</label><br> 
				<input type="password" name="password"
				placeholder="Contraseña" required><br>
				<input type="submit" value="Iniciar sesion">
			</fieldset>
			
		</form>

	</div>

</body>
</html>
