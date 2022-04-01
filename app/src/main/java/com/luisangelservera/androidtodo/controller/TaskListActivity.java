package com.luisangelservera.androidtodo.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.luisangelservera.androidtodo.R;
import com.luisangelservera.androidtodo.model.Task;
import com.luisangelservera.androidtodo.model.api.APIClient;
import com.luisangelservera.androidtodo.model.api.JsonPlaceHolderAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TaskListActivity extends AppCompatActivity {

    private ArrayList<Task> tasks = new ArrayList<>();

    private FloatingActionButton addTaskFAB;

    private final int CREATE_TASK_ACTIVITY = 1;
    private Fragment fragment;

    private Task newTask;

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

        ArrayList<Task> tasksSP = SharedPreferencesManager.getTasks(this);

        //TODO control shared preferences problem
        //if (tasksSP == null) {

        APIClient.getInstance().getTasks(new Callback<List<Task>>() {
            @Override
            public void onResponse(Call<List<Task>> call, Response<List<Task>> response) {
                Log.d("ANDROID_TODO", "GET TASKS REQUEST OK");
                if (response.body() != null) {
                    tasks.addAll(response.body());
                    ((TaskListFragment) fragment).updateUI();
                }

            }

            @Override
            public void onFailure(Call<List<Task>> call, Throwable t) {
                Log.d("ANDROID_TODO", "GET TASKS REQUEST KO");
            }
        });


        SharedPreferencesManager.saveTasks(this, tasks);
        //} else {
        //  this.tasks.addAll(tasksSP);
        //}

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CREATE_TASK_ACTIVITY) {
            if (resultCode == Activity.RESULT_OK) {
                String newTaskName = data.getStringExtra(CreateTaskFragment.NEW_TASK_NAME);
                newTask = new Task(newTaskName);
                
                APIClient.getInstance().createTask(new Callback<Task>() {
                    @Override
                    public void onResponse(Call<Task> call, Response<Task> response) {
                        Log.d("ANDROID_TODO", "CREATE TASK REQUEST OK");
                        tasks.add(newTask);
                        TaskListFragment taskFragment = (TaskListFragment) fragment;
                        taskFragment.addNewData(tasks.size() - 1);
                        Toast.makeText(TaskListActivity.this, R.string.created_task, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Task> call, Throwable t) {
                        Log.d("ANDROID_TODO", "CREATE TASK REQUEST KO");
                        Toast.makeText(TaskListActivity.this, R.string.error_created_task, Toast.LENGTH_SHORT).show();
                    }
                }, newTask);


            }
        } else if (requestCode == TaskListFragment.EDIT_TASK_ACTIVITY) {
            if (resultCode == Activity.RESULT_OK) {
                String newName = data.getStringExtra(EditTaskActivity.TASK_NAME);

                //TODO edit task PUT petition

                ((TaskListFragment) fragment).setNewTitle(newName);
            }
        }
    }

}