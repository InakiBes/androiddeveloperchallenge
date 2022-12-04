package com.religada.bemobile.domain.usecase

import com.religada.bemobile.data.datasource.RatesLocalDataSource
import com.religada.bemobile.data.repository.RatesRepository
import com.religada.bemobile.domain.EurCurrency
import com.religada.bemobile.domain.model.Rate
import com.religada.bemobile.domain.model.Transaction
import javax.inject.Inject

class GetRateToEurUseCase @Inject constructor(
    private val repository: RatesRepository
) {
    operator fun invoke(transaction: Transaction): Double? {
        if(transaction.currency == EurCurrency.EUR.toString())
            return 1.0

        val rate : Double? = repository.findByFromTo(transaction.currency, EurCurrency.EUR.toString())
        if(rate != null){
            return rate
        } else {
            val ratesFromCurrentToAny : List<Rate>? = repository.findByFromToAny(transaction.currency)
            ratesFromCurrentToAny?.forEach { rateFromCurrentToAny ->
                val rateFromAnyToEur : Double? = repository.findByFromTo(rateFromCurrentToAny.from, rateFromCurrentToAny.to)
                rateFromAnyToEur?.let {
                    val amountFromCurrentToAny = transaction.amount * rateFromCurrentToAny.rate
                    return amountFromCurrentToAny * rateFromAnyToEur
                } // TODO add more interations until be sure that there is not rate option
            }
            return null
        }
    }
}