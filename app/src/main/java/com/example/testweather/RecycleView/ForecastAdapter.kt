package com.example.testweather.RecycleView

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.testweather.R
import com.example.testweather.data.model.ForecastInfo
import com.example.testweather.databinding.FragmentForecastBinding
import com.example.testweather.databinding.LayoutListForecastBinding
import com.example.testweather.getIcon
import java.text.SimpleDateFormat
import java.util.*

class ForecastAdapter(
    var forecast: ForecastInfo,
    ) :RecyclerView.Adapter<ForecastAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            DataBindingUtil.inflate<LayoutListForecastBinding>(
                LayoutInflater.from(parent.context),
                R.layout.layout_list_forecast,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getIcon(holder.recyclerviewForecastBinding.image,forecast.list[position].weather[0].icon)
        holder.recyclerviewForecastBinding.showTime.text = forecast.list[position].dt_txt
        holder.recyclerviewForecastBinding.showTem.text = forecast.list[position].main.temp.toString().plus(" C°")
        holder.recyclerviewForecastBinding.showHum.text = forecast.list[position].main.humidity.toString().plus(" %")
    }

    inner class ViewHolder(
        val recyclerviewForecastBinding: LayoutListForecastBinding
    ):RecyclerView.ViewHolder(recyclerviewForecastBinding.root)

    override fun getItemCount(): Int {
        return forecast.list.size
    }

    fun getDateTimeFormLong(long: Long): String {
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return format.format(Date(long))
    }
}