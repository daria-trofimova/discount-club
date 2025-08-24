package com.example.discountclub.domain.model

data class User(
    val phoneNumber: String,
    val name: String?,
    val lastName: String?,
    val email: String?,
    val isEmailConfirmed: Boolean,
)