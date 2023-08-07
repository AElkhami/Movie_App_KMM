package com.example.movieapp.data.repository.discover

import com.example.movieapp.data.remote.MovieService
import com.example.movieapp.data.remote.api_handler.Result
import com.example.movieapp.data.remote.dto.DiscoverMovieResponse

/**
 * Created by A.Elkhami on 18/07/2023.
 */
class DiscoverMovieRepositoryImpl(private val api: MovieService) : DiscoverMovieRepository {
    override suspend fun getDiscoverMovies(page: Int): Result<DiscoverMovieResponse> {
        return api.getDiscoverMovies(page)
    }
}