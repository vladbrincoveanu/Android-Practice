package com.example.vladpc.testap.testapp.Adapter;

import com.example.vladpc.testap.testapp.Models.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface API {
    String baseUrl = "https://cgisdev.utcluj.ro/moodle/chat-piu/";

    @POST("authenticate.php")
    @Headers("Content-Type: application/json")
    Call<Void> login(@Body User user);
}
