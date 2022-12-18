package com.example.testweather.api

import com.example.testweather.data.model.ForecastInfo
import com.example.testweather.data.model.WeatherInfo2
import dagger.Binds
import dagger.Component
import dagger.Provides
import dagger.hilt.InstallIn
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenweatherAPI {
    @GET("/data/2.5/weather")
    suspend fun getCurrentWeatherInfo(@Query("q")q:String, @Query("appid")appid:String, @Query("units")units:String):Response<WeatherInfo2>

    @GET("/data/2.5/forecast")
    suspend fun getForecastInfo(@Query("q")q:String, @Query("appid")appid:String, @Query("units")units:String):Response<ForecastInfo>

    companion object{
        var openweatherAPI:OpenweatherAPI? = null

        fun getInstance() : OpenweatherAPI{
            if(openweatherAPI == null){
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.openweathermap.org")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
                openweatherAPI = retrofit.create(OpenweatherAPI::class.java)
            }
            return openweatherAPI!!
        }
    }
}