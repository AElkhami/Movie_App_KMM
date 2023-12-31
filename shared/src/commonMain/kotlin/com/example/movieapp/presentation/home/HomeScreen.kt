package com.example.movieapp.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.example.movieapp.Route
import com.example.movieapp.domain.model.MovieModel
import com.example.movieapp.presentation.home.composables.EmptyListView
import com.example.movieapp.presentation.home.composables.ErrorView
import com.example.movieapp.presentation.home.composables.MovieHorizontalItem
import com.example.movieapp.presentation.home.composables.MovieItem
import com.example.movieapp.presentation.home.composables.ToggleButton
import com.example.movieapp.presentation.home.composables.rememberForeverLazyListState
import com.example.movieapp.presentation.ui.LocalSpacing
import org.koin.core.scope.Scope

/**
 * Created by A.Elkhami on 18/07/2023.
 */

data class HomeScreen(
    val route: (Route)-> Unit,
    val scope: Scope
) {
    private val viewModel = scope.get<HomeViewModel>()

    @Composable
    fun View(){

        viewModel.getDiscoverMovie(1)
        viewModel.getFavouriteMovies()

        HomeScreenUi(
            viewModel.uiState,
            onNavigateToOverview = {
                route(Route.Overview(movie = it))
            },
            onRefresh = {
                viewModel.getDiscoverMovie(1)
            }
        )
    }
}

@Composable
fun HomeScreenUi(
    uiState: HomeUiModel,
    onRefresh: () -> Unit,
    onNavigateToOverview: (MovieModel) -> Unit
) {
    val spacing = LocalSpacing.current

    var selectedTab by remember {
        mutableStateOf("Favorites")
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Discover Movies",
            fontWeight = FontWeight.Bold,
            fontSize = spacing.fontTitle,
            modifier = Modifier.padding(
                start = spacing.spaceMedium + spacing.spaceSmall,
                end = spacing.spaceMedium,
                top = spacing.mainTitleVerticalPadding
            )
        )
        if (uiState.errorMessage != "") {
            ErrorView(uiState.errorMessage) {
                onRefresh()
            }
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(contentAlignment = Alignment.Center) {
                if (uiState.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.padding(spacing.spaceMedium))
                }
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth(),
                    state = rememberForeverLazyListState(key = "Discover")
                ) {
                    itemsIndexed(uiState.discoverMovieList) { index, movie ->
                        MovieItem(
                            modifier = Modifier.padding(
                                start = if (index == 0) spacing.spaceMedium else spacing.spaceExtraSmall,
                                end = if (index == uiState.discoverMovieList.size - 1) spacing.spaceMedium else spacing.spaceExtraSmall,
                                bottom = spacing.spaceMedium,
                                top = spacing.spaceSmall
                            ), movie = movie
                        ) {
                            onNavigateToOverview(it)
                        }
                    }
                }
            }
            ToggleButton(
                modifier = Modifier
                    .padding(horizontal = spacing.spaceMedium),
                buttons = listOf("Favorites", "Recommended"),
                width = spacing.toggleButtonWidth,
                currentSelection = selectedTab
            ) {
                selectedTab = it
            }
            if (selectedTab == "Favorites") {
                if (uiState.favouriteMovieList.isEmpty()) {
                    EmptyListView(Icons.Default.Favorite, "No favourite movies yet.")
                }

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = spacing.spaceExtraSmall),
                    state = rememberForeverLazyListState(key = "Overview")
                ) {
                    itemsIndexed(uiState.favouriteMovieList) { index, movie ->
                        MovieHorizontalItem(
                            modifier = Modifier.padding(
                                top = if (index == 0) spacing.spaceSmall else spacing.spaceExtraSmall,
                                bottom = if (index == uiState.discoverMovieList.size - 1) spacing.spaceSmall else spacing.spaceExtraSmall,
                                start = spacing.spaceMedium,
                                end = spacing.spaceMedium
                            ), movie = movie
                        ) {
                            onNavigateToOverview(it)
                        }
                    }
                }
            } else {
                EmptyListView(Icons.Default.ThumbUp, "No recommendations available yet")

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(spacing.spaceMedium)
                ) {
                    items(emptyList<MovieModel>()) { movie ->
                        MovieHorizontalItem(modifier = Modifier, movie = movie) {

                        }
                    }
                }
            }
        }
    }
}