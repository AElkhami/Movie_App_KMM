package com.example.movieapp.di

import com.example.movieapp.data.local.favourite.FavouriteMovieDataSource
import com.example.movieapp.data.local.favourite.FavouriteMovieDataSourceImpl
import com.example.movieapp.data.local.rating.MovieRatingDataSource
import com.example.movieapp.data.local.rating.MovieRatingDataSourceImpl
import com.example.sqldelight.database.moviedb.MovieDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.dsl.module

/**
 * Created by A.Elkhami on 03/08/2023.
 */
val localDataModule = module {
    single {
        MovieDatabase(get())
    }
    single<FavouriteMovieDataSource> {
        FavouriteMovieDataSourceImpl(MovieDatabase(get()), Dispatchers.IO)
    }
    single<MovieRatingDataSource> {
        MovieRatingDataSourceImpl(MovieDatabase(get()), Dispatchers.IO)
    }
}

