/**
 * Peter fight
 *
 * (27/06/2020) All my comments and fucking variables translated
 * at Victor's good practice accomplishment request)
 *
 Here were two string user and admin fields. My does not understand ...
 it will be a field name permission and then Now if that is admin or user ...
 I grope and go.
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
     I put an enum of the possible ones. It didn't make sense to me to have
     two variables user and admin in role, I do not catch what the roll is
     about, and I have become very nervous ... Ostia !! so I do it My Waaaaaay !!!
     New York, New Yooork !!!
     */
    public static enum availableRoles {
        ANONYMOUS(new Long(1)),
        ADMIN (new Long(2)),
        SUPERADMIN(new Long(3));
        private final Long value;
        availableRoles(Long i) {
            this.value = i;
        }
        public Long getId(){
            return this.value;
        }
    }
    /**
     * If you don't get what I do here, to study POO !!!
     * @param rol
     */
    public Role(availableRoles rol)
    {
        this.rolName = rol.name();
        this.refId = rol.value;
    }


    /**
     * SECTION VARIABLES AND OTHER PARIDETS FOR USE
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

    public void setRolName(String roleName) {
        this.rolName = roleName;
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
