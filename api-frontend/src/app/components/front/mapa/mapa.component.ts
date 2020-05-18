/**
 *  Peter Fight
 *  09/05/2020
 *
 *  Componente que gestiona los mapas. Para los mapas empleo jqueryVectorMap porque es gratiiiis y mucho
 *  más fácil de implementar para el problema que nos ocupa que openstreetmap. Y el resultado es igual
 *  o máIs mejor.
 */


import {Component, ElementRef, OnInit} from '@angular/core';
import {BaseLayout} from "../../../commons/BaseLayout";
import {DomSanitizer, Meta, Title} from "@angular/platform-browser";
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import {FormBuilder} from "@angular/forms";
import {ModeloDatosCovid} from "../../../models/ModeloDatosCovid";


@Component({
  selector: 'app-mapa',
  templateUrl: './mapa.component.html',
  styleUrls: ['./mapa.component.css']
})
export class MapaComponent extends BaseLayout implements OnInit {
  continente:string;
  sanitizer: DomSanitizer;
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
   * Muestra el mapa una fecha en concreto
   * @param fechaActual la fecha
   * @param muestraTooltips si es false, no muestra los tooltips de nombre país con los casos, porque cascaba
   * al lanzar la animación
   */
  updateArrays(fechaActual, muestraTooltips){
    this.textoFechas = fechaActual;

    switch (this.tipoMapa) {
      case(1):
        /**
         * 1 -> muertos en una fecha
         */
        this.arrayPaisesMuertesPorFecha = this.dameArrayPaisesMuertesForFecha(fechaActual);
        this.pintaMapa(this.arrayPaisesMuertesPorFecha, this.tipoMapa, fechaActual, muestraTooltips);
        break;
      case(2):
        /**
         * 2 -> casos en una fecha
         */
        this.arrayPaisesCasosPorFecha = this.dameArrayPaisesCasosForFecha(fechaActual);
        this.pintaMapa(this.arrayPaisesCasosPorFecha, this.tipoMapa, fechaActual, muestraTooltips);
        break;
      case(3):
        /**
         * 3 -> Muertos acumulado a una fecha
         */
        this.arrayPaisesMuertesAcumuladoPorFecha = this.dameArrayPaisesMuertesAcumuladoForFecha(fechaActual);
        this.pintaMapa(this.arrayPaisesMuertesAcumuladoPorFecha, this.tipoMapa, fechaActual, muestraTooltips);
        break;
      case(4):
        /**
         * 4 -> Casos acumulados a una fecha
         */
        this.arrayPaisesCasosAcumuladoPorFecha = this.dameArrayPaisesCasosAcumuladoForFecha(fechaActual);
        this.pintaMapa(this.arrayPaisesCasosAcumuladoPorFecha, this.tipoMapa, fechaActual, muestraTooltips);
        break;
    }
  }

  /**
   * Cuando el tipo de mapa cambia, recárgalo
   */
  tipoMapa:number = 3;
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


