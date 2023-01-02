package com.example.testweather

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import com.example.testweather.api.OpenweatherAPI
import com.example.testweather.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.schedulers.IoScheduler
import kotlinx.coroutines.delay
import javax.inject.Inject
import kotlin.concurrent.thread

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    var myAppId:String? = "..."
    private lateinit var binding:ActivityMainBinding
    private val cel = "metric"
    private val fah = "imperial"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        //WindowCompat.setDecorFitsSystemWindows(window, false)
        setContentView(binding.root)


//        val thread = Thread(Runnable {
//            var aa = 0
//            for (i in 1..5000){
//                val myApi : OpenweatherAPI = OpenweatherAPI.getInstance()
//                aa+=i
//                println(myApi.toString().plus(aa).plus(" ").plus(i))
//            }
//        })
//        //thread.isDaemon = true
//        thread.start()


//        findNavController(R.id.forecastFragment)
//        findNavController(R.id.weatherFragment).popBackStack()

//        supportFragmentManager.beginTransaction().add(R.id.fragmentContainer,WeatherFragment.newInstance()).commit()
//        binding.btWeather.setOnClickListener {
//            println("onClickWeather")
//            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer,WeatherFragment.newInstance()).commit()
//        }
//        binding.btForecast.setOnClickListener {
//            println("onForecast")
//            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer,ForecastFragment.newInstance()).commit()
//        }

//        GlobalScope.launch (Dispatchers.Main){
//            val repository = WeatherRepository(OpenweatherAPI.getInstance())
//            val weather = repository.getWeather("Bangkok",myAppId,cel)
//            Toast.makeText(this@MainActivity,weather.toString(),Toast.LENGTH_LONG).show()
//        }

//        thread(start = true){
//            for (i in 1..1000){
//                val myApi : OpenweatherAPI = OpenweatherAPI.getInstance()
//                val result = myApi.getCurrentWeather("Bangkok",myAppId,cel)
//                result.observeOn(AndroidSchedulers.mainThread()).subscribeOn(IoScheduler()).subscribe { println(it) }
//            }
//        }

    }

    fun getTemType(): String {
        val shard = baseContext.getSharedPreferences("TEMPTYPE", Context.MODE_PRIVATE)
        return shard.getString("unit", "metric").toString()
    }
}