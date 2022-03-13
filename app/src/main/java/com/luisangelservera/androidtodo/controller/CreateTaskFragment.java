package com.luisangelservera.androidtodo.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.luisangelservera.androidtodo.R;

public class CreateTaskFragment extends Fragment {

    public static final String NEW_TASK_NAME = "NEW_TASK_NAME";
    private EditText taskNameET;
    private FloatingActionButton saveTaskFAB;

    private String newTaskName;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_create_task, container, false);


        taskNameET = v.findViewById(R.id.taskTitleET);
        taskNameET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                newTaskName = taskNameET.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        saveTaskFAB = v.findViewById(R.id.saveTaskFAB);
        saveTaskFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!taskNameET.getText().toString().equals("")) {
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra(NEW_TASK_NAME, newTaskName);
                    getActivity().setResult(Activity.RESULT_OK, returnIntent);
                    getActivity().finish();
                } else {
                    Toast.makeText(getContext(), R.string.task_name_error_string, Toast.LENGTH_SHORT).show();
                }
            }
        });


        return v;
    }


}
