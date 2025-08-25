package com.example.discountclub.data.local.model

data class User(
    val phoneNumber: String,
    val firstName: String?,
    val lastName: String?,
    val email: String?,
    val isEmailConfirmed: Boolean,
)