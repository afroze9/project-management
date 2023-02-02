package com.afroze.projectmanagement.project.api;

import com.afroze.projectmanagement.project.api.data.SpringSecurityAuditorAware;
import com.afroze.projectmanagement.project.api.data.Auditable;
import com.afroze.projectmanagement.project.api.domain.Project;
import com.afroze.projectmanagement.project.api.dto.ProjectDto;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class ProjectApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectApiApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper mapper = new ModelMapper();
		mapper.typeMap(Project.class, ProjectDto.class)
				.addMapping(Auditable::getId, ProjectDto::setId);
		return new ModelMapper();
	}

	@Bean
	public AuditorAware<String> auditorAware() {
		return new SpringSecurityAuditorAware();
	}
}
