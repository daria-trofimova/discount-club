package com.example.discountclub.profile

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProfileViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ProfileUiState.Unauthenticated)
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()
}

sealed class ProfileUiState {
    data object Unauthenticated : ProfileUiState()
    data class Authenticated(val name: String, val lastName: String) : ProfileUiState()
}