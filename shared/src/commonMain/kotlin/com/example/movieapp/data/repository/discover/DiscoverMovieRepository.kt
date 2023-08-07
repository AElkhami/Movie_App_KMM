package com.example.movieapp.data.repository.discover

import com.example.movieapp.data.remote.api_handler.Result
import com.example.movieapp.data.remote.dto.DiscoverMovieResponse

/**
 * Created by A.Elkhami on 18/07/2023.
 */
interface DiscoverMovieRepository {
    suspend fun getDiscoverMovies(page: Int): Result<DiscoverMovieResponse>
}