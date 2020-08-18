
/**
 * Peter Fight
 * Importante que se corresponda con el modelo de la web, si no, mal vamos...
 */

export class UserModel {
  constructor(email: string | undefined, pass: string | undefined, nombre: string | undefined, apellidos: string | undefined, imagen: string | undefined)
  {
    this.email = email;
    this.username = email;
    this.password = pass;
    this.firstName = nombre;
    this.lastName = apellidos;
    this.imageBase64 = imagen;
  }
  id: number | undefined;
  email: string | undefined;
  username: string | undefined;
  password: string | undefined;
  firstName: string | undefined;
  lastName: string | undefined;
  lastLoggedOn: Date | undefined;
  registeredOn: Date | undefined;
  attempts: number | undefined;
  role: string | undefined;
  imageBase64:string | undefined;
  permissions:any;
  lastPasswordResetDate:any;

}
