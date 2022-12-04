package com.religada.bemobile.data.database.transaction

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionsDao {
    @Query("SELECT * FROM TransactionRoom")
    fun getAll(): Flow<List<TransactionRoom>>

    @Query("SELECT * FROM TransactionRoom WHERE sku = :sku")
    fun findBySku(sku: String): Flow<List<TransactionRoom>>

    @Query("SELECT COUNT(id) FROM TransactionRoom")
    suspend fun transactionCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransactions(transactionRooms: List<TransactionRoom>)
}

