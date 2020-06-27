/**
 * Peter Fight
 *
 * (27/06/2020) All my comments and fucking variables translated
 * at Victor's good practice accomplishment request)
 *
 */

package com.covid19.common.peterFightValidations;

import java.util.List;

public class ValidationError {
    /**
     * Error code in case an error occurs.
     */
    private int ErrorCode;
    public int getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(int errorCode) {
        ErrorCode = errorCode;
    }

    /**
     * List with localized error codes
     */
    private List<LocalizedErrors> LocalizedErrorsList;
    public List<LocalizedErrors> getLocalizedErrorsList() {
        return LocalizedErrorsList;
    }

    public void setLocalizedErrorsList(List<LocalizedErrors> ErrorList) {
        this.LocalizedErrorsList = ErrorList;
    }

    public ValidationError(int error, List<LocalizedErrors> localizedErrores)
    {
        this.ErrorCode = error;
        this.LocalizedErrorsList = localizedErrores;
    }
}