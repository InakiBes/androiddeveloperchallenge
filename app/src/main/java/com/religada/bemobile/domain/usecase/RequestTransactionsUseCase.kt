package com.religada.bemobile.domain.usecase

import com.religada.bemobile.data.repository.RatesRepository
import com.religada.bemobile.domain.ErrorApp
import com.religada.bemobile.domain.Transaction
import javax.inject.Inject

//class RequestTransactionsUseCase @Inject constructor(private val ratesRepository: RatesRepository) {
//    suspend operator fun invoke(): ErrorApp? {
//        //return ratesRepository.requestRates()
//        return  emit(listOf<Transaction>(Transaction(1, "T1000", 10.0, "EUR")))
//    }
//}