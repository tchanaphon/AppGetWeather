package com.example.testweather

import android.app.Activity
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.example.testweather.data.GetModel
import com.example.testweather.data.model.WeatherInfo2
import com.example.testweather.databinding.FragmentWeatherBinding

class WeatherFragment : Fragment() {
    private lateinit var binding:FragmentWeatherBinding

    companion object {
        fun newInstance() = WeatherFragment()
    }

    private lateinit var viewModel: WeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[WeatherViewModel::class.java]

        binding.btSearchWeather.setOnClickListener {
            val cityname:String = binding.editText.text.toString()
            GetModel.callWeatherApi(cityname,getTemType(),object :GetModel.OnGetWeather{
                override fun onSuccess(w: WeatherInfo2) {
                    println(w.toString())
                    binding.tvTem.text = w.main.temp.toString()
                    binding.tvHum.text = w.main.humidity.toString()
                    when(getTemType()){
                        "metric" -> binding.showtype.text = getString(R.string.Celsius)
                        "imperial" -> binding.showtype.text = getString(R.string.Fahrenheit)
                    }
                }
                override fun onFail(string: String) {
                    println(string)
                    Toast.makeText(context,string,Toast.LENGTH_SHORT).show()
                }
            })
            requireActivity().hideKeyboard()
        }

        binding.temType.setOnClickListener{
            when(getTemType()){
                "metric" -> { setTemType("imperial") }
                "imperial" -> { setTemType("metric") }
            }
            binding.showtype.text = ""
            binding.tvTem.text = ""
            binding.tvHum.text = ""
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