package com.scoresaver.app.wear.features.game.timer

import com.scoresaver.app.wear.features.game.timer.TimerHandler
import com.scoresaver.app.wear.features.game.timer.TimerHandlerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object TimerModule {
    @Provides
    fun provideTimerHandler(): TimerHandler = TimerHandlerImpl()
}