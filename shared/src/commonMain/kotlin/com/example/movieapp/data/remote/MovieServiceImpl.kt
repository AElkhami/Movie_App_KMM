package com.example.movieapp.data.remote

import com.example.movieapp.BuildKonfig
import com.example.movieapp.data.remote.api_handler.Result
import com.example.movieapp.data.remote.api_handler.handleApi
import com.example.movieapp.data.remote.dto.DiscoverMovieResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.url


/**
 * Created by A.Elkhami on 03/08/2023.
 */
class MovieServiceImpl(private val client: HttpClient) : MovieService {
    override suspend fun getDiscoverMovies(page: Int): Result<DiscoverMovieResponse> {
        return handleApi {
            client.get {
                header("Authorization", BuildKonfig.AUTH_KEY)
                url(HttpRoutes.DISCOVER_MOVIES)
                parameter("page", page)
            }
        }
    }
}