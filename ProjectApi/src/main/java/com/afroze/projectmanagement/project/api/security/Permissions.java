package com.afroze.projectmanagement.project.api.security;

public class Permissions {
    private Permissions(){}
    public static final String READ_PROJECT = "SCOPE_read:project";
    public static final String WRITE_PROJECT = "SCOPE_write:project";
    public static final String UPDATE_PROJECT = "SCOPE_update:project";
    public static final String DELETE_PROJECT = "SCOPE_delete:project";
    public static final String READ_TASK = "SCOPE_read:task";
    public static final String WRITE_TASK = "SCOPE_write:task";
    public static final String UPDATE_TASK = "SCOPE_update:task";
    public static final String DELETE_TASK = "SCOPE_delete:task";
}
