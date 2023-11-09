package com.taskmanager.models;

import jakarta.persistence.*;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    @Column(length = 400)
    private String description;
    private Status taskStatus;
    public enum Status {
        PENDING,
        COMPLETED
    }

    public Task() { }

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        taskStatus = Status.PENDING;
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

    public void changeStatus(Status status) {
        taskStatus = status;
    }

    public int getId() {
        return id;
    }

    public Status getTaskStatus() {
        return taskStatus;
    }
}
