package com.example.testweather.RecycleView

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testweather.R
import com.example.testweather.data.model.ForecastInfo
import java.text.SimpleDateFormat
import java.util.*

class ForecastAdapter(var forecast: ForecastInfo, val context: Context) :RecyclerView.Adapter<ForecastAdapter.ViewHolder>(){

    class ViewHolder(itemview: View):RecyclerView.ViewHolder(itemview)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_list_forecast,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.show_time).text = forecast.list[position].dt_txt
        holder.itemView.findViewById<TextView>(R.id.show_tem).text = forecast.list[position].main.temp.toString()
        holder.itemView.findViewById<TextView>(R.id.show_hum).text = forecast.list[position].main.humidity.toString()
    }

    override fun getItemCount(): Int {
        return forecast.list.size
    }

    fun getDateTimeFormLong(long: Long): String {
        val format = SimpleDateFormat("yyyy-MM--dd HH:mm:ss", Locale.getDefault())
        return format.format(Date(long))
    }
}