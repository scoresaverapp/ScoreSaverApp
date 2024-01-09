package com.scoresaver.app.util.util

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable

@Composable
fun requestPermission(
    permission: String,
    permissionResultCallback: (isGranted: Boolean) -> Unit
): () -> Unit {
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = {
            permissionResultCallback(it)
        }
    )

    return {
        permissionLauncher.launch(permission)
    }
}