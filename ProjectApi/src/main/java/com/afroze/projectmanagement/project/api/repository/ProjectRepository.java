package com.afroze.projectmanagement.project.api.repository;

import com.afroze.projectmanagement.project.api.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Optional<Project> findByCompanyIdAndName(long companyId, String name);

    List<Project> findAllByCompanyId(long companyId);
}
