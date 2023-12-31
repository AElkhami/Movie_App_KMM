package com.example.movieapp.presentation.home.composables

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import com.example.movieapp.presentation.ui.LocalSpacing

/**
 * Created by A.Elkhami on 18/07/2023.
 */
@Composable
fun LoadingAnimation() {
    val spacing = LocalSpacing.current

    val animation = rememberInfiniteTransition()

    val progress by animation.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Restart,
        )
    )

    Box(
        modifier = Modifier
            .size(spacing.loadingIconSize)
            .scale(progress)
            .alpha(1f - progress)
            .border(
                spacing.loadingIconBorder,
                color = Color.Black,
                shape = CircleShape
            )
    )
}