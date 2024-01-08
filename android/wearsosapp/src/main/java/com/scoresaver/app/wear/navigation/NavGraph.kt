package com.scoresaver.app.wear.navigation

import androidx.compose.runtime.Composable
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.scoresaver.app.wear.features.new_game.presentation.NewGameViewModel
import com.scoresaver.app.wear.features.user_settings.GenderScreen
import com.scoresaver.app.wear.features.user_settings.HeightScreen
import com.scoresaver.app.wear.features.user_settings.WeightScreen
import com.scoresaver.app.wear.util.sharedViewModel

@Composable
fun NavGraph(firstLaunch: Boolean) {
    val navController = rememberSwipeDismissableNavController()

    SwipeDismissableNavHost(
        navController = navController,
        startDestination = Screen.GenderScreen.route
    ) {
        composable(Screen.GenderScreen.route) {
            val viewModel = it.sharedViewModel<NewGameViewModel>(navController = navController)
            GenderScreen(navController = navController, viewModel = viewModel)
        }

        composable(Screen.HeightScreen.route) {
            val viewModel = it.sharedViewModel<NewGameViewModel>(navController = navController)
            HeightScreen(navController = navController, viewModel = viewModel)
        }

        composable(Screen.WeightScreen.route) {
            val viewModel = it.sharedViewModel<NewGameViewModel>(navController = navController)
            WeightScreen(navController = navController, viewModel = viewModel)
        }

    }
}