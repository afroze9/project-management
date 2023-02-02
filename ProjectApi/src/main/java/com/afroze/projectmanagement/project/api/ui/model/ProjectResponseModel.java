package com.afroze.projectmanagement.project.api.ui.model;

import java.util.List;

public class ProjectResponseModel {
    private Long id;

    private String name;

    private String tags;

    private List<TaskResponseModel> tasks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<TaskResponseModel> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskResponseModel> tasks) {
        this.tasks = tasks;
    }
}