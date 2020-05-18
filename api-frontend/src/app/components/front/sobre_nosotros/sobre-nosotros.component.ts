/**
 *  Peter Fight
 *  09/05/2020
 *
 *  Componente que gestiona la página de Sobre nosotros
 */


import { Component, OnInit } from '@angular/core';
import {BaseLayout} from "../../../commons/BaseLayout";
import {DomSanitizer, Meta, Title} from "@angular/platform-browser";
import {Router} from "@angular/router";
import {FormBuilder} from "@angular/forms";

@Component({
  selector: 'app-sobre-nosotros',
  templateUrl: './sobre-nosotros.component.html',
  styleUrls: ['./sobre-nosotros.component.css']
})
export class SobreNosotrosComponent extends BaseLayout implements OnInit {

  sanitizer: DomSanitizer;
  /**
   * *********************************************************************
   *                        CONSTRUCTOR
   * *********************************************************************
   * En el constructor incluyo la metainformación para el SEO
   */
  constructor(sanitizer: DomSanitizer, meta: Meta, router: Router, private formBuilder: FormBuilder, title:Title) {
    super(sanitizer, meta,
      "Sobre nosotros - Covid Máster Java (Uned)",
      "Página sobre nosostros de la web Covid Máster Java (Uned)",
      ["covid",
        "coronavirus",
        "fake",
        "news",
        "uned",
        "apocalipsis",
        "fary"], title);
    this.sanitizer = sanitizer;
  }


  ngOnInit() {
  }

}
