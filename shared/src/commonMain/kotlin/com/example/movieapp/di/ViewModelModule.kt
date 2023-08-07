package com.example.movieapp.di

import com.example.movieapp.presentation.home.HomeViewModel
import org.koin.dsl.module


/**
 * Created by A.Elkhami on 04/08/2023.
 */
val viewModelModule = module {
    single {
        HomeViewModel(get(), get(), get())
    }
}
