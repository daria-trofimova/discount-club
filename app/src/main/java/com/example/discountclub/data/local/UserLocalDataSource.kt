package com.example.discountclub.data.local

import com.example.discountclub.data.local.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserLocalDataSource @Inject constructor() {
    private val user =
        MutableStateFlow<User?>(
            User(
                phoneNumber = "+15550000000",
                name = null,
                lastName = null,
                email = "example@mail.com",
            )
        )

    fun getUser(): Flow<User?> {
        return user.asStateFlow()
    }

    suspend fun updateUser(user: User) {
        this.user.emit(user)
    }
}