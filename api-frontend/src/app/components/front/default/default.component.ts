/**
 * Peter Fight
 * 10/05/2020
 * Code Behind de la default
 */

import { Component, OnInit } from '@angular/core';
import {BaseLayout} from "../../../app_code/viewsUtils/BaseLayout";
import {DomSanitizer, Meta, Title} from "@angular/platform-browser";
import {Router} from "@angular/router";

@Component({
  selector: 'app-default',
  templateUrl: './default.component.html',
  styleUrls: ['./default.component.css']
})
export class DefaultComponent extends BaseLayout implements OnInit {

  /**
   * Llamo al constructor de BaseLayout en el layout
   */
  constructor(sanitizer: DomSanitizer, meta: Meta, router: Router, title:Title) {
    router = router;
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
