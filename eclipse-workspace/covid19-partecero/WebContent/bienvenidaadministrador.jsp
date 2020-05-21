<%@page contentType="text/html" errorPage="error_jstl.jsp" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%
           //Capturamos los datos enviados desde el servlet
           // String nombre = (request.getAttribute("nombre")).toString();
          // String rol = (request.getAttribute("rol")).toString();
          // System.out.println(nombre);
       %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2><center>Bienvenido ${sessionScope.usuario}</center></h2>
        <h2><center>Tiene las siguientes opciones</center></h2>
        <h3><center>Cargar Excel en base de datos</center></h3>
        <div id="overlay" style="background-color: white; border-radius: 10px; border: 1px solid black; padding: 5px 10px;">
        	<form action="CargarExcelServlet" method="GET">
       		    archivo: <input type="text" name="archivo" value="" />
     	                 <button type="submit">Get Mensaje</button>
    		</form>
    		<% if (request.getAttribute("archivocreado")!=null)
             {
            %>
             <div id="info1"><center>Creado el archivo <%=request.getAttribute("archivocreado") %> &nbsp;</center></div>
          
            <%
           }
           else
           {
            %>
            <div id="info1"> &nbsp;</div>
       
            <%
          }
           %>
    	</div>
    	<h3><center>Crear nuevo usuario</center></h3>
    	<div id="overlay" style="background-color: white; border-radius: 10px; border: 1px solid black; padding: 5px 10px;">
    	<form action="<%=request.getContextPath()%>/CrearUsuarioServlet" method="post">
            Usuario:    <input type="text" name="usuario" value=""  />
            Contraseña: <input type="password" name="password" value="" />
            Rol:        <select type="text" name="rol">
               
				              <option value="empleado">empleado</option>
				              <option value="administrador">administrador</option>
				        </select>
 
            <input type="submit" value="Enviar" name="botonEnviar" />
            <input type="reset" value="Limpiar" name="botonLimpiar" />
        </form>
        <% if (request.getAttribute("usuariocreado")!=null)
        {
          %>
           <div id="info1"><center>Creado el usuario <%=request.getAttribute("usuariocreado") %> &nbsp;</center></div>
          
          <%
        }
        else
        {
        %>
         <div id="info1"> &nbsp;</div>
       
        <%
       }
        %>
    	</div>
    	<h3><center>Borrar usuario</center></h3>
    	<div id="overlay" style="background-color: white; border-radius: 10px; border: 1px solid black; padding: 5px 10px;">
    	<form action="<%=request.getContextPath()%>/BorrarUsuarioServlet" method="get">
            Usuario: <input type="text" name="usuario" value=""  />
             
            <input type="submit" value="Enviar" name="botonEnviar" />
            <input type="reset" value="Limpiar" name="botonLimpiar" />
        </form>
        <% if (request.getAttribute("usuarioborrado")!=null)
        {
          %>
           <div id="info2"><center>Borrado el usuario <%=request.getAttribute("usuarioborrado") %> &nbsp;</center></div>
          
          <%
        }
        else
        {
        %>
         <div id="info2"> &nbsp;</div>
       
        <%
       }
        %>
    	</div>
    	<h1><center>Salir de la aplicacion</center></h1>
        
        <center></center><a href="salir.jsp">Inicio</a></center>
    </body>
</html>