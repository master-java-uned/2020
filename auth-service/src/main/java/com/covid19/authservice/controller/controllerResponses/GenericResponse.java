/**
 * Peter Fight
 * 10/05/2020
 *
 * Clase genérica para devolver un objeto uniforme de respuesta que contenga código de error (0 si no hay error)
 * Mensajes de error en todos los idiomas, más vale que sobre información que no que falte... Y lo que se nos ocurra.
 */

package com.covid19.authservice.controller.controllerResponses;

import com.covid19.common.validaciones.ErrorValidacion;
import com.covid19.common.validaciones.Errores;
import com.covid19.common.validaciones.LocalizedErrores;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class GenericResponse {

    public List<ErrorValidacion> getListadoErrores() {
        return listadoErrores;
    }

    public void setListadoErrores(List<ErrorValidacion> listadoErrores) {
        this.listadoErrores = listadoErrores;
    }

    private List<ErrorValidacion> listadoErrores;


    /**
     * Respuesta, object mapeable a lo que convenga, que contendrá la respuesta en sí, si no se produce
     * ningún error
     */
    private Object respuesta;
    public Object getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(Object respuesta) {
        this.respuesta = respuesta;
    }


    /**
     * Si la respuesta tiene listado de errores, algo ha ido mal y el objeto respuesta vendrá nulo.
     * Si hay errores, devuelvo el código y todas las traducciones (es gratis), más vale que sobre información
     * que no que falte, o tener que pasar el locale en la petición, porque quizás luego el cliente traduce
     * la página y el error no lo podría traducir.
     *
     * @param respuesta El objeto a devolver (serializable en JSON)
     * @param codigosError Los códigos de error en su caso. En tal caso monto la respuesta con todos los locales.
     */
    public GenericResponse(Object respuesta, List<ErrorValidacion> codigosError)
    {
        this.respuesta = respuesta;
        this.listadoErrores = codigosError;
    }
}
