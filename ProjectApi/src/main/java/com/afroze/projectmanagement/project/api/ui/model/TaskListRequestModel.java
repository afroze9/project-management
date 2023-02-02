package com.afroze.projectmanagement.project.api.ui.model;

import java.util.List;

public class TaskListRequestModel {
    private List<TaskRequestModel> tasks;

    public List<TaskRequestModel> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskRequestModel> tasks) {
        this.tasks = tasks;
    }
}
