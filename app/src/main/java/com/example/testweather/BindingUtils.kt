package com.example.testweather

import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("image")
fun getIcon(view:ImageView, icon:String){
    when(icon){
        "01n" -> view.setImageResource(R.drawable.n01)
        "01d" -> view.setImageResource(R.drawable.d01)
        "02n" -> view.setImageResource(R.drawable.n02)
        "02d" -> view.setImageResource(R.drawable.d02)
        "03n" -> view.setImageResource(R.drawable.n03)
        "03d" -> view.setImageResource(R.drawable.d03)
        "04n" -> view.setImageResource(R.drawable.n04)
        "04d" -> view.setImageResource(R.drawable.d04)
        "10n" -> view.setImageResource(R.drawable.n10)
        "10d" -> view.setImageResource(R.drawable.d10)
    }
}