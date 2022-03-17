package com.luisangelservera.androidtodo.controller;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.luisangelservera.androidtodo.R;
import com.luisangelservera.androidtodo.model.Task;


import java.util.ArrayList;


public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private ArrayList<Task> tasks;
    private LayoutInflater inflater;
    private MyItemListener listener;

    public MyRecyclerViewAdapter(Context context, ArrayList<Task> tasks, MyItemListener listener) {
        this.inflater = LayoutInflater.from(context);
        this.tasks = tasks;
        this.listener = listener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item_task, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Task task = tasks.get(position);

        holder.taskDoneCB.setActivated(task.isDone());
        holder.tasknameTV.setText(task.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.myOnClick(holder.getAbsoluteAdapterPosition());
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return this.tasks.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tasknameTV;
        private CheckBox taskDoneCB;


        ViewHolder(View itemView) {
            super(itemView);
            tasknameTV = itemView.findViewById(R.id.taskNameTV);
            taskDoneCB = itemView.findViewById(R.id.taskDoneCB);

            taskDoneCB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tasks.get(getAbsoluteAdapterPosition()).setDone(taskDoneCB.isChecked());

                    if (tasks.get(getAbsoluteAdapterPosition()).isDone()) {
                        tasknameTV.setPaintFlags(tasknameTV.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    } else {
                        tasknameTV.setPaintFlags(tasknameTV.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    }
                }
            });


        }

    }


}
