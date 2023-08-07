package com.example.movieapp.presentation.home.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import com.example.aimovies.presentation.ui.theme.MovieYellow
import com.example.movieapp.domain.model.MovieModel
import com.example.movieapp.presentation.ui.LocalSpacing
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import io.ktor.http.Url
import org.jetbrains.compose.resources.ExperimentalResourceApi

/**
 * Created by A.Elkhami on 18/07/2023.
 */
@OptIn(ExperimentalResourceApi::class)
@Composable
fun MovieHorizontalItem(
    modifier: Modifier,
    movie: MovieModel,
    onClick: (MovieModel) -> Unit
) {
    val spacing = LocalSpacing.current

    Row(modifier = modifier
        .fillMaxWidth()
        .height(IntrinsicSize.Max)
        .clip(RoundedCornerShape(spacing.curvedCornerSize))
        .clickable {
            onClick(movie)
        }
        .padding(spacing.spaceSmall)) {

        Box(contentAlignment = Alignment.Center) {
            KamelImage(
                resource = asyncPainterResource(data = Url(movie.posterPath)),
                contentDescription = null,
                onLoading = { LoadingAnimation() },
                onFailure = {  },
                modifier = Modifier
                    .clip(RoundedCornerShape(spacing.curvedCornerSize))
                    .size(spacing.placeholderWidth, spacing.placeholderHeight)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = spacing.spaceMedium),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = movie.title,
                modifier = Modifier.padding(top = spacing.spaceExtraSmall),
                maxLines = 3,
            )
            Text(
                text = movie.releaseDate,
                color = Color.Gray,
                modifier = Modifier.padding(top = spacing.spaceExtraSmall),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Row(modifier = Modifier.padding(top = spacing.spaceExtraLarge)) {
                Icon(
                    Icons.Default.Star,
                    modifier = Modifier.size(spacing.ratingIconSize),
                    tint = MovieYellow,
                    contentDescription = null
                )
                Text(
                    text = movie.voteAverage.toString(),
                    color = Color.Gray,
                )
            }
        }
    }
}