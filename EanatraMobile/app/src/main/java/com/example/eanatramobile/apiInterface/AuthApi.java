package com.example.eanatramobile.apiInterface;

import com.example.eanatramobile.modele.Test;
import com.example.eanatramobile.modele.Users;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AuthApi {

    @GET("users/test")
    Call<HashMap<String,Object>> test();

    @POST("users/sign")
    Call<HashMap<String,Object>> createNewUser(@Body Users users) ;

    @POST("users/login")
    Call<HashMap<String,Object>> login(@Body Users users) ;
}
