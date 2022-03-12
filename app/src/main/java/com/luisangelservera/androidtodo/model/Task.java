package com.luisangelservera.androidtodo.model;

public class Task {


    private String name;
    private boolean done;

    public Task(String name) {
        this.name = name;
    }


    public boolean isDone() {
        return this.done;
    }

    public String getName() {
        return this.name;
    }

}
