package com.example.eanatramobile.apiInterface;

import com.example.eanatramobile.modele.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface PostsApi {

    @GET("posts")
    Call<List<Post>> getPost() ;

    @POST("posts")
    Call<Post> createPost(@Body Post post);

}
