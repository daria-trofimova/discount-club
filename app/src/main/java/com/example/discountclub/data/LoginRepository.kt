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
        userLocalDataSource.setUser(User(name = parameters.name, lastName = parameters.lastName))
        return Result.success(Unit)
    }
}