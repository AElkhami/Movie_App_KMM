package com.example.movieapp.data.remote

import com.example.movieapp.BuildKonfig


/**
 * Created by A.Elkhami on 18/07/2023.
 */
object HttpRoutes {
    val DISCOVER_MOVIES =
        "${BuildKonfig.BASE_URL}/3/discover/movie?language=en&sort_by=popularity.desc"
}