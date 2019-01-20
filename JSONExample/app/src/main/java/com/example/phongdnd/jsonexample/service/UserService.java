package com.example.phongdnd.jsonexample.service;

import com.example.phongdnd.jsonexample.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserService {
    @GET("findall")
    Call<List<User>> findAll();
    @GET("find/id/pass")
    Call<User> find(@Query("id") String id,@Query("pass") String pass);
    @POST("create")
    Call<Void>  create(@Body User user);
}
