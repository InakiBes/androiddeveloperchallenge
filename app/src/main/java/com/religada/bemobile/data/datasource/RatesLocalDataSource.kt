package com.religada.bemobile.data.datasource

import com.religada.bemobile.domain.ErrorApp
import com.religada.bemobile.domain.model.Rate
import kotlinx.coroutines.flow.Flow

interface RatesLocalDataSource {
    val rates: Flow<List<Rate>>

    suspend fun isEmpty(): Boolean
    fun findByFromTo(from: String, to: String): Double?
    fun findByFromToAny(to: String): List<Rate>?
    suspend fun save(rateRooms: List<Rate>): ErrorApp?
}