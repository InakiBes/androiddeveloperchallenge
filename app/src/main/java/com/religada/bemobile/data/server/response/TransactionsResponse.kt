package com.religada.bemobile.data.server.response

class TransactionsResponse(
    val rates: List<TransactionResponse> = emptyList(),
)

class TransactionResponse(
    val sku: String = "",
    val amount: Double = 0.0,
    val currency: String = "",
)

