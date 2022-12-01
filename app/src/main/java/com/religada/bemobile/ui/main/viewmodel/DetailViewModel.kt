package com.religada.bemobile.ui.main.viewmodel

import androidx.lifecycle.*
import com.religada.bemobile.domain.ErrorApp
import com.religada.bemobile.domain.model.Transaction
import com.religada.bemobile.domain.usecase.GetRateToEurUseCase
import com.religada.bemobile.domain.usecase.TransactionsBySkuUseCase
import com.religada.bemobile.ui.main.fragment.DetailFragmentArgs
import com.religada.bemobile.utils.log
import com.religada.bemobile.utils.round
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    transactionsBySkuUseCase: TransactionsBySkuUseCase,
    private val getRateToEurUseCase: GetRateToEurUseCase
) : ViewModel() {

    private val sku = DetailFragmentArgs.fromSavedStateHandle(savedStateHandle).sku
    fun getSku() = sku

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.Main) {
            transactionsBySkuUseCase(sku).collect { trans ->
                _state.update { UiState(transactions = trans) }
                calculateTotalTransactions(trans)
            }
        }
    }

    private fun calculateTotalTransactions(transactions: List<Transaction>?) {
        viewModelScope.launch(Dispatchers.IO) {
            var amountTotal = 0.0
            transactions?.forEach { transaction ->
                val rate: Double? = getRateToEurUseCase(transaction)
                rate?.let {
                    amountTotal += it * transaction.amount
                } ?: _state.update { UiState(error = ErrorApp.Unknown("")) }
            }
            _state.update { UiState(totalTransactions = amountTotal.round(2)) }
        }
    }

    fun onUiReady() {
    }

    data class UiState(
        val transactions: List<Transaction>? = null,
        val totalTransactions: Double? = null,
        val error: ErrorApp? = null
    )
}