package com.afroze.projectmanagement.project.api.service;

import com.afroze.projectmanagement.project.api.dto.ProjectDto;
import com.afroze.projectmanagement.project.api.dto.ProjectSummaryDto;
import com.afroze.projectmanagement.project.api.dto.TaskDto;
import com.afroze.projectmanagement.project.api.exception.ProjectAlreadyExistsException;
import com.afroze.projectmanagement.project.api.exception.ProjectNotFoundException;

import java.util.List;

public interface ProjectService {
    List<ProjectSummaryDto> getAll();
    ProjectDto getById(long projectId) throws ProjectNotFoundException;
    ProjectDto create(ProjectDto projectDto) throws ProjectAlreadyExistsException;
    ProjectDto update(long projectId, ProjectDto projectDto) throws ProjectNotFoundException;
    void delete(long projectId);
    ProjectDto addTasksToProject(long projectId, List<TaskDto> taskDtos) throws ProjectNotFoundException;
    List<ProjectSummaryDto> getAllByCompanyId(int companyId);
}
