/**
 *  Peter Fight
 *  09/05/2020
 *
 *  Componente que gestiona el registro de usuarios
 */

import {Component, NgModule, VERSION, Input, OnInit} from '@angular/core';
import {DomSanitizer, Meta, Title} from '@angular/platform-browser';
import {FormGroup, FormControl, Validators, AbstractControl, ValidatorFn, FormBuilder} from '@angular/forms';
import {ValidationErrors} from "@angular/forms/src/directives/validators";
import {Router} from "@angular/router";
import {BaseLayout} from "../../../app_code/viewsUtils/BaseLayout";
import {AuthService} from "../../../userServices/auth.service";
import {userAdapters} from "../../../adapters/userAdapters";
import {alertas} from "../../../app_code/viewsUtils/Alertas";
import * as bcrypt from 'bcryptjs';

const PassMatch: ValidatorFn = (fg: FormGroup) => {
  const pass = fg.get('Password').value;
  const rep = fg.get('RepitePassword').value;
  return pass !== "" && rep !== "" && pass != rep
    ? null
    : {range: true};
};

@Component({
  selector: 'app-registro',
  templateUrl: './registro.component.html',
  styleUrls: ['./registro.component.css'],
})


export class RegistroComponent extends BaseLayout implements OnInit {
  router: Router;
  sanitizer: DomSanitizer;
  /**
   * *********************************************************************
   *                        CONSTRUCTOR
   * *********************************************************************
   * En el constructor incluyo la metainformación para el SEO
   */
  constructor(sanitizer: DomSanitizer, meta: Meta, router: Router, private formBuilder: FormBuilder, title:Title,  private registerService: AuthService) {
    super(sanitizer, meta,
      "Registro - Covid Máster Java (Uned)",
      "Formulario de registro en la web Covid Máster Java (Uned)",
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
      Nombre: ["", Validators.compose([Validators.required, Validators.minLength(1)])],
      Apellidos: ["", Validators.compose([Validators.required, Validators.minLength(1)])],
      Email: ["", Validators.compose([Validators.required, Validators.email, Validators.minLength(1)])],
      Password: ["", Validators.compose([Validators.required, Validators.minLength(1)])],
      RepitePassword: [
        "",
        [
          Validators.required,
          RegistroComponent.matchValues("Password")
      ]],
    });
  }

  /**
   * Validador copiado de stackOverflow para comprobar que los passwords coinciden
   * ref -> https://stackoverflow.com/questions/51605737/confirm-password-validation-in-angular-6
   * @param matchTo el nombre del otro control sobre el que comprobar si coincide
   */
  public static matchValues(
    matchTo: string
  ): (AbstractControl) => ValidationErrors | null {
    return (control: AbstractControl): ValidationErrors | null => {
      return !!control.parent &&
      !!control.parent.value &&
      control.value === control.parent.controls[matchTo].value
        ? null
        : { isMatching: false };
    };
  }






  /**
   * *********************************************************************
   *                            IMAGEN
   * *********************************************************************
   */
  /**
   * GESTIÓN IMAGEN -> Capturo el evento y convierto el archivo del cliente en base64
   * y la añado a la variable filepreview (a su vez sirve para pintarla en el front)
   * así como el nombre del archivo
   * @param event capturo el evento
   */
  fileName: string;
  filePreview: string;
  cargandoFichero:boolean;
  onFileChanged(event) {
    this.cargandoFichero = true;
    let reader = new FileReader();
    if (event.target.files && event.target.files.length > 0) {
      let file = event.target.files[0];
      reader.readAsDataURL(file);
      reader.onload = () => {
        this.fileName = file.name + " " + file.type;
        this.filePreview = 'data:image/png' + ';base64,' + reader.result.split(',')[1];
      };
    }
  }

  /**
   * El metodo sanitize permite emplear como url de la etiqueta img la imagen en base64
   * @param url
   */
  sanitize(url: string) {
    //return url;
    return this.sanitizer.bypassSecurityTrustUrl(url);
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
    if(
      (this.fileName == null || this.fileName == undefined || this.fileName.length == 0)
      &&
      cambiaImagen == null || cambiaImagen == undefined || cambiaImagen == false)
    {
      this.errores.push("La imagen de perfil es obligatoria");
    }
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
  async onSubmit(datos) {
    /**
     * Primero compruebo si el formulario es válido
     */
    if(!this.datosFormulario.valid
      || this.filePreview == undefined || this.filePreview == null || this.filePreview.length == 0)
    {
      this.errores = [];//Limpio los errores para eliminar los errores de validaciones previas
      this.checkErrores(null);
      this.validando = true;//Después del primer postback, ante cualquier modificación en el formulario
      //actualizo la validación
    }
    else {
      //Si no está metida, la meto, por si acaso
      this.datosFormulario.value.imageBase64 = this.filePreview;
      //Encripto el password
      const salt = bcrypt.genSaltSync(10);
      var pass = bcrypt.hashSync(this.datosFormulario.value.Password, salt);
      this.datosFormulario.value.Password = pass;
      let x = this.registerService.registrame_PerFavoooore(userAdapters.get(this.datosFormulario));
      x.then((result:any) => {
        //Ha ido guay
        if(result.listadoErrores != undefined && result.listadoErrores != null
          && result.listadoErrores.length > 0)
        {
          this.errores = [];
          for(var i = 0; i<result.listadoErrores.length; i++)
          {
            var err = result.listadoErrores[i];
            //TODO: De momento sólo muestro errores en español, aunque tengo el resto
            this.errores.push(err.listadoErrores[0].mensajeError);//[0] primer idioma, español
          }
          alertas.error("Se ha producido un error", "Por favor revisa el listado de mensajes de error");
        }
        else{
          //Registro ok, redirijo y muestro mensaje
          this.router.navigate(["/login"])
          alertas.exito("Registrado", "El registro se ha procesado correctamente");
        }
      }, function(error){
        //Se ha producido un error chungo
        //TODO: Meter modal advirtiendo de error inesperado, de momento en plan zaaaRdo.
        alertas.error("Error", "Se ha producido un error inesperado");
      });
    }
  }
}


