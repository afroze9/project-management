package com.afroze.projectmanagement.project.api.exception;

import com.afroze.projectmanagement.project.api.domain.Project;

public class ProjectAlreadyExistsException extends Exception {
    public ProjectAlreadyExistsException(Project project) {
        super("Project with the name '" + project.getName() + "' already exists");
    }
}
