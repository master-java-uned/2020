/**
 * Peter Fight
 * 10/05/2020
 *
 * CLase para la gestión de los errores. Los códigos de error van enlazados con los archivos de propiedades de mensajesValidacion
 */

package com.covid19.common.validaciones;

public class Errores {

    /**
     * Le zancocho un enum numerado de estos para poder manejar los numericos de forma entendible... Si me apuras
     * son los dominios de las Uhp's.
     */
    public enum CODIGOS {
        NOMBRE_INCORRECTO(101),
        APELLIDO_INCORRECTO(102),
        FALTA_PASSWORD(103),
        FALTA_EMAIL(104),
        EMAIL_INCORRECTO(105),
        ERROR_INSERTAR_USER(106),
        USERNAME_YA_PILLADO(107),
        ERROR_MODIFICAR_USER(108),
        ERROR_INEXPLICABLE(666);

        private int Id;

        CODIGOS(int Id) {
            this.Id = Id;
        }

        public int getId() {
            return Id;
        }
    }



}
