package com.lucascabral.appdesafionexaas.api;

import com.lucascabral.appdesafionexaas.model.ProductResponse;

import java.util.List;

import retrofit2.Call;

import retrofit2.http.GET;


public interface Api {


   @GET("api/data.json")
   Call<List<ProductResponse>> recuperarDados();

}
