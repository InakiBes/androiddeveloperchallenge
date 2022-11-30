package com.religada.bemobile.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.religada.bemobile.data.datasource.toError
import com.religada.bemobile.domain.ErrorApp
import com.religada.bemobile.domain.Rate
import com.religada.bemobile.domain.Transaction
import com.religada.bemobile.domain.usecase.GetRatesUseCase
import com.religada.bemobile.domain.usecase.RequestRatesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    getRatesUseCase: GetRatesUseCase,
    private val requestRatesUseCase: RequestRatesUseCase,
    getTransactionsUseCase: GetTransactionsUseCase,
    private val requestTransactionsUseCase: RequestTransactionsUseCase
    ): ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            getRatesUseCase()
                .catch { cause -> _state.update { it.copy(errorApp = cause.toError()) } }
                .collect { rates -> _state.update { UiState(rates = rates) } }
            getTransactionsUseCase()
                .catch { cause -> _state.update { it.copy(errorApp = cause.toError()) } }
                .collect { rates -> _state.update { UiState(transactions = rates) } }
        }
    }

    fun onUiReady() {
        viewModelScope.launch {
            _state.value = _state.value.copy(downloading = true)
            val error = requestRatesUseCase()
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