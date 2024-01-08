package com.scoresaver.core_ui.components.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.SwitchDefaults
import com.scoresaver.app.util.LightBlack
import com.scoresaver.app.util.LightGrey
import com.scoresaver.app.util.Orange
import com.scoresaver.app.util.White
import com.scoresaver.core_ui.components.switchs.CustomSwitch
import com.scoresaver.core_ui.components.typography.CustomText

@Composable
fun FullWidthRoundSwitchButton(
    text: String,
    checked: Boolean,
    onClick: (value: Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .rootContainer()
            .clickable { onClick(!checked) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        CustomText(
            modifier = Modifier.weight(1f),
            text = text,
            textStyle = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight(400),
                color = White,
            )
        )
        CustomSwitch(
            checked = checked,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Orange,
                uncheckedThumbColor = LightGrey,
                checkedTrackColor = White,
                uncheckedTrackColor = LightGrey
            ),
        )
    }
}

private fun Modifier.rootContainer() = this
    .fillMaxWidth()
    .clip(RoundedCornerShape(26.19.dp))
    .background(LightBlack)
    .padding(vertical = 6.dp, horizontal = 14.5.dp)
