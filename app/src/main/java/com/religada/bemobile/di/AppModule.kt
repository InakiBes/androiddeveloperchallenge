package com.religada.bemobile.di

import android.app.Application
import androidx.room.Room
import com.religada.bemobile.data.datasource.RateLocalDataSource
import com.religada.bemobile.data.datasource.RateRemoteDataSource
import com.religada.bemobile.data.database.AppDatabase
import com.religada.bemobile.data.database.datasource.RateRoomDataSource
import com.religada.bemobile.data.server.datasource.RateServerDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application) = Room.databaseBuilder(
        app,
        AppDatabase::class.java,
        "beMobile-db"
    ).build()

    @Provides
    @Singleton
    fun provideRateDao(db: AppDatabase) = db.ratesDao()

    @Provides
    @Singleton
    fun provideTransactions(db: AppDatabase) = db.transactionsDao()
}

@Module
@InstallIn(SingletonComponent::class)
abstract class AppDataModule {

    @Binds
    abstract fun bindLocalDataSource(localDataSource: RateRoomDataSource): RateLocalDataSource

    @Binds
    abstract fun bindRemoteDataSource(remoteDataSource: RateServerDataSource): RateRemoteDataSource

}