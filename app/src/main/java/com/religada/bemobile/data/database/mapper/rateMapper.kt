package com.religada.bemobile.data.database.mapper

import com.religada.bemobile.data.database.model.RateRoom
import com.religada.bemobile.domain.Rate

fun List<RateRoom>.toDomainModel(): List<Rate> = map { it.toDomainModel() }

fun RateRoom.toDomainModel(): Rate =
    Rate(
        id = 0,
        from,
        to,
        rate
    )

fun List<Rate>.fromDomainModel(): List<RateRoom> = map { it.fromDomainModel() }

fun Rate.fromDomainModel(): RateRoom = RateRoom(
    id,
    from,
    to,
    rate
)