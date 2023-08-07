package com.example.movieapp.data.repository.rating

import com.example.movieapp.data.local.rating.MovieRatingDataSource
import database.moviedb.MovieRateEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by A.Elkhami on 01/08/2023.
 */
class MovieRatingRepositoryImpl(val dataSource: MovieRatingDataSource) : MovieRatingRepository {
    override fun getAllMovieRatings(): Flow<List<MovieRateEntity>> {
        return dataSource.getAllMovieRatings()
    }

    override suspend fun getMovieRating(movieId: Long): MovieRateEntity? {
        return dataSource.getMovieRating(movieId)
    }

    override suspend fun insertMovieRating(movieId: Long, rating: Float) {
        dataSource.insertMovieRating(movieId, rating)
    }

    override suspend fun updateMovieRating(movieId: Long, rating: Float) {
        dataSource.updateMovieRating(movieId, rating)
    }

}