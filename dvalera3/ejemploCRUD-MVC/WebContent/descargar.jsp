<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="stylos.css" media="screen"/>
<title>Localizar Fichero</title>
</head>
<body>
     
    <form id="reg" action="gestServidor?action=descargar" enctype="multipart/form-data" method="post">
       <fieldset>
            <div class="error"><p>${sessionScope['error']}</p></div>
       </fieldset>
       <fieldset>
           <legend>Selecciona el Archivo</legend>
          <ol>
            <li>
              <label for="file">Examinar</label>
              <input id="file" type="file" name="file" size="50" />
              <input type="submit" id="sumit" name="sumit" value="Enviar">
           </li>
          </ol>
        </fieldset>
        
    </form>
</body>
</html>