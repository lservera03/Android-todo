package com.luisangelservera.androidtodo.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.luisangelservera.androidtodo.R;
import com.luisangelservera.androidtodo.model.Task;

import java.util.ArrayList;

public class TaskListFragment extends Fragment {

    private MyRecyclerViewAdapter adapter;

    private RecyclerView recyclerView;
    private ArrayList<Task> tasks;


    public TaskListFragment(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_task_list, container, false);

        recyclerView = v.findViewById(R.id.taskListRecyclerView);

        adapter = new MyRecyclerViewAdapter(getContext(), tasks);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(adapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        return v;
    }
}
