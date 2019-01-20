package com.example.phongdnd.jsonexample.service;

import com.example.phongdnd.jsonexample.model.User;
import com.example.phongdnd.jsonexample.model.Ve;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface VeService {
    @GET("findall")
    Call<List<Ve>> findAll();
    @POST("create")
    Call<Void> create(@Body Ve ve);
    @GET("findve/ngay/marap/masuat")
    Call<List<Ve>> findve(@Query("ngay") String ngay,@Query("marap") int marap,@Query("masuat") int masuat);
}
