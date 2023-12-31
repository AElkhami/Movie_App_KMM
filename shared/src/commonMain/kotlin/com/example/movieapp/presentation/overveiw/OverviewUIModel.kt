package com.example.movieapp.presentation.overveiw

/**
 * Created by A.Elkhami on 26/07/2023.
 */
data class OverviewUIModel(
    val isMovieFavourite: Boolean = false,
    val rating: Float = 0.0f,
    val isRatingAvailable: Boolean = false
)
