package com.example.testweather.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.testweather.ForecastFragment
import com.example.testweather.WeatherFragment

const val WEATHER_PAGE_INDEX = 0
const val FORECAST_PAGE_INDEX = 1

class PagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val tabFragment: Map<Int,() -> Fragment> = mapOf(
        WEATHER_PAGE_INDEX to { WeatherFragment() },
        FORECAST_PAGE_INDEX to { ForecastFragment() }
    )

    override fun getItemCount(): Int {
        return tabFragment.size
    }

    override fun createFragment(position: Int): Fragment {
        return tabFragment[position]?.invoke()?: throw IndexOutOfBoundsException()
    }
}