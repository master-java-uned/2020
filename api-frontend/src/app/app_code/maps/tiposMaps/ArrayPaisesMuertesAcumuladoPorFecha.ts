/**
 * Peter Fight
 *
 * ArrayPaisesMuertesAcumuladoPorFecha
 * Pinta un mapa teniendo en cuenta la fecha proporcionada
 */


import {ArrayPaisesFactory} from "../ArrayPaisesFactory";
import {ModeloDatosCovid} from "../../../models/ModeloDatosCovid";
import * as $ from 'jquery';
import * as jvm from "jvectormap-next";

export class ArrayPaisesMuertesAcumuladoPorFecha implements ArrayPaisesFactory{
  muertesMin:number;
  muertesMax:number;
  fecha:string;
  arr:Array<any>;

  /**
   * array con idPais, color de muertes acumuladas en una fecha
   * @param fecha
   */
  constructor(fecha){
    this.fecha = fecha;
    this.muertesMin = 0;
    this.muertesMax = this.getMuertesMaxAcumuladoByFecha();
    this.arr = this.damePaisColorMuertesAcumulado();


  }


  /**
   * Aquí pinto el mapa
   * @param arrayPaisesColores array que contiene el color con el que se mostrará el mapa
   * @param tipo determina de qué variable sacamos los datos
   * @param fecha la fecha a pintar
   * @param muestraTooltips si es false, no muestra los tooltips de nombre país con los casos, porque cascaba
   * al lanzar la animación
   */
  pintaMapa(muestraTooltips)
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
        scale: 1
      },
      series: {
        regions: [{
          values: this.arr
        }]
      },
      onRegionTipShow: function(e, el, code){
        //muertes acumulado
        for (var i = 0; i < ModeloDatosCovid.distinctPaises.length; i++) {
          var pais = ModeloDatosCovid.distinctPaises[i];
          if(pais.idPais.toLowerCase() == code.toLowerCase())
          {
            var totalMuertes = 0;
            // pais.arrayInfoDias.forEach(function (value) {
            //   if (ArrayPaisesFactory.getFecha_DDMMYYYY(value.dia)
            //     < ArrayPaisesFactory.getFecha_DDMMYYYY(f)) {//La meto como f para el scope
            //     totalMuertes += parseInt(value.muertes);
            //   }
            // })
            /**
             * Poco eficiente, intento mejorar con bucle for... Tampoco gano nada...
             */
            for(var vi = 0; vi < pais.arrayInfoDias.length; vi ++) {
              // pais.arrayInfoDias.forEach(function (value) {
              //   if (ArrayPaisesFactory.getFecha_DDMMYYYY(value.dia)
              if (ArrayPaisesFactory.getFecha_DDMMYYYY(pais.arrayInfoDias[vi].dia)
                < ArrayPaisesFactory.getFecha_DDMMYYYY(f)) {//La meto como f para el scope
                // totalMuertes += parseInt(value.muertes);
                totalMuertes += parseInt(pais.arrayInfoDias[vi].muertes);
              }
              // })
            }
            el.html(el.html() + "("+ totalMuertes +")");
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
  }





  /**
   * Máximo de muertes acumuladas que hay en una fecha -> para montar los colores en una intensidad concreta
   * @param fecha
   */
  getMuertesMaxAcumuladoByFecha(){
    var mMax = 0;
    for (var i = 0; i < ModeloDatosCovid.distinctPaises.length; i++) {
      var pais = ModeloDatosCovid.distinctPaises[i];
      var vMaxPais = 0;
      // pais.arrayInfoDias.forEach(function (value) {
      //   vMaxPais = parseInt(vMaxPais.toString()) + parseInt(value.muertes);
      // });
      /**
       * Aquí se nota chachaco que es poco eficiente, lo meto con for
       */
      for(var conta = 0; conta < pais.arrayInfoDias.length; conta++)
      {
        vMaxPais = parseInt(vMaxPais.toString()) + parseInt(pais.arrayInfoDias[conta].muertes);
      }
      if(parseInt(vMaxPais.toString()) > parseInt(mMax.toString()))
      {
        mMax = vMaxPais;
      }
    };
    return mMax;
  }


  /**
   * devuelve el array con intensidad de color correspondiente
   * @param fecha
   */
  damePaisColorMuertesAcumulado()
  {
    let f = this.fecha;
    var arrayClaveValor = [];
    for (var i = 0; i < ModeloDatosCovid.distinctPaises.length; i++) {
      var pais = ModeloDatosCovid.distinctPaises[i];
      var muertesTotalesPaisAcumuladas = 0;
      // pais.arrayInfoDias.forEach(function (value) {
      //   if (ArrayPaisesFactory.getFecha_DDMMYYYY(value.dia)
      //     < ArrayPaisesFactory.getFecha_DDMMYYYY(f)) {//F para que lo pille en este scope
      //     muertesTotalesPaisAcumuladas += parseInt(value.muertes);
      //   }
      // })
      /**
       * Poco eficiente
       */
      for(var xi = 0; xi < pais.arrayInfoDias.length; xi ++) {
        if (ArrayPaisesFactory.getFecha_DDMMYYYY(pais.arrayInfoDias[xi].dia)
          < ArrayPaisesFactory.getFecha_DDMMYYYY(f)) {//F para que lo pille en este scope
          muertesTotalesPaisAcumuladas += parseInt(pais.arrayInfoDias[xi].muertes);
        }
        // })
      }
      var porcentaje = parseFloat(String(muertesTotalesPaisAcumuladas)) / parseFloat(String(this.muertesMax));
      var idPais = pais.idPais.toString().toLowerCase();
      var cantidadRojo = porcentaje * 255;
      var color = 'rgb('+(255 -cantidadRojo)+', '+(255 -cantidadRojo)+', '+(255 -cantidadRojo)+')';
      if(color == "rgb(NaN, NaN, NaN)")
      {
        color = "rgb(255,255,255)";
      }
      arrayClaveValor[idPais.toUpperCase()] = color;
    };
    return arrayClaveValor;
  }
}
