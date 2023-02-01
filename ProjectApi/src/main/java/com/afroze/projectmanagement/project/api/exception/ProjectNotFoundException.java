package com.afroze.projectmanagement.project.api.exception;

public class ProjectNotFoundException  extends Throwable{
    public ProjectNotFoundException(long id) {
        super("Project with the id: " + id + " not found");
    }
}
