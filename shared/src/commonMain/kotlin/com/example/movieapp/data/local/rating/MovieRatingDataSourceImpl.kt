package com.example.movieapp.data.local.rating

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.example.sqldelight.database.moviedb.MovieDatabase
import database.moviedb.MovieRateEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

/**
 * Created by A.Elkhami on 01/08/2023.
 */
class MovieRatingDataSourceImpl(
    db: MovieDatabase,
    private val ioDispatcher: CoroutineDispatcher
) : MovieRatingDataSource {
    private val queries = db.movieRateEntitiyQueries
    override fun getAllMovieRatings(): Flow<List<MovieRateEntity>> {
        return queries.getAllMovieRatings().asFlow().mapToList(ioDispatcher)
    }

    override suspend fun getMovieRating(movieId: Long): MovieRateEntity? {
        return queries.getMovieRating(movieId).executeAsOneOrNull()
    }

    override suspend fun insertMovieRating(movieId: Long, rating: Float) {
        withContext(ioDispatcher) {
            queries.insertMovieRating(id = null, movieId = movieId, rate = rating.toDouble())

        }
    }

    override suspend fun updateMovieRating(movieId: Long, rating: Float) {
        withContext(ioDispatcher) {
            queries.updateMovieRating(movieId = movieId, rate = rating.toDouble())
        }
    }
}