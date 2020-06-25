/**
 * Created by PeterFight (03/05/2020):
 * Clase Almacen para devolver las variables de entorno atendiendo a si el entorno es producción o pruebas.
 */

import {environmentDebug} from "../../../environments/environment.debug";
import {environment} from "../../../environments/environment.prod";

export class Almacen{
    //Aquí está la gracia. Si tenemos más de dos environments (p.ej. niespruebas niesproducción)
    //habrá que meter una ñapa adicional. No obstante, por el momento sólo tenemos local y tal vez
    //la aplicación jamás verá la luz... así que prima la productividad.
    static produccion:boolean = environment.production;
    //Te devuelve la url base de la aplicación, con ella puedes hacer lo que quieras: el límite lo pone
    //tu imaginación de developer.
    static getBaseUrl(){
      return this.produccion == true? environment.baseUrl:environmentDebug.baseUrl;
    }
}
