package com.example.movieapp.presentation.home.mapper

import com.example.movieapp.domain.model.MovieModel
import database.moviedb.FavouriteEntitiy

/**
 * Created by A.Elkhami on 25/07/2023.
 */

fun FavouriteEntitiy.toMovieModel(): MovieModel {
    return MovieModel(
        movieId = movieId,
        title = title,
        overview = overview,
        posterPath = posterPath,
        voteAverage = voteAverage.toDouble(),
        releaseDate = releaseDate
    )
}