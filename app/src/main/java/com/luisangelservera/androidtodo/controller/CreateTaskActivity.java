package com.luisangelservera.androidtodo.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.luisangelservera.androidtodo.R;

public class CreateTaskActivity extends AppCompatActivity {

    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);

/**
 actionBar = getActionBar();
 getSupportActionBar().setDisplayHomeAsUpEnabled(true);
 **/
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.create_task_container);

        if (fragment == null) {
            fragment = new CreateTaskFragment();
            fm.beginTransaction()
                    .add(R.id.create_task_container, fragment)
                    .commit();
        }


    }


    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, CreateTaskActivity.class);


        return intent;
    }

}