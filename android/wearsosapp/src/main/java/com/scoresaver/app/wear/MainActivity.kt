/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter and
 * https://github.com/android/wear-os-samples/tree/main/ComposeAdvanced to find the most up to date
 * changes to the libraries and their usages.
 */

package com.scoresaver.app.wear


import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.scoresaver.app.R
import com.scoresaver.app.util.LightBlack
import com.scoresaver.app.util.Orange
import com.scoresaver.app.wear.navigation.NavGraph
import com.scoresaver.core_ui.components.icons.CustomImageVectorIcon
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val PREFS_NAME = "ScoreSaverApp"
    private val FIRST_LAUNCH_KEY = "isFirstLaunch"

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SplashScreen()
            NavGraph(isFirstLaunch())
            if (isFirstLaunch()) {
                markFirstLaunchDone()
            }
        }
    }

    private fun isFirstLaunch(): Boolean {
        val prefs: SharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getBoolean(FIRST_LAUNCH_KEY, true)
    }

    private fun markFirstLaunchDone() {
        val prefs: SharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = prefs.edit()
        editor.putBoolean(FIRST_LAUNCH_KEY, false)
        editor.apply()
    }
}

@Composable
fun SplashScreen() {
    LaunchedEffect(key1 = true) {
        delay(3000)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBlack),
        contentAlignment = Alignment.Center
    ) {
        CustomImageVectorIcon(
            imageVector = ImageVector.vectorResource(id =R.drawable.ic_logo),
            contentDescription = "",
            color = Orange
        )
    }
}