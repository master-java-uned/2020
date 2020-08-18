/*
Peter fight, empleo la librería sweetAlert para mostrar alertas chulas
MIRA AQUÍ --> https://www.npmjs.com/package/sweetalert
 */
const Swal = require("sweetalert2");

export class alertas {
  // static exito(titulo: any, mensaje: any) {
  //   Swal.fire(
  //     titulo,
  //     mensaje,
  //     'success'
  //   )
  // }
  static exito(titulo: any, mensaje: any, redireccion: any) {
    Swal.fire(
      titulo,
      mensaje,
      'success'
    ).then((res: any) => {
      if (redireccion != undefined && redireccion != null && redireccion != "") {
        window.location.href = redireccion;
      }
    })
  }
  static error(titulo: any, mensaje: any) {
    Swal.fire(
      titulo,
      mensaje,
      'error'
    )
  }
}
