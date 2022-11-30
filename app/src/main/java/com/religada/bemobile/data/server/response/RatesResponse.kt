package com.religada.bemobile.data.server.response

class RatesResponse(
    val rates: List<RateResponse> = emptyList(),
)

class RateResponse(
    val from: String = "",
    val to: String = "",
    val rate: Double = 0.0,
)
