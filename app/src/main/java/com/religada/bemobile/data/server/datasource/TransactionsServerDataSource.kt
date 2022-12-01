package com.religada.bemobile.data.server.datasource

import arrow.core.Either
import com.religada.bemobile.data.datasource.TransactionsRemoteDataSource
import com.religada.bemobile.data.datasource.tryCall
import com.religada.bemobile.data.server.ApiConnection
import com.religada.bemobile.data.server.mapper.toDomainModel
import com.religada.bemobile.domain.ErrorApp
import com.religada.bemobile.domain.Transaction
import javax.inject.Inject

class TransactionsServerDataSource @Inject constructor() :
    TransactionsRemoteDataSource {

    override suspend fun findTransactions(): Either<ErrorApp, List<Transaction>> = tryCall {
        ApiConnection.service
            .getTransactions()
            .toDomainModel()
    }
}
