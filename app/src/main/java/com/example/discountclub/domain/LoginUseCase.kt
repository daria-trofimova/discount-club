package com.example.discountclub.domain

import com.example.discountclub.domain.dto.LoginParameters
import com.example.discountclub.domain.repository.LoginRepositoryApi
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: LoginRepositoryApi,
) {
    suspend operator fun invoke(
        loginParameters: LoginParameters,
    ): Result<Unit> {
        return repository.login(loginParameters)
    }
}