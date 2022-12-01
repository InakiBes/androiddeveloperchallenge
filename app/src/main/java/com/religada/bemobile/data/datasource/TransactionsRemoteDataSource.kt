package com.religada.bemobile.data.datasource

import arrow.core.Either
import com.religada.bemobile.domain.ErrorApp
import com.religada.bemobile.domain.Transaction

interface TransactionsRemoteDataSource {
    suspend fun findTransactions(): Either<ErrorApp, List<Transaction>>
}