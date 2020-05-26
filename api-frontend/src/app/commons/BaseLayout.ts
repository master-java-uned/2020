/**
 * Created by PeterFight (03/05/2020):
 * Implementa la interfaz para gestionar el contenido que no va en el body: metas, scripts y styles...
 * Los scripts los meto en al final del body (buena práctica) y los styles en el constructor de cada component
 */
import {DomSanitizer, Meta} from '@angular/platform-browser';
import { BrowserModule, Title } from '@angular/platform-browser';

export abstract class BaseLayout implements IBaseLayout{
  metaTitle: string;
  metaDesc: string;
  metaKeys: Array<string>;

  title:Title

  constructor(sanitizer: DomSanitizer, meta: Meta, mt: string, md: string, mk: Array<string>, title:Title)
  {
    this.metaTitle = mt;
    this.metaDesc = md;
    this.metaKeys = mk;
    this.title = title;
    //Llamo al método para construir el header, sí o sí, dado que esta es la gracia de esta interfaz...
    this.buildHead(meta, sanitizer);
  }
  private buildHead(meta: Meta, sanitizer: DomSanitizer){
    meta.addTag({
      name:"title",content:this.metaTitle
    });
    meta.addTag({
      name:"description",content:this.metaDesc
    });
    meta.addTag({
      name:"keywords",content:this.metaKeys.join(",")
    });
    this.title.setTitle(this.metaTitle)
  }
}
