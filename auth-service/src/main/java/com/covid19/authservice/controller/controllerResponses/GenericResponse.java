/**
 * Peter Fight
 * 10/05/2020
 *
 * (27/06/2020) All my comments and fucking variables translated
 * at Victor's good practice accomplishment request)
 *

 Generic class to return a uniform response object containing error code
 (0 if there is no error) Error messages in all languages, better than
 information that is not missing ... And what we can think of.
 */

package com.covid19.authservice.controller.controllerResponses;

import com.covid19.common.peterFightValidations.ValidationError;

import java.util.List;

public class GenericResponse {

    public List<ValidationError> getErrorsList() {
        return errorsList;
    }

    public void setErrorsList(List<ValidationError> errorsList) {
        this.errorsList = errorsList;
    }

    private List<ValidationError> errorsList;


    /**
     Response, object mappable to whatever is convenient, which will contain the
     response itself, if it does not occur no mistake
     */
    private Object response;
    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }


    /**
     If the response has a list of errors, something has gone wrong and the
     response object will be null. If there are errors, I return the code and
     all translations (it's free), better than information
     not that it is missing, or having to pass the locale in the request,
     because perhaps later the client translates
     the page and the error could not translate it.
     *
     * @param response The object to return (serializable in JSON)
     * @param errorCodes The error codes in your case. In such case
     *                     I mount the answer with all the premises.
     */
    public GenericResponse(Object response, List<ValidationError> errorCodes)
    {
        this.response = response;
        this.errorsList = errorCodes;
    }
}
