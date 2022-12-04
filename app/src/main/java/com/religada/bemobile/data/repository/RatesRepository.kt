package com.religada.bemobile.data.repository

import com.religada.bemobile.data.datasource.RatesLocalDataSource
import com.religada.bemobile.data.datasource.RatesRemoteDataSource
import com.religada.bemobile.domain.ErrorApp
import com.religada.bemobile.domain.model.Rate
import javax.inject.Inject

class RatesRepository @Inject constructor(
    private val localDataSource: RatesLocalDataSource,
    private val remoteDataSource: RatesRemoteDataSource
) {
    val rates = localDataSource.rates

    fun findByFromTo(from: String, to: String): Double? = localDataSource.findByFromTo(from, to)

    fun findByFromToAny(from: String): List<Rate>? = localDataSource.findByFromToAny(from)

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