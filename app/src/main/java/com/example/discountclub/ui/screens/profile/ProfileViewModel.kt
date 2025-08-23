package com.example.discountclub.ui.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.discountclub.domain.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
) : ViewModel() {
    private val _uiState: MutableStateFlow<ProfileUiState> =
        MutableStateFlow(ProfileUiState.Loading)
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    init {
        loadUser()
    }

    private fun loadUser() {
        viewModelScope.launch {
            getUserUseCase().collect { user ->
                user?.let {
                    _uiState.value =
                        ProfileUiState.Authenticated(name = it.name, lastName = it.lastName)
                } ?: run {
                    _uiState.value = ProfileUiState.Unauthenticated
                }
            }
        }
    }
}

sealed class ProfileUiState {
    data object Loading : ProfileUiState()
    data object Unauthenticated : ProfileUiState()
    data class Authenticated(val name: String, val lastName: String) : ProfileUiState()
}