/**
 * Peter Fight
 *
 * ArrayPaisesCasosPorFecha
 * Pinta un mapa teniendo en cuenta la fecha proporcionada
 */


import ArrayPaisesFactory from "../ArrayPaisesFactory";
import ModeloDatosCovid from "../../ModeloDatosCovid";
// const { JSDOM } = require( "jsdom" );
// const { window } = new JSDOM( "" );
// const $ = require( "jquery" )( window );
const $ = require( "jquery" );
import "jvectormap-next";

export class ArrayPaisesCasosPorFecha implements ArrayPaisesFactory{
  casosMin:number;
  casosMax:number;
  fecha:string;
  arr:Array<any>;

  constructor(fecha:any){
    this.fecha = fecha;
    this.casosMin = 0;
    this.casosMax = this.getCasosMaxByFecha();
    this.arr = this.damePaisColorCasos();

    // return arr;
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

    let f = this.fecha;
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
        //casos
        for (var i = 0; i < ModeloDatosCovid.distinctPaises.length; i++) {
          var pais = ModeloDatosCovid.distinctPaises[i];
          if(pais.idPais.toLowerCase() == code.toLowerCase())
          {
            pais.arrayInfoDias.forEach(function (value: { dia: string; casos: string; }) {
              if (value.dia == f) {
                $(el).html($(el).html() + "("+ value.casos +")");
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



  /**
   * Máximo de casos que hay en una fecha -> para montar los colores en una intensidad concreta
   * @param fecha
   */
  private getCasosMaxByFecha(){
    var cMax = 0;
    let f = this.fecha;
    for (var i = 0; i < ModeloDatosCovid.distinctPaises.length; i++) {
      var pais = ModeloDatosCovid.distinctPaises[i];
      pais.arrayInfoDias.forEach(function (value: { dia: string; casos: string | number; }) {
        if (value.dia == f && parseInt(String(cMax)) < parseInt(value.casos.toString())) {
          cMax = parseInt(value.casos.toString());
        }
      });
    };
    return cMax;
  }


  /**
   * devuelve el array con intensidad de color correspondiente
   * @param fecha
   */
  private damePaisColorCasos()
  {
    let f = this.fecha;
    let cmax = this.casosMax;
    var arrayClaveValor: any[] = [];
    for (var i = 0; i < ModeloDatosCovid.distinctPaises.length; i++) {
      var pais = ModeloDatosCovid.distinctPaises[i];
      pais.arrayInfoDias.forEach(function (value: { dia: string; casos: string; }) {
        if (value.dia == f) {
          var porcentaje = parseFloat(value.casos) / parseFloat(String(cmax));

          var idPais = pais.idPais.toString().toLowerCase();
          var cantidadNegro = parseInt((porcentaje * 255).toString());
          var color = 'rgb('+(255 -cantidadNegro)+', '+(255 -cantidadNegro)+', '+(255 -cantidadNegro)+')';
          if(color == "rgb(NaN, NaN, NaN)")
          {
            color = "rgb(255,255,255)";
          }
          arrayClaveValor[idPais.toUpperCase()] = color;
        }
      });
    };
    return arrayClaveValor;
  }

}
