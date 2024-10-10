package com.scoresaver.app.wear.di

import android.content.Context
import android.hardware.SensorManager
import com.scoresaver.app.util.db.dao.GameSettingsDao
import com.scoresaver.app.wear.features.game.repository.GameRepository
import com.scoresaver.app.wear.features.game.repository.GameRepositoryImpl
import com.scoresaver.app.wear.features.game.use_cases.GameInteractor
import com.scoresaver.app.wear.features.game.use_cases.GameInteractorImpl
import com.scoresaver.app.wear.features.game.health.Health
import com.scoresaver.app.wear.features.game.health.HealthImpl
import com.scoresaver.app.wear.features.game.timer.TimerService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object GameModule {

    @Provides
    fun getSensorManager(
        @ApplicationContext context: Context
    ): SensorManager {
        return context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    @Provides
    fun provideHealthImpl(
        sensorManager: SensorManager
    ): Health = HealthImpl(sensorManager)


    @Provides
    fun provideGameRepository(gameSettingsDao: GameSettingsDao): GameRepository =
        GameRepositoryImpl(gameSettingsDao)

    @Provides
    fun provideTimerInteractor(
        health: Health,
        gameRepository: GameRepository
    ): GameInteractor =
        GameInteractorImpl(health, gameRepository)

    @Provides
    fun provideTimerService(): TimerService {
        return TimerService()
    }
}