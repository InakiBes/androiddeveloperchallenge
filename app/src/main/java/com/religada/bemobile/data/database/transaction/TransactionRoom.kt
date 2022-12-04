package com.religada.bemobile.data.database.transaction

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TransactionRoom(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val sku: String = "",
    val amount: Double = 0.0,
    val currency: String = "",
)