package com.afroze.projectmanagement.project.api.repository;

import com.afroze.projectmanagement.project.api.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
