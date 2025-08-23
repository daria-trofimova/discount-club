package com.example.discountclub.data.local

import com.example.discountclub.data.local.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class UserLocalDataSource {
    private val user = MutableStateFlow<User?>(null)

    fun getUser(): Flow<User?> {
        return user.asStateFlow()
    }
}