package com.example.submission2.API;

import com.example.submission2.BuildConfig;
import com.example.submission2.Model.FollowerModel;
import com.example.submission2.Model.FollowingModel;
import com.example.submission2.Model.UserDetailModel;
import com.example.submission2.Response.UserResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIUser {
    @GET("search/users")
    @Headers("Authorization: token "+ BuildConfig.GITHUB_TOKEN)
    Call<UserResponse> getSearchUser(
            @Query("q") String username
    );

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
