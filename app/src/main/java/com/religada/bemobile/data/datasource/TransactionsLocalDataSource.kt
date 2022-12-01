package com.religada.bemobile.data.datasource

import com.religada.bemobile.domain.ErrorApp
import com.religada.bemobile.domain.Transaction
import kotlinx.coroutines.flow.Flow

interface TransactionsLocalDataSource {
    val transactions: Flow<List<Transaction>>

    suspend fun isEmpty(): Boolean
    fun findBySku(sku: String): Flow<List<Transaction>>
    suspend fun save(rateRooms: List<Transaction>): ErrorApp?
}