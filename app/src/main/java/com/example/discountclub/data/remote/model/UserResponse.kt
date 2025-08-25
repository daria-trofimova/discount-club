package com.example.discountclub.data.remote.model

data class UserResponse(
    val phoneNumber: String,
    val firstName: String?,
    val lastName: String?,
    val email: String?,
    val isEmailConfirmed: Boolean,
)