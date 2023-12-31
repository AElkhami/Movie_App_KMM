package com.example.movieapp.presentation.overveiw

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.movieapp.domain.model.MovieModel
import com.example.movieapp.domain.use_case.DeleteFavouriteMovie
import com.example.movieapp.domain.use_case.GetFavouriteMovie
import com.example.movieapp.domain.use_case.GetMovieRating
import com.example.movieapp.domain.use_case.InsertFavouriteMovie
import com.example.movieapp.domain.use_case.InsertMovieRating
import com.example.movieapp.domain.use_case.UpdateMovieRating
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.launch

/**
 * Created by A.Elkhami on 25/07/2023.
 */
class OverviewViewModel(
    private val insertFavouriteMovieUseCase: InsertFavouriteMovie,
    private val getFavouriteMovieUseCase: GetFavouriteMovie,
    private val deleteFavouriteMovieUseCase: DeleteFavouriteMovie,
    private val getMovieRatingUseCase: GetMovieRating,
    private val insertMovieRatingUseCase: InsertMovieRating,
    private val updateMovieRatingUseCase: UpdateMovieRating,
//    private val analytics: Analytics
) :
    ViewModel() {

    var uiState by mutableStateOf(OverviewUIModel())
        private set

    fun checkIfMovieIsFavourite(movieId: Long) {
        viewModelScope.launch {
            getFavouriteMovieUseCase(movieId)?.let {
                uiState = uiState.copy(isMovieFavourite = true)
            } ?: run {
                uiState = uiState.copy(isMovieFavourite = false)
            }
        }
    }

    fun insertFavouriteMovie(movie: MovieModel) {
        viewModelScope.launch {
            insertFavouriteMovieUseCase(movie)
            uiState = uiState.copy(isMovieFavourite = true)
        }
    }

    fun deleteFavouriteMovie(id: Long) {
        viewModelScope.launch {
            deleteFavouriteMovieUseCase(id)
            uiState = uiState.copy(isMovieFavourite = false)
        }
    }

    fun getMovieRating(movieId: Long) {
        viewModelScope.launch {
            getMovieRatingUseCase(movieId)?.let { movieRateEntity ->
                uiState = uiState.copy(rating = movieRateEntity.rate.toFloat())
                uiState = uiState.copy(isRatingAvailable = true)
            }
        }
    }

    fun insertOrUpdateRating(movieId: Long, rating: Float) {
        if (uiState.isRatingAvailable) {
            updateMovieRating(movieId, rating)
        } else {
            insertMovieRating(movieId, rating)
            uiState = uiState.copy(isRatingAvailable = true)
        }
//        analytics.logMovieRatingEvent(movieId, rating.toDouble())
    }

    fun insertMovieRating(movieId: Long, rating: Float) {
        viewModelScope.launch {
            insertMovieRatingUseCase(movieId, rating)
        }
    }

    fun updateMovieRating(movieId: Long, rating: Float) {
        viewModelScope.launch {
            updateMovieRatingUseCase(movieId, rating)
        }
    }
}