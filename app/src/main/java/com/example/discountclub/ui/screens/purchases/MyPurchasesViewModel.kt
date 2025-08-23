package com.example.discountclub.ui.screens.purchases

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.discountclub.domain.GetPurchasesByDateUseCase
import com.example.discountclub.domain.model.DatePurchases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPurchasesViewModel @Inject constructor(
    private val getPurchasesByDateUseCase: GetPurchasesByDateUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<MyPurchasesUiState>(MyPurchasesUiState.Loading)
    val uiState: StateFlow<MyPurchasesUiState> = _uiState.asStateFlow()

    init {
        loadMyPurchases()
    }

    private fun loadMyPurchases() {
        viewModelScope.launch {
            val purchasesByDate = getPurchasesByDateUseCase()
            _uiState.value = MyPurchasesUiState.Success(purchasesByDate = purchasesByDate)
        }
    }
}

sealed class MyPurchasesUiState {
    data object Loading : MyPurchasesUiState()
    data class Success(val purchasesByDate: List<DatePurchases>) : MyPurchasesUiState()
}