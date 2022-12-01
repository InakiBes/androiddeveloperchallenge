package com.religada.bemobile.domain.usecase

import com.religada.bemobile.data.repository.RatesRepository
import javax.inject.Inject

class GetRatesUseCase @Inject constructor(private val repository: RatesRepository) {
    operator fun invoke() = repository.rates
}