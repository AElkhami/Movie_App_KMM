package com.example.movieapp.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

/**
 * Created by A.Elkhami on 18/07/2023.
 */
fun initKoin(appDeclaration: KoinAppDeclaration = {}) {
    startKoin {
        appDeclaration()
        modules(
            platformModule(),
            useCaseModule,
            repositoryModule,
            remoteDataModule,
            localDataModule,
            viewModelModule
        )
    }
}

fun initKoin() = initKoin {}