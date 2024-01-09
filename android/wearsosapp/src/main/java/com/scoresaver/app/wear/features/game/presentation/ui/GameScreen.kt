@file:OptIn(ExperimentalFoundationApi::class)

package com.scoresaver.app.wear.features.game.presentation.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.scoresaver.app.wear.features.game.presentation.GameViewModel
import com.scoresaver.game_ui.screens.ScoreGameScreen

@Composable
internal fun GameScreen(
    navController: NavController,
    viewModel: GameViewModel
) {

    val pagerState = rememberPagerState(pageCount = {
        3
    })

    LaunchedEffect(Unit) {
        pagerState.scrollToPage(0)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.weight(1f)) {
            HorizontalPager(state = pagerState) { page ->
                when (page) {
                    0 -> ScoreGameScreen(viewModel = viewModel)
                    1 -> TimeAndCaloriesScreen(viewModel = viewModel)
                    2 -> CompleteGameScreen(navController = navController)
                }
            }
        }
    }
}