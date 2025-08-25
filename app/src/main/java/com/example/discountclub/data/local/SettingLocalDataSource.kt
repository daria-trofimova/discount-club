package com.example.discountclub.data.local

import com.example.discountclub.data.local.model.Settings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class SettingLocalDataSource @Inject constructor() {
    private val settings =
        MutableStateFlow(
            Settings(
                language = Settings.Language.RUSSIAN,
                isBiometricAuthAllowed = true,
            )
        )

    fun getSettings(): Flow<Settings> {
        return settings.asStateFlow()
    }
}