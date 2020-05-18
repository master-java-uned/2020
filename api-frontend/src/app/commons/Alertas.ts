/*
Peter fight, empleo la librería sweetAlert para mostrar alertas chulas
MIRA AQUÍ --> https://www.npmjs.com/package/sweetalert
 */

import swal from "sweetalert";

export class alertas{
  static exito(titulo, mensaje){
    swal(
      titulo,
      mensaje,
      'success'
    )
  }
  static error(titulo, mensaje)
  {
    swal(
      titulo,
      mensaje,
      'error'
    )
  }
}
