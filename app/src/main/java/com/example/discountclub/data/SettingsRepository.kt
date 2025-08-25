package com.example.discountclub.data

import com.example.discountclub.domain.model.Language
import com.example.discountclub.domain.model.Settings
import com.example.discountclub.domain.repository.SettingsRepositoryApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class SettingsRepository @Inject constructor() : SettingsRepositoryApi {
    private val settings =
        MutableStateFlow(
            Settings(
                language = Language.RUSSIAN,
                isBiometricAuthAllowed = true,
            )
        )

    override fun getSettings(): Flow<Settings> {
        return settings.asStateFlow()
    }
}