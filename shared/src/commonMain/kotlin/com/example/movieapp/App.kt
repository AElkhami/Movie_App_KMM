package com.example.movieapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.movieapp.presentation.home.HomeScreen
import com.example.movieapp.presentation.overveiw.OverviewScreen
import org.koin.mp.KoinPlatform

/**
 * Created by A.Elkhami on 03/08/2023.
 */

@Composable
fun App() {
    val homeScope = KoinPlatform.getKoin().createScope<HomeScreen>()
    var overviewScope = KoinPlatform.getKoin().createScope<OverviewScreen>()

    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
        ) {
            var currentScreen: Route by remember { mutableStateOf(Route.Home) }

            when (currentScreen) {
                Route.Home -> {
                    HomeScreen(route = { currentScreen = it }, homeScope).View()
                }

                is Route.Overview -> {
                    OverviewScreen(
                        movie = (currentScreen as Route.Overview).movie,
                        backAction = {
                            currentScreen = Route.Home
                            overviewScope = KoinPlatform.getKoin().createScope<OverviewScreen>()
                        },
                        overviewScope
                    ).View()
                }
            }
        }
    }
}