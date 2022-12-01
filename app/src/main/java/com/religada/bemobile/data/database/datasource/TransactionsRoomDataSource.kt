package com.religada.bemobile.data.database.datasource

import com.religada.bemobile.data.database.dao.TransactionsDao
import com.religada.bemobile.data.database.mapper.fromDomainModel
import com.religada.bemobile.data.database.mapper.toDomainModel
import com.religada.bemobile.data.datasource.TransactionsLocalDataSource
import com.religada.bemobile.data.datasource.tryCall
import com.religada.bemobile.domain.ErrorApp
import com.religada.bemobile.domain.model.Transaction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TransactionsRoomDataSource @Inject constructor(private val transactionsDao: TransactionsDao) : TransactionsLocalDataSource {

    override val transactions: Flow<List<Transaction>> = transactionsDao.getAll().map { it.toDomainModel() }

    override suspend fun isEmpty(): Boolean = transactionsDao.transactionCount() == 0

    override fun findBySku(sku: String): Flow<List<Transaction>> = transactionsDao.findBySku(sku).map { it.toDomainModel() }

    override suspend fun save(transactions: List<Transaction>): ErrorApp? = tryCall {
        transactionsDao.insertTransactions(transactions.fromDomainModel())
    }.fold(
        ifLeft = { it },
        ifRight = { null }
    )
}

