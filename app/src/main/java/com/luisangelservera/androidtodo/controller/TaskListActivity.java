package com.luisangelservera.androidtodo.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.luisangelservera.androidtodo.R;
import com.luisangelservera.androidtodo.model.Task;

import java.util.ArrayList;

public class TaskListActivity extends AppCompatActivity {

    private ArrayList<Task> tasks = new ArrayList<>();

    private FloatingActionButton addTaskFAB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        loadTasks();

        addTaskFAB = findViewById(R.id.addTaskFAB);
        addTaskFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = CreateTaskActivity.newIntent(TaskListActivity.this);
                startActivity(intent);
            }
        });

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            fragment = new TaskListFragment(tasks);
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }

    }


    private void loadTasks() {
        //TODO check Strings problems
        this.tasks.add(new Task("Walk the dog"));
        this.tasks.add(new Task("Buy bread"));
        this.tasks.add(new Task("Check La Salle mail"));
        this.tasks.add(new Task("Prepare today's meetings"));
        this.tasks.add(new Task("Workout"));

    }


}