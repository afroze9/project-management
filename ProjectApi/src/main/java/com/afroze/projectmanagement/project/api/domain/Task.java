package com.afroze.projectmanagement.project.api.domain;

import com.afroze.projectmanagement.project.api.data.Auditable;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "task", indexes = {
        @Index(name = "idx_task_id", columnList = "id")
})
public class Task extends Auditable<String, Long> {
    private String name;
    private String description;
    private BigDecimal estimatedEffort;
    private BigDecimal actualEffort;
    private boolean isComplete;
    private long userId;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getEstimatedEffort() {
        return estimatedEffort;
    }

    public void setEstimatedEffort(BigDecimal estimatedEffort) {
        this.estimatedEffort = estimatedEffort;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public BigDecimal getActualEffort() {
        return actualEffort;
    }

    public void setActualEffort(BigDecimal actualEffort) {
        this.actualEffort = actualEffort;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
