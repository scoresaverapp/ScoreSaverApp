package com.scoresaver.app.wear.components.dialogs

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.dialog.Alert
import com.scoresaver.app.util.White
import com.scoresaver.app.wear.components.typography.CustomText

@Composable
fun AlertDialog(
    textTitle: String,
    textMessage: String? = null,
    content: @Composable () -> Unit
) {

    Alert(
        title = {
            CustomText(
                text = textTitle,
                textStyle = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight(400),
                    color = White,
                    textAlign = TextAlign.Center
                )
            )
        },
        message = {
            CustomText(
                text = textMessage ?: "",
                textStyle = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight(400),
                    color = White,
                    textAlign = TextAlign.Center
                )
            )
        },
    ) {
        item {
            content()
        }
    }
}