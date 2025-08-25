package com.example.discountclub.data

import com.example.discountclub.data.local.UserLocalDataSource
import com.example.discountclub.data.local.mapper.toDomainUser
import com.example.discountclub.data.remote.UserRemoteDataSource
import com.example.discountclub.data.remote.mapper.toLocalUser
import com.example.discountclub.domain.dto.RegistrationParameters
import com.example.discountclub.domain.model.User
import com.example.discountclub.domain.repository.UserRepositoryApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val localDataSource: UserLocalDataSource,
    private val remoteDataSource: UserRemoteDataSource,
) : UserRepositoryApi {

    override fun getUser(): Flow<User?> {
        return localDataSource.getUser().map { user -> user?.toDomainUser() }
    }

    override suspend fun register(
        parameters: RegistrationParameters,
    ): Result<Unit> {
        val user = remoteDataSource.register(
            participantNumber = parameters.participantNumber,
            code = parameters.code,
            firstName = parameters.firstName,
            lastName = parameters.lastName,
        ).toLocalUser()
        localDataSource.updateUser(user)
        return Result.success(Unit)
    }
}