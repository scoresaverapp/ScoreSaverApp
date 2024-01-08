package com.scoresaver.app.wear.navigation

sealed class Screen(val route: String) {
    data object GenderScreen: Screen("gender_screen")
    data object HeightScreen: Screen("height_screen")
    data object WeightScreen: Screen("weight_screen")
}