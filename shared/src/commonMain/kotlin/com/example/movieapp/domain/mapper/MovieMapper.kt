package com.example.movieapp.domain.mapper


import com.example.movieapp.data.local.favourite.dto.MovieLocal
import com.example.movieapp.data.remote.dto.Movie
import com.example.movieapp.domain.model.MovieModel

/**
 * Created by A.Elkhami on 18/07/2023.
 */
fun Movie.toMovieModel() = MovieModel(
    movieId = (id ?: 0).toLong(),
    title = title ?: "",
    overview = overview ?: "",
    releaseDate = release_date ?: "",
    posterPath = poster_path ?: "",
    voteAverage = vote_average ?: 0.0
)

fun MovieModel.toMovieLocalDto() = MovieLocal(
    movieId = movieId,
    title = title,
    overview = overview,
    releaseDate = releaseDate,
    posterPath = posterPath,
    voteAverage = voteAverage.toString()
)