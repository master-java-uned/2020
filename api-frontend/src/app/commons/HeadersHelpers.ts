/**
 * Peter fight 14/05/2020
 *
 * Separo la generación de headers que usarán los services. Por el momento un genérico para posts con objetos JSON
 * y otro para autenticados
 */

import {HttpHeaders} from "@angular/common/http";

export class HeadersHelpers{
  /**
   * Le devuelvo unos headers en JSON para los posts
   */
  static getHeadersANON() {
    return new HttpHeaders({
      'Content-Type': 'application/json'//Le meto headers de app/json o mal vamos si le paso un JSON
    })
  }

  /**
   * Headers para cualquier método que requiera autenticación en las cabeceras
   */
  static getHeadersLogged(){
    return new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': sessionStorage.getItem('username')
    })
  }

}
