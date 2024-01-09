package com.example.wsselixir

import android.app.Application
import com.example.wsselixir.remote.NetworkModule

class App : Application() {
    val serviceLocator = ServiceLocator()

    override fun onCreate() {
        super.onCreate()
        serviceLocator.provideMemberRepository(NetworkModule.reqresApi)
    }
}