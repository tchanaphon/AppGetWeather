package com.example.testweather

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.testweather.api.OpenWeatherAPIRepository
import com.example.testweather.api.OpenweatherAPI
import com.example.testweather.factory.WeatherViewModelFactory
import com.example.testweather.databinding.FragmentWeatherBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.async
import java.util.*

@AndroidEntryPoint
class WeatherFragment : Fragment() {
    private lateinit var binding:FragmentWeatherBinding

    private val app_id = "2e1a07ea728d2b38affd10d9d70f3fe2"

    private val viewModel: WeatherViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
       // viewModel = ViewModelProvider(this,factory)[WeatherViewModel::class.java]
        getWeather("Bangkok")

    }

    @SuppressLint("SetTextI18n")
    private fun getWeather(city:String){
        viewModel.getWeather(city,app_id,getTemType())

        viewModel.weather.observe(viewLifecycleOwner) {
            binding.city.text = it.name
            val datetime = Date(it.dt*1000)
            binding.time.text = datetime.toString()
            getIcon(binding.icon,it.weather[0].icon)
            binding.tvTem.text = it.main.temp.toString()+" °C"
            binding.tvHum.text = it.main.humidity.toString()+" %"
            binding.feels.text = "Feels like "+it.main.feelsLike+" °C"
            binding.description.text = it.weather[0].description
            binding.wind.text = "Wind Speed "+it.wind.speed
        }
    }

    fun Activity.hideKeyboard() {
        hideKeyboard(currentFocus ?: View(this))
    }

    fun Activity.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun getTemType(): String {
        val shard = requireContext().getSharedPreferences("TEMPTYPE", Context.MODE_PRIVATE)
        return shard.getString("unit", "metric").toString()
    }

    fun setTemType(string: String){
        val shard = requireContext().getSharedPreferences("TEMPTYPE", Context.MODE_PRIVATE)
        shard.edit().putString("unit",string).apply()
    }

}