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

/**
 * Created by A.Elkhami on 03/08/2023.
 */

@Composable
fun App() {
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
        ) {
            var currentScreen: Route by remember { mutableStateOf(Route.Home) }

            when (currentScreen) {
                Route.Home -> HomeScreen(route = { currentScreen = it })

                is Route.Overview -> {
                    OverviewScreen(
                        movie = (currentScreen as Route.Overview).movie,
                        backAction = { currentScreen = Route.Home }
                    )
                }
            }
        }
    }
}