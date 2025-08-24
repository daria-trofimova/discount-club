package com.example.discountclub.domain.model

data class Settings(
    val language: Language,
    val isBiometricAuthAllowed: Boolean,
)

enum class Language {
    RUSSIAN,
}