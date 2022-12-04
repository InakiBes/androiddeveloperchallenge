package com.religada.bemobile.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.religada.bemobile.data.database.rates.RatesDao
import com.religada.bemobile.data.database.transaction.TransactionsDao
import com.religada.bemobile.data.database.rates.RateRoom
import com.religada.bemobile.data.database.transaction.TransactionRoom

@Database(entities = [TransactionRoom::class, RateRoom::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun transactionsDao(): TransactionsDao
    abstract fun ratesDao(): RatesDao
}