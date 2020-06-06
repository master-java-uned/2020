/**
 *  Peter Fight
 *  09/05/2020
 *
 *  Componente que gestiona los mapas. Para los mapas empleo jqueryVectorMap porque es gratiiiis y mucho
 *  más fácil de implementar para el problema que nos ocupa que openstreetmap. Y el resultado es igual
 *  o máIs mejor.
 */


import {Component, ElementRef, OnInit} from '@angular/core';
import {BaseLayout} from "../../../app_code/viewsUtils/BaseLayout";
import {DomSanitizer, Meta, Title} from "@angular/platform-browser";
import {ActivatedRoute, Router} from '@angular/router';
import {FormBuilder} from "@angular/forms";
import {tiposMaps} from "../../../app_code/maps/tiposMaps";
import {ArrayPaisesFactory} from "../../../app_code/maps/ArrayPaisesFactory";

import {ModeloDatosCovid} from "../../../models/ModeloDatosCovid";

@Component({
  selector: 'app-mapa',
  templateUrl: './mapa.component.html',
  styleUrls: ['./mapa.component.css']
})
export class MapaComponent extends BaseLayout implements OnInit {
  continente:string;
  sanitizer: DomSanitizer;
  Mapa:ArrayPaisesFactory;
  /**
   * Esta variable almacena las muertes que se ha producido un día en concreto (en array)
   */
  arrayPaisesMuertesPorFecha:Array<any>;
  /**
   * Los casos en un día (en array)
   */
  arrayPaisesCasosPorFecha:{};
  /**
   * Muertes que se han acumulado hasta un día concreto
   */
  arrayPaisesMuertesAcumuladoPorFecha:Array<any>;
  /**
   * Casos acumulados hasta un día concreto
   */
  arrayPaisesCasosAcumuladoPorFecha:{};


  /**
   * *********************************************************************
   *                        CONSTRUCTOR
   * *********************************************************************
   * En el constructor incluyo la metainformación para el SEO
   */
  constructor(sanitizer: DomSanitizer, meta: Meta,private route: ActivatedRoute,
              router: Router, private formBuilder: FormBuilder, title:Title,
              private _elementRef : ElementRef) {
    super(sanitizer, meta,
      "Mapa - Covid Máster Java (Uned)",
      "Mapas de la web Covid Máster Java (Uned)",
      ["covid",
        "coronavirus",
        "fake",
        "news",
        "uned",
        "apocalipsis",
        "fary"], title);
    this.sanitizer = sanitizer;
    this.tipoMapa = tiposMaps.MUERTES_ACUMULADAS_X_FECHA;
  }
  private sub: any;
  arrayFechas:Array<any>;
  ngOnInit() {
    /**
     * Esto estaba pensado por si segmentaba por continente, pero no tiene mucho sentido y parece
     * mejor, con los datos que tenemos, mostrar to'L mundo arround.
     */
    this.sub = this.route.params.subscribe(params => {
      this.continente = params['continent'];
    });

    /**
     * Saco todas las fechas posibles y las ordeno
     */
    this.arrayFechas = this.dameArrayFechas();
    /**
     * Ordeno las fechas
     */
    this.arrayFechas = this.arrayFechas.sort(function(a, b){
      var aa = a.split('/').reverse().join(),
        bb = b.split('/').reverse().join();
      return aa < bb ? -1 : (aa > bb ? 1 : 0);
    });

    /**
     *  Establezco la fecha a mostrar por defecto cuando carga el mapa
     */
    var fechaActual = this.arrayFechas[this.arrayFechas.length -1];

    this.updateArrays(fechaActual, true);
  }


  /**
   * Devuelve un array de objetos con las distintas fechas únicas que manejamos
   */
  dameArrayFechas(){
    var arrayDistinctFechas = [];
    for (var i = 0; i < ModeloDatosCovid.distinctPaises.length; i++) {
      var pais = ModeloDatosCovid.distinctPaises[i];
      pais.arrayInfoDias.forEach(function (value) {
        if (arrayDistinctFechas.indexOf(value.dia) === -1) {
          arrayDistinctFechas.push(value.dia);
        }
      });
    };
    return arrayDistinctFechas;
  }

  /**
   * Muestra el mapa una fecha en concreto
   * @param fechaActual la fecha
   * @param muestraTooltips si es false, no muestra los tooltips de nombre país con los casos, porque cascaba
   * al lanzar la animación
   */
  updateArrays(fechaActual:any, muestraTooltips){
    this.textoFechas = fechaActual;
    this.Mapa = ArrayPaisesFactory.getTipoFactory(this.tipoMapa, fechaActual);
    this.Mapa.pintaMapa(muestraTooltips);
  }

  /**
   * Cuando el tipo de mapa cambia, recárgalo
   */
  tipoMapa:tiposMaps;
  tipoMapaChanged(){
    this.tipoMapa = parseInt(this._elementRef.nativeElement.querySelector("#selectorTipoMapa").value);
    this.updateArrays(this.textoFechas, true);
  }

  /**
   * Cuando el slider de fechas cambia, recarga el mapa
   */
  textoFechas:string;
  updateFecha(e){
    var sliderValue = e.target.value;
    this.textoFechas = this.arrayFechas[parseInt(e.target.value)];
    this.updateArrays(this.arrayFechas[parseInt(e.target.value)], true)
  }

  /**
   * Lanza una animación que recorre todas las fechas
   */
  mostrarEvolucion(){
    var slider = this._elementRef.nativeElement.querySelector("#sliderFecha");
    // slider.value = 1;
    this.textoFechas = this.arrayFechas[1];
    this.updateArrays(this.arrayFechas[1], false);
    var internalArrayFechas = this.arrayFechas;
    this.animacionFechas(internalArrayFechas, 1);//Si no voy borrando las fechas buenas
  }

  /**
   * Recorredor de fechas
   * @param fechas
   * @param indice
   */
  private animacionFechas(fechas, indice) {
    indice = indice + 1;
    if (indice < this.arrayFechas.length) {
      let wait = fechas[indice];
      this.textoFechas = wait;
      this.updateArrays(wait, false);
      this._elementRef.nativeElement.querySelector("#sliderFecha").value = indice;
      setTimeout(() => {
        this.animacionFechas(fechas, indice);
      }, 50);
    }
  }
}
