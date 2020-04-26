<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@page import="javax.servlet.http.HttpServletRequest" %>
    <%@page import="javax.servlet.ServletRequest" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin</title>
</head>
<body>
<%
HttpServletRequest httpRequest = (HttpServletRequest) request;
    String logoutURI = httpRequest.getContextPath() + "/LogOut"; 

    %>
<h1>EQUIPOS</h1>

    <p>
    <c:forEach var='i' items='${equipos}' >
    <p>NOMBRE: <c:out value="${i.getNombre()}"></c:out></p><br>
    <p>CIUDAD: <c:out value="${i.getCiudad()}"></c:out></p><br>
    <p>PAIS: <c:out value="${i.getPais()}"></c:out></p><br>
    <p>-----------------------------------------------------</p>
    <br>
    </p>
    </c:forEach>
    <button>
        <a href="<%=logoutURI %>">Cerrar Sesión</a>
    </button>
</body>
</html>