/**
 * Para adaptar un formulario al modelo User
 */

import {User} from "../userServices/auth.service";
import {UserModel} from "../models/user.model";

export class userAdapters{
  userAdapters(){}

  static get(datosFormulario: any) {
    /**
     *
     * Para mapear los datos posibles, un copia pega es muy útil
     *
     id: number;
     email: string;
     username: string;
     password: string;
     firstName: string;
     lastName: string;
     lastLoggedOn: Date;
     registeredOn: Date;
     attempts: number;
     role: string;
     imageBase64:string;
     */
    var respuesta = new UserModel();
    respuesta.email = datosFormulario.value.Email;
    respuesta.username = datosFormulario.value.Email;
    respuesta.password = datosFormulario.value.Password;
    respuesta.firstName = datosFormulario.value.Nombre;
    respuesta.lastName = datosFormulario.value.Apellidos;
    respuesta.imageBase64 = datosFormulario.value.imageBase64;
    return respuesta;
  }
}
