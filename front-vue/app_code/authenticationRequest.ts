/**
 * Peter Fight
 * En el login posteo este objeto que es más corto, para no volvernos locos con nombres y ya de paso encapsular un poquillo
 */
export class authenticationRequest {
  username: string;
  password: string;

  constructor(username: string, pass: string)
  {
    this.username = username;
    this.password = pass;
  }
}
