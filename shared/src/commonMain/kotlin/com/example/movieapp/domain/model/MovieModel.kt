package com.example.movieapp.domain.model

/**
 * Created by A.Elkhami on 18/07/2023.
 */
data class MovieModel(
    val movieId: Long,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double
)
