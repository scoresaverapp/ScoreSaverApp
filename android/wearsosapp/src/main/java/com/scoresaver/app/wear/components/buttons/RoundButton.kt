package com.scoresaver.core_ui.components.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.scoresaver.app.util.Black
import com.scoresaver.app.util.White
import com.scoresaver.core_ui.components.icons.CustomImageVectorIcon
import com.scoresaver.core_ui.components.layout.CustomSpacer
import com.scoresaver.app.wear.components.typography.CustomText

@Composable
fun RoundButton(
    size: Dp,
    backgroundColor: Color,
    icon: Int = 0,
    borderBackground: Color = backgroundColor,
    iconColor: Color? = null,
    iconSize: Dp? = null,
    isDoubleMatch: Boolean = false,
    showOnlyText: Boolean = false,
    titleButton: String = "",
    onClick: () -> Unit,
    textSize: TextUnit = 10.5.sp,
    textColor: Color = Black,
    serviceOrder: String? = null
) {
    Box(
        modifier = Modifier
            .size(size)
            .clip(CircleShape)
            .background(backgroundColor)
            .border(1.dp, borderBackground, shape = CircleShape)
            .clickable { onClick() }
    ) {
        if (showOnlyText) {
            CustomText(
                modifier = Modifier.align(Alignment.Center),
                text = titleButton,
                textStyle = TextStyle(
                    fontSize = textSize,
                    fontWeight = FontWeight(400),
                    color = textColor,
                )
            )
        } else {
            Row(
                modifier = Modifier.align(Alignment.Center)
            ) {
                CustomImageVectorIcon(
                    modifier = Modifier
                        .size(iconSize ?: 16.dp),
                    imageVector = ImageVector.vectorResource(icon),
                    color = iconColor ?: Color.White,
                    contentDescription = ""
                )
                if (isDoubleMatch) {
                    CustomSpacer(size = 3.dp, horizontal = true)
                    CustomText(
                        text = serviceOrder ?: "",
                        textStyle = TextStyle(
                            fontSize = textSize,
                            fontWeight = FontWeight(400),
                            color = White,
                        )
                    )
                }
            }
        }
    }
}