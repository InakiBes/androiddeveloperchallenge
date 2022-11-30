package com.religada.bemobile.domain.usecase

import com.religada.bemobile.data.repository.RatesRepository
import com.religada.bemobile.domain.ErrorApp
import javax.inject.Inject

class RequestRatesUseCase @Inject constructor(private val ratesRepository: RatesRepository) {
    suspend operator fun invoke(): ErrorApp? {
        return ratesRepository.requestRates()
    }
}