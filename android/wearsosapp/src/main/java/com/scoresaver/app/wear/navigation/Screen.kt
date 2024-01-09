package com.scoresaver.app.wear.navigation

sealed class Screen(val route: String) {
    data object GenderScreen: Screen("gender_screen")
    data object HeightScreen: Screen("height_screen")
    data object WeightScreen: Screen("weight_screen")
    data object HomeScreen: Screen("home_screen")
    data object NewGameScreen: Screen("new_game_screen")
    data object GameTypeScreen: Screen("game_type_screen")
    data object GameRulesScreen: Screen("game_rules_screen")
    data object ServiceOrderScreen: Screen("service_order_screen")
}