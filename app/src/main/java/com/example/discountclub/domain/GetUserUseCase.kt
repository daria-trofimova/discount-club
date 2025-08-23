package com.example.discountclub.domain

import com.example.discountclub.domain.model.User
import com.example.discountclub.domain.repository.UserRepositoryApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val userRepository: UserRepositoryApi) {
    operator fun invoke(): Flow<User?> {
        return userRepository.getUser()
    }
}