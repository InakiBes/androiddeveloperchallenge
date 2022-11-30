package com.religada.bemobile.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RateRoom(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val from: String = "",
    val to: String = "",
    val rate: Double = 0.0,
)

