package com.religada.bemobile.data.datasource

import arrow.core.Either
import com.religada.bemobile.domain.ErrorApp
import com.religada.bemobile.domain.Rate

interface RateRemoteDataSource {
    suspend fun findRates(): Either<ErrorApp, List<Rate>>
}