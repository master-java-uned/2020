/**
 * Peter Fight
 * 10/05/2020
 *
 * (27/06/2020) All my comments and variables translated
 * at Victor's good practice accomplishment request)
 *
 * Class for error management. The error codes are linked to the validationMessages properties files
 */

package com.covid19.common.peterFightValidations;

public class peterFightErrors {

    /**
     I build a numbered enumeration of these to be able to handle the numbers in an understandable way ... If you rush me
     are the domains of the Uhp's.
     */
    public enum CODES {
        INCORRECT_NAME(101),
        INCORRECT_SURNAME(102),
        PASSWORD_MISSING(103),
        EMAIL_MISSING(104),
        INCORRECT_EMAIL(105),
        ERROR_INSERTING_USER(106),
        USERNAME_ALREADY_TAKEN(107),
        ERROR_MODIFYING_USER(108),
        UNEXPECTED_ERROR(666);

        private int Id;

        CODES(int Id) {
            this.Id = Id;
        }

        public int getId() {
            return Id;
        }
    }



}
