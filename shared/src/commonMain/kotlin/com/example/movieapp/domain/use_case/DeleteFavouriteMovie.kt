package com.example.movieapp.domain.use_case

import com.example.movieapp.data.repository.favourite.FavouriteMovieRepository

/**
 * Created by A.Elkhami on 25/07/2023.
 */
class DeleteFavouriteMovie(private val repository: FavouriteMovieRepository) {
    suspend operator fun invoke(id: Long) {
        repository.deleteFavouriteMovie(id)
    }
}