package com.example.discountclub.data

import com.example.discountclub.data.local.UserLocalDataSource
import com.example.discountclub.data.local.model.User
import com.example.discountclub.domain.dto.LoginParameters
import com.example.discountclub.domain.repository.LoginRepositoryApi
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val userLocalDataSource: UserLocalDataSource,
) : LoginRepositoryApi {

    override suspend fun login(
        parameters: LoginParameters,
    ): Result<Unit> {
        val user = User(
            phoneNumber = "+15550000000",
            name = parameters.name,
            lastName = parameters.lastName,
            email = "example@mail.com",
        )
        userLocalDataSource.updateUser(user)
        return Result.success(Unit)
    }
}