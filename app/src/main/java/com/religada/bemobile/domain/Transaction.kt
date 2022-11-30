package com.religada.bemobile.domain

data class Transaction (
    val id: Int = -1,
    val sku: String = "",
    val amount: Double = 0.0,
    val currency: String = "",
)