
/**
 * Peter Fight
 * Importante que se corresponda con el modelo de la web, si no, mal vamos...
 */
export class UserModel {
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
  permissions:any;
  lastPasswordResetDate:any;

}
