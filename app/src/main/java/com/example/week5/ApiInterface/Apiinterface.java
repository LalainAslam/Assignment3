package com.example.week5.ApiInterface;

import com.example.week5.Model.UserResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Apiinterface {
    @GET("api")
    Call<UserResponse> getSingleUser();

     @GET("api")
    Call<UserResponse> getMultipleUser(@Query("results") int count);
}
