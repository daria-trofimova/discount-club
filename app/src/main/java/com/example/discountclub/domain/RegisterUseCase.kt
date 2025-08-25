package com.example.discountclub.domain

import com.example.discountclub.domain.dto.RegistrationParameters
import com.example.discountclub.domain.repository.UserRepositoryApi
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val repository: UserRepositoryApi,
) {
    suspend operator fun invoke(
        parameters: RegistrationParameters,
    ): Result<Unit> {
        return repository.register(parameters)
    }
}