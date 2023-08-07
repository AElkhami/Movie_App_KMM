package com.example.movieapp.domain.use_case

import com.example.movieapp.data.remote.api_handler.Result
import com.example.movieapp.data.remote.dto.DiscoverMovieResponse
import com.example.movieapp.data.repository.discover.DiscoverMovieRepository

/**
 * Created by A.Elkhami on 18/07/2023.
 */
class GetDiscoverMovie(private val repository: DiscoverMovieRepository) {
    suspend operator fun invoke(page: Int): Result<DiscoverMovieResponse> {
        return repository.getDiscoverMovies(page)
    }
}