package com.example.movieapp.di

import com.example.movieapp.data.local.DatabaseDriverFactory
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * Created by A.Elkhami on 22/08/2023.
 */
actual fun platformModule(): Module = module {
    single { DatabaseDriverFactory(get()).createDriver() }
}