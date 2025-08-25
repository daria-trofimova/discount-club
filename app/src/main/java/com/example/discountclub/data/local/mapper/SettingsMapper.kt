package com.example.discountclub.data.local.mapper

import com.example.discountclub.data.local.model.Settings
import com.example.discountclub.domain.model.Settings as DomainSettings

fun Settings.toDomainSettings(): com.example.discountclub.domain.model.Settings =
    DomainSettings(
        language = when (language) {
            Settings.Language.RUSSIAN -> DomainSettings.Language.RUSSIAN
        },
        isBiometricAuthAllowed = isBiometricAuthAllowed,
    )
