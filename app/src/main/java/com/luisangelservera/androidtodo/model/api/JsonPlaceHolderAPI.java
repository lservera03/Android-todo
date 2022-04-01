package com.luisangelservera.androidtodo.model.api;


import com.luisangelservera.androidtodo.model.Task;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JsonPlaceHolderAPI {

    @GET("todos/")
    Call<List<Task>> getTasks();

}
