package com.example.movieapp.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.movieapp.BuildKonfig
import com.example.movieapp.data.remote.api_handler.Result
import com.example.movieapp.domain.mapper.toMovieModel
import com.example.movieapp.domain.model.MovieModel
import com.example.movieapp.domain.use_case.GetDiscoverMovie
import com.example.movieapp.domain.use_case.GetFavouriteMovies
import com.example.movieapp.domain.use_case.InsertFavouriteMovie
import com.example.movieapp.presentation.home.mapper.toMovieModel
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * Created by A.Elkhami on 18/07/2023.
 */
class HomeViewModel(
    private val getDiscoverMovieUseCase: GetDiscoverMovie,
    private val getFavouriteMoviesUseCase: GetFavouriteMovies,
    private val insertFavouriteMovieUseCase: InsertFavouriteMovie
) : ViewModel() {
    var uiState by mutableStateOf(HomeUiModel())
        private set

    fun insertFavouriteMovie(movie: MovieModel) {
        viewModelScope.launch {
            insertFavouriteMovieUseCase(movie)
        }

        getFavouriteMovies()
    }

    fun getDiscoverMovie(page: Int) {
        viewModelScope.launch {
            when (val response = getDiscoverMovieUseCase(page)) {
                is Result.Error -> {
                    uiState = uiState.copy(isLoading = false, errorMessage = response.message)
                }

                is Result.Success -> {
                    var data = response.data

                    val results = data.results.map { movie ->
                        movie.copy(
                            poster_path = BuildKonfig.POSTER_BASE_URL + movie.poster_path,
                            release_date = movie.release_date?.substringBefore("-") ?: ""
                        )
                    }
                    data = data.copy(results = results)
                    val movieList = data.results.map { it.toMovieModel() }
                    uiState = uiState.copy(
                        discoverMovieList = movieList,
                        isLoading = false,
                        errorMessage = ""
                    )
                }
            }
        }
    }

    fun getFavouriteMovies() {
        viewModelScope.launch {
            getFavouriteMoviesUseCase().collectLatest {
                uiState = uiState.copy(
                    favouriteMovieList = it.map { favouriteEntity ->
                        favouriteEntity.toMovieModel()
                    }
                )
            }
        }
    }
}