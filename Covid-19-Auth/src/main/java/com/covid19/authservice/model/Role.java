package com.covid19.authservice.model;


import com.covid19.common.model.Permission;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.ArrayList;
import java.util.List;

@NodeEntity
public class Role {

    public static final String ROLE_ADMIN = "role.admin";

    private List<Permission> permissions = new ArrayList<Permission>();

    public Role() {
	}

	public Role(String roleAdmin) {
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	public static Role Create_Admin(){
		return new Role(Role.ROLE_ADMIN);
	}


	
}
