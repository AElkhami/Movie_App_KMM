package com.example.movieapp.domain.use_case

import com.example.movieapp.data.repository.favourite.FavouriteMovieRepository
import database.moviedb.FavouriteEntitiy
import kotlinx.coroutines.flow.Flow

/**
 * Created by A.Elkhami on 25/07/2023.
 */
class GetFavouriteMovies(private val repository: FavouriteMovieRepository) {
    operator fun invoke(): Flow<List<FavouriteEntitiy>> {
        return repository.getFavouriteMovies()
    }
}