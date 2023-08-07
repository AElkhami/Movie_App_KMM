package com.example.movieapp.domain.use_case

import com.example.movieapp.data.repository.favourite.FavouriteMovieRepository
import database.moviedb.FavouriteEntitiy

/**
 * Created by A.Elkhami on 26/07/2023.
 */
class GetFavouriteMovie(private val repository: FavouriteMovieRepository) {
    suspend operator fun invoke(movieId: Long): FavouriteEntitiy? {
        return repository.getFavouriteMovie(movieId)
    }
}