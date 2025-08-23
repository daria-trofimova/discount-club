package com.example.discountclub.di

import com.example.discountclub.data.UserRepository
import com.example.discountclub.domain.repository.UserRepositoryApi
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoriesModule {

    @Binds
    fun bindUserRepositoryApi(impl: UserRepository): UserRepositoryApi
}