# FRONT VUE

## Intro

En clase se planteó la necesidad de desarrollar la versión Vue del front del proyecto de forma adicional a la del front de Angular.

## Tecnologías usadas

* **VisualStudioCode**. El desarrollo se ha llevado a cabo con este IDE dado que el anterior desarrollo (Angular) se hizo con WebStorm.
* **Vue**. Framework MVC en javascript.
* **Vuetify**. Framework complementario a Vue. Integra Vue, Bootstrap y las indicaciones de Material Design.
* **Nuxt**. Framework complementario a Vue. Toma e implementa decisiones de diseño que favorecen la productividad.
* **ESLint**. Analizador sintáctico de javascript.
* **CSS** Lint. Analizador sintáctico de CSS.
* **YARN** como gestor de paquetes, en sustitución de npm por considerarlo mejor que éste.
* **JEST** para los test unitarios.
* **Nuxt/PWA** para generar aplicación PWA.
* **Nuxt/i18n** para la internacionalización. En el proyecto empleamos dos idiomas: inglés y español.
* **SASS** para la gestión de estilos, pero debido a la simplicidad de la web, no ha sido necesario su uso.
* **SweetAlert2** para mostrar alertas.
* **JVectorMap** para mostrar el mapa.
* **Jquery** para manejar las peticiones Ajax, principalmente.

## Cómo arrancar este módulo

#### Desarrollo
```bash
yarn dev
```

#### Producción (para que funcione pwa)
 Construir el proyecto: 
 ```bash
  yarn build
```   
 Arrancar el proyecto con seguridad SSL a efectos de poder incorporar la aplicación PWA
 ```bash
 ($env:HTTPS = "true") -and (npm start)
```

## Sobre los tests unitarios
Se ha realizado una primera toma de contacto con JEST y el entorno elegido (Nuxt, VUE, VUETIFY).

Probablemente la integración con un entorno excludivamente VUE será más sencilla. En cualquier caso en el entorno que emplea este proyecto la integración sólo ha resultado simple para los componentes y para el código. Lanzar tests sobre las vistas ha resultado muy costoso en tiempo, y finalmente no se ha logrado.

Para lanzar los tests basta con ejecutar el comando: 
```bash
yarn test
```

## Documentación adicional
Se ha prescindido de comentar las vistas dado que implicaría explicar el funcionamiento de VUE en lugar del código en si.

El código "puro" que no pertenece a ningún framework, como el que genera los mapas sí está comentado.

