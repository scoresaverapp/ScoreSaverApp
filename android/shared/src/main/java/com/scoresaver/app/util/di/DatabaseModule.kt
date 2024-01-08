package com.scoresaver.app.wear.di

import android.content.Context
import androidx.room.Room
import com.scoresaver.app.wear.db.CoreDatabase
import com.scoresaver.app.wear.db.DATABASE_NAME
import com.scoresaver.app.wear.db.dao.GameSettingsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideCoreDatabase(
        @ApplicationContext
        context: Context,
    ): CoreDatabase {
        return Room.databaseBuilder(
            context, CoreDatabase::class.java,
            DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideSettingDao(
        coreDatabase: CoreDatabase
    ): GameSettingsDao {
        return coreDatabase.gameSettingsDao
    }
}