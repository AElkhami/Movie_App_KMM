package com.example.movieapp.data.remote

import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

/**
 * Created by A.Elkhami on 24/07/2023.
 */
suspend fun isOnline(): Boolean {
    return withContext(Dispatchers.IO) {
        try {
//            val timeoutMs = INTERNET_CONNECTION_TIME_OUT
//            val socket = Socket()
//            val socketAddress: SocketAddress = InetSocketAddress(GoogleDNS, GOOGLE_PORT)
//            socket.connect(socketAddress, timeoutMs)
//            socket.close()
            true
        } catch (e: IOException) {
            false
        }
    }
}