package com.luisangelservera.androidtodo.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.Activity;
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

    private final int CREATE_TASK_ACTIVITY = 1;
    private Fragment fragment;


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
                startActivityForResult(intent, CREATE_TASK_ACTIVITY);
            }
        });

        FragmentManager fm = getSupportFragmentManager();
        fragment = fm.findFragmentById(R.id.fragment_container);

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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CREATE_TASK_ACTIVITY) {
            if (resultCode == Activity.RESULT_OK) {
                String newTaskName = data.getStringExtra(CreateTaskFragment.NEW_TASK_NAME);
                tasks.add(new Task(newTaskName));
                TaskListFragment taskFragment = (TaskListFragment) fragment;
                taskFragment.updateData(tasks.size() - 1);

                Toast.makeText(TaskListActivity.this, R.string.created_task, Toast.LENGTH_SHORT).show();
            }
        }
    }

}