package com.example.movieapp.application

import android.app.Application
import org.koin.core.context.startKoin

/**
 * Created by A.Elkhami on 18/07/2023.
 */
class AiMoviesApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
//            androidLogger()
//            androidContext(this@AiMoviesApp)
//            modules(dataModule, homeModule)
        }
    }
}