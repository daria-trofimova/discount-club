package com.example.discountclub.data.local.model

data class Settings(
    val language: Language,
    val isBiometricAuthAllowed: Boolean,
) {
    enum class Language {
        RUSSIAN,
    }
}