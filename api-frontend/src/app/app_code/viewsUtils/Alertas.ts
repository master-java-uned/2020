/*
Peter fight, empleo la librería sweetAlert para mostrar alertas chulas
MIRA AQUÍ --> https://www.npmjs.com/package/sweetalert
 */

import Swal from "sweetalert2";

export class alertas{
  static exito(titulo, mensaje){
    Swal.fire(
      titulo,
      mensaje,
      'success'
    )
  }
  static error(titulo, mensaje)
  {
    Swal.fire(
      titulo,
      mensaje,
      'error'
    )
  }
}
