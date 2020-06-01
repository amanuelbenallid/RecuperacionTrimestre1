<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@page import="javax.servlet.http.HttpServletRequest" %>
    <%@page import="javax.servlet.ServletRequest" %>
    <%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
    <fmt:setBundle basename="interface" />
<!DOCTYPE html>
<html>
<head>
<% HttpServletRequest httpRequest = (HttpServletRequest) request;
String css = httpRequest.getContextPath() + "/css/user.css"; %>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="<%=css%>">
<title><fmt:message key="users" /></title>
</head>
<body class="bodyUser">
<%
    String logoutURI = httpRequest.getContextPath() + "/LogOut"; 
    String DeleteUser = httpRequest.getContextPath() + "/DeleteUser";
    String UpdateUserRole = httpRequest.getContextPath() + "/UpdateUserRole";
    String Read = httpRequest.getContextPath() + "/Read";
    %>
 <p>
 
 <h1 id="title">USUARIOS</h1>
 
 <table>
 
 	<caption><h1><fmt:message key="users" /></h1></caption>
 	<thead>
 		<th><fmt:message key="user" /></th>
 		<th><fmt:message key="password" /></th>
 		<th><fmt:message key="fullname" /></th>
 		<th><fmt:message key="DateBirth" /></th>
 		<th><fmt:message key="role" /></th>
 		<th><fmt:message key="delete" /></th>
 		<th><fmt:message key="change.role" /></th>

 	</thead>
 		
	<tbody>
		<c:forEach var='i' items='${usuarios}' >
			<tr>
				<td><c:out value="${i.getUsername()}"></c:out></td>
				<td><c:out value="${i.getPassword()}"></c:out></td>
				<td><c:out value="${i.getFullname()}"></c:out></td>
				<td><c:out value="${i.getFechanac()}"></c:out></td>
				<td><c:out value="${i.getRole()}"></c:out></td>
				<td>
					<form action="<%=DeleteUser %>" method="POST">
			    		<input type="hidden" name="user_id" value="${i.getUser_id()}">
			    		<input type="submit" class="boton2" value="<fmt:message key="delete" />">
					</form>
				</td>
				<td>
					<form action="<%= UpdateUserRole%>" method="POST">
			    		<input type="hidden" name="userId" value="${i.getUser_id()}">
			    		<input type="hidden" name="oldRole" value="${i.getRole()}">
			    		<input type="submit" class="boton2" value="<fmt:message key="change.role" />">
					</form>
				</td>
			</tr>
		</c:forEach>
	</tbody>
 
 </table>
 <hr>
 <hr>
 
 <div class="botones">
	 <button class="boton"><a href="private/index.jsp"><fmt:message key="return" /></a></button>
	 <button class="boton"><a href="<%=logoutURI%>"><fmt:message key="logout" /></a></button>
 </div>
 
    
    
</body>
</html>