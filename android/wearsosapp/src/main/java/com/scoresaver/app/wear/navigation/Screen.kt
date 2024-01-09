package com.scoresaver.app.wear.navigation

sealed class Screen(val route: String) {
    data object GenderScreen: Screen("gender_screen")
    data object AgeScreen: Screen("age_screen")
    data object HeightScreen: Screen("height_screen")
    data object WeightScreen: Screen("weight_screen")
    data object HomeScreen: Screen("home_screen")
    data object NewGameScreen: Screen("new_game_screen")
    data object GameTypeScreen: Screen("game_type_screen")
    data object GameRulesScreen: Screen("game_rules_screen")
    data object ServiceOrderScreen: Screen("service_order_screen")
    data object GameScreen: Screen("game_screen")
    data object ScoreGameScreen: Screen("score_game_screen")
    data object TimerAndCalories: Screen("time_and_calories_screen")
    data object ListGameScreen: Screen("list_game_screen")
}