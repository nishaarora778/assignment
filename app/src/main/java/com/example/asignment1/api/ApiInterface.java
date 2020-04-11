package com.example.asignment1.api;

import com.example.asignment1.models.PostModel;
import com.example.asignment1.models.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("users")
    Call<List<UserModel>> getUser();

    @GET("posts")
    Call<List<PostModel>> getPost(@Query("userId")int userId);
}
