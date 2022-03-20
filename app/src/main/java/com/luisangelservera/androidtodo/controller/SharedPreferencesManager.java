package com.luisangelservera.androidtodo.controller;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.luisangelservera.androidtodo.model.Task;

import java.util.ArrayList;

public class SharedPreferencesManager {


    public static String TASKS = "TASKS";

    public static void saveTasks(Activity activity, ArrayList<Task> tasks) {

        Gson gson = new Gson();

        String tasksJson = gson.toJson(tasks);

        SharedPreferences preferences = activity.getPreferences(Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();

        editor.putString(TASKS, tasksJson);
        editor.apply();
    }


    public static ArrayList<Task> getTasks(Activity activity) {
        SharedPreferences preferences = activity.getPreferences(Context.MODE_PRIVATE);

        String tasksString = preferences.getString(TASKS, "");

        Gson gson = new Gson();

        return gson.fromJson(tasksString, new TypeToken<ArrayList<Task>>() {
        }.getType());
    }


}
