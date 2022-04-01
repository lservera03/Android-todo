package com.luisangelservera.androidtodo.model.api;


import com.luisangelservera.androidtodo.model.Task;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface JsonPlaceHolderAPI {

    @GET("todos/")
    Call<List<Task>> getTasks();

    @Headers({
            "'Content-type':'application/json; charset=UTF-8'",
    })
    @POST("todos/")
    Call<Task> createTask(@Body Task task);

}
