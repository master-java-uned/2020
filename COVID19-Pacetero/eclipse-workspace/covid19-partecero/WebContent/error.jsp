<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   isErrorPage="true" pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>pagina de error</title>
</head>
<body>
<h1>Se ha producido un error</h1>

    <p>



        ${requestScope.errorMessage}<br><br>

        ${requestScope.errorCause}<br><br>

        ${requestScope.errorLocation}

    </p>

</body>
</html>