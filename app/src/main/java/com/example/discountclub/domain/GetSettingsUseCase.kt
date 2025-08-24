package com.example.discountclub.domain

import com.example.discountclub.domain.model.Settings
import com.example.discountclub.domain.repository.SettingsRepositoryApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSettingsUseCase @Inject constructor(private val repository: SettingsRepositoryApi) {

    operator fun invoke(): Flow<Settings> {
        return repository.getSettings()
    }
}