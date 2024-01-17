package com.scoresaver.core_ui.components.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.scoresaver.core_ui.components.icons.CustomImageVectorIcon
import com.scoresaver.app.wear.components.typography.CustomText

@Composable
fun FullWidthRoundButton(
    text: String,
    textColor: Color,
    textAlign: TextAlign = TextAlign.Left,
    borderColor: Color,
    backgroundColor: Color,
    rightIcon: Int? = null,
    iconColor: Color = Color.Black,
    iconSize: Dp = 17.dp,
    textSize: Int = 12,
    iconContentDescription: String = "",
    onPress: () -> Unit,
    enable: Boolean = true
) {
    Row(
        modifier = Styles
            .rootContainer(
                backgroundColor = backgroundColor,
                borderColor = borderColor
            )
            .clickable {
                if (enable) {
                    onPress()
                }
            },
        verticalAlignment = Alignment.CenterVertically

    ) {
        CustomText(
            modifier = Modifier.weight(1f),
            text = text,
            textStyle = TextStyle(
                fontSize = textSize.sp,
                fontWeight = FontWeight(400),
                color = textColor,
                textAlign = textAlign
            )
        )

        rightIcon?.let {
            CustomImageVectorIcon(
                modifier = Modifier.size(iconSize),
                imageVector = ImageVector.vectorResource(id = rightIcon),
                color = iconColor,
                contentDescription = iconContentDescription
            )
        }
    }
}

private object Styles {
    fun rootContainer(backgroundColor: Color, borderColor: Color) = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(26.19.dp))
        .border(1.dp, borderColor, shape = RoundedCornerShape(26.19.dp))
        .background(backgroundColor)
        .padding(vertical = 6.dp, horizontal = 14.5.dp)
}
