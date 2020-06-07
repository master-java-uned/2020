# CovidDivStats 1.0 - Ivan
**Ejercicio para la uned.**

**Recursos utilizados:**
- <a href="https://www.eclipse.org/" target="_blank">**Eclipse**</a>
  ( IDE de desarrollo )
- <a href="http://tomcat.apache.org/" target="_blank">**Apache Tomcat**</a>
  ( Servidor Local )
- <a href="https://www.mysql.com/" target="_blank">**MySQL**</a>
  ( Base de datos)
- <a href="https://maven.apache.org/" target="_blank">**Maven**</a>
 ( Gestor de librerias)
- <a href="https://marketplace.eclipse.org/content/eclipse-docker-tooling" target="_blank">**Eclipse Docker Tooling**</a>
 ( Virtualizacion en contenedores)
- <a href="https://docs.docker.com/compose/" target="_blank">**Docker compose**</a>
 ( Inicializacion de multi-contenedores)
 
**Patrones utilizados:**
- <a href="https://es.wikipedia.org/wiki/Modelo%E2%80%93vista%E2%80%93controlador" target="_blank">**MVC**</a>
  ( Separacion del modelo, vista, controlador)
- <a href="https://es.wikipedia.org/wiki/Singleton" target="_blank">**Singleton**</a>
  ( Para el acceso a la base de datos)
  
**Librerias Java:**  
- <a href="https://es.wikipedia.org/wiki/JavaServer_Pages" target="_blank">**JSP**</a>
  ( Vista)
- <a href="https://es.wikipedia.org/wiki/Java_Servlet" target="_blank">**Servlet**</a>
  ( Controlador)
- <a href="https://taglib.org/" target="_blank">**TagLib**</a>
  ( Elimina los scriptlets del JSP)
- <a href="https://mvnrepository.com/artifact/commons-fileupload/commons-fileupload" target="_blank">**FileUpload**</a>
  ( Manejo de subida de ficheros)
- <a href="https://mvnrepository.com/artifact/org.apache.poi/poi" target="_blank">**POI**</a>
  ( Manejo de xlxs)
- <a href="https://mvnrepository.com/artifact/javax.mail/javax.mail-api" target="_blank">**Mail**</a>
  ( Manejo de mails)
- <a href="https://mvnrepository.com/artifact/com.google.code.gson/gson" target="_blank">**GSon**</a>
  ( Manejo de JSON)
- <a href="https://mvnrepository.com/artifact/mysql/mysql-connector-java" target="_blank">**MySQL Connector/J**</a>
  ( Conector de la base de datos)
 
  
**Librerias Front-end:** 
- <a href="https://getbootstrap.com/" target="_blank">**BootStrap**</a>
  ( Diseño del Front )
- <a href="https://jquery.com/" target="_blank">**jQuery**</a>
  ( Libreria JS - Manejo JS mas simple )
- <a href="https://www.chartjs.org/" target="_blank">**Chart.js**</a>
  ( Libreria JS - Manejo de graficas )
- <a href="https://jvectormap.com/" target="_blank">**jVectorMap**</a>
  ( Libreria JS - Manejo de Mapa )


## Ejecucion

Aqui un par de maneras de hacerlo funcionar.


- Clona el repo.
- Abre el proyecto con Eclipse

- Opcion A
  - Levanta un mysql.
  - Crear la base de datos con este fichero: `/db/init.sql`
  - En el siguiente fichero: `/src/main/java/database/DataBase.java` se debe modificar lo siguiente:
  ```java
    runInDocker = false;
  ```
  - Run on Server (Apache Tomcat)
  
- Opcion B
  - Run as Maven Clean.
  - Run as Maven Install. La ruta y nombre de la creacion: `/target/coviddivstats.war`
  - Abre docker.
  - En el siguiente fichero: `/target/coviddivstats.war` se debe modificar los siguiente:
  ```java
    runInDocker = true;
  ```
  - Run as Docker Compose (Configurar primero).
  - Se bajara las imagenes(Tomcat y MySQL) y las ejecutara en los contenedores, creara la base de datos, y hara el desplegue del War

- El usuario con mas permisos es el:
 ```java
     usuario: root
    password: root
 ```
