package com.example.movieapp.domain.use_case

import com.example.movieapp.data.repository.rating.MovieRatingRepository
import database.moviedb.MovieRateEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by A.Elkhami on 01/08/2023.
 */
class GetAllMovieRatings(private val repository: MovieRatingRepository) {
    operator fun invoke(): Flow<List<MovieRateEntity>> {
        return repository.getAllMovieRatings()
    }
}