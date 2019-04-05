package com.example.weatherjson;

import com.example.weatherjson.model.Feed;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface OpenweatherAPI {

    String BASE_URL = "https://api.openweathermap.org/data/2.5/";
    @Headers("Content-Type: application/json")
    @GET("weather?id=498817&APPID=86d7eac094f6e0b83461ce08010bc713")
    Call<Feed> getMain();
}
