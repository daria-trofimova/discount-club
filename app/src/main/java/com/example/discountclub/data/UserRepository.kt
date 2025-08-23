package com.example.discountclub.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class UserRepository {
    private val user = MutableStateFlow<User?>(null)

    fun getUser(): Flow<User?> {
        return user.asStateFlow()
    }
}