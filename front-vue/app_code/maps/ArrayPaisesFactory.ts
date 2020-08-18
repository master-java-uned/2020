/**
 * Peter Fight
 *
 * CLase abstracta que para cada tipo de mapa posible, devuelve una instancia de la misma. Los mapas
 * comparten que son mapas para esta aplicación, de modo que tiene sentido esta implementación
 */



import tiposMaps from "./tiposMaps";
import {ArrayPaisesCasosAcumuladoPorFecha} from "./tiposMaps/ArrayPaisesCasosAcumuladoPorFecha";
import {ArrayPaisesCasosPorFecha} from "./tiposMaps/ArrayPaisesCasosPorFecha";
import {ArrayPaisesMuertesAcumuladoPorFecha} from "./tiposMaps/ArrayPaisesMuertesAcumuladoPorFecha";
import {ArrayPaisesMuertesPorFecha} from "./tiposMaps/ArrayPaisesMuertesPorFecha";

export default abstract class ArrayPaisesFactory{
  abstract pintaMapa(muestraTooltips:boolean):any;

  /**
   * Me das un tipo y te devuelvo una instancia
   * @param tipo
   * @param fecha el mapa a una fecha y tipo concreto
   */
  static getTipoFactory(tipo:tiposMaps, fecha:any){
    switch (tipo) {
      case(tiposMaps.CASOS_ACUMULADOS_X_FECHA):
        return new ArrayPaisesCasosAcumuladoPorFecha(fecha);
        // break;
      case(tiposMaps.CASOS_X_FECHA):
        return new ArrayPaisesCasosPorFecha(fecha);
        // break;
      case(tiposMaps.MUERTES_ACUMULADAS_X_FECHA):
        return new ArrayPaisesMuertesAcumuladoPorFecha(fecha);
        // break;
      case(tiposMaps.MUERTES_X_FECHA):
        return new ArrayPaisesMuertesPorFecha(fecha);
        // break;
    }
  }

  /**
   * Todos los hijos usan este método, así que no está de más meterlo aquí y así evitamos picar código duplicado
   * @param fecha
   */
  static getFecha_DDMMYYYY(fecha:string)
  {
    var f = fecha.split("/");
    return new Date(Number(f[2]), Number(f[1]) - 1, Number(f[0]));
  }
}
