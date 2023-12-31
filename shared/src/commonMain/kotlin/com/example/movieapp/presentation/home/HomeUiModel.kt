package com.example.movieapp.presentation.home

import com.example.movieapp.domain.model.MovieModel

/**
 * Created by A.Elkhami on 19/07/2023.
 */
data class HomeUiModel(
    val discoverMovieList: List<MovieModel> = emptyList(),
    val favouriteMovieList: List<MovieModel> = emptyList(),
    val isLoading: Boolean = true,
    val errorMessage: String = ""
)
