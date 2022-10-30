package com.example.testweather.data

import com.example.testweather.api.OpenweatherAPI
import com.example.testweather.data.model.ForecastInfo
import com.example.testweather.data.model.WeatherInfo2
import com.google.gson.GsonBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.schedulers.IoScheduler
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object GetModel{

    public interface OnGetWeather{
        fun onSuccess(w: WeatherInfo2)
        fun onFail(string: String)
    }

    interface OnGetForecast{
        fun onSuccess(w: ForecastInfo)
        fun onFail(string: String)
    }

    private val retrofit = Retrofit.Builder().addConverterFactory(
        GsonConverterFactory.create(GsonBuilder().create()))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl("https://api.openweathermap.org").build()
    private val postsAPI = retrofit.create(OpenweatherAPI::class.java)
    private const val app_id = "2e1a07ea728d2b38affd10d9d70f3fe2"

    fun callWeatherApi(city:String,unit:String, onGetWeather: OnGetWeather){
        val response = postsAPI.getCurrentWeatherInfo(city,app_id,unit)
        response.observeOn(AndroidSchedulers.mainThread()).subscribeOn(IoScheduler()).subscribe({t: WeatherInfo2? ->
            if (t != null) {
                onGetWeather.onSuccess(t)
            }
        },{t:Throwable? ->
            run {
                onGetWeather.onFail(t.toString())
            }
        })
    }

    fun callWeatherApi(city:String,unit:String, onGetForecast: OnGetForecast){
        val response = postsAPI.getForecastInfo(city,app_id,unit)
        response.observeOn(AndroidSchedulers.mainThread()).subscribeOn(IoScheduler()).subscribe({t: ForecastInfo? ->
            if (t != null) {
                onGetForecast.onSuccess(t)
            }
        },{t:Throwable? ->
            run {
                onGetForecast.onFail(t.toString())
            }
        })
    }
}