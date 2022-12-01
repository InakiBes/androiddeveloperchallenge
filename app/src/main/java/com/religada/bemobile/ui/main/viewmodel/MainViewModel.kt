package com.religada.bemobile.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.religada.bemobile.data.datasource.toError
import com.religada.bemobile.domain.ErrorApp
import com.religada.bemobile.domain.model.Rate
import com.religada.bemobile.domain.model.Transaction
import com.religada.bemobile.domain.usecase.GetRatesUseCase
import com.religada.bemobile.domain.usecase.GetTransactionsUseCase
import com.religada.bemobile.domain.usecase.RequestRatesUseCase
import com.religada.bemobile.domain.usecase.RequestTransactionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getRatesUseCase: GetRatesUseCase,
    private val requestRatesUseCase: RequestRatesUseCase,
    private val getTransactionsUseCase: GetTransactionsUseCase,
    private val requestTransactionsUseCase: RequestTransactionsUseCase
    ): ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        launchGetRatesUseCase()
        launchGeTransactionsUseCase()
    }

    fun onUiReady() {
        launchRequestRateUseCase()
        launchRequestTransactionsUseCase()
    }

    private fun launchGetRatesUseCase() {
        viewModelScope.launch {
            getRatesUseCase()
                .catch { cause -> _state.update { it.copy(errorApp = cause.toError()) } }
                .collect { rates -> _state.update { UiState(rates = rates) } }
        }
    }

    private fun launchGeTransactionsUseCase() {
        viewModelScope.launch {
            getTransactionsUseCase()
                .catch { cause -> _state.update { it.copy(errorApp = cause.toError()) } }
                .collect { transactions -> _state.update { UiState(transactions = transactions) } }
        }
    }

    private fun launchRequestRateUseCase() {
        viewModelScope.launch {
            _state.value = _state.value.copy(downloading = true)
            val error = requestRatesUseCase()
            _state.update { _state.value.copy(downloading = false, errorApp = error) }
        }
    }

    private fun launchRequestTransactionsUseCase() {
        viewModelScope.launch {
            _state.value = _state.value.copy(downloading = true)
            val error = requestTransactionsUseCase()
            _state.update { _state.value.copy(downloading = false, errorApp = error) }
        }
    }

    data class UiState(
        val downloading: Boolean = false,
        val rates: List<Rate>? = null,
        val transactions: List<Transaction>? = null,
        val errorApp: ErrorApp? = null
    )
}