<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Administrar Artículos</title>
</head>
<body>
	<h1>Lista  Usuarios</h1>
	<table>
		<tr>
			<td><a href="adminusuarios?action=index" >Ir al menú</a> </td>
		</tr>
	</table>
	
	<table border="1" width="100%">
		<tr>
		 <td> ID</td>
		 <td> NOMBRE</td>
		 <td> PASSWORD</td>
		 <td> USERID</td>
		
		 <td colspan=2>ACCIONES</td>
		</tr>
		<c:forEach var="usuario" items="${lista}">
			<tr>
				<td><c:out value="${usuario.id}"/></td>
				<td><c:out value="${usuario.nombre}"/></td>
				<td><c:out value="${usuario.password}"/></td>
				<td><c:out value="${usuario.userid}"/></td>
				
				<td><a href="adminusuarios?action=showedit&id=<c:out value="${usuario.id}" />">Editar</a></td>
				<td><a href="adminusuarios?action=eliminar&id=<c:out value="${usuario.id}"/>">Eliminar</a> </td>				
			</tr>
		</c:forEach>
	</table>
	
</body>
</html>