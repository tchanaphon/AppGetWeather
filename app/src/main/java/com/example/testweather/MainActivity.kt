package com.example.testweather

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.fragment.app.FragmentManager
import com.example.testweather.data.GetModel
import com.example.testweather.data.model.WeatherInfo2
import com.example.testweather.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().add(R.id.fragmentContainer,WeatherFragment.newInstance()).commit()
        binding.btWeather.setOnClickListener {
            println("onClickWeather")
            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer,WeatherFragment.newInstance()).commit()
        }
        binding.btForecast.setOnClickListener {
            println("onForecast")
            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer,ForecastFragment.newInstance()).commit()
        }

    }
}