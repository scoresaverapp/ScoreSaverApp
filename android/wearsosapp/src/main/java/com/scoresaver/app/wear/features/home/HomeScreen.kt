package com.scoresaver.app.wear.features.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.wear.compose.foundation.lazy.AutoCenteringParams
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.ScalingLazyListAnchorType
import androidx.wear.compose.foundation.lazy.ScalingLazyListState
import com.scoresaver.app.R
import com.scoresaver.app.util.Black
import com.scoresaver.app.util.Orange
import com.scoresaver.app.util.White
import com.scoresaver.app.wear.navigation.Screen
import com.scoresaver.app.wear.components.MyScaffold
import com.scoresaver.core_ui.components.buttons.FullWidthRoundButton
import com.scoresaver.core_ui.components.layout.CustomSpacer

@Composable

fun HomeScreen(navController: NavController) {

    val scalingLazyState = remember {
        ScalingLazyListState(
            initialCenterItemIndex = 2,
            initialCenterItemScrollOffset = 40
        )
    }

    MyScaffold(scalingLazyState = scalingLazyState, hideTimer = true) {
        ScalingLazyColumn(
            modifier = Modifier.fillMaxSize(),
            autoCentering = AutoCenteringParams(itemIndex = 2),
            state = scalingLazyState,
            anchorType = ScalingLazyListAnchorType.ItemStart
        ) {
            item {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 32.dp, end = 32.dp, top = 16.dp),
                    painter = painterResource(id = R.drawable.ic_logo),
                    contentDescription = "title_view",
                )
            }
            item {
                CustomSpacer(size = 8.dp)
            }
            item {
                FullWidthRoundButton(
                    text = stringResource(id = R.string.new_game),
                    onPress = { navController.navigate(Screen.NewGameScreen.route) },
                    backgroundColor = Black,
                    borderColor = Orange,
                    textColor = Orange,
                    rightIcon = R.drawable.ic_add,
                    iconSize = 17.dp,
                    textSize = 16,
                    iconColor = Orange
                )
            }
            item {
                FullWidthRoundButton(
                    text = stringResource(id = R.string.game_save),
                    onPress = { },
                    backgroundColor = Orange,
                    borderColor = Orange,
                    textColor = White,
                    rightIcon = R.drawable.ic_clock,
                    iconSize = 17.dp,
                    textSize = 16,
                    iconColor = White
                )
            }
            item {
                FullWidthRoundButton(
                    text = stringResource(id = R.string.history_game),
                    onPress = {
                        navController.navigate(Screen.ListGameScreen.route)
                    },
                    backgroundColor = Orange,
                    borderColor = Orange,
                    textColor = White,
                    rightIcon = R.drawable.ic_round_arrow_left,
                    iconSize = 17.dp,
                    textSize = 16,
                    iconColor = White
                )
            }
        }

    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    HomeScreen(navController = navController)
}