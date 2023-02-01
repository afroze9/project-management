package com.afroze.projectmanagement.project.api.service;

import com.afroze.projectmanagement.project.api.dto.ProjectDto;
import com.afroze.projectmanagement.project.api.exception.ProjectNotFoundException;

import java.util.List;

public interface ProjectService {
    List<ProjectDto> getAll();
    ProjectDto getById(long projectId) throws ProjectNotFoundException;
    ProjectDto create(ProjectDto projectDto);
    ProjectDto update(long projectId, ProjectDto projectDto) throws ProjectNotFoundException;
    void delete(long projectId);
}
