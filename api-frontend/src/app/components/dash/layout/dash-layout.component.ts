/**
 * Peter Fight
 *
 * Dash Layout
 */

import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {BaseLayout} from "../../../app_code/viewsUtils/BaseLayout";
import {DomSanitizer, Meta, Title} from "@angular/platform-browser";
import {Roles, rolesPosibles} from "../../../app_code/userUtils/GestorRoles";
import {Router} from "@angular/router";
import {alertas} from "../../../app_code/viewsUtils/Alertas";

@Component({
  selector: 'app-usuarios',
  templateUrl: './dash-layout.component.html',
  styleUrls: [
    "../../../../assets/css/bootstrap.min.css",
    "../../../../assets/css/nunitoFontGoogle.css",
    "../../../../assets/css/audiowidefont.css",
    './dash-layout.component.css'],
  encapsulation: ViewEncapsulation.None,
})
export class DashLayoutComponent extends BaseLayout implements OnInit {

  esSuper:boolean;
  router:Router;

  constructor(sanitizer: DomSanitizer, meta: Meta, title:Title, router: Router) {
    /**
     * Yo llamo al constructor de BaseLayout en el layout, y luego heredaré otra vez en los componentes
     * para sobre-escribir las metas y extender los styles y scripts
     */

    var metaTitle: string = "Covid Máster Java (UNED)";
    var metaDesc: string = "This web application is intended to be the world's Covid reference site. Developed in Spain (El Fary's land).";
    var metaKeys: Array<string> = [
      "covid",
      "coronavirus",
      "fake",
      "news",
      "uned",
      "apocalipsis",
      "fary"
    ];
    super(sanitizer, meta, metaTitle, metaDesc, metaKeys, title);

    this.router = router;

    var gestorRol = new Roles();
    let rol = gestorRol.getRol();
    if(gestorRol.getRol().roleInternalId == rolesPosibles.ANONIMO)
    {
      //Bloqueo acceso, redirijo a login y alerto del error
      this.router.navigate(['/login']);
      alertas.error("No autorizado", "No estás autorizado para consultar esta zona. Por favor, identifícate");
    }
    if(gestorRol.getRol().roleInternalId == rolesPosibles.SUPERADMIN)
    {
      this.esSuper = true;
    }
    else{
      this.esSuper = false;
    }
  }
  ngOnInit() {
  }

  scrollTop() {
    window.scroll(0,0);
  }
}