  /**
   * Aquí pinto el mapa
   * @param arrayPaisesColores array que contiene el color con el que se mostrará el mapa
   * @param tipo determina de qué variable sacamos los datos
   * @param fecha la fecha a pintar
   * @param muestraTooltips si es false, no muestra los tooltips de nombre país con los casos, porque cascaba
   * al lanzar la animación
   */
  pintaMapa(arrayPaisesColores, tipo, fecha, muestraTooltips)
  {
    $('#world-map').html("");
    $('#world-map').vectorMap({
      map: 'world_en',
      backgroundColor: "white",
      focusOn: {
        x: 0,
        y: 0,
        scale: 1
      },
      series: {
        regions: [{
          values: arrayPaisesColores
        }]
      },
      onRegionTipShow: function(e, el, code){
        switch (tipo) {
          case(1):
            //muertes
            for (var i = 0; i < ModeloDatosCovid.distinctPaises.length; i++) {
              var pais = ModeloDatosCovid.distinctPaises[i];
              if(pais.idPais.toLowerCase() == code)
              {
                pais.arrayInfoDias.forEach(function (value) {
                  if (value.dia == fecha) {
                    el.html(el.html() + "("+ value.muertes +")");
                  }
                })
              }
            }
            break;
          case(2):
            //casos
            for (var i = 0; i < ModeloDatosCovid.distinctPaises.length; i++) {
              var pais = ModeloDatosCovid.distinctPaises[i];
              if(pais.idPais.toLowerCase() == code)
              {

                pais.arrayInfoDias.forEach(function (value) {
                  if (value.dia == fecha) {
                    el.html(el.html() + "("+ value.casos +")");
                  }
                })
              }
            }
            break;


          case(3):
            //muertes acumulado
            for (var i = 0; i < ModeloDatosCovid.distinctPaises.length; i++) {
              var pais = ModeloDatosCovid.distinctPaises[i];
              if(pais.idPais.toLowerCase() == code)
              {
                var totalMuertes = 0;
                pais.arrayInfoDias.forEach(function (value) {
                  if (moment(value.dia, "DD/MM/YYYY") < moment(fecha, "DD/MM/YYYY")) {
                    totalMuertes += parseInt(value.muertes);
                  }
                })
                el.html(el.html() + "("+ totalMuertes +")");
              }
            }
            break;
          case(4):
            //casos acumulado
            for (var i = 0; i < ModeloDatosCovid.distinctPaises.length; i++) {
              var pais = ModeloDatosCovid.distinctPaises[i];
              if(pais.idPais.toLowerCase() == code)
              {

                var totalCasos = 0;
                pais.arrayInfoDias.forEach(function (value) {
                  if (moment(value.dia, "DD/MM/YYYY") < moment(fecha, "DD/MM/YYYY")) {
                    totalMuertes += parseInt(value.casos);
                  }
                })
                el.html(el.html() + "("+ totalCasos +")");
              }
            }
            break;
        }
        if(muestraTooltips == false)
        {
          /**
           * SI no le meto la ñapa esta al hacer hover se van los estilos a tomar por saco
           */
          el.html("");
          return false;
        }
      }
    });
    //Le meto el color al mar una vez se ha cargado el mapa
    $(".jvectormap-container").css("background","lightblue");
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
   * Máximo de muertes que hay en una fecha -> para montar los colores en una intensidad concreta
   * @param fecha
   */
  getMuertesMaxByFecha(fecha){
    var mMax = 0;
    for (var i = 0; i < ModeloDatosCovid.distinctPaises.length; i++) {
      var pais = ModeloDatosCovid.distinctPaises[i];
      pais.arrayInfoDias.forEach(function (value) {
        if (value.dia == fecha && parseInt(String(mMax)) < parseInt(value.muertes)) {
          mMax = value.muertes;
        }
      });
    };
    return mMax;
  }
  /**
   * Máximo de casos que hay en una fecha -> para montar los colores en una intensidad concreta
   * @param fecha
   */
  getCasosMaxByFecha(fecha){
    var cMax = 0;
    for (var i = 0; i < ModeloDatosCovid.distinctPaises.length; i++) {
      var pais = ModeloDatosCovid.distinctPaises[i];
      pais.arrayInfoDias.forEach(function (value) {
        if (value.dia == fecha && parseInt(String(cMax)) < parseInt(value.casos)) {
          cMax = value.casos;
        }
      });
    };
    return cMax;
  }


  /**
   * Máximo de muertes acumuladas que hay en una fecha -> para montar los colores en una intensidad concreta
   * @param fecha
   */
  getMuertesMaxAcumuladoByFecha(fecha){
    var mMax = 0;
    for (var i = 0; i < ModeloDatosCovid.distinctPaises.length; i++) {
      var pais = ModeloDatosCovid.distinctPaises[i];
      var vMaxPais = 0;
      pais.arrayInfoDias.forEach(function (value) {
        vMaxPais = parseInt(vMaxPais.toString()) + parseInt(value.muertes);
      });
      if(parseInt(vMaxPais.toString()) > parseInt(mMax.toString()))
      {
        mMax = vMaxPais;
      }
    };
    return mMax;
  }
  /**
   * Máximo de casos que hay en una fecha -> para montar los colores en una intensidad concreta
   * @param fecha
   */
  getCasosMaxAcumuladoByFecha(fecha){
    var cMax = 0;
    for (var i = 0; i < ModeloDatosCovid.distinctPaises.length; i++) {
      var pais = ModeloDatosCovid.distinctPaises[i];
      var pais = ModeloDatosCovid.distinctPaises[i];
      var vMaxPais = 0;
      pais.arrayInfoDias.forEach(function (value) {
      vMaxPais = parseInt(vMaxPais.toString()) + parseInt(value.casos);
      });
      if(parseInt(vMaxPais.toString()) > parseInt(cMax.toString()))
      {
        cMax = vMaxPais;
      }
    };
    return cMax;
  }








  /**
   * array con idPais, color de muertes en una ficha
   * @param fecha
   */
  dameArrayPaisesMuertesForFecha(fecha){
    var muertesMin = 0;
    var muertesMax = this.getMuertesMaxByFecha(fecha);
    var arr = this.damePaisColorMuertes(muertesMin, muertesMax, fecha);

    return arr;
  }
  /**
   * array con idPais, color de casos en una ficha
   * @param fecha
   */
  dameArrayPaisesCasosForFecha(fecha){
    var casosMin = 0;
    var casosMax = this.getCasosMaxByFecha(fecha);
    var arr = this.damePaisColorCasos(casosMin, casosMax, fecha);

    return arr;

  }


  /**
   * array con idPais, color de muertes acumuladas en una ficha
   * @param fecha
   */
  dameArrayPaisesMuertesAcumuladoForFecha(fecha){
    var muertesMin = 0;
    var muertesMax = this.getMuertesMaxAcumuladoByFecha(fecha);
    var arr = this.damePaisColorMuertesAcumulado(muertesMin, muertesMax, fecha);

    return arr;
  }
  /**
   * array con idPais, color de casos acumulados en una ficha
   * @param fecha
   */
  dameArrayPaisesCasosAcumuladoForFecha(fecha){
    var casosMin = 0;
    var casosMax = this.getCasosMaxAcumuladoByFecha(fecha);
    var arr = this.damePaisColorCasosAcumulado(casosMin, casosMax, fecha);

    return arr;

  }





  /**
   * devuelve el array con intensidad de color correspondiente
   * @param fecha
   */
  damePaisColorMuertes(min, max, fecha)
  {
    var arrayClaveValor = [];
    for (var i = 0; i < ModeloDatosCovid.distinctPaises.length; i++) {
      var pais = ModeloDatosCovid.distinctPaises[i];
      pais.arrayInfoDias.forEach(function (value) {
        if (value.dia == fecha) {
          var porcentaje = parseFloat(value.muertes) / parseFloat(max);
          var idPais = pais.idPais.toString().toLowerCase();
          var cantidadRojo = parseInt((porcentaje * 255).toString());
          var color = 'rgb('+(255 -cantidadRojo)+', '+(255 -cantidadRojo)+', '+(255 -cantidadRojo)+')';
          if(color == "rgb(NaN, NaN, NaN)")
          {
            color = "rgb(255,255,255)";
          }
          arrayClaveValor[idPais] = color;
          return true;
        }
      });
      if(arrayClaveValor[pais.idPais.toString().toLowerCase()] == undefined)
      {
        arrayClaveValor[pais.idPais.toString().toLowerCase()] ="rgb(255,255,255)";
      }
    };
    return arrayClaveValor;
  }

  /**
   * devuelve el array con intensidad de color correspondiente
   * @param fecha
   */
  damePaisColorCasos(min, max, fecha)
  {
    var arrayClaveValor = {};
    for (var i = 0; i < ModeloDatosCovid.distinctPaises.length; i++) {
      var pais = ModeloDatosCovid.distinctPaises[i];
      pais.arrayInfoDias.forEach(function (value) {
        if (value.dia == fecha) {
          var porcentaje = parseFloat(value.casos) / parseFloat(max);

          var idPais = pais.idPais.toString().toLowerCase();
          var cantidadRojo = parseInt((porcentaje * 255).toString());
          var color = 'rgb('+(255 -cantidadRojo)+', '+(255 -cantidadRojo)+', '+(255 -cantidadRojo)+')';
          if(color == "rgb(NaN, NaN, NaN)")
          {
            color = "rgb(255,255,255)";
          }
          arrayClaveValor[idPais] = color;
        }
      });
      if(arrayClaveValor[pais.idPais.toString().toLowerCase()] == undefined)
      {
        arrayClaveValor[pais.idPais.toString().toLowerCase()] ="rgb(255,255,255)";
      }
    };
    return arrayClaveValor;
  }




  /**
   * devuelve el array con intensidad de color correspondiente
   * @param fecha
   */
  damePaisColorMuertesAcumulado(min, max, fecha)
  {
    var arrayClaveValor = [];
    for (var i = 0; i < ModeloDatosCovid.distinctPaises.length; i++) {
      var pais = ModeloDatosCovid.distinctPaises[i];
      var muertesTotalesPaisAcumuladas = 0;
      pais.arrayInfoDias.forEach(function (value) {
        if (moment(value.dia, "DD/MM/YYYY") < moment(fecha, "DD/MM/YYYY")) {
          muertesTotalesPaisAcumuladas += parseInt(value.muertes);
        }
      })
      var porcentaje = parseFloat(String(muertesTotalesPaisAcumuladas)) / parseFloat(max);
      var idPais = pais.idPais.toString().toLowerCase();
      var cantidadRojo = porcentaje * 255;
      var color = 'rgb('+(255 -cantidadRojo)+', '+(255 -cantidadRojo)+', '+(255 -cantidadRojo)+')';
      if(color == "rgb(NaN, NaN, NaN)")
      {
        color = "rgb(255,255,255)";
      }
      arrayClaveValor[idPais] = color;
    };
    return arrayClaveValor;
  }
  /**
   * devuelve el array con intensidad de color correspondiente
   * @param fecha
   */
  damePaisColorCasosAcumulado(min, max, fecha)
  {
    var arrayClaveValor = {};
    for (var i = 0; i < ModeloDatosCovid.distinctPaises.length; i++) {
      var pais = ModeloDatosCovid.distinctPaises[i];
      var muertesTotalesPaisAcumuladas = 0;
      pais.arrayInfoDias.forEach(function (value) {
        if (moment(value.dia, "DD/MM/YYYY") < moment(fecha, "DD/MM/YYYY")) {
          muertesTotalesPaisAcumuladas += parseInt(value.casos);
        }
      })
      var porcentaje = parseFloat(String(muertesTotalesPaisAcumuladas)) / parseFloat(max);
      var idPais = pais.idPais.toString().toLowerCase();
      var cantidadRojo = porcentaje * 255;
      var color = 'rgb('+(255 -cantidadRojo)+', '+(255 -cantidadRojo)+', '+(255 -cantidadRojo)+')';
      if(color == "rgb(NaN, NaN, NaN)")
      {
        color = "rgb(255,255,255)";
      }
      arrayClaveValor[idPais] = color;
    };
    return arrayClaveValor;
  }
}
