package com.example.phongdnd.jsonexample.service;

import com.example.phongdnd.jsonexample.model.Phim;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PhimService {
    @GET("findall")
    Call<List<Phim>> findAll();
    @GET("findphim/id")
    Call<Phim> findphim(@Query("id") int id);
}
