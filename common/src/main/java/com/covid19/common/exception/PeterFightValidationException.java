/**
 * Clase para la gestión de errores numerados y traducidos.
 *
 * (27/06/2020) All my comments and fucking variables translated
 * at Victor's good practice accomplishment request)
 *
 LocalizedErrors is the class that stores error codes along with all translations
 the application uses. Better than information that is missing.
 *
 * @author Peter Fight
 */

package com.covid19.common.exception;

import com.covid19.common.peterFightValidations.ValidationError;
import com.covid19.common.peterFightValidations.LocalesErrors;
import com.covid19.common.peterFightValidations.LocalizedErrors;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class PeterFightValidationException extends RuntimeException {
    PeterFightValidationException(){
        super();
    }


    /**
     I build the exception with the list of detected error codes. I could throw one
     at a time, but it is a little bit to waste the opportunity to call the user
     silly for multiple reasons. This is: when a user misfills several fields, it
     is better to get rid of it at once (pass mal, name mal, mail mal ...
     FOOL!) Instead of dosing insults as the educated people would do by the stories
     of the left.
     *
     With stilt error codes the powerful listing of LocalizedErrors that all translations
     contain ... that data remain, ALWAYS THAT DATA IS EXCESSED, THAT DATA NEVER
     IS MISSING !!!! ---it's better to prevent than to cure---
     *
     * @param errorCodes el listado de enteros con el código de error
     */
    public PeterFightValidationException(List<Integer> errorCodes)
    {
        /**
         * I go through the error codes and get the translation of the message
         * in all the available languages
         */
        for(Integer code : errorCodes)
        {
            List<LocalizedErrors> erroraKoUshings = new ArrayList<LocalizedErrors>();
            /**
             Ouh yeah !!, I put the available rooms in an enum (I will not create
             properties files at runtime (not because I couldn't with mystical
             reflections, huh !!)
             */
            for(LocalesErrors.LOCALE_ERRORS locale: LocalesErrors.LOCALE_ERRORS.values())
            {
                //I call it add, surprisingly though, because the variable is added in the
                // add 0u0 method
                LocalizedErrors add = new LocalizedErrors();
                add.setLocaleError(locale.toString());
                String errorMessage = ResourceBundle.getBundle(
                        String.format("validationMessages_%s", locale), new Locale("es","ES"))
                        //Locale es Es para que pille bien la codificación
                        .getString(code.toString());

                add.setErrorMessage(errorMessage);
                erroraKoUshings.add(add);
            }
            this.addLocalizedErrores(new ValidationError(code, erroraKoUshings));
        }
    }



    private ArrayList<ValidationError> localizedErrores;
    public ArrayList<ValidationError> getLocalizedErrors() {
        return localizedErrores;
    }

    public void setLocalizedErrores(ArrayList<ValidationError> localizedErrores) {
        this.localizedErrores = localizedErrores;
    }

    /**
     * Ñapilla to expand the rapapolvo that the unwary user will receive
     * @param localizedError
     */
    public void addLocalizedErrores(ValidationError localizedError) {
        if(this.localizedErrores == null)
        {
            this.localizedErrores = new ArrayList<ValidationError>();
        }
        this.localizedErrores.add(localizedError);
    }
}
