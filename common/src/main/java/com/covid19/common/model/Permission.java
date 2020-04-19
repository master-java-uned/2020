package com.covid19.common.model;

import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity(label="permission")
public class Permission {
    public static String user = "USER";
    public static String user_admin = "USER_ADMIN";

}