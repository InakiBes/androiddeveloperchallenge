package com.religada.bemobile.domain.usecase

import com.religada.bemobile.data.repository.TransactionRepository
import com.religada.bemobile.domain.ErrorApp
import javax.inject.Inject

class RequestTransactionsUseCase @Inject constructor(private val transactionRepository: TransactionRepository){
    suspend operator fun invoke(): ErrorApp? {
        return transactionRepository.requestTransactions()
    }
}