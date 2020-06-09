El ejercicio de javaEE correspondiente al curso lleva como nombre ejemploCRUD-MVC,  es el nombre que he decidido ponerle,  se trata de el manejo de los frameworks CRUD y MVC , que en èste caso se han implementado bajo el lenguaje de programaciòn Java.

He utilizado el IDE de Eclipse , el contenedor de servlets Tomcat, el gestor de base de datos relacionales Mysql, asì como distintas librerìas de apache, y lenguajes de programaciòn  entre los que cabe destacar Javascript para el lado del cliente.

El espìritu de la aplicaciòn consiste en seleccionar un fichero XLSX del disco, subirlo a la aplicaciòn, guardarlo en una carpeta del disco que la aplicaciòn crea, para poder ser visto por el servicio web, una vez que èste ha sido almacenado, podrà ser subido y para ser leido, todo ello es posible siempre que el usuario estè debidamente registrado. 

La carpeta se crea por defecto en el disco C, tiene nombre ejemploCRUD-MVC/ficherosXLS,  y el fichero descargado se guarda dentro de dicha carpeta, pueden guardarse tantos ficheros como capacidad de almacenamiento se tenga, la carpeta se puede seleccionar para ver todos los ficheros descargados, y asì poder ser utilizados dentro de las capacidades de la aplicaciòn(en principio sòlo permite su lectura). 

El usuario que use la aplicaciòn se encontrarà con una pàgina sencilla, en la que podrà navegar y manejar la aplicaciòn, bajo las opciones implìcitas en el menù.  La pàgina contiene una imagen de fondo, traida desde una direcciòn de internet, ademàs de un logo en la cabecera y un enlace a github en el pie de pàgina. Para ello el usuario debe registrarse y logearse correctamente, un dato importante cuando se estè registrando, introducir como mìnimo ocho caracteres alfanumèricos para elegir su contraseña. Pero la pàgina le brinda poder ver los usuarios registrados, y los que estàn con una sesiòn iniciada sin tener que haberse registrado previamente.  Ademàs de la òpciòn anterior, el usurio una vez registrado podrà logearse, descargar ficheros, leer ficheros, deslogearse y darse de baja en el sistema.

Todo ello es posible gracias a las clases que componen el proyecto. 

La clase principal GestServidor es la encargada de iniciar la aplicaciòn, conecta con la base de datos usando usuario y contraseña e indexa la opciòn solicitada por el usuario en el menù de la interfaz al mètodo correspondiente, verificando mediante una variable global de la clase, si tiene permisos para llevar a cabo dicha acciòn, en general significa si està registrado y tiene sesiòn iniciada, èsta se lleva a cabo cuando el usuario se ha registrado y a continuaciòn se logea, es cuando entonces se activa la varfiable de sesiòn iniciada.

Clase Conexiòn es la encargada de conectar la aplicaciòn(Tomcat) con la base de datos(Mysql)  a travès del conector JDBC version java-8.0.19 , èsta devuelve el conector al mètodo que lo solicita de la clase BaseDatos.

Clase BaseDatos contiene todos los datos referentes al usuario que se registra, como son el nombre, apellidos, email, usuario, contraseña y sesiòn. Los mètodos que contiene verificàn si el usuario que se registra lo hace con datos que no contiene otro usuario ya registrado, ademaàs de darse de  baja si ya està registrado, si realiza èsta acciòn se borran por completo sus datos, y tendrà que volverse a registrar de nuevo, ya sea con los mismos datos o con otros de su elecciòn siempre y cuando sean correctos. Ademàs de todo lo anteriror, podrà seleccionar el mètodo para visualizar los usuarios conectados, y otro mètodo para ver los usuarios registrados. La clase contiene ademàs dos mètodos para activar y desactivar la sesiòn del usuario.

Clase Fichero es la encargada de crear la carpeta donde se guardan los ficheros descargados mediante el mètodo descargar, y de poder seleccionar cualquiera de ellos para poder ser leido mediante su mètodo leer, recibe el fichero y lo guarda en un ArrayList  pata luego mandarlo a la pàgina .jsp correspondiente. El mètodo retunFicheros devuelve una list con todos los ficheros que hay en el directorio.

Clase Persona contiene todo lo necesario para que un usuario pueda ser identificado en la aplicaciòn, contiene dos constructores uno para cuando el usuario se registra por primera vez y otro que se usa para visualizar informaciòn de usuarios  conectados, o tamnbièn para los que estàn registrados. 

Clase Error envia un mensaje con el error o informaciòn sobre la navegaciòn del usuario.

Ademàs de todas èsta clases, la aplicaciòn contiene una serie de ficheros .jsp utilizados para manejar visualmente la aplicaciòn, hay que destacar que algunos de ellos utilizan el lenguaje del lado del cliente javascript, como por ejemplo registrar.jsp, login.jsp, logout.jsp, eliminar.jsp  que analizan los datos introducidos, comprobando si son correctos antes de enviarselos al servlet . Otros ficheros como, decargar.jsp  despliega un buscador de ficheros de windows para seleccionar el fichero a descargar, en la carpeta elegida por la aplicaciòn. y el fichero leer.jsp recibe del servlet encargado un ArrayList, lo recorre y crea una tabla html para visualizar su contenido.

 