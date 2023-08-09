package com.example.movieapp.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import com.example.movieapp.presentation.home.HomeScreen

/**
 * Created by A.Elkhami on 03/08/2023.
 */

@Composable
fun App() {
    Surface(
        modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
    ) {
        Navigator(
            HomeScreen
        )
    }
}