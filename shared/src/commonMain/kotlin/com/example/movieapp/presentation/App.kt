package com.example.movieapp.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.movieapp.presentation.home.HomeScreen
import com.example.movieapp.presentation.overveiw.OverviewScreen

/**
 * Created by A.Elkhami on 03/08/2023.
 */

@Composable
fun App() {
    Surface(
        modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
    ) {
        HomeScreen { id, title, overview, releaseDate, posterPath, voteAverage ->
        }
    }
}