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

        holder.taskDoneCB.setChecked(task.getCompleted());
        holder.tasknameTV.setText(task.getTitle());

        if (task.getCompleted()) {
            holder.tasknameTV.setPaintFlags(holder.tasknameTV.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            holder.tasknameTV.setPaintFlags(holder.tasknameTV.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        }

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
                    tasks.get(getAbsoluteAdapterPosition()).setCompleted(taskDoneCB.isChecked());

                    if (tasks.get(getAbsoluteAdapterPosition()).getCompleted()) {
                        tasknameTV.setPaintFlags(tasknameTV.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    } else {
                        tasknameTV.setPaintFlags(tasknameTV.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    }
                }
            });


        }

    }


}
