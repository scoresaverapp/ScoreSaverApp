package com.scoresaver.core_ui.components.lifecycle_observer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

@Composable
fun lifecycleObserver(
    onCreate: () -> Unit = {},
    onStart: () -> Unit = {},
    onResume: () -> Unit = {},
    onPause: () -> Unit = {},
    onStop: () -> Unit = {},
    onDestroy: () -> Unit = {},
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val lifecycle = lifecycleOwner.lifecycle

    DisposableEffect(key1 = lifecycle) {
        val observer = object : DefaultLifecycleObserver {
            override fun onCreate(owner: LifecycleOwner) {
                super.onCreate(owner)
                onCreate()
            }

            override fun onStart(owner: LifecycleOwner) {
                super.onStart(owner)
                onStart()
            }

            override fun onResume(owner: LifecycleOwner) {
                super.onResume(owner)
                onResume()
            }

            override fun onPause(owner: LifecycleOwner) {
                super.onPause(owner)
                onPause()
            }

            override fun onStop(owner: LifecycleOwner) {
                super.onStop(owner)
                onStop()
            }

            override fun onDestroy(owner: LifecycleOwner) {
                super.onDestroy(owner)
                onDestroy()
            }
        }

        lifecycle.addObserver(observer)

        onDispose {
            lifecycle.removeObserver(observer)
        }
    }
}