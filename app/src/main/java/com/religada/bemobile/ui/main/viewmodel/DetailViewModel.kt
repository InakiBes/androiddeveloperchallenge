package com.religada.bemobile.ui.main.viewmodel

import androidx.lifecycle.*
import com.religada.bemobile.domain.ErrorApp
import com.religada.bemobile.domain.model.Transaction
import com.religada.bemobile.domain.usecase.ConvertToEurUseCase
import com.religada.bemobile.domain.usecase.TransactionsBySkuUseCase
import com.religada.bemobile.ui.common.launchAndCollect
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
    private val convertToEurUseCase: ConvertToEurUseCase
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

    fun calculateTotalTransactions(transactions: List<Transaction>?) {
        viewModelScope.launch(Dispatchers.IO) {
            var amountTotal = 0.0
            transactions?.forEach { transaction ->
                val rate: Double = convertToEurUseCase(transaction) //.collect { rate ->
                amountTotal += rate * transaction.amount
            }
            _state.update { UiState(totalTransactions = amountTotal.round(2)) }
            log("Total $amountTotal")
        }
    }

    fun onUiReady() {
        log("sku recibido en DetailViewModel $sku")
    }

    data class UiState(
        val transactions: List<Transaction>? = null,
        val totalTransactions: Double? = null,
        val error: ErrorApp? = null
    )
}