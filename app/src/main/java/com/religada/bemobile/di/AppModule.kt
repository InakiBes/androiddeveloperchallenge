package com.religada.bemobile.di

import android.app.Application
import androidx.room.Room
import com.religada.bemobile.data.datasource.RatesLocalDataSource
import com.religada.bemobile.data.datasource.RatesRemoteDataSource
import com.religada.bemobile.data.database.AppDatabase
import com.religada.bemobile.data.database.datasource.RatesRoomDataSource
import com.religada.bemobile.data.database.datasource.TransactionsRoomDataSource
import com.religada.bemobile.data.datasource.TransactionsLocalDataSource
import com.religada.bemobile.data.datasource.TransactionsRemoteDataSource
import com.religada.bemobile.data.server.datasource.RatesServerDataSource
import com.religada.bemobile.data.server.datasource.TransactionsServerDataSource
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
    abstract fun bindRatesLocalDataSource(localDataSource: RatesRoomDataSource): RatesLocalDataSource

    @Binds
    abstract fun bindRatesRemoteDataSource(remoteDataSource: RatesServerDataSource): RatesRemoteDataSource

    @Binds
    abstract fun bindTransactionsLocalDataSource(localDataSource: TransactionsRoomDataSource): TransactionsLocalDataSource

    @Binds
    abstract fun bindTransactionsRemoteDataSource(remoteDataSource: TransactionsServerDataSource): TransactionsRemoteDataSource

}