package com.afroze.projectmanagement.project.api.service;

import com.afroze.projectmanagement.project.api.domain.Project;
import com.afroze.projectmanagement.project.api.dto.ProjectDto;
import com.afroze.projectmanagement.project.api.exception.ProjectNotFoundException;
import com.afroze.projectmanagement.project.api.repository.ProjectRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    private final ModelMapper mapper;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository, ModelMapper mapper) {
        this.projectRepository = projectRepository;
        this.mapper = mapper;
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @Override
    public List<ProjectDto> getAll() {
        List<Project> companies = projectRepository.findAll();
        return mapper.map(companies, new TypeToken<List<ProjectDto>>(){}.getType());
    }

    @Override
    public ProjectDto getById(long projectId) throws ProjectNotFoundException {
        Project company = projectRepository.findById(projectId).orElse(null);

        if(company == null) {
            throw new ProjectNotFoundException(projectId);
        }
        return mapper.map(company, ProjectDto.class);
    }

    @Override
    public ProjectDto create(ProjectDto projectDto) {
        Project company = mapper.map(projectDto, Project.class);
        projectRepository.save(company);

        return mapper.map(company, ProjectDto.class);
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
        Optional<Project> companyToDelete = projectRepository.findById(projectId);
        companyToDelete.ifPresent(projectRepository::delete);
    }
}
