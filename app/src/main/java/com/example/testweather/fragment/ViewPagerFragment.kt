package com.example.testweather.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.PagerAdapter
import com.example.testweather.adapters.FORECAST_PAGE_INDEX
import com.example.testweather.adapters.WEATHER_PAGE_INDEX
import com.example.testweather.databinding.FragmentViewPagerBinding
import com.google.android.material.tabs.TabLayoutMediator

class ViewPagerFragment : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentViewPagerBinding.inflate(inflater,container,false)
        val tabLayout = binding.tabs
        val viewPager = binding.viewPager

        viewPager.adapter = com.example.testweather.adapters.PagerAdapter(this)

        TabLayoutMediator(tabLayout,viewPager) {tab, position ->
            tab.text = getTabTitle(position)
        }.attach()

        (activity as AppCompatActivity)

        return binding.root
    }

    private fun getTabTitle(position: Int): String?{
        return when(position){
            WEATHER_PAGE_INDEX -> "Weather"
            FORECAST_PAGE_INDEX -> "Forecast"
            else -> null
        }
    }
}