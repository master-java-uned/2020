/**
 * Peter fight
 *
 * aquí había dos campos string user y admin. Mi no entiende... será un campo nombre permiso y luego
 * ya si eso si es admin o user... Yo manoseo y ya.
 */

package com.covid19.authservice.model;


import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.ArrayList;
import java.util.List;

@NodeEntity
public class Role {

    /**
     * Le meto un enum de los posibles. Para mí no tenía sentido tener dos variables user y admin en role,
     * no pillo de qué va el rollo, y me he puesto muy nervioso... Óstia!! así que lo hago My Waaaaaay!!!
     * New York, New Yooork!!!
     */
    public static enum rolesPosibles{
        ANONIMO (new Long(1)),
        ADMIN (new Long(2)),
        SUPERADMIN(new Long(3));
        private final Long value;
        rolesPosibles(Long i) {
            this.value = i;
        }
        public Long getId(){
            return this.value;
        }
    }
    /**
     * Si no pillas que hago aquí, a estudiar POO!!!
     * @param rol
     */
    public Role(rolesPosibles rol)
    {
        this.rolName = rol.name();
        this.refId = rol.value;
    }


    /**
     * SECCIÓN VARIABLES Y OTRAS PARIDETAS AL USO
     */
    private List<Permission> permissions = new ArrayList<Permission>();

    public Role() {
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

        public String getRolName() {
        return rolName;
    }

    public void setRolName(String permisoName) {
        this.rolName = permisoName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String rolName;

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
