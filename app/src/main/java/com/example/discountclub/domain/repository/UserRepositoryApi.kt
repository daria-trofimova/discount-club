package com.example.discountclub.domain.repository

import com.example.discountclub.domain.dto.RegistrationParameters
import com.example.discountclub.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepositoryApi {

    fun getUser(): Flow<User?>

    suspend fun register(parameters: RegistrationParameters): Result<Unit>
}