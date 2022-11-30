package com.religada.bemobile.data.database.datasource

import com.religada.bemobile.data.datasource.RateLocalDataSource
import com.religada.bemobile.data.datasource.tryCall
import com.religada.bemobile.data.database.dao.RatesDao
import com.religada.bemobile.data.database.mapper.fromDomainModel
import com.religada.bemobile.data.database.mapper.toDomainModel
import com.religada.bemobile.domain.ErrorApp
import com.religada.bemobile.domain.Rate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RateRoomDataSource @Inject constructor(private val ratesDao: RatesDao) : RateLocalDataSource {

    override val rates: Flow<List<Rate>> = ratesDao.getAll().map { it.toDomainModel() }

    override suspend fun isEmpty(): Boolean = ratesDao.rateCount() == 0

    override fun findByFromTo(from: String, to: String): Flow<Rate> = ratesDao.findByFromTo(from, to).map { it.toDomainModel() }

    override suspend fun save(rates: List<Rate>): ErrorApp? = tryCall {
        ratesDao.insertRates(rates.fromDomainModel())
    }.fold(
        ifLeft = { it },
        ifRight = { null }
    )
}

