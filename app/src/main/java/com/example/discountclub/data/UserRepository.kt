package com.example.discountclub.data

import com.example.discountclub.data.local.UserLocalDataSource
import com.example.discountclub.data.local.mapper.toDomainUser
import com.example.discountclub.domain.model.User
import com.example.discountclub.domain.repository.UserRepositoryApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val localDataSource: UserLocalDataSource,
) : UserRepositoryApi {

    override fun getUser(): Flow<User?> {
        return localDataSource.getUser().map { user -> user?.toDomainUser() }
    }
}