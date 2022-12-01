package com.religada.bemobile.domain.usecase

import com.religada.bemobile.data.repository.TransactionRepository
import javax.inject.Inject

class GetTransactionsUseCase @Inject constructor(private val repository: TransactionRepository) {

    operator fun invoke() = repository.transactions
}