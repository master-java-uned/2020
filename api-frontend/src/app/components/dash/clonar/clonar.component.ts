/**
 * Peter Fight  17/05/2020
 *
 * Para poder generar usuarios que aparezcan en los listados que ve el admin
 */

import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {userAdapters} from "../../../adapters/userAdapters";
import {Title} from "@angular/platform-browser";
import {UserService} from "../../../userServices/user.service";
import {alertas} from "../../../app_code/viewsUtils/Alertas";

@Component({
  selector: 'app-clonar',
  templateUrl: './clonar.component.html',
  styleUrls: ['./clonar.component.css']
})
export class ClonarComponent implements OnInit {
  router: Router;

  constructor(router: Router, private formBuilder: FormBuilder,  private userService: UserService) {
    this.router = router;
  }


  /**
   * *********************************************************************
   *                      FORMULARIO Y VALIDACIÓN
   * *********************************************************************
   */
  datosFormulario;
  form: FormGroup;

  ngOnInit() {
    this.datosFormulario = this.formBuilder.group({
      Cantidad: ["", Validators.compose([Validators.required])]
    });
  }

  /**
   * *********************************************************************
   *                            POST DEL FORMULARIO
   * *********************************************************************
   */
  /**
   * ENVIO DE FORMULARIO
   * @param datos
   */
  async onSubmit(datos) {
    let cantidad = this.datosFormulario.value.Cantidad;
    let x = this.userService.clonarOvejitas(cantidad);
    x.then((result:any) => {
      //Ha ido guay.
      alertas.exito("Clonación exitosa", "Se han creado " + cantidad +" clones a partir de tu perfil.")
    }, function(error){
      //Se ha producido un error
      alertas.error("Se ha producido un error", "Se ha producido un error al tratar de clonar tu perfil");
    })
  }
}
