package com.religada.bemobile.data.datasource

import arrow.core.Either
import com.religada.bemobile.domain.ErrorApp
import com.religada.bemobile.domain.model.Rate

interface RatesRemoteDataSource {
    suspend fun findRates(): Either<ErrorApp, List<Rate>>
}