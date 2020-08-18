/**
 * Peter Fight
 *
 * ArrayPaisesMuertesPorFecha
 * Pinta un mapa teniendo en cuenta la fecha proporcionada
 */

import ArrayPaisesFactory from "../ArrayPaisesFactory";
import ModeloDatosCovid from "../../ModeloDatosCovid";
// const { JSDOM } = require( "jsdom" );
// const { window } = new JSDOM( "" );
// const $ = require( "jquery" )( window );

const $ = require( "jquery" );


export class ArrayPaisesMuertesPorFecha implements ArrayPaisesFactory{
  muertesMin:number;
  muertesMax:number;
  arr:Array<any>;
  fecha:string;

  constructor(fecha:any){
    this.fecha = fecha;
    this.muertesMin = 0;
    this.muertesMax = this.getMuertesMaxByFecha();
    this.arr = this.damePaisColorMuertes();

    // return this.arr;
  }

  /**
   * Máximo de muertes que hay en una fecha -> para montar los colores en una intensidad concreta
   * @param fecha
   */
  private getMuertesMaxByFecha(){
    let f = this.fecha;
    var mMax = 0;
    for (var i = 0; i < ModeloDatosCovid.distinctPaises.length; i++) {
      var pais = ModeloDatosCovid.distinctPaises[i];
      pais.arrayInfoDias.forEach(function (value: { dia: string; muertes: string | number; }) {
        if (value.dia == f && parseInt(String(mMax)) < parseInt(value.muertes.toString())) {
          mMax = parseInt(value.muertes.toString());
        }
      });
    };
    return mMax;
  }

  /**
   * devuelve el array con intensidad de color correspondiente
   * @param fecha
   */
  private damePaisColorMuertes()
  {
    let f = this.fecha;
    let mmax = this.muertesMax;
    var arrayClaveValor: any[] = [];
    for (var i = 0; i < ModeloDatosCovid.distinctPaises.length; i++) {
      var pais = ModeloDatosCovid.distinctPaises[i];
      pais.arrayInfoDias.forEach(function (value: { dia: string; muertes: string; }) {
        if (value.dia == f) {
          var porcentaje = parseFloat(value.muertes) / parseFloat(String(mmax));
          var idPais = pais.idPais.toString().toLowerCase();
          var cantidadNegro = parseInt((porcentaje * 255).toString());
          var color = 'rgb('+(255 -cantidadNegro)+', '+(255 -cantidadNegro)+', '+(255 -cantidadNegro)+')';
          if(color == "rgb(NaN, NaN, NaN)")
          {
            color = "rgb(255,255,255)";
          }
          arrayClaveValor[idPais.toUpperCase()] = color;
          return true;
        }
      });
    };
    return arrayClaveValor;
  }


  /**
   * Aquí pinto el mapa
   * @param arrayPaisesColores array que contiene el color con el que se mostrará el mapa
   * @param tipo determina de qué variable sacamos los datos
   * @param fecha la fecha a pintar
   * @param muestraTooltips si es false, no muestra los tooltips de nombre país con los casos, porque cascaba
   * al lanzar la animación
   */
  pintaMapa(muestraTooltips: boolean)
  {

    var f = this.fecha;
    $('#world-map').html("");
    // @ts-ignore
    require('jvectormap-next')($);
    // @ts-ignore
    $.fn.vectorMap('addMap', 'world-mill', require('jvectormap-content/world-mill'));

    $('#world-map').vectorMap({
      map: 'world-mill',
      backgroundColor: "white",
      focusOn: {
        x: 0,
        y: 0,
        scale: 0
      },
      series: {
        regions: [{
          values: this.arr
        }]
      },
      onRegionTipShow: function(e: any, el: any, code: string){
        //muertes
        for (var i = 0; i < ModeloDatosCovid.distinctPaises.length; i++) {
          var pais = ModeloDatosCovid.distinctPaises[i];
          if(pais.idPais.toLowerCase() == code.toLowerCase())
          {
            pais.arrayInfoDias.forEach(function (value: { dia: string; muertes: string; }) {
              if (value.dia == f) {
                $(el).html($(el).html() + "("+ value.muertes +")");
              }
            })
          }
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
    $(".jvectormap-container").css(
      "height", "500px");
  }
}
