package com.example.movieapp.di

import com.example.movieapp.data.repository.discover.DiscoverMovieRepository
import com.example.movieapp.data.repository.discover.DiscoverMovieRepositoryImpl
import com.example.movieapp.data.repository.favourite.FavouriteMovieRepository
import com.example.movieapp.data.repository.favourite.FavouriteMovieRepositoryImpl
import com.example.movieapp.data.repository.rating.MovieRatingRepository
import com.example.movieapp.data.repository.rating.MovieRatingRepositoryImpl
import org.koin.dsl.module

/**
 * Created by A.Elkhami on 03/08/2023.
 */
val repositoryModule = module {
    single<FavouriteMovieRepository> {
        FavouriteMovieRepositoryImpl(get())
    }
    single<MovieRatingRepository> {
        MovieRatingRepositoryImpl(get())
    }
    single<DiscoverMovieRepository> {
        DiscoverMovieRepositoryImpl(get())
    }
}