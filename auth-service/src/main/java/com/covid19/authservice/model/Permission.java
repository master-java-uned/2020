/**
 * Peter fight
 *
 * (27/06/2020) All my comments and variables translated
 * at Victor's good practice accomplishment request)
 *
 Here were two string user and admin fields. My does not understand ...
 it will be a field name permission and then Now if that is admin or user ...
 I grope and go.
 */


package com.covid19.authservice.model;

import com.covid19.common.exception.DataAccessException;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class Permission  extends DataAccessException {
    /**
     I put an enum of the possible ones. It didn't make sense to me to have
     two variables user and admin in permissions, I do not catch what the roll
     is about, and I have become very nervous ... Ostia !! so I do it My Waaaaaay !!!
     New York, New Yooork !!!
     */
    public static enum AvailablePermissions {
        USER (new Long(1)),
        ADMIN (new Long(2));
        private final Long value;
        AvailablePermissions(Long i) {
            this.value = i;
        }
        public Long getId(){
            return this.value;
        }
    }

    /**
     * If you don't get what I do here, to study POO !!!
     * @param permisoConcreto
     */
    public Permission(AvailablePermissions permisoConcreto)
    {
        this.permissionName = permisoConcreto.name();
        this.refId = permisoConcreto.value;
    }



    /**
     * SECCIÓN VARIABLES Y OTRAS PARIDETAS AL USO
     */
    public Permission(){}//// If you don't get what I do here, to study POO !!!

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String permissionName;

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