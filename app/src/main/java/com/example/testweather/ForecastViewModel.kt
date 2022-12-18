package com.example.testweather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testweather.api.OpenWeatherAPIRepository
import com.example.testweather.data.model.ForecastInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import javax.inject.Inject

@HiltViewModel
class ForecastViewModel @Inject constructor(
    private val repository : OpenWeatherAPIRepository
) : ViewModel() {

    private lateinit var job: Job

    private val _forcast = MutableLiveData<ForecastInfo>()
    val forcast : LiveData<ForecastInfo>
        get() = _forcast

    fun getForcast(q:String, appId:String, unit:String){
        job = Coroutines.ioThenMain({
                repository.getForecast(q,appId,unit)
            }, {
                _forcast.value = it!!.body()
            }
        )
    }

    override fun onCleared() {
        super.onCleared()
        if(::job.isInitialized)
            job.cancel()
    }
}