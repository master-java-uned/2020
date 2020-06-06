/**
 * VIctor
 *
 * Modified by Peter Fight
 */

import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../../userServices/auth.service';
import { Router } from '@angular/router';
import {alertas} from "../../../app_code/viewsUtils/Alertas";
@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {

  constructor(
    private authenticationService: AuthService,
    private router: Router) {

  }

  ngOnInit() {
    this.authenticationService.logOut();

    /**
     * Limpio las variables chungas de sesión
     */
    sessionStorage.setItem('username', "");
    sessionStorage.setItem('token', "");
    sessionStorage.setItem("rolId", "");

    this.router.navigate(['login']);
    alertas.exito("Desconectado", "Has cerrado la sesión correctamente");
  }

}
