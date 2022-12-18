package com.example.testweather.api

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(ActivityRetainedComponent::class)
class OpenWeatherAPIRepository @Inject constructor(private val api:OpenweatherAPI){

    suspend fun getWeather(q:String, appId:String, units:String) = api.getCurrentWeatherInfo(q,appId,units)

    suspend fun getForecast(q:String, appId:String, units:String) = api.getForecastInfo(q,appId,units)

}