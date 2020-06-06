// Peter Fight
// 09/05/2020
// CODEBEHIND DEL LAYOUT DEL FRONT

import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {BaseLayout} from "../../../app_code/viewsUtils/BaseLayout";
import {DomSanitizer, Meta} from "@angular/platform-browser";
import {Router, NavigationEnd, NavigationStart} from '@angular/router';

import {ModeloDatosCovid} from "../../../models/ModeloDatosCovid";

/**
 * CARGO TODAS LAS HOJAS DE ESTILO
 */
@Component({
  selector: 'app-default',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.css',
    "/../../../../assets/css/bootstrap.min.css",
    "../../../../assets/css/nunitoFontGoogle.css",
    "../../../../assets/css/audiowidefont.css"
    ],
    encapsulation: ViewEncapsulation.None,
})
export class LayoutComponent implements OnInit  {
  router: Router;
  constructor(sanitizer: DomSanitizer, meta: Meta, router: Router) {

  }
  ngOnInit() {
  }

  getModeloDatosCovid(){
    return ModeloDatosCovid;
  }

  isUserLogged(){
    if(sessionStorage.getItem("token") == null)
    {
      return false;
    }
    else {
      return sessionStorage.getItem("token").length > 0;
    }
  }

  scrollTop() {
    window.scroll(0,0);
  }
}
