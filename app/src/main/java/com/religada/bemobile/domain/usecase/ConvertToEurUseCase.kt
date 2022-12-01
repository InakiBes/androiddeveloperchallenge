package com.religada.bemobile.domain.usecase

import com.religada.bemobile.data.datasource.RatesLocalDataSource
import com.religada.bemobile.domain.Currencies
import com.religada.bemobile.domain.model.Transaction
import javax.inject.Inject

class ConvertToEurUseCase @Inject constructor(
    private val repository: RatesLocalDataSource
) {
    operator fun invoke(transaction: Transaction): Double =
        repository.findByFromTo(transaction.currency, Currencies.EUR.toString())
}