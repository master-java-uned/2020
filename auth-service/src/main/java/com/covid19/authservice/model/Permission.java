/**
 * Peter fight
 *
 * aquí había dos campos string user y admin. Mi no entiende... será un campo nombre permiso y luego
 * ya si eso si es admin o user... Yo manoseo y ya.
 */


package com.covid19.authservice.model;

import com.covid19.common.exception.DataAccessException;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class Permission  extends DataAccessException {
    /**
     * Le meto un enum de los posibles. Para mí no tenía sentido tener dos variables user y admin en permisos,
     * no pillo de qué va el rollo, y me he puesto muy nervioso... Óstia!! así que lo hago My Waaaaaay!!!
     * New York, New Yooork!!!
     */
    public static enum permisosPosibles{
        USER (new Long(1)),
        ADMIN (new Long(2));
        private final Long value;
        permisosPosibles(Long i) {
            this.value = i;
        }
        public Long getId(){
            return this.value;
        }
    }

    /**
     * Si no pillas que hago aquí, a estudiar POO!!!
     * @param permisoConcreto
     */
    public Permission(permisosPosibles permisoConcreto)
    {
        this.permisoName = permisoConcreto.name();
        this.refId = permisoConcreto.value;
    }



    /**
     * SECCIÓN VARIABLES Y OTRAS PARIDETAS AL USO
     */
    public Permission(){}//Constructor por defecto, para las cosas de las entidades

    public String getPermisoName() {
        return permisoName;
    }

    public void setPermisoName(String permisoName) {
        this.permisoName = permisoName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String permisoName;

    public Long getRefId() {
        return refId;
    }

    public void setRefId(Long refId) {
        this.refId = refId;
    }

    public Long refId;

    @Id
    @GeneratedValue
    private Long id;

}