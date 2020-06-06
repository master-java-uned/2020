# ApiFrontend COVID 
###### ...nividivinchi...

## Aplicación Angular para correr junto al back de microservicios

#### FAQ
 - Pasos para resolver: "Esta cosa no arranca, le doy al primer botón que veo y no hace nada..."
    1) Evita cualquier contacto con un teclado.
    2) Daré por sentado que no atenderás al primer punto, así que asumiremos que tienes instalado el paquete npm.
    3) Dirígete a la carpeta del proyecto por línea de comandos. y le das a:
```bash
npm install
```
 
#### ESTRUCTURA DEL POJECT
Básicamente exprópiese lo siguiente.
 - FRONT:
	 - Default
	 - Sobre nosotros
	 - Contacto (sin mail configurado)
	 - Login, registro, recordar pass...
	 - Mapa con diferente información estadística de la situación del COVID. Contiene una animación para ver de forma interactiva la evolución del COVID
 - Página 404
 - BACK:
	 - Desde usuario normal:
		 - Mis datos (modificables)
		 - Clonar usuario ***n*** veces para que los listados que consulte el superadmin tengan paginación.
		 - Cerrar sesión:
	 - Desde superadmin:
		 - Mis datos (modificables)
		 - Ver usuarios
		 - Cerrar sesión

#### CONSIDERACICONES IMPORTANTES
 - Obviamente para poder arrancar la aplicación, debes haber arrancado previamente la bbdd neo4j, el microservicio *config* y el microservicio *auth*.
 - Al arrancar la aplicación de authorización Java, si no existe el usuario admin creamos un _**admin@admin.com**_ / _**123456**_.
 - El código está a tope con la COPE de comentarios... Resuelve tus dudas con ellos.
 
