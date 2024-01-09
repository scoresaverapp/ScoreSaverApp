package com.scoresaver.app.wear.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.scoresaver.app.wear.features.game.presentation.GameViewModel
import com.scoresaver.app.wear.features.game.presentation.ui.GameScreen
import com.scoresaver.app.wear.features.game.presentation.ui.ListGameScreen
import com.scoresaver.app.wear.features.home.HomeScreen
import com.scoresaver.app.wear.features.new_game.presentation.NewGameViewModel
import com.scoresaver.app.wear.features.new_game.presentation.ui.GameRuleScreen
import com.scoresaver.app.wear.features.new_game.presentation.ui.GameTypeScreen
import com.scoresaver.app.wear.features.new_game.presentation.ui.NewGameScreen
import com.scoresaver.app.wear.features.new_game.presentation.ui.ServiceOrderScreen
import com.scoresaver.app.wear.features.user_settings.ui.AgeScreen
import com.scoresaver.app.wear.features.user_settings.ui.GenderScreen
import com.scoresaver.app.wear.features.user_settings.ui.HeightScreen
import com.scoresaver.app.wear.features.user_settings.ui.WeightScreen

@Composable
fun NavGraph() {
    val navController = rememberSwipeDismissableNavController()
    val newGameViewModel = hiltViewModel<NewGameViewModel>()
    val gameViewModel = hiltViewModel<GameViewModel>()

    SwipeDismissableNavHost(
        navController = navController,
        startDestination = if (gameViewModel.userData.value == null) Screen.GenderScreen.route else Screen.HomeScreen.route
    ) {
        composable(Screen.GenderScreen.route) {
            GenderScreen(navController = navController, viewModel = newGameViewModel)
        }

        composable(Screen.AgeScreen.route) {
            AgeScreen(navController = navController, viewModel = newGameViewModel)
        }

        composable(Screen.HeightScreen.route) {
            HeightScreen(navController = navController, viewModel = newGameViewModel)
        }

        composable(Screen.WeightScreen.route) {
            WeightScreen(navController = navController, viewModel = newGameViewModel)
        }

        composable(Screen.HomeScreen.route) {
            HomeScreen(navController = navController)
        }

        composable(Screen.NewGameScreen.route) {
            NewGameScreen(navController = navController, viewModel = newGameViewModel)
        }

        composable(Screen.GameTypeScreen.route) {
            GameTypeScreen(viewModel = newGameViewModel)
        }

        composable(Screen.GameRulesScreen.route) {
            GameRuleScreen(viewModel = newGameViewModel)
        }

        composable(Screen.ServiceOrderScreen.route) {
            ServiceOrderScreen(viewModel = newGameViewModel)
        }

        composable(Screen.GameScreen.route) {
            GameScreen(navController = navController,viewModel = gameViewModel)
        }
        composable(Screen.ListGameScreen.route) {
            ListGameScreen(navController = navController)
        }

    }
}