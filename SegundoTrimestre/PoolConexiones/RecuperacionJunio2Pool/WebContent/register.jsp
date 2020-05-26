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
<% HttpServletRequest httpRequest = (HttpServletRequest) request;
String css = httpRequest.getContextPath() + "/css/register.css"; %>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="<%=css%>">
<title><fmt:message key="register" /></title>
</head>
<body class="bodyRegister">
<%
    String logoutURI = httpRequest.getContextPath() + "/LogOut"; 
    String register = httpRequest.getContextPath() + "/Register"; 

    %>
<h1><fmt:message key="register" /></h1>

<form action="<%=register%>" method="POST">
	<fieldset>
	    <label for="username"><fmt:message key="user" />:</label><br>
	    <input type="text" name="username" id="username" required autofocus><br>
	    <p class="error"><c:out value="${errorUsername}"></c:out></p>
	    <label for="password"><fmt:message key="password" />:</label><br>
	    <input type="password" name="password" id="password" required><br>
	    <p class="error"><c:out value="${errorPassword}"></c:out></p>
	    <label for="fullname"><fmt:message key="fullname" />:</label><br>
	    <input type="text" name="fullname" id="fullname" required><br>
	    <p class="error"><c:out value="${errorFullname}"></c:out></p>
	    <label for="fechanac"><fmt:message key="DateBirth" />:</label><br>
	    <input type="date" name="fechanac" id="fechanac" required><br>
	    <div class="botones">
	    <input type="submit" class="boton" value="<fmt:message key="register" />">
	    <button class="boton"><a href="login.jsp"><fmt:message key="return" /></a></button>
	    </div>
    </fieldset>
    
    
</form>
</body>
</html>