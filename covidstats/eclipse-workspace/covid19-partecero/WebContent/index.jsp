<%@ page language="java" contentType="text/html; charset=ISO-8859-1" errorPage="error.jsp" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Date"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Buscador de casos de COVID</title>
</head>
<body>
 
    <h1>Bienvenido a la página del buscador de casos COVID.</h1>
 
   
  <h2>Identifíquese</h2>
 
        <form action="<%=request.getContextPath()%>/HolaUsuarioServlet" method="post">
            Usuario: <input type="text" name="usuario" value="" size="15" />
            Contraseña: <input type="password" name="password" value="" size="15" />
 
            <input type="submit" value="Enviar" name="botonEnviar" />
            <input type="reset" value="Limpiar" name="botonLimpiar" />
        </form>
 
 
</body>
</html>