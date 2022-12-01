package com.religada.bemobile.data.server.mapper

import com.religada.bemobile.data.server.response.TransactionResponse
import com.religada.bemobile.domain.model.Transaction

fun List<TransactionResponse>.toDomainModel(): List<Transaction> = map { it.toDomainModel() }

fun TransactionResponse.toDomainModel(): Transaction =
    Transaction(
        id = 0,
        sku,
        amount,
        currency,
    )