package com.scoresaver.app.wear.di

import com.scoresaver.app.util.db.dao.GameSettingsDao
import com.scoresaver.newgame_interactor.interactors.NewGameInteractor
import com.scoresaver.newgame_interactor.interactors.NewGameInteractorImpl
import com.scoresaver.app.wear.features.new_game.repository.NewGameRepository
import com.scoresaver.newgame_repository.repository.NewNewGameRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NewGameModule {

    @Provides
    fun provideGameRepository(gameSettingsDao: GameSettingsDao): NewGameRepository =
        NewNewGameRepositoryImpl(gameSettingsDao)

    @Provides
    fun provideTimerInteractor(newGameRepository: NewGameRepository): NewGameInteractor =
        NewGameInteractorImpl(newGameRepository)
}