package com.example.movieapp.di

import com.example.movieapp.data.Constants.KtorLogger
import com.example.movieapp.data.remote.MovieService
import com.example.movieapp.data.remote.MovieServiceImpl
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

/**
 * Created by A.Elkhami on 03/08/2023.
 */
val remoteDataModule = module {
    single {
        provideHttpClient()
    }
    single<MovieService> {
        MovieServiceImpl(get())
    }
}

fun provideHttpClient(): HttpClient {
    return HttpClient {
        install(DefaultRequest) {
            header(HttpHeaders.ContentType, ContentType.Application.Json)
        }
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    print(KtorLogger + message)
                }
            }
            level = LogLevel.BODY
        }
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }
}