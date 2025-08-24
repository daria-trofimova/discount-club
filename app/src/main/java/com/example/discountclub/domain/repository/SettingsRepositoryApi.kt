package com.example.discountclub.domain.repository

import com.example.discountclub.domain.model.Settings
import kotlinx.coroutines.flow.Flow

interface SettingsRepositoryApi {

    fun getSettings(): Flow<Settings>
}