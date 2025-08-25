package com.example.discountclub.data.remote

import com.example.discountclub.data.remote.model.UserResponse
import com.example.discountclub.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) {

    suspend fun register(
        participantNumber: String,
        code: String,
        name: String,
        lastName: String,
    ): UserResponse {
        return withContext(dispatcher) {
            delay(2000)
            UserResponse(
                phoneNumber = "+15550000000",
                name = name,
                lastName = lastName,
                email = "example@mail.com",
                isEmailConfirmed = false,
            )
        }
    }
}