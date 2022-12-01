package com.religada.bemobile.domain.usecase

import com.religada.bemobile.data.repository.TransactionRepository
import com.religada.bemobile.domain.model.Transaction
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class TransactionsBySkuUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository,
) {
    operator fun invoke(sku: String): Flow<List<Transaction>> = transactionRepository.findBySku(sku)
}