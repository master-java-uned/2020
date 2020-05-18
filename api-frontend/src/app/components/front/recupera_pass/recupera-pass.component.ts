/**
 *  Peter Fight
 *  09/05/2020
 *
 *  Componente que gestiona el registro de usuarios
 */

import { Component, OnInit } from '@angular/core';
import {BaseLayout} from "../../../commons/BaseLayout";
import {Router} from "@angular/router";
import {DomSanitizer, Meta, Title} from "@angular/platform-browser";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../../userServices/auth.service";

@Component({
  selector: 'app-recupera-pass',
  templateUrl: './recupera-pass.component.html',
  styleUrls: ['./recupera-pass.component.css']
})
export class RecuperaPassComponent extends BaseLayout implements OnInit {

  username = ''
  password = ''
  invalidLogin = false
  router: Router;

  sanitizer: DomSanitizer;
  /**
   * *********************************************************************
   *                        CONSTRUCTOR
   * *********************************************************************
   * En el constructor incluyo la metainformación para el SEO
   */
  constructor(sanitizer: DomSanitizer, meta: Meta, router: Router, private formBuilder: FormBuilder, private loginservice: AuthService, title:Title) {
    super(sanitizer, meta,
      "Entrar - Covid Máster Java (Uned)",
      "Formulario de Login en la web Covid Máster Java (Uned)",
      ["covid",
        "coronavirus",
        "fake",
        "news",
        "uned",
        "apocalipsis",
        "fary"], title);
    this.sanitizer = sanitizer;
    this.router = router;
  }








  /**
   * *********************************************************************
   *                      FORMULARIO Y VALIDACIÓN
   * *********************************************************************
   */
  datosFormulario;
  form: FormGroup;
  /**
   * ngOnInit -> instancio los controles y sus validadores
   */
  ngOnInit() {
    //Creo los campos del formGroup e incluyo los validadores
    this.datosFormulario = this.formBuilder.group({
      Email: ["", Validators.compose([Validators.required, Validators.email, Validators.minLength(1)])],

    });
  }






  /**
   * *********************************************************************
   *                            GESTIÓN DE ERRORES
   * *********************************************************************
   */

  /**
   * Array que contiene todos los mensajes de error
   */
  errores: string[] = [];
  /**
   * Seteo esta variable a true una vez se ha hecho el primer postback
   * tras el primer postback, en cualquier cambio de input verifico de nuevo la validación
   */
  validando:boolean = false;
  /**
   * Llamo a este evento a través del onChange de los inputs.
   * @param cambiaImagen Si el evento de input changed lo produce el cambio de imagen, ésta tiene
   * que ser procesada una vez se captura el evento, de modo que empleo este parámetro para incluir
   * como imagen rellenada aquel caso en el que el evento es aquél en el que se ha modificado el archivo.
   */
  validacionDinamica(cambiaImagen:boolean){
    if(this.validando)//Una vez hecho el primer postback, para cada cambio de input vuelvo a chequear
      //la validación de todos los campos
    {
      this.errores = [];
      this.checkErrores(cambiaImagen);
    }
  }
  /**
   * Gestión de errores
   * @param cambiaImagen Si el evento de input changed lo produce el cambio de imagen, ésta tiene
   * que ser procesada una vez se captura el evento, de modo que empleo este parámetro para incluir
   * como imagen rellenada aquel caso en el que el evento es aquél en el que se ha modificado el archivo.
   */
  checkErrores(cambiaImagen:boolean){
    /**
     * Recorro el formulario en busca de controles con error. En ese caso
     * los incluyo en el array de errores y los pinto en el front.
     */
    Object.keys(this.datosFormulario.controls).forEach(controlName => {
      let control = this.datosFormulario.controls[controlName];
      let errors = control.errors;
      if (errors === null || errors.count === 0) {
        return;
      }
      // Handle the 'required' case
      if (errors.required) {
        //campo requerido
        this.errores.push(`${controlName} es un campo requerido`);
      }
      if(errors.email)
      {
        //mail mal formado
        this.errores.push("El formato del email es incorrecto");
      }
      if(errors.isMatching != null && errors.isMatching != undefined && errors.isMatching == false)
      {
        //Los pass no coinciden
        this.errores.push("Los passwords no coinciden");
      }
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
  onSubmit(datos) {
    /**
     * Primero compruebo si el formulario es válido
     */
    if(!this.datosFormulario.valid)
    {
      this.errores = [];//Limpio los errores para eliminar los errores de validaciones previas
      this.checkErrores(null);
      this.validando = true;//Después del primer postback, ante cualquier modificación en el formulario
      //actualizo la validación
    }
    // const formData = new FormData();
    // formData.append('file', this.uploadForm.get('profile').value);
    //
    // this.httpClient.post<any>(this.SERVER_URL, formData).subscribe(
    //   (res) => console.log(res),
    //   (err) => console.log(err)
    // );

  }



}
