package com.example.testweather.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testweather.ForecastViewModel
import com.example.testweather.api.OpenWeatherAPIRepository
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class ForcastViewModelFactory @Inject constructor(
    private val repository : OpenWeatherAPIRepository
) : ViewModelProvider.NewInstanceFactory(){


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ForecastViewModel(repository) as T
    }
}

