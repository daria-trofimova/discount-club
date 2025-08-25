package com.example.discountclub.data

import com.example.discountclub.data.local.SettingLocalDataSource
import com.example.discountclub.data.local.mapper.toDomainSettings
import com.example.discountclub.domain.model.Settings
import com.example.discountclub.domain.repository.SettingsRepositoryApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SettingsRepository @Inject constructor(
    private val localDataSource: SettingLocalDataSource,
) : SettingsRepositoryApi {

    override fun getSettings(): Flow<Settings> {
        return localDataSource.getSettings().map { it.toDomainSettings() }
    }
}