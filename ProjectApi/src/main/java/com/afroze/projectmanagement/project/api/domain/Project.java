package com.afroze.projectmanagement.project.api.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import org.springframework.data.jpa.domain.AbstractAuditable;

@Entity
@Table(name = "project", indexes = {
        @Index(name = "idx_project_id", columnList = "id")
})

public class Project extends AbstractAuditable<Project, Long> {

    private String name;

    private String tags;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}