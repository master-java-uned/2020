/**
 * Peter Fight
 *
 * Clase estática para gestionar el modelo de datos. Es estática porque corre del lado del cliente, que sólo
 * usará un origen de datos para los mapas, y está bien que una vez cargados y formateados los datos, sean
 * accesibles everYWHERE!!!
 */


export default abstract class ModeloDatosCovid {
  public static JSON:any = "";

  /**
   * Inicio el cacharro. Saco los distintos continentes, por el momento no lo uso, y luego el país y la info
   * @param JSON
   */
  static init(json: string) {
    ModeloDatosCovid.JSON = JSON.parse(json);
    ModeloDatosCovid.getDistinctContinentes();
    /**
     * Primero saco los distintes paises con su info
     */
    ModeloDatosCovid.getDistinctPaises();
    /**
     * Luego, para cada pais, le añado su array de información por fecha
     */
    ModeloDatosCovid.setArrayDiasPaises();


    // LayoutComponent.setModeloDatosCovid(this);

    return true;
  }

  static distinctContinentes:Array<any>;

  /**
   * Really don't know¿?
   */
  static getDistinctContinentes() {
    if (ModeloDatosCovid.distinctContinentes == undefined || ModeloDatosCovid.distinctContinentes.length == 0) {
      ModeloDatosCovid.distinctContinentes = [];

      ModeloDatosCovid.JSON.forEach(function (value: { continentExp: any; }) {
        if (ModeloDatosCovid.distinctContinentes.indexOf(value.continentExp) === -1) {
          ModeloDatosCovid.distinctContinentes.push(value.continentExp);
        }
      });
    }
    return ModeloDatosCovid.distinctContinentes;
  }

  static distinctPaises:Array<any>;

  /**
   * Seguro que hay una expresión lambda que mapee en un objeto directamente y tal y cual... Tampoco semo 80
   * currando para Google... es más, somos dos y trabajamos por placer, no por disgustos.
   */
  static getDistinctPaises() {
    if (ModeloDatosCovid.distinctPaises == undefined || ModeloDatosCovid.distinctPaises.length == 0) {
      ModeloDatosCovid.distinctPaises = [];

      var paisesId: any[] = [];
      ModeloDatosCovid.JSON.forEach(function (value: { countriesAndTerritories: any; popData2018: any; countryterritoryCode: any; geoId: any; continentExp: any; }) {
        if (paisesId.indexOf(value.countriesAndTerritories) === -1) {
          ModeloDatosCovid.distinctPaises.push({
            nombre: value.countriesAndTerritories,
            poblacion: value.popData2018,
            codigoPais: value.countryterritoryCode,
            idPais: value.geoId,
            continente: value.continentExp//Añado la variable continente a nivel de país para poderla recuperar luego
          });
          paisesId.push(value.countriesAndTerritories);
        }
      });
    }
    return ModeloDatosCovid.distinctPaises;
  }

  /**
   * Aquí está el meollo de la info relevante con la que jugaremos en el mapa. Asocio a un país, el número de
   * casos y muertes para cada día de los que tenemos info.
   */
  static setArrayDiasPaises() {
    for (var i = 0; i < ModeloDatosCovid.distinctPaises.length; i++) {
      var pais = ModeloDatosCovid.distinctPaises[i];
      var arrayInfoDias: { dia: any; casos: any; muertes: any; }[] = [];
      ModeloDatosCovid.JSON.forEach(function (value: { geoId: any; dateRep: any; cases: any; deaths: any; }) {
        if (pais.idPais == value.geoId) {
          arrayInfoDias.push({
            dia: value.dateRep,
            casos: value.cases,
            muertes: value.deaths
          })
        }
      });
      ModeloDatosCovid.distinctPaises[i].arrayInfoDias = arrayInfoDias;
    }
  }
}

