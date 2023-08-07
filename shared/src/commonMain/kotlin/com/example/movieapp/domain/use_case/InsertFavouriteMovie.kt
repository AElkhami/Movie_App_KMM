package com.example.movieapp.domain.use_case

import com.example.movieapp.data.repository.favourite.FavouriteMovieRepository
import com.example.movieapp.domain.mapper.toMovieLocalDto
import com.example.movieapp.domain.model.MovieModel

/**
 * Created by A.Elkhami on 25/07/2023.
 */
class InsertFavouriteMovie(private val repository: FavouriteMovieRepository) {
    suspend operator fun invoke(movie: MovieModel) {
        repository.insertFavouriteMovie(movie.toMovieLocalDto())
    }
}