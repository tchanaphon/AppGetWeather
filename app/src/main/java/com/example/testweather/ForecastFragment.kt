package com.example.testweather

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testweather.adapters.ForecastAdapter
import com.example.testweather.factory.ForcastViewModelFactory
import com.example.testweather.databinding.FragmentForecastBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForecastFragment : Fragment() {
    private lateinit var binding: FragmentForecastBinding

    private lateinit var factory: ForcastViewModelFactory
    private val viewModel: ForecastViewModel by viewModels()

    private val app_id = "2e1a07ea728d2b38affd10d9d70f3fe2"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentForecastBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.getForcast("bangkok",app_id,getTemType())
        viewModel.forcast.observe(viewLifecycleOwner, Observer { forcastInfo ->
            binding.recyclerview.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter = ForecastAdapter(forcastInfo)
            }
        })
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