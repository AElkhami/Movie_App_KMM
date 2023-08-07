package com.example.movieapp.application

import android.app.Application
import com.example.movieapp.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

/**
 * Created by A.Elkhami on 18/07/2023.
 */
class AiMoviesApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidLogger()
            androidContext(this@AiMoviesApp)
        }
    }
}