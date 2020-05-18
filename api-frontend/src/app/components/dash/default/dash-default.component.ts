/**
 * Peter Fight
 *
 * Default del dash
 */

import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {DomSanitizer, Meta, Title} from "@angular/platform-browser";
import {Roles, rolesPosibles} from "../../../commons/GestorRoles";
import {alertas} from "../../../commons/Alertas";
import {UserService} from "../../../userServices/user.service";

@Component({
  selector: 'app-default',
  templateUrl: './dash-default.component.html',
  styleUrls: ['./dash-default.component.css']
})
export class DashDefaultComponent implements OnInit {

  esSuper: boolean;
  router: Router;

  constructor(sanitizer: DomSanitizer, meta: Meta, title: Title, router: Router) {
    this.router = router;
    var gestorRol = new Roles();
    let rol = gestorRol.getRol();
    if (gestorRol.getRol().roleInternalId == rolesPosibles.ANONIMO) {
      //Bloqueo acceso, redirijo a login y alerto del error
      this.router.navigate(['/login']);
      alertas.error("No autorizado", "No estás autorizado para consultar esta zona. Por favor, identifícate");
    }
    if (gestorRol.getRol().roleInternalId == rolesPosibles.SUPERADMIN) {
      this.esSuper = true;
    } else {
      this.esSuper = false;
    }
  }

  ngOnInit() {

  }

}
