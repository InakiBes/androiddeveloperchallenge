package com.religada.bemobile.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.religada.bemobile.data.database.dao.RatesDao
import com.religada.bemobile.data.database.dao.TransactionsDao
import com.religada.bemobile.data.database.model.RateRoom
import com.religada.bemobile.data.database.model.TransactionRoom

@Database(entities = [TransactionRoom::class, RateRoom::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun transactionsDao(): TransactionsDao
    abstract fun ratesDao(): RatesDao
}