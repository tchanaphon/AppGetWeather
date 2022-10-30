package com.example.testweather.api

import com.example.testweather.data.model.ForecastInfo
import com.example.testweather.data.model.WeatherInfo2
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenweatherAPI {
    @GET("/data/2.5/weather")
    fun getCurrentWeatherInfo(@Query("q")q:String, @Query("appid")appid:String, @Query("units")units:String):Observable<WeatherInfo2>

    @GET("/data/2.5/forecast")
    fun getForecastInfo(@Query("q")q:String, @Query("appid")appid:String, @Query("units")units:String):Observable<ForecastInfo>

}