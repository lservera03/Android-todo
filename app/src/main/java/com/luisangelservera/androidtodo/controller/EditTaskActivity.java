package com.luisangelservera.androidtodo.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.luisangelservera.androidtodo.R;

public class EditTaskActivity extends AppCompatActivity {


    public static final String TASK_NAME = "TASK_NAME";

    private EditText taskTitleET;
    private FloatingActionButton editTaskFAB;

    private String taskName = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        taskName = getIntent().getStringExtra(TASK_NAME);

        taskTitleET = findViewById(R.id.editTaskTitleET);

        taskTitleET.setText(taskName);

        taskTitleET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                taskName = taskTitleET.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        editTaskFAB = findViewById(R.id.editSaveTaskFAB);
        editTaskFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!taskTitleET.getText().toString().equals("")) {
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra(TASK_NAME, taskName);
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                } else {
                    Toast.makeText(EditTaskActivity.this, R.string.task_name_error_string, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, EditTaskActivity.class);


        return intent;
    }


}