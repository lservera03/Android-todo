package com.luisangelservera.androidtodo.model.api;

import com.luisangelservera.androidtodo.model.Task;

import java.util.List;

import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {


    private static APIClient shared;

    private Retrofit retrofit;
    private JsonPlaceHolderAPI service;


    public static APIClient getInstance() {
        if (shared == null) {
            shared = new APIClient();
        }

        return shared;
    }


    public APIClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.service = retrofit.create(JsonPlaceHolderAPI.class);
    }


    public void getTasks(Callback<List<Task>> callback) {
        this.service.getTasks().enqueue(callback);
    }

}
