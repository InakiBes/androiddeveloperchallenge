package com.religada.bemobile.data.server.mapper

import com.religada.bemobile.data.server.response.RateResponse
import com.religada.bemobile.domain.model.Rate

fun List<RateResponse>.toDomainModel(): List<Rate> = map { it.toDomainModel() }

fun RateResponse.toDomainModel(): Rate =
    Rate(
        id = 0,
        from = from,
        to = to,
        rate = rate
    )