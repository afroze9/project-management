package com.afroze.projectmanagement.project.api.ui.model;

import java.math.BigDecimal;

public class TaskResponseModel {
    private long id;
    private String name;
    private String description;
    private BigDecimal estimatedEffort;
    private BigDecimal actualEffort;
    private boolean isComplete;
    private long userId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public BigDecimal getActualEffort() {
        return actualEffort;
    }

    public void setActualEffort(BigDecimal actualEffort) {
        this.actualEffort = actualEffort;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
