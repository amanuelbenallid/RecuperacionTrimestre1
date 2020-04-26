<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@page import="javax.servlet.http.HttpServletRequest" %>
    <%@page import="javax.servlet.ServletRequest" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User</title>
</head>
<body>
<%
HttpServletRequest httpRequest = (HttpServletRequest) request;
    String logoutURI = httpRequest.getContextPath() + "/LogOut"; 

    %>
<h1>JUGADORES</h1>

    <p>
    <c:forEach var='i' items='${jugadores}' >
    <p>NOMBRE: <c:out value="${i.getNombre()}"></c:out></p><br>
    <p>APELLIDOS: <c:out value="${i.getApellidos()}"></c:out></p><br>
    <p>DNI: <c:out value="${i.getDni()}"></c:out></p><br>
    <p>DIRECCION: <c:out value="${i.getDireccion()}"></c:out></p><br>
    <p>EQUIPO: <c:out value="${i.getEquipo()}"></c:out></p><br>
    <p>-----------------------------------------------------</p>
    <br>
    </p>
    </c:forEach>
    <button>
        <a href="<%=logoutURI %>">Cerrar Sesión</a>
    </button>
</body>
</html>