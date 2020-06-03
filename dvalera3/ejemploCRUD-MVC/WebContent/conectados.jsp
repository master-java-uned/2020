<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
 
 <!DOCTYPE html>
<html>
  <head>
     <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
     <link rel="stylesheet" type="text/css" href="stylos.css" media="screen"/>
     <style>
     
           
      table {
        width: 100%;
        height: 100%;
        style-margin: 0 auto;
        border-collapse: collapse;
        border: 1px solid #000;
        
      }
      th, td  { 
       width: 25%;
       text-align: left;
       vertical-align: top;
       border: 1px solid #000;
        
       padding: 0.3em;
       caption-side: bottom;
      }
      caption {
        padding: 0.3em;
        color: #fff;
        background: #000;
      }
      
      th { background: #eee;}
      
      .contenedor {
	    background: red; 
	    margin: 1rem;
	    padding: 2px solid #ccc;
	    display: flex;
        align-items:center;
        justify-content:center;
      }
      
       
      .mensaje {
          line-height: 100px;
      }
      
    </style>
    <title>Lista de Usuarios en Sesion</title>
  </head>
  <body> 
    <div class="mensaje"><p>${sessionScope['error']}</p></div> 
    <div class="contenedor">
      <table>
        <tr>
            <th>Nombre</th>
            <th>Apellidos</th>
            <th>Email</th>
            <th>Sesion</th>
         </tr>
         <c:forEach items="${lista}" var="item">        
                <tr>
                   <td> ${item.getNombre()} </td>
                   <td> ${item.getApellidos()} </td>
                   <td> ${item.getEmail()} </td>
                   <td> ${item.getSesion()} </td>
                </tr><br>             
          </c:forEach>
       </table>   
    </div>
 </body>
</html>

   