package com.example.discountclub.domain

import com.example.discountclub.data.User
import com.example.discountclub.data.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val userRepository: UserRepository) {
    operator fun invoke(): Flow<User?> {
        return userRepository.getUser()
    }
}