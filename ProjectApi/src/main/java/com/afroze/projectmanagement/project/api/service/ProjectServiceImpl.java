package com.afroze.projectmanagement.project.api.service;

import com.afroze.projectmanagement.project.api.domain.Project;
import com.afroze.projectmanagement.project.api.domain.Task;
import com.afroze.projectmanagement.project.api.dto.ProjectDto;
import com.afroze.projectmanagement.project.api.dto.ProjectSummaryDto;
import com.afroze.projectmanagement.project.api.dto.TaskDto;
import com.afroze.projectmanagement.project.api.exception.ProjectAlreadyExistsException;
import com.afroze.projectmanagement.project.api.exception.ProjectNotFoundException;
import com.afroze.projectmanagement.project.api.repository.ProjectRepository;
import com.afroze.projectmanagement.project.api.repository.TaskRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;
    private final ModelMapper mapper;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository, TaskRepository taskRepository, ModelMapper mapper) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
        this.mapper = mapper;
    }

    @Override
    public List<ProjectSummaryDto> getAll() {
        return projectRepository.findAll()
                .stream()
                .map(ProjectServiceImpl::mapProjectDtoToProjectSummary)
                .toList();
    }

    private static ProjectSummaryDto mapProjectDtoToProjectSummary(Project project) {
        ProjectSummaryDto summary = new ProjectSummaryDto();
        summary.setId(project.getId());
        summary.setTags(project.getTags());
        summary.setName(project.getName());
        summary.setCompanyId(project.getCompanyId());
        summary.setTaskCount(project.getTasks().size());
        return summary;
    }

    @Override
    public ProjectDto getById(long projectId) throws ProjectNotFoundException {
        Project project = projectRepository.findById(projectId).orElse(null);

        if(project == null) {
            throw new ProjectNotFoundException(projectId);
        }
        return mapper.map(project, ProjectDto.class);
    }

    @Override
    public ProjectDto create(ProjectDto projectDto) throws ProjectAlreadyExistsException {
        Project existingProject = projectRepository
                .findByCompanyIdAndName(projectDto.getCompanyId(), projectDto.getName())
                .orElse(null);

        if(existingProject != null) {
            throw new ProjectAlreadyExistsException(existingProject);
        }

        Project project = mapper.map(projectDto, Project.class);
        Project savedProject = projectRepository.save(project);

        return mapper.map(savedProject, ProjectDto.class);
    }

    @Override
    public ProjectDto update(long projectId, ProjectDto projectDto) throws ProjectNotFoundException {
        Project projectToUpdate = projectRepository.findById(projectId).orElse(null);
        if(projectToUpdate == null) {
            throw new ProjectNotFoundException(projectDto.getId());
        }

        projectToUpdate.setName(projectDto.getName());
        projectToUpdate.setTags(projectDto.getTags());

        projectRepository.save(projectToUpdate);

        return mapper.map(projectToUpdate, ProjectDto.class);
    }

    @Override
    public void delete(long projectId) {
        Optional<Project> projectToDelete = projectRepository.findById(projectId);
        projectToDelete.ifPresent(projectRepository::delete);
    }

    @Override
    public ProjectDto addTasksToProject(long projectId, List<TaskDto> taskDtos) throws ProjectNotFoundException {
        Project projectToUpdate = projectRepository.findById(projectId).orElse(null);
        if(projectToUpdate == null) {
            throw new ProjectNotFoundException(projectId);
        }

        List<Task> tasksToAdd =  mapper.map(taskDtos, new TypeToken<List<Task>>(){}.getType());

        for (var task : tasksToAdd) {
            task.setProject(projectToUpdate);
            taskRepository.save(task);
        }

        List<TaskDto> addedTasks =  mapper.map(tasksToAdd, new TypeToken<List<TaskDto>>(){}.getType());
        ProjectDto updatedProject = mapper.map(projectToUpdate, ProjectDto.class);
        updatedProject.setTasks(addedTasks);

        return updatedProject;
    }

    @Override
    public List<ProjectSummaryDto> getAllByCompanyId(int companyId) {

        return projectRepository.findAllByCompanyId(companyId)
                .stream()
                .map(ProjectServiceImpl::mapProjectDtoToProjectSummary)
                .toList();
    }
}
