package com.scoresaver.app.wear.components.dialogs

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.dialog.Confirmation
import com.scoresaver.app.util.Black
import com.scoresaver.core_ui.components.icons.CustomImageVectorIcon

@Composable
fun ConfirmationAlert(
    icon: Int,
    colorIcon: Color,
    content: @Composable ()-> Unit
) {
    Confirmation(
        backgroundColor = Black,
        onTimeout = {
        },
        icon = {
            CustomImageVectorIcon(
                modifier = Modifier.size(40.dp),
                imageVector = ImageVector.vectorResource(id = icon),
                color = colorIcon,
                contentDescription = ""
            )
        }
    ) {
        content()
    }
}