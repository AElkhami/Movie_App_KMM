package com.example.movieapp.presentation.overveiw.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.StarHalf
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.aimovies.presentation.ui.theme.MovieYellow

/**
 * Created by A.Elkhami on 09/08/2023.
 */
@Composable
fun RatingBar(
    modifier: Modifier,
    rating: Float,
    maxRating: Int = 5,
    onRatingChanged: (Float) -> Unit
) {
    var halfStar = rating.rem(1) != 0.0f

    Row(modifier = modifier) {
        for (i in 1..maxRating) {
            IconButton(onClick = { onRatingChanged(i.toFloat()) }) {
                if (i <= rating) Icon(
                    imageVector = Icons.Outlined.Star,
                    contentDescription = null,
                    tint = MovieYellow,
                    modifier = Modifier.size(32.dp)
                )
                else if (halfStar) {
                    Icon(
                        imageVector = Icons.Outlined.StarHalf,
                        contentDescription = null,
                        tint = MovieYellow,
                        modifier = Modifier.size(32.dp)
                    )
                    halfStar = false
                }
                else Icon(
                    imageVector = Icons.Outlined.StarOutline,
                    contentDescription = null, tint = MovieYellow,
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    }
}