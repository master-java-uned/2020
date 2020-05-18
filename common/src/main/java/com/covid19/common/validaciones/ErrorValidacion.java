/**
 * Peter Fight
 */

package com.covid19.common.validaciones;

import java.util.List;

public class ErrorValidacion {
    /**
     * Código de error en caso de que se produzca un error.
     */
    private int CodigoError;
    public int getCodigoError() {
        return CodigoError;
    }

    public void setCodigoError(int codigoError) {
        CodigoError = codigoError;
    }

    /**
     * Listado con códigos de error localizados
     */
    private List<LocalizedErrores> listadoErroresLocalized;
    public List<LocalizedErrores> getListadoErrores() {
        return listadoErroresLocalized;
    }

    public void setListadoErrores(List<LocalizedErrores> listadoErrores) {
        this.listadoErroresLocalized = listadoErrores;
    }

    public ErrorValidacion(int error, List<LocalizedErrores> localizedErrores)
    {
        this.CodigoError = error;
        this.listadoErroresLocalized = localizedErrores;
    }
}