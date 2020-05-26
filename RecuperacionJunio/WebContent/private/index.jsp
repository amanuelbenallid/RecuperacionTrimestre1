<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<%@page import="javax.servlet.ServletRequest"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<fmt:setBundle basename="interface" />
<!DOCTYPE html>
<html>
<head>
<% HttpServletRequest httpRequest = (HttpServletRequest) request;
String css = httpRequest.getContextPath() + "/css/index.css"; %>
<meta charset="ISO-8859-1">
<title><fmt:message key="user.user" /></title>
<link rel="stylesheet" href="<%=css%>">
</head>
<body class="bodyIndex">
	<%
		String logoutURI = httpRequest.getContextPath() + "/LogOut";
		String UpdatePlayerTeam = httpRequest.getContextPath() + "/UpdatePlayerTeam";
		String CreatePlayer = httpRequest.getContextPath() + "/CreatePlayer";
		String DeleteTeam = httpRequest.getContextPath() + "/DeleteTeam";
		String DeletePlayer = httpRequest.getContextPath() + "/DeletePlayer";
		String CreateTeam = httpRequest.getContextPath() + "/CreateTeam";
		String ReadUser = httpRequest.getContextPath() + "/ReadUser";
		String UpdateTeam = httpRequest.getContextPath() + "/UpdateTeam";
	%>
	
	<c:if test = "${usuario == 1}">
         <h1 id="title">
		<fmt:message key="user.welcome" />
	</h1>
	

	<ul>
		<li><fmt:message key="user.add" />:
			<button class="boton2"
				onclick="document.getElementById('add').style.cssText='display:block'">
				<fmt:message key="user.add" />
			</button></li>

		<div id="add" style="display: none">
			<form action="<%=CreatePlayer%>" method="post">

				<label for="nombre"><fmt:message key="name" /></label> 
				<input type="text" name="nombre" id="nombre" required> 
				<label for="apellido"><fmt:message key="surname" /></label> 
				<input type="text" name="apellido" id="apellido" required> 
				<label for="dni"><fmt:message key="dni" /></label> 
				<input type="text" name="dni" id="dni" required>
				<label for="Direccion"><fmt:message key="address" /></label>
				<input type="text" name="Direccion" id="Direccion" required> 
				<select id="equipo" name="equipo" required>
					<c:forEach var='z' items='${equipos}'>
						<option value="${z.getId()}">
						<c:out value="${z.getNombre()}"></c:out>
						</option>
						<br>
					</c:forEach>
				</select> 
				<input class="boton2" type="submit" value="<fmt:message key="user.add" />">
			</form>

		</div>
	</ul>
	
	<hr>
	<hr>
	
	<div class="tables">
	
	<table class="player">

  		<caption><h1><fmt:message key="players" /></h1></caption>
  		
  		<thead>
  			<th><fmt:message key="name" /></th>
  			<th><fmt:message key="surname" /></th>
  			<th><fmt:message key="dni" /></th>
  			<th><fmt:message key="address" /></th>
  			<th><fmt:message key="team" /></th>
  			<th><fmt:message key="user.update" /></th>
  		</thead>
  		
  		<tbody>
  			<c:forEach var='i' items='${jugadores}'>
  			<tr>
  				<td><c:out value="${i.getNombre()}"></c:out></td>
  				<td><c:out value="${i.getApellidos()}"></c:out></td>
  				<td><c:out value="${i.getDni()}"></c:out></td>
  				<td><c:out value="${i.getDireccion()}"></c:out></td>
  				<td><c:out value="${i.getEquipo()}"></c:out></td>
  				<td>
  				<form action="<%=UpdatePlayerTeam%>" method="post">				
				<input type="hidden" name="jugador_id" value="${i.getId()}"> 
				<select id="team" name="team">
					<c:forEach var='z' items='${equipos}'>
						<option value="${z.getId()}">
							<c:out value="${z.getNombre()}"></c:out>
						</option>
						<br>
					</c:forEach>
				</select>
				<button class="boton2" type="submit"><fmt:message key="user.update" /></button>
				</form>
  				</td>
  			</tr>
  			</c:forEach>
  		</tbody>
	</table>
	

	<table class="team">	
		<caption><h1><fmt:message key="teams" /></h1></caption>
		
		<thead>
			<th><fmt:message key="name" /></th>
			<th><fmt:message key="city" /></th>
			<th><fmt:message key="country" /></th>
		</thead>
		<tbody>
			<c:forEach var='i' items='${equipos}'>
				<tr>
					<td><c:out value="${i.getNombre()}"></c:out></td>
					<td><c:out value="${i.getCiudad()}"></c:out></td>
					<td><c:out value="${i.getPais()}"></c:out></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
		
		
	</div>
	<hr>
	<hr>
			
     </c:if>
     
     <c:if test= "${usuario == 0}">
     	<h1 id="title">
		<fmt:message key="admin.welcome" />
	</h1>

	<ul>
		<li><fmt:message key="admin.management" /> <a
			href="<%=ReadUser%>"> <fmt:message key="go" />
		</a></li>
		<li><fmt:message key="admin.add" />
			<button class="boton2"
				onclick="document.getElementById('add').style.cssText='display:block'">
				<fmt:message key="admin.add" />
			</button></li>

		<div id="add" style='display: none'>
			<form action="<%=CreateTeam%>" method="post">

				<label for="nombre"><fmt:message key="name" /></label> <input
					type="text" name="nombre" id="nombre" required> <label for="ciudad"><fmt:message
						key="city" /></label> <input type="text" name="ciudad" id="ciudad" required>
				<label for="pais"><fmt:message key="country" /></label> <input
					type="text" name="pais" id="pais" required> <input type="submit" class="boton2"
					value="<fmt:message key="admin.add" />">
			</form>

		</div>
	</ul>
	
	<hr>
	<hr>
	
	<div class="tables">
		<table class="player">

  		<caption><h1><fmt:message key="players" /></h1></caption>
  		
  		<thead>
  			<th><fmt:message key="name" /></th>
  			<th><fmt:message key="surname" /></th>
  			<th><fmt:message key="dni" /></th>
  			<th><fmt:message key="address" /></th>
  			<th><fmt:message key="team" /></th>
  			<th><fmt:message key="delete" /></th>
  		</thead>
  		
  		<tbody>
  			<c:forEach var='i' items='${jugadores}'>
  			<tr>
  				<td><c:out value="${i.getNombre()}"></c:out></td>
  				<td><c:out value="${i.getApellidos()}"></c:out></td>
  				<td><c:out value="${i.getDni()}"></c:out></td>
  				<td><c:out value="${i.getDireccion()}"></c:out></td>
  				<td><c:out value="${i.getEquipo()}"></c:out></td>
  				<td>
  				<form action="<%=DeletePlayer%>" method="POST">

				<input type="hidden" name="jugador_id" value="${i.getId()}">
				<input type="submit" class="boton2" value="<fmt:message key="delete" />">
			</form>
  				</td>
  			</tr>
  			</c:forEach>
  		</tbody>
	</table>
	
	
	<table class="team">	
		<caption><h1><fmt:message key="teams" /></h1></caption>
		
		<thead>
			<th><fmt:message key="name" /></th>
			<th><fmt:message key="city" /></th>
			<th><fmt:message key="country" /></th>
			<th><fmt:message key="user.update" /></th>
			<th><fmt:message key="delete" /></th>
		</thead>
		<tbody>
			<c:forEach var='i' items='${equipos}'>
				<tr>
					<form id="teamForm" action="<%=UpdateTeam%>" method="POST">
						<input type="hidden" name="equipo_id" value="${i.getId()}">
						<td><input style="width: 80%" type="text" value="${i.getNombre()}" name="equipo_nombre"></td>
						<td><input style="width: 80%" type="text" value="${i.getCiudad()}" name="equipo_ciudad"></td>
						<td><input style="width: 80%" type="text" value="${i.getPais()}" name="equipo_pais"></td>
						<td><input style="width: 90%" type="submit" class="boton2"  value="<fmt:message key="user.update" />"></td>
					</form>
					
						<form action="<%=DeleteTeam%>" method="POST">
							<input type="hidden" name="equipo_id" value="${i.getId()}">
							<td style="width: 60px" ><input style="width: 100%" type="submit" class="boton2"  value="<fmt:message key="delete" />"></td>
						</form>
				
					
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
	
	<hr>
	<hr>
	
     </c:if>
	
	
	<button class="boton"><a href="<%=logoutURI%>"><fmt:message key="logout" /></a></button>
</body>
</html>