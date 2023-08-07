package com.example.movieapp.data.local

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.example.sqldelight.database.moviedb.MovieDatabase


/**
 * Created by A.Elkhami on 03/08/2023.
 */
actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(schema = MovieDatabase.Schema, name = "movie.db")
    }
}