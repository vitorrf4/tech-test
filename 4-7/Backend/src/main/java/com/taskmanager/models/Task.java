package com.taskmanager.models;

public class Task {
    private String title;
    private String description;
    private Status taskStatus;
    private enum Status {
        PENDING,
        COMPLETED
    };

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        Status status = Status.PENDING;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void completeTask() {
        taskStatus = Status.COMPLETED;
    }
}
