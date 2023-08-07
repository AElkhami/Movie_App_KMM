package com.example.movieapp.di

import com.example.movieapp.domain.use_case.DeleteFavouriteMovie
import com.example.movieapp.domain.use_case.GetAllMovieRatings
import com.example.movieapp.domain.use_case.GetDiscoverMovie
import com.example.movieapp.domain.use_case.GetFavouriteMovie
import com.example.movieapp.domain.use_case.GetFavouriteMovies
import com.example.movieapp.domain.use_case.GetMovieRating
import com.example.movieapp.domain.use_case.InsertFavouriteMovie
import com.example.movieapp.domain.use_case.InsertMovieRating
import com.example.movieapp.domain.use_case.UpdateMovieRating
import org.koin.dsl.module

/**
 * Created by A.Elkhami on 03/08/2023.
 */
val useCaseModule = module {
    single {
        GetDiscoverMovie(get())
    }
    single {
        GetFavouriteMovies(get())
    }
    single {
        GetFavouriteMovie(get())
    }
    single {
        InsertFavouriteMovie(get())
    }
    single {
        DeleteFavouriteMovie(get())
    }
    single {
        GetAllMovieRatings(get())
    }
    single {
        GetMovieRating(get())
    }
    single {
        InsertMovieRating(get())
    }
    single {
        UpdateMovieRating(get())
    }
}