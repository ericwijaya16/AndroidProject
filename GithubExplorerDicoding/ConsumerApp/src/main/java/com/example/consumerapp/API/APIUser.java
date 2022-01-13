package com.example.consumerapp.API;

import com.example.consumerapp.BuildConfig;
import com.example.consumerapp.Model.FollowerModel;
import com.example.consumerapp.Model.FollowingModel;
import com.example.consumerapp.Model.UserDetailModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface APIUser {

    @GET("users/{username}")
    @Headers("Authorization: token "+ BuildConfig.GITHUB_TOKEN)
    Call<UserDetailModel> getDetailUser(
            @Path("username") String username
    );

    @GET("users/{username}/followers")
    @Headers("Authorization: token "+ BuildConfig.GITHUB_TOKEN)
    Call<List<FollowerModel>> getFollowerUser(
            @Path("username") String username
    );

    @GET("users/{username}/following")
    @Headers("Authorization: token "+ BuildConfig.GITHUB_TOKEN)
    Call<List<FollowingModel>> getFollowingUser(
            @Path("username") String username
    );
}
