package com.example.movieapp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.movieapp.android.ui.theme.AIMoviesTheme
import com.example.movieapp.App
import com.example.movieapp.presentation.overveiw.composables.RatingBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AIMoviesTheme {
                App()
            }
        }
    }
}
@Composable
@Preview
fun Show(){
    RatingBar(modifier = Modifier, rating = 3.0f){

    }
}