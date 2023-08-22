package com.example.movieapp.data.local

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.example.sqldelight.database.moviedb.MovieDatabase

/**
 * Created by A.Elkhami on 22/08/2023.
 */
actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY).apply {
            MovieDatabase.Schema.create(this)
        }
    }
}