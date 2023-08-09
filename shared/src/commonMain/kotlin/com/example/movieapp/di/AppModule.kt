package com.example.movieapp.di

import com.example.movieapp.presentation.home.HomeViewModel
import com.example.movieapp.presentation.overveiw.OverviewViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
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

object GetViewModels : KoinComponent {
    fun getHomeViewModel() = get<HomeViewModel>()
    fun getOverviewViewModel() = get<OverviewViewModel>()
}

fun initKoin() = initKoin {}