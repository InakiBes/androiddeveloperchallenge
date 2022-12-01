package com.religada.bemobile.data.database.mapper

import com.religada.bemobile.data.database.model.TransactionRoom
import com.religada.bemobile.domain.model.Transaction

fun List<TransactionRoom>.toDomainModel(): List<Transaction> = map { it.toDomainModel() }

fun TransactionRoom.toDomainModel(): Transaction =
    Transaction(
        id = 0,
        sku,
        amount,
        currency,
    )

fun List<Transaction>.fromDomainModel(): List<TransactionRoom> = map { it.fromDomainModel() }

fun Transaction.fromDomainModel(): TransactionRoom = TransactionRoom(
    id,
    sku,
    amount,
    currency,
)