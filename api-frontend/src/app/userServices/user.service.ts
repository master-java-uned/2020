/**
 * Peter Fight
 *
 * Service para los users. De momento lo creo para obtener los datos del usuario logado, pero seguro que luego lo
 * uso para multitud de cosas más.
 */

import {HttpClient} from "@angular/common/http";
import {HeadersHelpers} from "../commons/HeadersHelpers";
import {Injectable} from '@angular/core';
import {map} from 'rxjs/operators';
import {Observable} from "rxjs";
import {authenticationRequest} from "../models/authenticationRequest";
import {subscribeToPromise} from "rxjs/internal-compatibility";
@Injectable({
  providedIn: 'root'
})
export class UserService {
  constructor(
    private httpClient: HttpClient
  ) {
  }


  /**
   * Peter Fight
   * Método obtener los datos del usuario registrado
   * Le paso un User serializado en JSON y a correr
   */
  async quienSoy() {
    let p = Promise;
    return this.httpClient.post('http://127.0.0.1:8080/users/getUser',//TODO: meter la url por variable global
      sessionStorage.getItem('token'),
      {
        headers: HeadersHelpers.getHeadersANON()
      })
      .toPromise();
  };


  async modificaUser(usuario)
  {
    let p = Promise;
    return this.httpClient.post("http://127.0.0.1:8080/users/modificaUser",
      JSON.stringify(usuario),
      {
      headers: HeadersHelpers.getHeadersANON()
    })
      .toPromise();
  }


  async clonarOvejitas(cantidad:number)
  {
    let p = Promise;
    return this.httpClient.post("http://127.0.0.1:8080/users/ovejitasDolly",
      JSON.stringify(cantidad),
      {
        headers: HeadersHelpers.getHeadersANON()
      })
      .toPromise();
  }


  async getUsersPaged(pageSize:number, indicePage:number)
  {
    let p = Promise;
    return this.httpClient.get("http://127.0.0.1:8080/users/getUsersPaged?pageSize="+pageSize+"&indicePage="+indicePage,
      {
        headers: HeadersHelpers.getHeadersANON()
      })
      .toPromise();
  }
}
