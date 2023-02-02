package com.afroze.projectmanagement.project.api.ui.controller;

import com.afroze.projectmanagement.project.api.dto.ProjectDto;
import com.afroze.projectmanagement.project.api.dto.ProjectSummaryDto;
import com.afroze.projectmanagement.project.api.dto.TaskDto;
import com.afroze.projectmanagement.project.api.exception.ProjectNotFoundException;
import com.afroze.projectmanagement.project.api.service.ProjectService;
import com.afroze.projectmanagement.project.api.ui.model.*;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {

    private final ProjectService projectService;
    private final ModelMapper mapper;

    public ProjectController(ProjectService projectService, ModelMapper mapper) {
        this.projectService = projectService;
        this.mapper = mapper;
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @GetMapping
    public ResponseEntity<HttpResponseModel<List<ProjectSummaryResponseModel>>> getAll() {
        List<ProjectSummaryDto> projects = projectService.getAll();
        if(projects.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<ProjectSummaryResponseModel> response = mapper.map(projects, new TypeToken<List<ProjectSummaryResponseModel>>(){}.getType());
        return ResponseEntity.status(HttpStatus.OK).body(HttpResponseModel.Success(response));
    }

    @GetMapping("/{projectId}/")
    public ResponseEntity<HttpResponseModel<ProjectResponseModel>> getById(@PathVariable("projectId") int projectId) {
        try {
            ProjectDto project = projectService.getById(projectId);
            ProjectResponseModel response = mapper.map(project, ProjectResponseModel.class);
            return ResponseEntity.status(HttpStatus.OK).body(HttpResponseModel.Success(response));
        } catch (ProjectNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpResponseModel.Failure(null, e.getLocalizedMessage()));
        }
    }

    @PostMapping()
    public ResponseEntity<HttpResponseModel<ProjectResponseModel>> create(@RequestBody @Valid ProjectRequestModel project) {
        ProjectDto dto = mapper.map(project, ProjectDto.class);

        ProjectDto createdProject = projectService.create(dto);
        ProjectResponseModel response = mapper.map(createdProject, ProjectResponseModel.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(HttpResponseModel.Success(response));
    }

    @PostMapping("/{projectId}/tasks")
    public ResponseEntity<HttpResponseModel<ProjectResponseModel>> addTasks(
            @PathVariable("projectId") int projectId,
            @RequestBody @Valid TaskListRequestModel taskModel) {
        try {
            List<TaskDto> taskDtos = mapper.map(taskModel.getTasks(), new TypeToken<List<TaskDto>>(){}.getType());
            ProjectDto updatedProject = projectService.addTasksToProject(projectId, taskDtos);
            ProjectResponseModel response = mapper.map(updatedProject, ProjectResponseModel.class);

            return ResponseEntity.status(HttpStatus.CREATED).body(HttpResponseModel.Success(response));
        } catch (ProjectNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpResponseModel.Failure(null, e.getLocalizedMessage()));
        }
    }
}
