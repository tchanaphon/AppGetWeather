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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testweather.RecycleView.ForecastAdapter
import com.example.testweather.data.GetModel
import com.example.testweather.data.model.ForecastInfo
import com.example.testweather.data.model.WeatherInfo2
import com.example.testweather.databinding.FragmentForecastBinding
import com.example.testweather.databinding.FragmentWeatherBinding

class ForecastFragment : Fragment() {
    private lateinit var binding: FragmentForecastBinding

    companion object {
        fun newInstance() = ForecastFragment()
    }

    private lateinit var viewModel: ForecastViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentForecastBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ForecastViewModel::class.java)

        binding.recyclerview.layoutManager = GridLayoutManager(requireContext(),1,GridLayoutManager.VERTICAL,false)
        binding.btSearchForecast.setOnClickListener {
            val cityname:String = binding.editText.text.toString()

            GetModel.callWeatherApi(cityname,getTemType(),object : GetModel.OnGetForecast{
                override fun onSuccess(w: ForecastInfo) {
                    binding.recyclerview.adapter = ForecastAdapter(w,requireContext())
                }

                override fun onFail(string: String) {
                    println(string)
                    Toast.makeText(context,string, Toast.LENGTH_SHORT).show()
                }

            })
            requireActivity().hideKeyboard()
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