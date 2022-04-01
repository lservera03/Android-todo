package com.luisangelservera.androidtodo.model;


import java.io.Serializable;

public class Task implements Serializable {

    private Integer userId;
    private Integer id;
    private String title;
    private Boolean completed;

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Task(String title) {
        this.title = title;
        this.userId = 1;
        this.completed = false;
    }

    public String getTitle() {
        return title;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setTitle(String newName) {
        this.title = newName;
    }
}
