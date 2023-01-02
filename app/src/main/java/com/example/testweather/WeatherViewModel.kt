package com.example.testweather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import com.example.testweather.api.OpenWeatherAPIRepository
import com.example.testweather.data.model.WeatherInfo2
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: OpenWeatherAPIRepository
) : ViewModel() {

    private lateinit var job: Job

    private val _weather = MutableLiveData<WeatherInfo2>()
    val weather : LiveData<WeatherInfo2>
        get() = _weather

    fun getWeather(q:String,appId:String,unit:String){
        job = Coroutines.ioThenMain({
            repository.getWeather(q,appId,unit)
        },{
            _weather.value = it!!.body()
        })
    }

    private val _weather2 :MutableSharedFlow<WeatherInfo2> = MutableSharedFlow()

//    init {
//        ScopeProvider.launch {
//            _weather2.collect { w ->
//                _weather.value = w
//            }
//        }
//    }

    fun getWeather2(q:String,appId:String,unit:String):Job{
        return ScopeProvider.launch {
            _weather2.collect{
                repository.getWeather(q,appId,unit)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        if(::job.isInitialized)
            job.cancel()
    }
}