package com.example.discountclub.data.remote.mapper

import com.example.discountclub.data.local.model.User
import com.example.discountclub.data.remote.model.UserResponse

fun UserResponse.toLocalUser(): User {
    return User(
        phoneNumber = phoneNumber,
        firstName = firstName,
        lastName = lastName,
        email = email,
        isEmailConfirmed = isEmailConfirmed,
    )
}
