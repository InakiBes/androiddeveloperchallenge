package com.religada.bemobile.data.database.rates

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.religada.bemobile.domain.model.Rate
import kotlinx.coroutines.flow.Flow

@Dao
interface RatesDao {

    @Query("SELECT * FROM RateRoom")
    fun getAll(): Flow<List<RateRoom>>

    @Query("SELECT rate FROM RateRoom WHERE `from` = :from AND `to` = :to")
    fun findByFromTo(from: String, to: String): Double?

    @Query("SELECT * FROM RateRoom WHERE `from` = :from")
    fun findByFromToAny(from: String): List<Rate>?

    @Query("SELECT COUNT(id) FROM RateRoom")
    suspend fun rateCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRates(rateRooms: List<RateRoom>)
}