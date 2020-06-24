<%@ page language="java" session="true" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
 	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>Iniciar Sesion</title>
  <link rel="stylesheet" type="text/css" href="stylos.css" media="screen"/>
  <script type="text/javascript">

  function validacion() {
   var algo_mal = true;
   /*var exp = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/; al menos una letra y un numero,
	al menos ocho caracteres alfanumericos*/
   var expresionemail = /^[a-z][\w.-]+@\w[\w.-]+\.[\w.-]*[a-z][a-z]$/i;
	 
   if(!expresionemail.test(document.getElementById("email").value) ) {
   	   algo_mal = false;
   } 
      
   if(!(/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/.test(document.getElementById("password").value))) {
	   algo_mal = false;
	   
   }
    
   if(algo_mal == false) {   
     document.getElementById("info").innerHTML="Revise los campos.";
   }

   return algo_mal; 
}  
 
</script>
  
</head>
 <body>

  
  <form id="reg" action="gestServidor?action=login" method="post" onsubmit="return validacion()">
     <fieldset>
         <div class="error"><p>${sessionScope['error']}</p></div>
      </fieldset>
    <fieldset>
      <legend>&nbsp;Datos Privados&nbsp; &nbsp; </legend>
     <ol>
       <li>
         <label for="email">Email</label>
         <input id="email" name="email" />
       </li>
       <li>
         <label for="password">Pasword</label>
         <input type="password" id="password" name="password" />
       </li>
     </ol>
    </fieldset>
    <fieldset>
       <div id="mensaje"><p id="info"></p></div>
       <center><input type="submit" value="Entrar"></center>
    </fieldset>
  </form>
 </body>
</html>
 