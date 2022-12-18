package com.example.testweather.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testweather.WeatherViewModel
import com.example.testweather.api.OpenWeatherAPIRepository
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class WeatherViewModelFactory @Inject constructor(
    private val repository: OpenWeatherAPIRepository
) : ViewModelProvider.AndroidViewModelFactory(){

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return WeatherViewModel(repository) as T
    }

}