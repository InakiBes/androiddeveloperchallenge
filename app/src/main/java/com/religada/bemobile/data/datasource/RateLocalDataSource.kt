package com.religada.bemobile.data.datasource

import com.religada.bemobile.domain.ErrorApp
import com.religada.bemobile.domain.Rate
import kotlinx.coroutines.flow.Flow

interface RateLocalDataSource {
    val rates: Flow<List<Rate>>

    suspend fun isEmpty(): Boolean
    fun findByFromTo(from: String, to: String): Flow<Rate>
    suspend fun save(rateRooms: List<Rate>): ErrorApp?
}