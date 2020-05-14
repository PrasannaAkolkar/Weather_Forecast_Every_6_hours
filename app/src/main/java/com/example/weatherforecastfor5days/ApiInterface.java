package com.example.weatherforecastfor5days;

import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiInterface {


    @GET("forecast")
    Call<ModelClass> getWeatherDetails(

            @Query("q") String q,
            @Query("appid") String appid

    );
}
