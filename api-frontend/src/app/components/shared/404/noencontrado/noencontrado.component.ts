/**
 * Peter fight
 *
 * Pagina 404 sin misterio
 */


import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {BaseLayout} from "../../../../commons/BaseLayout";
import {Almacen} from "../../../../commons/Almacen";
import {DomSanitizer, Meta, Title} from "@angular/platform-browser";

@Component({
  selector: 'app-noencontrado',
  templateUrl: './noencontrado.component.html',
  styleUrls: ['./noencontrado.component.css',
    "../../../../../assets/css/bootstrap.min.css",
    "../../../../../assets/css/nunitoFontGoogle.css",
    "../../../../../assets/css/audiowidefont.css"],
  encapsulation: ViewEncapsulation.None
})
export class NoencontradoComponent extends BaseLayout implements OnInit  {

  constructor(sanitizer: DomSanitizer, meta: Meta, title:Title) {
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
  }
  ngOnInit() {
  }
}

