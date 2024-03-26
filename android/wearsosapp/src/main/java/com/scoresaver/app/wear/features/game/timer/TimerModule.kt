package com.scoresaver.app.wear.features.game.timer

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object TimerModule {
    @Provides
    fun provideTimerHandler(
        @ApplicationContext
        context: Context
    ): TimerHandler = TimerHandlerImpl(context)
}