package com.religada.bemobile.ui.main.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.religada.bemobile.ui.main.fragment.DetailFragmentArgs
import com.religada.bemobile.utils.log
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val sku = DetailFragmentArgs.fromSavedStateHandle(savedStateHandle).sku

//    private val _state = MutableStateFlow(UiState())
//    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        log("Sku recibido: $sku")
        viewModelScope.launch {
//            resumeTransactionsBySkuUseCase(sku)
//                .catch { cause -> _state.update { it.copy(error = cause.toError()) } }
//                .collect { transactionsResume -> _state.update { UiState(transactionsResume = transactionsResume) } }
        }
    }

    //data class UiState(val transactionsResume: TransactionsResume? = null, val error: Error? = null)
//}
}