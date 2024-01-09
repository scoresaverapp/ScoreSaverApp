package com.scoresaver.app.wear.features.new_game.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.scoresaver.app.R
import com.scoresaver.app.util.White
import com.scoresaver.core_ui.components.icons.CustomImageVectorIcon
import com.scoresaver.core_ui.components.layout.CustomSpacer

@Composable
internal fun PlayerOrder(isUserPlayer: Boolean, number: String) {
    val userIcon = if (isUserPlayer) R.drawable.ic_profile_selected else R.drawable.ic_profile_not_selected

    Row (verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
        CustomImageVectorIcon(
            modifier = Modifier.size(20.dp),
            imageVector = ImageVector.vectorResource(userIcon),
            color = White,
            contentDescription = ""
        )
        CustomSpacer(size = 7.5.dp, horizontal = true)
        NumberBox(number = number)
    }
}