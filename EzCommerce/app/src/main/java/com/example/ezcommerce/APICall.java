package com.example.ezcommerce;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APICall {

    @GET("staging/book?nim=2201782731&nama=Eric%20Wijaya")
    Call<ModelUser> getData();

    @GET("staging/book?nim=2201782731&nama=Eric%20Wijaya")
    Call<JSRes> getProduct();

}
