package com.example.movieapp.data.local

import app.cash.sqldelight.db.SqlDriver


/**
 * Created by A.Elkhami on 03/08/2023.
 */
expect class DatabaseDriverFactory {
    fun createDriver(): SqlDriver
}