# ApiFrontend

#### Atention please
1) npm cache clean -f
2) npm install npm@latest -g -> Instala el npm
3) npm install -g n -> Instala el node.js
4) npm install -> Instala los paquetes **con múltiples conflictos de versiones**
5) rezarle a una estampita¿?

En MAC (léase linux, léase sáltese en Wirndous)
Ante Unsupported engine for watchpack-chokidar2@2.0.0: (... o errores similares)
1) npm i --package-lock-only --> Te lo puedes saltar si tienes un packagej
3) npm install -g @angular/cli --force ("--force", como un valiente...)
4) npm audit fix
...Estoy en ello, en windows arranca y en MAC arrancaba antes de subir a GIT


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
