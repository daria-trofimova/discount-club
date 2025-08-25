package com.example.discountclub.data.local

import com.example.discountclub.data.local.model.User
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserLocalDataSource @Inject constructor() {
    private val user =
        MutableStateFlow<User?>(
            User(
                phoneNumber = "+15550000000",
                firstName = null,
                lastName = null,
                email = "example@mail.com",
                isEmailConfirmed = false,
            )
        )

    fun getUser(): Flow<User?> {
        return user.asStateFlow()
            .onEach { delay(2000) }
    }

    suspend fun updateUser(user: User) {
        this.user.emit(user)
    }
}