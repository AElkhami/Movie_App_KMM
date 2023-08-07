package com.example.movieapp.data.repository.favourite

import com.example.movieapp.data.local.favourite.dto.MovieLocal
import database.moviedb.FavouriteEntitiy
import kotlinx.coroutines.flow.Flow

/**
 * Created by A.Elkhami on 25/07/2023.
 */
interface FavouriteMovieRepository {
    fun getFavouriteMovies(): Flow<List<FavouriteEntitiy>>

    suspend fun getFavouriteMovie(movieId: Long): FavouriteEntitiy?

    suspend fun insertFavouriteMovie(
        movie: MovieLocal
    )

    suspend fun deleteFavouriteMovie(id: Long)
}