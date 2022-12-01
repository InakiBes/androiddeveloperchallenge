package com.religada.bemobile.data.repository

import com.religada.bemobile.data.datasource.TransactionsLocalDataSource
import com.religada.bemobile.data.datasource.TransactionsRemoteDataSource
import com.religada.bemobile.domain.ErrorApp
import com.religada.bemobile.domain.Transaction
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TransactionRepository @Inject constructor(
    private val localDataSource: TransactionsLocalDataSource,
    private val remoteDataSource: TransactionsRemoteDataSource
) {
    val transactions = localDataSource.transactions

    fun findBySku(sku: String): Flow<List<Transaction>> = localDataSource.findBySku(sku)

    suspend fun requestTransactions(): ErrorApp? {
        if (localDataSource.isEmpty()) {
            val transactions = remoteDataSource.findTransactions()
            transactions.fold(ifLeft = { return it }) {
                localDataSource.save(it)
            }
        }
        return null
    }
}