package com.example.movieapp

import com.example.movieapp.domain.model.MovieModel

/**
 * Created by A.Elkhami on 09/08/2023.
 */
sealed interface Route {
    object Home : Route
    data class Overview(val movie: MovieModel) : Route
}