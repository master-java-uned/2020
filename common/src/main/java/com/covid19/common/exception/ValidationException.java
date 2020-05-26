/**
 * Clase para la gestión de errores numerados y traducidos.
 *
 * LocalizedErrores es la clase que almacena los códigos de error junto a todas las traducciones
 * que emplea la aplicación. Más vale que sobre información que no que falte.
 *
 * @author Peter Fight
 */

package com.covid19.common.exception;

import com.covid19.common.validaciones.ErrorValidacion;
import com.covid19.common.validaciones.Errores;
import com.covid19.common.validaciones.LocalesErrores;
import com.covid19.common.validaciones.LocalizedErrores;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.tomcat.jni.Local;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class ValidationException extends RuntimeException {
    ValidationException(){
        super();
    }


    /**
     * Construyo la excepción con el listado de códigos de error detectados. Podría lanzar uno cada vez,
     * pero es un poquillo desaprovechar la ocasión de llamar tonto al usuario por múltiples motivos. Esto es:
     * cuando un usuario rellena mal varios campos, es mejor deshaogarse de una vez (pass mal, name mal, mail mal...
     * TONTO!) en lugar de ir dosificando insultos como harían las gentes educadas por los relatos de las izquierdas.
     *
     * Con los códigos de error zancocho el listado poderoso de LocalizedErrores que contienen todas las traducciones...
     * que sobren datos, SIEMPRE QUE SOBREN DATOS, QUE NO FALTEN NUNCA DATOS!!!! ---más vale prevenir que curar---
     *
     * @param codigosError el listado de enteros con el código de error
     */
    public ValidationException(List<Integer> codigosError)
    {
        /**
         * Recorro los códigos de error y saco la traducción del mensaje en todos los idiomas disponibles
         */
        for(Integer codigo : codigosError)
        {
            List<LocalizedErrores> erroracos = new ArrayList<LocalizedErrores>();
            //Ouh yeah!!, meto en un enum los locales disponibles (no me pondré a crear archivos de properties
            //en tiempo de ejecución (no porque no pudiese con reflexiones místicas, eh!!)
            for(LocalesErrores.LOCALES_ERRORES locale: LocalesErrores.LOCALES_ERRORES.values())
            {
                //Lo llamo add, aunque resulte sorprendente, porque la variable se añade en el método add 0u0
                LocalizedErrores add = new LocalizedErrores();
                add.setLocaleError(locale.toString());
                String mensajeError = ResourceBundle.getBundle(
                        String.format("mensajesValidacion_%s", locale), new Locale("es","ES"))
                        //Locale es Es para que pille bien la codificación
                        .getString(codigo.toString());

                add.setMensajeError(mensajeError);
                erroracos.add(add);
            }
            this.addLocalizedErrores(new ErrorValidacion(codigo, erroracos));
        }
    }



    private ArrayList<ErrorValidacion> localizedErrores;
    public ArrayList<ErrorValidacion> getLocalizedErrores() {
        return localizedErrores;
    }

    public void setLocalizedErrores(ArrayList<ErrorValidacion> localizedErrores) {
        this.localizedErrores = localizedErrores;
    }

    /**
     * Ñapilla para ir ampliando el rapapolvo que recibirá el usuario incauto
     * @param localizedError
     */
    public void addLocalizedErrores(ErrorValidacion localizedError) {
        if(this.localizedErrores == null)
        {
            this.localizedErrores = new ArrayList<ErrorValidacion>();
        }
        this.localizedErrores.add(localizedError);
    }
}
