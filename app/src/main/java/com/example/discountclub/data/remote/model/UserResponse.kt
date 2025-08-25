package com.example.discountclub.data.remote.model

data class UserResponse(
    val phoneNumber: String,
    val name: String?,
    val lastName: String?,
    val email: String?,
    val isEmailConfirmed: Boolean,
)