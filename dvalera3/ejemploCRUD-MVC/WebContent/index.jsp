<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

 

<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script>
	  $(document).ready(function(){
	    $(".lista").hide();

	    $("li").hover(function() {
	        $(this).css("background-color", "#191970");
	        $(this).find("ul").show();
	    }, function() {
	        $(this).css("background-color", "#555555");
	        $(this).find("ul").hide();
	    });
	  });
    </script>
    <style>
@import url("https://fonts.googleapis.com/css?family=Dosis:400,700");
   :root {
  --main-white-color: #f2f2f2;
  --main-black-color: black;
  --main-purple-color: #9e89b8;
}

* {
  padding: 0;
  margin: 0;
  box-sizing: border-box;
}

button {
  background: none;
  outline: none;
  cursor: pointer;
}

ol {
  list-style: none;
}

ul {
  list-style: none;
}

a {
  text-decoration: none;
  color: inherit;
}

h3 {
   text-align:center;
}

body {
  font: 16px/1.5 "Dosis", sans-serif;
  /*IE FIX*/
  /*display: flex;
  flex-direction: column;*/
}
/* CONTAINER
–––––––––––––––––––––––––––––––––––––––––––––––––– */
.wrapper {
  display: flex;
  flex-direction: column;
  min-height: 100vh;
}

.wrapper > * {
  padding: 20px;
}


/* HEADER
–––––––––––––––––––––––––––––––––––––––––––––––––– */
.page-header {
  background: var(--main-white-color);
}
 
.page-header .cta-contact {
/*width: 450px;*/
font-family: inherit;
font-size: 1.2rem;
padding: 5px 18px;
border: 1px solid;
border-radius: 5px;
border-color:black;
}

.page-header .cta-contact {
    color: black;}    
    
 .page-header nav {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-around; /*between;*/
  align-items: center;
}

/* MAIN
–––––––––––––––––––––––––––––––––––––––––––––––––– */
.page-main {
  display: flex;
  flex-direction: column;
  justify-content: center;
  flex-grow: 1;
  min-height: 350px;
  background: var(--main-purple-color) 
  
  /*background: url("imagenes/carpetas.jpg") no-repeat*/
 /*  background: center / cover;*/
             /* url(https://www.ui1.es/sites/default/files/blog/images/96458710_s_1.jpg) no-repeat */
      url(https://sites.google.com/site/nes4informatica/_/rsrc/1525436631460/home/coders.png) no-repeat          
   /* url(https://s3-us-west-2.amazonaws.com/s.cdpn.io/162656/city.jpg) no-repeat*/
             center / cover;
   
  background-blend-mode: luminosity;
  color: var(--main-white-color);
}



.page-main .derecha {
   
  text-align:center;
  max-width: 500px;
}

.page-main h1 {
  margin-bottom: 20px;
}

.page-main p + p {
  margin-top: 10px;
}
/* FOOTER
–––––––––––––––––––––––––––––––––––––––––––––––––– */
.page-footer {
  display: flex;
  flex-direction: column-reverse;
  background: var(--main-white-color);
}

.page-footer ul {
  display: flex;
  font-size: 1.5rem;
  margin-bottom: 5px;
}

.page-footer ul li:not(:last-child) {
  margin-right: 20px;
}


/* MQ
–––––––––––––––––––––––––––––––––––––––––––––––––– */
@media screen and (min-width: 550px) {
  .page-header ul {
    width: auto;
    margin-top: 0;
  }

  .page-header .cta-contact {
    order: 1;
  }

  .page-footer {
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
  }

  .page-footer ul {
    margin-bottom: 0;
  }
}

@media screen and (min-width: 768px) {
  body {
    font-size: 18px;
  }

  .page-main {
    padding-left: 90px;
  }
}


    
 nav {
	margin: 0 0 20px 0;
	overflow: visible;
	height: 50px;
	
}

nav li {
	float: left;
	padding: 15px;
	background-color: #555555;
	margin: 0 5px 0 0;
	position: relative;
	height: auto;
	list-style-type: none;
	color: #fff;
	cursor: pointer;
	width: 120px;
	text-align: center;
	
}

nav li.selected {
	background-color: red;
	font-weight: normal;
}

nav li.hover {
	background-color: blue;
}

nav li ul {
	position: absolute;
	top: 50px;
	left: 0;
	width: 200px;
/*	display: none;  Esta linea oculta las opciones del menú */
	margin: 0;
}

nav li ul li {
	float: none;
	padding: 5px;
	border-bottom: 1px solid #fff;
	height: auto;
}

nav a {
	color: #fff;
}

 

    </style>
        

</head>

 

<div class="wrapper">
  <header class="page-header">
    <nav>
        <img class="logo" src="milogo.png" width="100" height="100" />    
       <ul>
        <li>Usuario
          <ul class="lista">
            <li><a href="gestServidor?action=listaUsuariosSesion">Usuarios en Sesion</a></li>
	        <li><a href="gestServidor?action=listaUsuarios">Usuarios Registrados</a></li>
          </ul>
        </li>
        <li>Archivo
           <ul class="lista">
              <li><a href="gestServidor?action=sesionDescargar">Descargar</a></li>
	   </ul>
        </li>
        <li>Entra
           <ul class="lista">
	      <li><a href="gestServidor?action=sesionLogin">Entrar</a></li>
           </ul>
        </li>
        <li>Registrate
           <ul class="lista">          
               <li><a href="gestServidor?action=sesionRegistrar">Registrarse</a></li>
           </ul>
        </li>
        <li>Baja
           <ul class="lista">          
               <li><a href="gestServidor?action=sesionEliminar">Darse de Baja</a></li>
           </ul>
        </li>
        <li>Desconectar
           <ul class="lista">          
               <li><a href="gestServidor?action=sesionLogout">Salir</a></li>
           </ul>
        </li>  
    </ul>  
      
              
    <a href="gestServidor?action=listaFicheros" class="cta-contact">Seleccione aqui el fichero</a>  
         
     <div id="mensaje">
          <p id="info">${sessionScope['error']}</p>
    </div>   
    </nav>
  </header>
  <main class="page-main">
   
    <div class="derecha">
      <h1>EJEMPLO DE FRAMEWORKS</h1>
      <h1>MVC-CRUD</h1>
      <p>Puede buscar un fichero excel, lo puede seleccionar y descargarlo.</p>
      <p>Y puede a continuacion abrirlo para ver su contenido.</p>
    </div>
  </main>
  <footer class="page-footer">
    <small>© Copyright 2020. All rights reserved.</small>   
    <a href=""><img class="github" src="github.png" width="20" height="20" /></a> 
  </footer>
</div>
