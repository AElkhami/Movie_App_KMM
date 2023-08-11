package com.example.movieapp.di

import com.example.movieapp.presentation.home.HomeScreen
import com.example.movieapp.presentation.home.HomeViewModel
import com.example.movieapp.presentation.overveiw.OverviewScreen
import com.example.movieapp.presentation.overveiw.OverviewViewModel
import org.koin.dsl.module


/**
 * Created by A.Elkhami on 04/08/2023.
 */
val viewModelModule = module {
    scope<HomeScreen> {
        scoped {
            HomeViewModel(get(), get(), get())
        }
    }
    scope<OverviewScreen> {
        scoped {
            OverviewViewModel(get(), get(), get(), get(), get(), get())
        }
    }
}
