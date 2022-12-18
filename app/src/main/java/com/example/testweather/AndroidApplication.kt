package com.example.testweather

import android.app.Application
import com.example.testweather.api.OpenweatherAPI
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@HiltAndroidApp()
class AndroidApplication : Application() {

}

@Module
@InstallIn(SingletonComponent::class)
object OpenweatherAPIModule{

    @Provides
    fun provideOpenweatherService(): OpenweatherAPI = OpenweatherAPI.getInstance()
}

