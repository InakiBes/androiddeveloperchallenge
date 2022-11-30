package com.religada.bemobile.domain

data class Rate(
    val id: Int = -1,
    val from: String = "",
    val to: String = "",
    val rate: Double = 0.0,
)