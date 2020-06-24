<%@taglib prefix="t" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" session="true" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
 	
<%-- En caso de que exista una sesion iniciada redirecciono a index.jsp. 
     "NO tiene caso mostrar este formulario cuando ya se està registrado --%>
     
<t:if test="${sessionScope['sessionEmail']!=null}">
    <% response.sendRedirect("index.jsp");%>
</t:if>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registrar Usuario</title>
<link rel="stylesheet" type="text/css" href="stylos.css" media="screen"/>
<script type="text/javascript">

  function validacion() {
	var algo_mal = true;
	var ok = true;
	/*var exp = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/; al menos una letra y un numero,
	al menos ocho caracteres alfanumericos*/
	var expresionemail = /^[a-z][\w.-]+@\w[\w.-]+\.[\w.-]*[a-z][a-z]$/i;
	 
   if(document.getElementById('nombre').value == null || 
	  document.getElementById('nombre').value.length == 0 ||
      (/^\s+$/.test(document.getElementById('nombre').value)) ) {
      algo_mal = false;
   }

   if(document.getElementById('apellidos').value == null || 
	  document.getElementById('apellidos').value.length == 0 ||
	  (/^\s+$/.test(document.getElementById('apellidos').value)) ) {
	  algo_mal = false;
    }

   if(!expresionemail.test(document.getElementById("email").value) ) {
   	   algo_mal = false;
   } 
   
   if(document.getElementById('usuario').value == null || 
      document.getElementById('usuario').value.length == 0 ||
	  (/^\s+$/.test(document.getElementById("usuario").value)) ) {
      algo_mal = false;
   }
      
   if(!(/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/.test(document.getElementById("password").value))) {
	   algo_mal = false;
	   ok = false;
   }
   
   if(!(/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/.test(document.getElementById("password2").value))) {
	   algo_mal = false;
	   ok = false;
   }
   
   if(ok == true) {
	  if(!(document.getElementById("password").value ==  document.getElementById("password2").value )) {
	     algo_mal = false;
	  }
   }
    
   if(algo_mal == false) {   
     document.getElementById("info").innerHTML="Revise los campos.";
   }

   return algo_mal; 
}  
 
</script>

</head>

<body>
   
   <form id="reg" action="gestServidor?action=registrar" method="post" onsubmit="return validacion()">
       <fieldset>
          <div class="error">
             <p>${sessionScope['error']}</p>
          </div>
      </fieldset>
       <fieldset>
           <legend>&nbsp;Datos personales&nbsp; &nbsp; </legend>
         <ol>
           <li>
             <label for="nombre">Nombre</label>
             <input type="text" id="nombre" name="nombre"  />
           </li>
           <li>
             <label for="apellidos">Apellidos</label>
             <input type="text" id="apellidos" name="apellidos"  />
           </li>
           <li>
             <label for="email">Email</label>
             <input type="text" id="email" name="email"  />
           </li>
         </ol>
       </fieldset>    
       <br>
       <fieldset>
         <legend>&nbsp;Datos Privados&nbsp; &nbsp; </legend>
         <ol>
           <li>
             <label for="usuario">Usuario</label>
             <input type="text" id="usuario" name="usuario" />
           </li>
            <li>
              <label for="password">Pasword</label>
              <input type="password" id="password" name="password"  />
            </li>
            <li>
              <label for="password2">Confirma</label>
              <input type="password" id="password2" name="password2" />
            </li>
          </ol>
      </fieldset>
      <br>
      <fieldset>
          <center><input type="submit" id="sumit" name="sumit"><center>Enviar</center></center>
          <div id="mensaje">
               <p id="info"></p>
          </div>
      </fieldset>
   </form>
 
</body>
</html>