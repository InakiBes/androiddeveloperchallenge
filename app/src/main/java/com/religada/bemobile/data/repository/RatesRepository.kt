package com.religada.bemobile.data.repository

import com.religada.bemobile.data.datasource.RateLocalDataSource
import com.religada.bemobile.data.datasource.RateRemoteDataSource
import com.religada.bemobile.domain.ErrorApp
import com.religada.bemobile.domain.Rate
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RatesRepository @Inject constructor(
    private val localDataSource: RateLocalDataSource,
    private val remoteDataSource: RateRemoteDataSource
) {
    val rates = localDataSource.rates

    fun findByFromTo(from: String, to: String): Flow<Rate> = localDataSource.findByFromTo(from, to)

    suspend fun requestRates(): ErrorApp? {
        if (localDataSource.isEmpty()) {
            val rates = remoteDataSource.findRates()
            rates.fold(ifLeft = { return it }) {
                localDataSource.save(it)
            }
        }
        return null
    }
}