package com.religada.bemobile.data.server.datasource

import arrow.core.Either
import com.religada.bemobile.data.datasource.RatesRemoteDataSource
import com.religada.bemobile.data.datasource.tryCall
import com.religada.bemobile.data.server.ApiConnection
import com.religada.bemobile.data.server.mapper.toDomainModel
import com.religada.bemobile.domain.ErrorApp
import com.religada.bemobile.domain.Rate
import javax.inject.Inject

class RatesServerDataSource @Inject constructor() :
    RatesRemoteDataSource {

    override suspend fun findRates(): Either<ErrorApp, List<Rate>> = tryCall {
        ApiConnection.service
            .getRates()
            .toDomainModel()
    }
}
