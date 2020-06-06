/**
 * Peter Fight
 *
 * Clase para gestionar los roles, tiene un getter para devolver el rol que tira del sessionStorage que a su vez
 * se llena cuando se hace el login.
 */


export enum rolesPosibles{
  ANONIMO=1,
  ADMIN=2,
  SUPERADMIN=3
}



export class Roles {
  roleName: string;
  roleInternalId: rolesPosibles;


  /**
   * Dime con qué enum y te diré quien eres... uuuuuuhhhhh...
   * @param rolId
   */
  constructor(rolId?: number) {
    this.roleName = rolesPosibles[rolId];
    this.roleInternalId = rolId;
  }

  getRol() {
    if(sessionStorage.getItem("rolId").length == 0)
    {
      /**
       * Keep It Comin' Love!!!, don't stop it nooooow... (KC And The Sunshine Band)
       */
      let rol = new Roles(rolesPosibles.ANONIMO);
      sessionStorage.setItem("rolId", JSON.stringify(rol))
      return new Roles(1);//Devuelvo rol anónimo
    }
    else{
      let rol = new Roles(parseInt(sessionStorage.getItem("rolId")));
      return rol;
    }
  }
}
