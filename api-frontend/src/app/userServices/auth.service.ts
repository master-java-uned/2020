/**
 * Peter Fight
 *
 * Service que contiene la lógica de la autenticación
 */

import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {map} from 'rxjs/operators';
import {Observable} from "rxjs";
import {HeadersHelpers} from "../app_code/viewsUtils/HeadersHelpers";
import {authenticationRequest} from "../models/authenticationRequest";

export class User {
  constructor(
    public status: string,
  ) {
  }

}

export class JwtResponse {
  constructor(
    public jwttoken: string,
  ) {
  }
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  /**
   * Como los posts son maravillosos, creo una variable debuggeame para
   * meter todas las variables que quiera debuggear. Yeah!!!
   */
  private debuggeame: Observable<any>;

  constructor(
    private httpClient: HttpClient
  ) {
  }

  /**
   * Mi no entender. Mi asynks de luxe.
   */
  // authenticate(username, password) {
  //   console.log(username);
  //   console.log(password);
  //
  //   return this.httpClient.post<any>('http://127.0.0.1:8080/users/login', {username, password}).pipe(
  //     map(
  //       userData => {
  //         sessionStorage.setItem('username', username);
  //         let tokenStr = 'Bearer ' + userData.token;
  //         sessionStorage.setItem('token', tokenStr);
  //         return userData;
  //       }
  //     )
  //   );
  // }

  /**
   *
   * @param authenticationRequest
   */
  async authenticate(authenticationRequest) {
    let p = Promise;
    return this.httpClient.post('http://127.0.0.1:8080/users/login',//TODO: meter la url por variable global
      JSON.stringify(authenticationRequest),
      {
        headers: HeadersHelpers.getHeadersANON()
      })
      .toPromise();
  }


  /**
   * Checamos si existe la variable username en sesión. Se puede hacer de muchas maneras pero como esto es el front
   * si el user va al back de la app pero no dispone de credenciales, los métodos del servidor no le
   * funcarán
   */
  isUserLoggedIn() {
    let user = sessionStorage.getItem('username')
    console.log(!(user === null))
    return !(user === null)
  }

  logOut() {
    sessionStorage.removeItem('username')
  }


  /**
   * Peter Fight
   * Método register on the fly, a tope con la COPE!!
   * Le paso un User serializado en JSON y a correr
   */
  async registrame_PerFavoooore(user) {
    let p = Promise;
    return this.httpClient.post('http://127.0.0.1:8080/users/register',//TODO: meter la url por variable global
      JSON.stringify(user),
      {
        headers: HeadersHelpers.getHeadersANON()
      })
      .toPromise();
  };



}
