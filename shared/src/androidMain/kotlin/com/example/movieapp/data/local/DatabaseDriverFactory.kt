package com.example.movieapp.data.local

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.example.sqldelight.database.moviedb.MovieDatabase


/**
 * Created by A.Elkhami on 03/08/2023.
 */
actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(schema = MovieDatabase.Schema, context = context, name = "movie.db")
    }
}