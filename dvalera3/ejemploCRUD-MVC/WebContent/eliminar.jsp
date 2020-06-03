<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  
<!DOCTYPE html>
<html>
<head>
   <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
   <link rel="stylesheet" type="text/css" href="stylos.css" media="screen"/>
   <title>Eliminar Usuario</title>
</head>
 <body>
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
    <form id="reg" action="gestServidor?action=eliminar" method="post" onsubmit="return validacion()">
     <fieldset>
         <div class="error"><p>${sessionScope['error']}</p></div>
     </fieldset>
     <fieldset>
        <legend>Escriba su email</legend>
     <ol>
       <li>
         <label for="email">Email</label>
         <input id="email" name="email" />
       </li>
     </ol>
    </fieldset>
    <fieldset>
       <div id="mensaje"><p id="info"></p></div>
       <input type="submit" value="Eliminar">
    </fieldset>
  </form>
</body>
</html>