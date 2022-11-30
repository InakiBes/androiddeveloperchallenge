package com.religada.bemobile.data.server.datasource

import arrow.core.Either
import com.religada.bemobile.data.datasource.RateRemoteDataSource
import com.religada.bemobile.data.datasource.tryCall
import com.religada.bemobile.data.database.model.RateRoom
import com.religada.bemobile.data.server.ApiConnection
import com.religada.bemobile.data.server.mapper.toDomainModel
import com.religada.bemobile.data.server.response.RateResponse
import com.religada.bemobile.domain.ErrorApp
import com.religada.bemobile.domain.Rate
import javax.inject.Inject

class RateServerDataSource @Inject constructor() :
    RateRemoteDataSource {

    override suspend fun findRates(): Either<ErrorApp, List<Rate>> = tryCall {
        ApiConnection.service
            .getRates()
            .rates
            .toDomainModel()
    }
}
