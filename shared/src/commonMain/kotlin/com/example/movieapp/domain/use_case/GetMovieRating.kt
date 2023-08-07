package com.example.movieapp.domain.use_case

import com.example.movieapp.data.repository.rating.MovieRatingRepository
import database.moviedb.MovieRateEntity

/**
 * Created by A.Elkhami on 01/08/2023.
 */
class GetMovieRating(private val repository: MovieRatingRepository) {
    suspend operator fun invoke(movieId: Long): MovieRateEntity? {
        return repository.getMovieRating(movieId)
    }
}