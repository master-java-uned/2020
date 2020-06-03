<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
 
<!DOCTYPE html>
<html>
   <head>
     <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
     <link rel="stylesheet" type="text/css" href="stylos.css" media="screen"/>
     <style>
     
     .contenedor {
        width: 80%;
	    background: white; 
	    margin: 1rem;
	    padding: 2px solid #ccc;
	    display: flex;
        align-items:center;
        justify-content:center;
      }
         
    .contenedor table {
        width: 100%;
        height: 100%;
        style-margin: 0 auto;
        border-collapse: collapse;
        border: 1px solid #000;
        
      }
     .contenedor th, td  { 
       width: 5%;
       text-align: left;
       vertical-align: top;
       border: 1px solid #000;
        
       padding: 0.3em;
       caption-side: bottom;
      }
    .contenedor  caption {
        padding: 0.3em;
        color: #fff;
        background: #000;
      }
      
    .contenedor  th { background: white;}
      
      
       
     .mensaje {
          line-height: 100px;
      }
      
    </style>
    <title>Leer FicheroXLSX</title>
  </head>
  <body>   
     <div class="mensaje"><p>${sessionScope['error']}</p></div> 
     <div class="contenedor">  
       <table> 
          
         <c:forEach items="${listaXLS}" var="item">     
            <tr>   
              <c:forEach items="${item}" var="item2">   
                   <td> ${item2} </td>
              </c:forEach>  
            </tr>  <br>       
         </c:forEach> 
       </table>      
    </div>
    
 </body>
</html>

  