package com.example.discountclub.ui.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.discountclub.domain.GetSettingsUseCase
import com.example.discountclub.domain.GetUserUseCase
import com.example.discountclub.domain.model.Settings
import com.example.discountclub.domain.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val getSettingsUseCase: GetSettingsUseCase,
) : ViewModel() {
    private val _uiState: MutableStateFlow<ProfileUiState> =
        MutableStateFlow(ProfileUiState.Loading)
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    init {
        observeProfileData()
    }

    private fun observeProfileData() {
        viewModelScope.launch {
            combine(getUserUseCase(), getSettingsUseCase()) { user, settings ->
                if (user != null) {
                    ProfileUiState.Authenticated(
                        user = user,
                        settings = settings,
                    )
                } else {
                    ProfileUiState.UnAuthenticated
                }
            }.collect { newState ->
                _uiState.value = newState
            }
        }
    }
}

sealed class ProfileUiState {
    data object Loading : ProfileUiState()
    data object UnAuthenticated : ProfileUiState()
    data class Authenticated(
        val user: User,
        val settings: Settings,
    ) : ProfileUiState()
}